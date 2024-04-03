package io.github.crative.extended_armor.items;

import io.github.crative.extended_armor.ExtendedArmor;
import io.github.crative.extended_armor.items.armor_materials.ExtendedArmorMaterials;
import io.github.crative.extended_armor.items.custom.ExtendedArmorItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ExtendedArmorItems {
	//* Ingots
	public static final Item OBSIDIAN_INGOT = registerItem("obsidian_ingot",
		new Item(new QuiltItemSettings()));

	//* Copper Armor
	public static final ArmorItem COPPER_HELMET = registerArmorItem("copper_helmet",
		new ExtendedArmorItem(ExtendedArmorMaterials.COPPER, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));
	public static final ArmorItem COPPER_CHESTPLATE = registerArmorItem("copper_chestplate",
		new ArmorItem(ExtendedArmorMaterials.COPPER, ArmorItem.ArmorSlot.CHESTPLATE, new QuiltItemSettings()));
	public static final ArmorItem COPPER_LEGGINGS = registerArmorItem("copper_leggings",
		new ArmorItem(ExtendedArmorMaterials.COPPER, ArmorItem.ArmorSlot.LEGGINGS, new QuiltItemSettings()));
	public static final ArmorItem COPPER_BOOTS = registerArmorItem("copper_boots",
		new ArmorItem(ExtendedArmorMaterials.COPPER, ArmorItem.ArmorSlot.BOOTS, new QuiltItemSettings()));

	//* Obsidian Armor
	public static final ArmorItem OBSIDIAN_HELMET = registerArmorItem("obsidian_helmet",
		new ExtendedArmorItem(ExtendedArmorMaterials.OBSIDIAN, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));
	public static final ArmorItem OBSIDIAN_CHESTPLATE = registerArmorItem("obsidian_chestplate",
		new ArmorItem(ExtendedArmorMaterials.OBSIDIAN, ArmorItem.ArmorSlot.CHESTPLATE, new QuiltItemSettings()));
	public static final ArmorItem OBSIDIAN_LEGGINGS = registerArmorItem("obsidian_leggings",
		new ArmorItem(ExtendedArmorMaterials.OBSIDIAN, ArmorItem.ArmorSlot.LEGGINGS, new QuiltItemSettings()));
	public static final ArmorItem OBSIDIAN_BOOTS = registerArmorItem("obsidian_boots",
		new ArmorItem(ExtendedArmorMaterials.OBSIDIAN, ArmorItem.ArmorSlot.BOOTS, new QuiltItemSettings()));


	//* Registry
	private static Item registerItem(String name, Item item){
		return Registry.register(Registries.ITEM, new Identifier(ExtendedArmor.MOD_ID, name), item);
	}
	private static ArmorItem registerArmorItem(String name, ArmorItem armorItem){
		return Registry.register(Registries.ITEM, new Identifier(ExtendedArmor.MOD_ID, name), armorItem);
	}

	public static void registerModItems(){
		ExtendedArmor.LOGGER.info("Registering mod items for " + ExtendedArmor.MOD_ID);
	}
}
