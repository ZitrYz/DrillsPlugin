package drills;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBT;
import dev.aurelium.auraskills.api.AuraSkillsApi;
import dev.aurelium.auraskills.api.trait.Traits;
import dev.aurelium.auraskills.api.user.SkillsUser;
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
							boolean alreadyCasted = false;
							Block clickedBlock = e.getClickedBlock();
							Collection<ItemStack> drops = clickedBlock.getDrops();
							Location loc = e.getClickedBlock().getLocation();
							String worldName = clickedBlock.getWorld().getName();
							Material clickedBlockMaterial = clickedBlock.getType();
							clickedBlock.setType(Material.AIR);
							for (ItemStack drop : drops) {	
								if (!netherDrillItem.blocksBlackList.contains(clickedBlockMaterial)) {
									p.getWorld().dropItem(loc, drop);
									if (!isPlacedByPlayer(worldName, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()) && !alreadyCasted) {
										AuraSkillsApi auraSkills = AuraSkillsApi.get();
										SkillsUser user = auraSkills.getUser(e.getPlayer().getUniqueId());
										double mL = user.getEffectiveTraitLevel(Traits.MINING_LUCK);
										double random = Math.random() * 100;
										
										if (mL > random) {
											p.getWorld().dropItem(clickedBlock.getLocation(), drop);
											alreadyCasted = true;
										}
									}
								}
							}
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
							boolean alreadyCasted = false;
							Block clickedBlock = e.getClickedBlock();
							Collection<ItemStack> drops = clickedBlock.getDrops();
							Location loc = e.getClickedBlock().getLocation();
							String worldName = clickedBlock.getWorld().getName();
							Material clickedBlockMaterial = clickedBlock.getType();
							clickedBlock.setType(Material.AIR);
							for (ItemStack drop : drops) {	
								p.getWorld().dropItem(loc, drop);
								if (!groundDrillItem.blocksBlackList.contains(clickedBlockMaterial)) {
									if (!isPlacedByPlayer(worldName, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()) && !alreadyCasted) {
										AuraSkillsApi auraSkills = AuraSkillsApi.get();
										SkillsUser user = auraSkills.getUser(e.getPlayer().getUniqueId());
										double mL = user.getEffectiveTraitLevel(Traits.MINING_LUCK);
										double random = Math.random() * 100;
										
										if (mL > random) {
											p.getWorld().dropItem(clickedBlock.getLocation(), drop);
											alreadyCasted = true;
										}
									}
								}
							}
							clickedBlock.setType(Material.AIR);
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
							boolean alreadyCasted = false;
							Block clickedBlock = e.getClickedBlock();
							Collection<ItemStack> drops = clickedBlock.getDrops();
							Location loc = e.getClickedBlock().getLocation();
							String worldName = clickedBlock.getWorld().getName();
							Material clickedBlockMaterial = clickedBlock.getType();
							clickedBlock.setType(Material.AIR);
							for (ItemStack drop : drops) {	
								p.getWorld().dropItem(loc, drop);
								if (!oreDrillItem.blocksBlackList.contains(clickedBlockMaterial)) {
									p.getWorld().dropItem(loc, drop);
									if (!isPlacedByPlayer(worldName, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()) && !alreadyCasted) {
										AuraSkillsApi auraSkills = AuraSkillsApi.get();
										SkillsUser user = auraSkills.getUser(e.getPlayer().getUniqueId());
										double mL = user.getEffectiveTraitLevel(Traits.MINING_LUCK);
										double random = Math.random() * 100;
										
										if (mL > random) {
											p.getWorld().dropItem(clickedBlock.getLocation(), drop);
											alreadyCasted = true;
										}
									}
								}
							}
						
							
							p.getWorld().spawn(clickedBlock.getLocation(), ExperienceOrb.class).setExperience(1);;
							clickedBlock.setType(Material.AIR);
							
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
	
	@EventHandler
	public void onBlockPlacement(BlockPlaceEvent e) {
		String sql = "INSERT OR IGNORE INTO placed_blocks(world, x, y, z) VALUES(?, ?, ?, ?)";
		Location loc = e.getBlock().getLocation();
		try (PreparedStatement ps = Drills.blockTracker.connection.prepareStatement(sql)) {
		    ps.setString(1, e.getBlock().getWorld().getName());
		    ps.setInt(2, loc.getBlockX());
		    ps.setInt(3, loc.getBlockY());
		    ps.setInt(4, loc.getBlockZ());
		    ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@EventHandler
	public boolean isPlacedByPlayer(String world, int x, int y, int z) {
		String sql = "SELECT * FROM placed_blocks WHERE world=? AND x=? AND y=? AND z=?";
		try (PreparedStatement ps = Drills.blockTracker.connection.prepareStatement(sql)) {
		    ps.setString(1, world);
		    ps.setInt(2, x);
		    ps.setInt(3, y);
		    ps.setInt(4, z);

		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		    	Bukkit.broadcastMessage("f");
		        return true;
		    } else {
		    	return false;
		    }
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
