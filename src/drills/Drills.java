package drills;

import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import blockPlaceTracker.BlockTracker;
import commands.giveDrill;

public class Drills extends JavaPlugin implements Listener {
	
	public static BlockTracker blockTracker = new BlockTracker();
	
	public void onEnable() {
		Bukkit.broadcastMessage("[DrillsPlugin] DrillsPlugin is enabled.");
		
		getConfig().addDefault("NetherDrillFuel", 10000);
		getConfig().addDefault("GroundDrillFuel", 10000);
		getConfig().addDefault("OreDrillFuel", 10000);
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		reloadConfig();
		
		Bukkit.getPluginManager().registerEvents(new breakHandler(), this);
		Bukkit.getPluginManager().registerEvents(this, this);
		getCommand("giveDrill").setExecutor(new giveDrill());
		
		String sql = "CREATE TABLE IF NOT EXISTS placed_blocks (" +
	             "world TEXT, x INT, y INT, z INT, PRIMARY KEY(world, x, y, z))";

		try (Statement stmt = blockTracker.connection.createStatement()) {
		    stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void onDisable() {
		Bukkit.broadcastMessage("[DrillsPlugin] DrillsPlugin is disabled");
	}
	
}
