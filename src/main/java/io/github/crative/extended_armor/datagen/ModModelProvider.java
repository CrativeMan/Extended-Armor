package io.github.crative.extended_armor.datagen;

import io.github.crative.extended_armor.items.ExtendedArmorItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.model.BlockStateModelGenerator;
import net.minecraft.data.client.model.Models;

public class ModModelProvider extends FabricModelProvider {
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		itemModelGenerator.register(ExtendedArmorItems.COPPER_HELMET, Models.SINGLE_LAYER_ITEM);
		itemModelGenerator.register(ExtendedArmorItems.COPPER_CHESTPLATE, Models.SINGLE_LAYER_ITEM);
		itemModelGenerator.register(ExtendedArmorItems.COPPER_LEGGINGS, Models.SINGLE_LAYER_ITEM);
		itemModelGenerator.register(ExtendedArmorItems.COPPER_BOOTS, Models.SINGLE_LAYER_ITEM);
	}
}
