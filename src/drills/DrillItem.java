package drills;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBT;

public abstract class DrillItem implements Listener {
	
	protected int drillFuelTank;
	protected int drillEngine;
	protected int drillCoil;
	public int maxFuel;
	protected double consChance = 1;
	
	public double fuel;
	protected double fuelConsumption = 1;
	
	protected ItemStack drillItem;

	protected List<Material> blocks;
	protected List<Material> blocksBlackList;
	
	public ItemStack createDrillItem(String name, int maxFuel, drillType drillTypeD) {
		ItemStack drillItem = new ItemStack(Material.PRISMARINE_SHARD, 1);
		
		ItemMeta drillItemMeta = drillItem.getItemMeta();
		drillItemMeta.setDisplayName(name);
		List<String> description = Arrays.asList(
				"§7Modifiers: ", 
				"   §7- Fuel tank: §cNONE", 
				"   §7- Engine: §cNONE", 
				"   §7- Coil: §cNONE",
				"", 
				"§7Fuel: §c" + (int) this.fuel + "/" + maxFuel
		);
		if (drillTypeD == drillType.ORE_DRILL) {
			description = Arrays.asList(
					"§eFortune IV",
					"§7Modifiers: ", 
					"   §7- Fuel tank: §cNONE", 
					"   §7- Engine: §cNONE", 
					"   §7- Coil: §cNONE",
					"", 
					"§7Fuel: §c" + (int) this.fuel + "/" + maxFuel
			);
		}
		drillItemMeta.setLore(description);
		drillItemMeta.setMaxStackSize(1);
		drillItem.setItemMeta(drillItemMeta);
		
		NBT.modify(drillItem, nbt -> {
			nbt.setInteger("fuelTank", 0);
			nbt.setInteger("engine", 0);
			nbt.setInteger("coil", 0);
			nbt.setDouble("fuel", 0.0);
			nbt.setBoolean("drill", true);
			if (drillTypeD == drillType.NETHER_DRILL) {
				nbt.setString("type", "nether");
			}
			if (drillTypeD == drillType.GROUND_DRILL) {
				nbt.setString("type", "ground");
			}
			if (drillTypeD == drillType.ORE_DRILL) {
				nbt.setString("type", "ore");
			}
		});
		
		return drillItem;
	}
	
	public ItemStack getDrillItem() {
		return this.drillItem;
	}
	
	public int getFuelTank() {
		return this.drillFuelTank;
	}
	
	public int getEngine() {
		return this.drillEngine;
	}
	
	public int getCoil() {
		return this.drillCoil;
	}
	
	public void setFuelTank(int tier) {
		this.drillFuelTank = tier;
		this.maxFuel = 10000 + (2500 * tier);
		ItemMeta drillItemMeta = this.drillItem.getItemMeta();
		
		List<String> description = this.drillItem.getItemMeta().getLore();
		
		if (tier == 0) {
			description.set(description.size() - 5, "   §7- Fuel tank: §cNONE");
		} else if (tier == 1) {
			description.set(description.size() - 5, "   §7- Fuel tank: §a§lBASIC FUEL TANK §7(+2500)");
		} else if (tier == 2) {
			description.set(description.size() - 5, "   §7- Fuel tank: §e§lADVANCED FUEL TANK §7(+5000)");
		} else if (tier == 3) {
			description.set(description.size() - 5, "   §7- Fuel tank: §d§lMODERN FUEL TANK §7(+7500)");
		} else if (tier == 4) {
			description.set(description.size() - 5, "   §7- Fuel tank: §c§lSUPERIOR FUEL TANK §7(+10000)");
		}
		description.set(description.size() - 1, "§7Fuel: §c" + (int) this.fuel + "/" + (int) maxFuel);
		drillItemMeta.setLore(description);
		this.drillItem.setItemMeta(drillItemMeta);
		
		NBT.modify(this.drillItem, nbt -> { nbt.setInteger("fuelTank", tier); } );
	}
	
	public void setEngine(int tier) {
		this.drillEngine = tier;
		
		ItemMeta drillItemMeta = this.drillItem.getItemMeta();
		
		List<String> description = this.drillItem.getItemMeta().getLore();
		
		if (tier == 0) {
			description.set(description.size() - 4, "   §7- Engine: §cNONE");
		} else if (tier == 1) {
			description.set(description.size() - 4, "   §7- Engine: §a§lBASIC ENGINE §7(-5% fuel consumption)");
		} else if (tier == 2) {
			description.set(description.size() - 4, "   §7- Engine: §e§lADVANCED ENGINE §7(-10% fuel consumption)");
		} else if (tier == 3) {
			description.set(description.size() - 4, "   §7- Engine: §d§lMODERN ENGINE §7(-15% fuel consumption)");
		} else if (tier == 4) {
			description.set(description.size() - 4, "   §7- Engine: §c§lSUPERIOR ENGINE §7(-20% fuel consumption)");
		}
		drillItemMeta.setLore(description);
		this.drillItem.setItemMeta(drillItemMeta);
		
		NBT.modify(this.drillItem, nbt -> { nbt.setInteger("engine", tier); } );
	}
	
	public void setCoil(int tier) {
		this.drillCoil = tier;
		
		ItemMeta drillItemMeta = this.drillItem.getItemMeta();
		
		List<String> description = this.drillItem.getItemMeta().getLore();
		
		if (tier == 0) {
			description.set(description.size() - 3, "   §7- Coil: §cNONE");
		} else if (tier == 1) {
			description.set(description.size() - 3, "   §7- Coil: §a§lBASIC COIL §7(+5% chance not to consume fuel)");
		} else if (tier == 2) {
			description.set(description.size() - 3, "   §7- Coil: §e§lADVANCED COIL §7(+10% chance not to consume fuel)");
		} else if (tier == 3) {
			description.set(description.size() - 3, "   §7- Coil: §d§lMODERN COIL §7(+15% chance not to consume fuel)");
		} else if (tier == 4) {
			description.set(description.size() - 3, "   §7- Coil: §c§lSUPERIOR COIL §7(+20% chance not to consume fuel)");
		}
		drillItemMeta.setLore(description);
		
		this.drillItem.setItemMeta(drillItemMeta);
		
		NBT.modify(this.drillItem, nbt -> { nbt.setInteger("coil", tier); } );
	}
	public void setFuel(double newFuel, double maxFuel) {
		this.fuel = newFuel;
		ItemMeta drillItemMeta = this.drillItem.getItemMeta();
		
		List<String> description = this.drillItem.getItemMeta().getLore();
		description.set(description.size() - 1, "§7Fuel: §c" + (int) this.fuel + "/" + (int) maxFuel);
		drillItemMeta.setLore(description);
		this.drillItem.setItemMeta(drillItemMeta);
		NBT.modify(this.drillItem, nbt -> {
			nbt.setDouble("fuel", newFuel);
		});
	}
	


}
