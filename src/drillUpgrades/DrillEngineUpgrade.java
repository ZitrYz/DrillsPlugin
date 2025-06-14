package drillUpgrades;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBT;

public class DrillEngineUpgrade {

	public ItemStack DrillEngineUpgrade() {
		
		ItemStack drillEngineUpgradeStack = new ItemStack(Material.GOLD_NUGGET);
		
		ItemMeta drillEngineUpgradeStackM = drillEngineUpgradeStack.getItemMeta();
		
		drillEngineUpgradeStackM.setDisplayName("§aFuel engine upgrade (-5% fuel consumption)");
		drillEngineUpgradeStackM.setLore(Arrays.asList("§7Put this item into hex alongside your drill to decrease fuel usage by 5%", "§8(Max 4)"));
		
		drillEngineUpgradeStackM.setEnchantmentGlintOverride(true);
		drillEngineUpgradeStackM.setMaxStackSize(1);
		
		drillEngineUpgradeStack.setItemMeta(drillEngineUpgradeStackM);
		
		NBT.modify(drillEngineUpgradeStack, nbt -> {
			nbt.setBoolean("drillUpgrade", true); 
			nbt.setString("type", "fuelEff");
		});
		
		return drillEngineUpgradeStack;
		
	}

}
