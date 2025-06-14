package drillsTypes;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBT;
import drills.BlockList;
import drills.DrillItem;
import drills.drillType;

public class OreDrill extends DrillItem {
	
	public OreDrill(ItemStack drillItemStack) {
		this.maxFuel = Bukkit.getPluginManager().getPlugin("Drills").getConfig().getInt("NetherDrillFuel");
		if (drillItemStack != null) {
			this.drillItem = drillItemStack;
			
			NBT.get(this.drillItem, nbt -> {
				//Amount of fuel
				this.drillFuelTank = nbt.getInteger("fuelTank");
				//Fuel effieciency
				this.drillEngine = nbt.getInteger("engine");
				//Chance not to use fuel or smth
				this.drillCoil = nbt.getInteger("coil");
				this.fuel = nbt.getDouble("fuel");
			});
			
		} else {
			this.drillItem = createDrillItem("§bOre Drill", this.maxFuel, drillType.ORE_DRILL);
			
			this.drillFuelTank = 0;
			this.drillEngine = 0;
			this.drillCoil = 0;
			this.fuel = 0;
		}
		this.fuelConsumption *= (1 - (0.05 * this.drillEngine));
		this.maxFuel += (2500 * this.drillFuelTank);
		this.consChance -= (0.05 * this.drillCoil);
		
		this.blocks = BlockList.blocks.get(drillType.ORE_DRILL);
		this.blocksBlackList = BlockList.blocksBlackList.get(drillType.ORE_DRILL);
	}

	@Override
	public ItemStack createDrillItem(String name, int maxFuel, drillType drillTypeD) { 
		return super.createDrillItem(name, maxFuel, drillTypeD);
	}
}
