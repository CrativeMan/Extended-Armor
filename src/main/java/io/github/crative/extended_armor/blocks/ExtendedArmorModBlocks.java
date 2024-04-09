package io.github.crative.extended_armor.blocks;

import io.github.crative.extended_armor.ExtendedArmor;
import io.github.crative.extended_armor.blocks.custom.ArmoryBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ExtendedArmorModBlocks {

	public static final Block ARMORY = registerBlock("armory",
		new ArmoryBlock(QuiltBlockSettings.create().nonOpaque()));

	private static Block registerBlock(String name, Block block) {
		registerBlockItem(name, block);
		return Registry.register(Registries.BLOCK, new Identifier(ExtendedArmor.MOD_ID, name), block);
	}

	private static Item registerBlockItem(String name, Block block) {
		return Registry.register(Registries.ITEM, new Identifier(ExtendedArmor.MOD_ID, name), new BlockItem(block, new QuiltItemSettings()));
	}

	public static void registerModBlocks(){
		ExtendedArmor.LOGGER.info("Registering blocks for " + ExtendedArmor.MOD_ID);
	}
}
