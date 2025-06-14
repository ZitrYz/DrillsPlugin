package drills;

import java.util.Random;

import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBT;
import drillsTypes.GroundDrill;
import drillsTypes.NetherDrill;
import drillsTypes.OreDrill;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class breakHandler implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			
			Player p = e.getPlayer();
			
			ItemStack itemInHand = p.getInventory().getItemInMainHand();
			
			if (!itemInHand.hasItemMeta()) {
				return;
			}
			
			if (NBT.get(itemInHand, nbt -> (boolean) nbt.getBoolean("drill"))) {
				if (NBT.get(itemInHand, nbt -> (String) nbt.getString("type")).equals("nether")) {
					NetherDrill netherDrillItem = new NetherDrill(itemInHand);
					if (netherDrillItem.fuel >= netherDrillItem.fuelConsumption) {
						if (netherDrillItem.blocks.contains(e.getClickedBlock().getType())) {
							
							Block clickedBlock = e.getClickedBlock();
							
							for (ItemStack drop : clickedBlock.getDrops()) {	
								p.getWorld().dropItem(clickedBlock.getLocation(), drop);
							}
							p.breakBlock(clickedBlock);
							if (doConsumeFuel((int) (netherDrillItem.consChance * 100))) {
								netherDrillItem.setFuel(netherDrillItem.fuel - netherDrillItem.fuelConsumption, netherDrillItem.maxFuel);
							}
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§aCurrent fuel: §b" + (int) netherDrillItem.fuel + "/" + netherDrillItem.maxFuel));
							p.getInventory().setItemInMainHand(netherDrillItem.getDrillItem());
						}
					}
				}
				if (NBT.get(itemInHand, nbt -> (String) nbt.getString("type")).equals("ground")) {
					GroundDrill groundDrillItem = new GroundDrill(itemInHand);
					if (groundDrillItem.fuel >= groundDrillItem.fuelConsumption) {
						if (groundDrillItem.blocks.contains(e.getClickedBlock().getType())) {
							
							Block clickedBlock = e.getClickedBlock();
							
							for (ItemStack drop : clickedBlock.getDrops()) {	
								p.getWorld().dropItem(clickedBlock.getLocation(), drop);
							}
							p.breakBlock(clickedBlock);
							if (doConsumeFuel((int) (groundDrillItem.consChance * 100))) {
								groundDrillItem.setFuel(groundDrillItem.fuel - groundDrillItem.fuelConsumption, groundDrillItem.maxFuel);
							}
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§aCurrent fuel: §b" + (int) groundDrillItem.fuel + "/" + groundDrillItem.maxFuel));
							p.getInventory().setItemInMainHand(groundDrillItem.getDrillItem());
						}
					}
				}
				if (NBT.get(itemInHand, nbt -> (String) nbt.getString("type")).equals("ore")) {
					OreDrill oreDrillItem = new OreDrill(itemInHand);
					if (oreDrillItem.fuel >= oreDrillItem.fuelConsumption) {
						if (oreDrillItem.blocks.contains(e.getClickedBlock().getType())) {
							
							Block clickedBlock = e.getClickedBlock();
							
							for (ItemStack drop : clickedBlock.getDrops()) {	
								p.getWorld().dropItem(clickedBlock.getLocation(), drop);
								p.getWorld().dropItem(clickedBlock.getLocation(), drop);
							}
							p.getWorld().spawn(clickedBlock.getLocation(), ExperienceOrb.class).setExperience(1);;
							p.breakBlock(clickedBlock);
							

							if (doConsumeFuel((int) (oreDrillItem.consChance * 100))) {
								oreDrillItem.setFuel(oreDrillItem.fuel - oreDrillItem.fuelConsumption, oreDrillItem.maxFuel);
							}
							p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§aCurrent fuel: §b" + (int) oreDrillItem.fuel + "/" + oreDrillItem.maxFuel));
							p.getInventory().setItemInMainHand(oreDrillItem.getDrillItem());
							
						}
					}
				}
			}
			
		}
	}
	
	private boolean doConsumeFuel(int chance) {
		Random r = new Random();
		int low = 10;
		int high = 100;
		int result = r.nextInt(high-low) + low;
		if (chance <= result) { 
			return false;
		} else {
			return true;
		}
	}

}
