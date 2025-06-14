package drillUpgrades;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBT;

public class FuelTankUpgrade {

	public ItemStack FuelTankUpgrade() {
		
		ItemStack fuelTankUpgradeItemStack = new ItemStack(Material.IRON_NUGGET);
		
		ItemMeta fuelTankUpgradeItemStackM = fuelTankUpgradeItemStack.getItemMeta();
		
		fuelTankUpgradeItemStackM.setDisplayName("§aFuel tank upgrade (+2500 fuel)");
		fuelTankUpgradeItemStackM.setLore(Arrays.asList("§7Put this item into hex alongside your drill to upgrade its fuel tank", "§8(Max 4)"));
		
		fuelTankUpgradeItemStackM.setEnchantmentGlintOverride(true);
		fuelTankUpgradeItemStackM.setMaxStackSize(1);
		
		fuelTankUpgradeItemStack.setItemMeta(fuelTankUpgradeItemStackM);
		
		NBT.modify(fuelTankUpgradeItemStack, nbt -> {
			nbt.setBoolean("drillUpgrade", true); 
			nbt.setString("type", "fuel");
		});
		
		return fuelTankUpgradeItemStack;
		
	}

}
