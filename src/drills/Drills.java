package drills;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import commands.giveDrill;

public class Drills extends JavaPlugin implements Listener {

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
		//Bukkit.getPluginManager().registerEvents(new Drills(), this);
		getCommand("giveDrill").setExecutor(new giveDrill());
	}

	public void onDisable() {
		Bukkit.broadcastMessage("[DrillsPlugin] DrillsPlugin is disabled");
	}
	
}
