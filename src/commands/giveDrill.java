package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import drillUpgrades.CoilUpgrade;
import drillUpgrades.DrillEngineUpgrade;
import drillUpgrades.FuelItem;
import drillUpgrades.FuelTankUpgrade;
import drillsTypes.GroundDrill;
import drillsTypes.NetherDrill;
import drillsTypes.OreDrill;

public class giveDrill implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lab, String[] args) {
		if (sender instanceof Player) {
			if (!(sender.isOp())) {
				sender.sendMessage("§cYou dont have permission to use that command!");
				return false;
			}
			if (args.length > 0) {
				Player p = (Player) sender;
				
				if (args[0].equals("nether")) {
					NetherDrill netherDrill = new NetherDrill(null);
					p.getInventory().addItem(netherDrill.getDrillItem());
				}
				if (args[0].equals("ground")) {
					GroundDrill groundDrill = new GroundDrill(null);
					p.getInventory().addItem(groundDrill.getDrillItem());
				}
				if (args[0].equals("ore")) {
					OreDrill oreDrill = new OreDrill(null);
					p.getInventory().addItem(oreDrill.getDrillItem());
				}
				if (args[0].equals("fuelTank")) {
					p.getInventory().addItem(new FuelTankUpgrade().FuelTankUpgrade());
				}
				if (args[0].equals("engine")) {
					p.getInventory().addItem(new DrillEngineUpgrade().DrillEngineUpgrade());
				}
				if (args[0].equals("coil")) {
					p.getInventory().addItem(new CoilUpgrade().CoilUpgrade());
				}
				if (args[0].equals("fuel")) {
					if (args.length > 1) {
						p.getInventory().addItem(new FuelItem().FuelItem(Integer.parseInt(args[1])));
					} else {
						p.getInventory().addItem(new FuelItem().FuelItem(1000));
					}
				}
				return true;
			}
			
		}
		return false;
	}

}
