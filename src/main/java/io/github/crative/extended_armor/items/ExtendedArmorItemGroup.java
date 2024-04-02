package io.github.crative.extended_armor.items;

import io.github.crative.extended_armor.ExtendedArmor;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ExtendedArmorItemGroup {
	public static final ItemGroup EXTENDED_ARMOR_GROUP = FabricItemGroup.builder()
		.icon(() -> new ItemStack(ExtendedArmorItems.COPPER_CHESTPLATE))
		.name(Text.translatable("itemgroup.extended_armor.extended_armor_group"))
		.entries((context, entries) ->{
			//* Copper Armor
			entries.addItem(ExtendedArmorItems.COPPER_HELMET);
			entries.addItem(ExtendedArmorItems.COPPER_CHESTPLATE);
			entries.addItem(ExtendedArmorItems.COPPER_LEGGINGS);
			entries.addItem(ExtendedArmorItems.COPPER_BOOTS);
		})
		.build();

	public static void registerItemGroup() {
		Registry.register(Registries.ITEM_GROUP, new Identifier(ExtendedArmor.MOD_ID, "extended_armor_group"), EXTENDED_ARMOR_GROUP);
	}
}
