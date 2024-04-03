package io.github.crative.extended_armor.items.armor_materials;

import io.github.crative.extended_armor.ExtendedArmor;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.function.Supplier;

public enum ExtendedArmorMaterials implements ArmorMaterial {
	COPPER("copper", 25, new int[] { 3, 8, 6, 3 }, 10,
		SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2f, 0f,()->Ingredient.ofItems(Items.COPPER_INGOT)),
	OBSIDIAN("obsidian", 100, new int[] { 5, 10, 6, 4 }, 20,
		SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3f, 10f, () -> Ingredient.ofItems(Items.OBSIDIAN))
	;

	private final String name;
	private final int durabilityMultiplier;
	private final int[] protectionAmounts;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Supplier<Ingredient> repairIngredient;
	private static final int[] BASE_DURABILITY = { 11, 16, 15, 13 };

    ExtendedArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound,
						   float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
	public int getDurability(ArmorItem.ArmorSlot slot) {
		return BASE_DURABILITY[slot.ordinal()] * this.durabilityMultiplier;
	}

	@Override
	public int getProtection(ArmorItem.ArmorSlot slot) {
		return protectionAmounts[slot.ordinal()];
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.equipSound;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

	@Override
	public String getName() {
		return ExtendedArmor.MOD_ID + ":" + this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}
}
