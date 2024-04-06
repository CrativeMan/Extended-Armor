package io.github.crative.extended_armor.items.custom;

import com.google.common.collect.ImmutableMap;
import io.github.crative.extended_armor.effects.ExtendedArmorParticles;
import io.github.crative.extended_armor.effects.ExtendedArmorStatusEffects;
import io.github.crative.extended_armor.items.armor_materials.ExtendedArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.Map;

/**
 * Most of the Code from Kaupenjoe's Fabric 1.20.X Modding YT-series
 * Added my own functionality and logic
 * @Credit: Kaupenjoe
 */
public class ExtendedArmorItem extends ArmorItem {
	private static final Map<ArmorMaterial, Integer> MATERIAL_TO_EFFECT_MAP =
		(new ImmutableMap.Builder<ArmorMaterial, Integer>())
			.put(ExtendedArmorMaterials.COPPER, 0)
			.put(ExtendedArmorMaterials.OBSIDIAN, 1)
			.put(ExtendedArmorMaterials.STEALTH, 2).build();

	public ExtendedArmorItem(ArmorMaterial material, ArmorSlot slot, Settings settings) {
		super(material, slot, settings);
	}

	/**
	 * Get called every tick and checks if the entity wearing the armor is the player,
	 * and if it has a full suit of armor on.
	 * If that is true, then it checks which setBonus to give to the player
	 * @param stack provided by ArmorItem
	 * @param world world the player is in
	 * @param entity entity that has the item in the inventory
	 * @param slot slot the item is in
	 * @param selected if item is selected
	 */
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(!world.isClient){
			if(entity instanceof PlayerEntity player && hasFullSuitOfArmorOn(player)){
				evaluateSetBonus(player, world);
			}
		}
	}


	/**
	 * Loops through every entry in the MATERIAL_TO_EFFECT_MAP map
	 * Checks if the player has an armor on that is on the list
	 * If yes then it calls the executeSetBonus methode
	 * @param player player entity
	 * @param world world player is in
	 */
	private void evaluateSetBonus(PlayerEntity player, World world) {
		for(Map.Entry<ArmorMaterial, Integer> entry : MATERIAL_TO_EFFECT_MAP.entrySet()){
			ArmorMaterial mapArmorMaterial = entry.getKey();
			Integer setBonusKey = entry.getValue();

			if(hasCorrectArmorOn(mapArmorMaterial, player)){
				executeSetBonus(player, mapArmorMaterial, setBonusKey, world);
			}
		}
	}

	/**
	 * Switches through the different setBoni keys and running the desired particle and/or StatusEffect function
	 * @param player player entity
	 * @param mapArmorMaterial Armor material
	 * @param setBonusKey Integer key for which setBonus to give
	 * @param world world where player is
	 */
	private void executeSetBonus(PlayerEntity player, ArmorMaterial mapArmorMaterial, Integer setBonusKey, World world) {
		switch (setBonusKey){
			case 0:
				addStatusEffectToPlayer(player, world, mapArmorMaterial, ExtendedArmorStatusEffects.COPPER);
				addParticalsToPlayer(player, world, ExtendedArmorParticles.COPPER);
				break;
			case 1:
				addStatusEffectToPlayer(player, world, mapArmorMaterial, ExtendedArmorStatusEffects.OBSIDIAN);
				addParticalsToPlayer(player, world, ExtendedArmorParticles.OBSIDIAN);
				break;
			case 2:
				stealthEffect(player, world, mapArmorMaterial);
			default:
				break;
		}
	}


	private void stealthEffect(PlayerEntity player, World world, ArmorMaterial mapArmorMaterial) {
		if(!world.isClient){
			if(world.getTimeOfDay() >= 13000 || world.getTimeOfDay() < 1000){
				addStatusEffectToPlayer(player, world, mapArmorMaterial, ExtendedArmorStatusEffects.STEALTH);
			}
			player.setCustomNameVisible(false);
		}
	}

	/**
	 * Checks if the world is a ServerWorld
	 * if so, then it spawns Particles using the {@link ExtendedArmorParticles} enum
	 * to get the different attributes
	 * @param player player entity
	 * @param world World which player is in
	 * @param particles instance of {@link ExtendedArmorParticles} enum
	 */
	private void addParticalsToPlayer(PlayerEntity player, World world, ExtendedArmorParticles particles) {
		if(world instanceof ServerWorld serverWorld){
			((ServerWorld) world).spawnParticles(particles.getParticles(), player.getX(), player.getY() + 1.0,
				player.getZ(), particles.getCount(), particles.getDeltaX(), particles.getDeltaY(), particles.getDeltaZ(), particles.getSpeed());
		}
	}

	/**
	 * Creating a StatusEffectInstance of the desired StatusEffect using the {@link ExtendedArmorStatusEffects}
	 * and then checking if the player already has the effect active, if not applying it to the player
	 * @param player player entity
	 * @param world world which player is in
	 * @param mapArmorMaterial which armorMaterial is to be affected
	 * @param statusEffects instance of {@link ExtendedArmorStatusEffects} enum
	 */
	private void addStatusEffectToPlayer(PlayerEntity player, World world, ArmorMaterial mapArmorMaterial, ExtendedArmorStatusEffects statusEffects) {

		StatusEffectInstance effectInstance = new StatusEffectInstance(statusEffects.getEffects(),
			statusEffects.getDuration(), statusEffects.getAmplifier(),
			statusEffects.isAmbient(), statusEffects.isShowParticles(), statusEffects.isShowIcon());

		boolean hasPlayerEffect = player.hasStatusEffect(effectInstance.getEffectType());

		if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect){
			player.addStatusEffect(effectInstance);
		}

	}

	/**
	 * for-loop is a failsafe for wearing armor
	 * gets each item in the armor slots and checks if it is form an armor material in the map
	 * @Credit: Kaupenjoe's YouTube series on Fabric modding
	 * @param mapArmorMaterial armor material to be effected
	 * @param player player entity
	 */
	private boolean hasCorrectArmorOn(ArmorMaterial mapArmorMaterial, PlayerEntity player) {
		for(ItemStack armorStack : player.getInventory().armor){
			if(!(armorStack.getItem() instanceof ArmorItem)){
				return false;
			}
		}

		ArmorItem boots = ((ArmorItem) player.getInventory().getArmorStack(0).getItem());
		ArmorItem leggings = ((ArmorItem) player.getInventory().getArmorStack(1).getItem());
		ArmorItem chestplate= ((ArmorItem) player.getInventory().getArmorStack(2).getItem());
		ArmorItem helmet = ((ArmorItem) player.getInventory().getArmorStack(3).getItem());

		return helmet.getMaterial() == mapArmorMaterial	&& chestplate.getMaterial() == mapArmorMaterial
			&& leggings.getMaterial() == mapArmorMaterial && boots.getMaterial() == mapArmorMaterial;
	}

	/**
	 * returns true if the player has a full suit of armor on
	 * @param player player entity
	 */
	private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
		ItemStack boots = player.getInventory().getArmorStack(0);
		ItemStack leggings = player.getInventory().getArmorStack(1);
		ItemStack chestplate = player.getInventory().getArmorStack(2);
		ItemStack helmet = player.getInventory().getArmorStack(3);

		return !helmet.isEmpty() && !chestplate.isEmpty()
			&& !leggings.isEmpty() && !boots.isEmpty();
	}
}
