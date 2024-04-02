package io.github.crative.extended_armor.items.armor_materials;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class CopperArmorMaterial implements ArmorMaterial{
	private static final int[] BASE_DURABILITY = new int[] {160, 220, 200, 180};
	private static final int[] PROTECTION_VALUES = new int[] {2, 5, 6, 2};

	@Override
	public int getDurability(ArmorItem.ArmorSlot slot) {
		return BASE_DURABILITY[slot.getEquipmentSlot().getEntitySlotId()];
	}

	@Override
	public int getProtection(ArmorItem.ArmorSlot slot) {
		return PROTECTION_VALUES[slot.getEquipmentSlot().getEntitySlotId()];
	}

	@Override
	public int getEnchantability() {
		return 10;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(Items.COPPER_INGOT);
	}

	@Override
	public String getName() {
		return "copper";
	}

	@Override
	public float getToughness() {
		return 2;
	}

	@Override
	public float getKnockbackResistance() {
		return 0;
	}
}
