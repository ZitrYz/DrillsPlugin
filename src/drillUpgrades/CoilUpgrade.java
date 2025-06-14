package drillUpgrades;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBT;

public class CoilUpgrade {

	public ItemStack CoilUpgrade() {
		
		ItemStack coilUpgrade = new ItemStack(Material.ARMADILLO_SCUTE);
		
		ItemMeta coilUpgradeM = coilUpgrade.getItemMeta();
		
		coilUpgradeM.setDisplayName("§aFuel engine upgrade (+5% chance not to consume fuel)");
		coilUpgradeM.setLore(Arrays.asList("§7Put this item into hex alongside your drill to increase chance not to consume fuel by 5%", "§8(Max 4)"));
		
		coilUpgradeM.setEnchantmentGlintOverride(true);
		coilUpgradeM.setMaxStackSize(1);
		
		coilUpgrade.setItemMeta(coilUpgradeM);
		
		NBT.modify(coilUpgrade, nbt -> {
			nbt.setBoolean("drillUpgrade", true); 
			nbt.setString("type", "coil");
		});
		
		return coilUpgrade;
		
	}

}
