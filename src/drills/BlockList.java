package drills;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;

public class BlockList {

	@SuppressWarnings("serial")
	public static Map<drillType, List<Material>> blocks = new HashMap<drillType, List<Material>>() {{
		put(drillType.NETHER_DRILL, Arrays.asList(
				Material.NETHER_GOLD_ORE,
				Material.RED_SANDSTONE,
				Material.SMOOTH_RED_SANDSTONE,
				Material.RED_SAND,
				Material.LIME_TERRACOTTA,
				Material.RED_TERRACOTTA,
				Material.YELLOW_TERRACOTTA,
				Material.GREEN_TERRACOTTA,
				Material.NETHERRACK,
				Material.CRIMSON_NYLIUM,
				Material.WARPED_NYLIUM,
				Material.SOUL_SAND,
				Material.SOUL_SOIL,
				Material.BONE_BLOCK,
				Material.BLACKSTONE,
				Material.MAGMA_BLOCK,
				Material.BASALT,
				Material.SMOOTH_BASALT,
				Material.NETHER_GOLD_ORE,
				Material.NETHER_QUARTZ_ORE,
				Material.ANCIENT_DEBRIS,
				Material.GLOWSTONE,
				Material.CRIMSON_STEM,
				Material.WARPED_STEM,
				Material.NETHER_WART_BLOCK,
				Material.WARPED_WART_BLOCK,
				Material.STRIPPED_CRIMSON_STEM,
				Material.STRIPPED_WARPED_STEM,
				Material.NETHER_BRICKS,
				Material.CRACKED_NETHER_BRICKS,
				Material.NETHER_BRICK_STAIRS,
				Material.NETHER_BRICK_SLAB,
				Material.NETHER_BRICK_WALL,
				Material.NETHER_BRICK_FENCE,
				Material.CHISELED_NETHER_BRICKS,
				Material.RED_NETHER_BRICKS,
				Material.RED_NETHER_BRICK_STAIRS,
				Material.RED_NETHER_BRICK_SLAB,
				Material.RED_NETHER_BRICK_WALL,
				Material.POLISHED_BASALT,
				Material.GILDED_BLACKSTONE,
				Material.BLACKSTONE_STAIRS,
				Material.BLACKSTONE_SLAB,
				Material.BLACKSTONE_WALL,
				Material.CHISELED_POLISHED_BLACKSTONE,
				Material.POLISHED_BLACKSTONE,
				Material.POLISHED_BLACKSTONE_STAIRS,
				Material.POLISHED_BLACKSTONE_SLAB,
				Material.POLISHED_BLACKSTONE_WALL,
				Material.POLISHED_BLACKSTONE_PRESSURE_PLATE,
				Material.POLISHED_BLACKSTONE_BUTTON,
				Material.POLISHED_BLACKSTONE_BRICKS,
				Material.CRACKED_POLISHED_BLACKSTONE_BRICKS,
				Material.POLISHED_BLACKSTONE_BRICK_STAIRS,
				Material.POLISHED_BLACKSTONE_BRICK_SLAB,
				Material.POLISHED_BLACKSTONE_BRICK_WALL,
				Material.IRON_BARS,
				Material.CHAIN,
				Material.WEEPING_VINES,
				Material.SHROOMLIGHT,
				Material.TWISTING_VINES,
				Material.GOLD_BLOCK,
				Material.LODESTONE
		));
		put(drillType.GROUND_DRILL, Arrays.asList(
				Material.STONE,
				Material.DEEPSLATE,
				Material.GRANITE,
				Material.DIORITE,
				Material.ANDESITE,
				Material.CALCITE,
				Material.TUFF,
				Material.DRIPSTONE_BLOCK,
				Material.POINTED_DRIPSTONE,
				Material.OBSIDIAN,
				Material.CRYING_OBSIDIAN,
				Material.BLACKSTONE,
				Material.BASALT,
				Material.SMOOTH_BASALT,
				Material.COBBLESTONE
		));
		put(drillType.ORE_DRILL, Arrays.asList(
				Material.COAL_ORE,
				Material.DEEPSLATE_COAL_ORE,
				Material.IRON_ORE,
				Material.DEEPSLATE_IRON_ORE,
				Material.COPPER_ORE,
				Material.DEEPSLATE_COPPER_ORE,
				Material.GOLD_ORE,
				Material.DEEPSLATE_GOLD_ORE,
				Material.REDSTONE_ORE,
				Material.DEEPSLATE_REDSTONE_ORE,
				Material.EMERALD_ORE,
				Material.DEEPSLATE_EMERALD_ORE,
				Material.LAPIS_ORE,
				Material.DEEPSLATE_LAPIS_ORE,
				Material.DIAMOND_ORE,
				Material.DEEPSLATE_DIAMOND_ORE,
				Material.NETHER_GOLD_ORE,
				Material.NETHER_QUARTZ_ORE,
				Material.ANCIENT_DEBRIS,
				Material.RAW_IRON_BLOCK,
				Material.RAW_COPPER_BLOCK,
				Material.RAW_GOLD_BLOCK,
				Material.AMETHYST_BLOCK,
				Material.BUDDING_AMETHYST,
				Material.SMALL_AMETHYST_BUD,
				Material.MEDIUM_AMETHYST_BUD,
				Material.LARGE_AMETHYST_BUD,
				Material.AMETHYST_CLUSTER
		));
	}};
	

}
