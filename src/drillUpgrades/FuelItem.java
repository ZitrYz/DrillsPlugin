package drillUpgrades;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBT;

public class FuelItem {

	public ItemStack FuelItem(double amount) {
		
		ItemStack fuelItem = new ItemStack(Material.GREEN_DYE);
		
		ItemMeta fuelItemM = fuelItem.getItemMeta();
		
		fuelItemM.setDisplayName("§aBioFuel §b(+"+ (int) amount +")");
		fuelItemM.setLore(Arrays.asList("§7Put this item into hex alongside your drill to refuel it"));
		
		fuelItemM.setEnchantmentGlintOverride(true);
		fuelItemM.setMaxStackSize(1);
		
		fuelItem.setItemMeta(fuelItemM);
		
		NBT.modify(fuelItem, nbt -> {
			nbt.setBoolean("drillUpgrade", true); 
			nbt.setString("type", "fuelAdd");
			nbt.setDouble("amount", amount);
		});
		
		return fuelItem;
		
	}

}
