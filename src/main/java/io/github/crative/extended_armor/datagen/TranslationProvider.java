package io.github.crative.extended_armor.datagen;

import io.github.crative.extended_armor.items.ExtendedArmorItemGroup;
import io.github.crative.extended_armor.items.ExtendedArmorItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class TranslationProvider extends FabricLanguageProvider {
	public TranslationProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generateTranslations(TranslationBuilder translationBuilder) {
		translationBuilder.add(ExtendedArmorItems.OBSIDIAN_INGOT, "Obsidian Ingot");
		translationBuilder.add(ExtendedArmorItems.STEALTH_WRAPPINGS, "Silent Wrappings");

		translationBuilder.add(ExtendedArmorItems.COPPER_HELMET, "Copper Helmet");
		translationBuilder.add(ExtendedArmorItems.COPPER_CHESTPLATE, "Copper Chestplate");
		translationBuilder.add(ExtendedArmorItems.COPPER_LEGGINGS, "Copper Leggings");
		translationBuilder.add(ExtendedArmorItems.COPPER_BOOTS, "Copper Boots");

		translationBuilder.add(ExtendedArmorItems.STEALTH_HELMET, "Stealth Mask");
		translationBuilder.add(ExtendedArmorItems.STEALTH_CHESTPLATE, "Stealth Shirt");
		translationBuilder.add(ExtendedArmorItems.STEALTH_LEGGINGS, "Stealth Leg Cover");
		translationBuilder.add(ExtendedArmorItems.STEALTH_BOOTS, "Stealth Sandals");

		translationBuilder.add(ExtendedArmorItems.OBSIDIAN_HELMET, "Obsidian Helmet");
		translationBuilder.add(ExtendedArmorItems.OBSIDIAN_CHESTPLATE, "Obsidian Chestplate");
		translationBuilder.add(ExtendedArmorItems.OBSIDIAN_LEGGINGS, "Obsidian Leggings");
		translationBuilder.add(ExtendedArmorItems.OBSIDIAN_BOOTS, "Obsidian Boots");

		translationBuilder.add("itemgroup.extended_armor.extended_armor_group", "Extended Armor");
	}
}
