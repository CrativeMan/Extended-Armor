package io.github.crative.extended_armor.items.custom;

import com.google.common.collect.ImmutableMap;
import io.github.crative.extended_armor.items.armor_materials.ExtendedArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Map;

public class ExtendedArmorItem extends ArmorItem {
	private static final Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_EFFECT_MAP =
		(new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
			.put(ExtendedArmorMaterials.COPPER, new StatusEffectInstance(StatusEffects.HASTE, 400, 0,
				false, false, true)).build();

	public ExtendedArmorItem(ArmorMaterial material, ArmorSlot slot, Settings settings) {
		super(material, slot, settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(!world.isClient){
			if(entity instanceof PlayerEntity player && hasFullSuitOfArmorOn(player)){
				evaluateArmorEffects(player);
			}
		}

		super.inventoryTick(stack, world, entity, slot, selected);
	}

	private void evaluateArmorEffects(PlayerEntity player) {
		for(Map.Entry<ArmorMaterial, StatusEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()){
			ArmorMaterial mapArmorMaterial = entry.getKey();
			StatusEffectInstance mapStatusEffect = entry.getValue();

			if(hasCorrectArmorOn(mapArmorMaterial, player)){
				addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
			}
		}
	}

	private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, StatusEffectInstance mapStatusEffect) {
		boolean hasPlayerEffect = player.hasStatusEffect(mapStatusEffect.getEffectType());

		if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect){
			player.addStatusEffect(new StatusEffectInstance(mapStatusEffect));
		}
	}

	private boolean hasCorrectArmorOn(ArmorMaterial mapArmorMaterial, PlayerEntity player) {
		for(ItemStack armorStack: player.getInventory().armor){
			if(!(armorStack.getItem() instanceof ArmorItem)){
				return false;
			}
		}

		ArmorItem boots = ((ArmorItem) player.getInventory().getArmorStack(0).getItem());
		ArmorItem leggings = ((ArmorItem) player.getInventory().getArmorStack(1).getItem());
		ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmorStack(2).getItem());
		ArmorItem helmet = ((ArmorItem) player.getInventory().getArmorStack(3).getItem());

		return helmet.getMaterial() == mapArmorMaterial && chestplate.getMaterial() == mapArmorMaterial &&
			leggings.getMaterial() == mapArmorMaterial && boots.getMaterial() == mapArmorMaterial;
	}

	private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
		ItemStack boots = player.getInventory().getArmorStack(0);
		ItemStack leggings = player.getInventory().getArmorStack(1);
		ItemStack chestplate = player.getInventory().getArmorStack(2);
		ItemStack helmet = player.getInventory().getArmorStack(3);

		return !helmet.isEmpty() && !chestplate.isEmpty()
			&& !leggings.isEmpty() && !boots.isEmpty();
	}
}
