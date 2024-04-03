package io.github.crative.extended_armor.items;

import io.github.crative.extended_armor.ExtendedArmor;
import io.github.crative.extended_armor.items.armor_materials.ExtendedArmorMaterials;
import io.github.crative.extended_armor.items.custom.ExtendedArmorItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ExtendedArmorItems {
	//* Copper Armor
	public static final ArmorItem COPPER_HELMET = registerArmorItem("copper_helmet",
		new ExtendedArmorItem(ExtendedArmorMaterials.COPPER, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));
	public static final ArmorItem COPPER_CHESTPLATE = registerArmorItem("copper_chestplate",
		new ArmorItem(ExtendedArmorMaterials.COPPER, ArmorItem.ArmorSlot.CHESTPLATE, new QuiltItemSettings()));
	public static final ArmorItem COPPER_LEGGINGS = registerArmorItem("copper_leggings",
		new ArmorItem(ExtendedArmorMaterials.COPPER, ArmorItem.ArmorSlot.LEGGINGS, new QuiltItemSettings()));
	public static final ArmorItem COPPER_BOOTS = registerArmorItem("copper_boots",
		new ArmorItem(ExtendedArmorMaterials.COPPER, ArmorItem.ArmorSlot.BOOTS, new QuiltItemSettings()));


	//* Registry
	private static ArmorItem registerArmorItem(String name, ArmorItem armorItem){
		return Registry.register(Registries.ITEM, new Identifier(ExtendedArmor.MOD_ID, name), armorItem);
	}

	public static void registerModItems(){
		ExtendedArmor.LOGGER.info("Registering mod items for " + ExtendedArmor.MOD_ID);
	}
}
