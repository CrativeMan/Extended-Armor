package io.github.crative.extended_armor.effects;


import io.github.crative.extended_armor.items.custom.ExtendedArmorItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class ExtendedArmorCustomEffects {

	public void stealthEffect(PlayerEntity player, World world, ArmorMaterial mapArmorMaterial) {
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
	public void addParticalsToPlayer(PlayerEntity player, World world, ExtendedArmorParticles particles) {
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
	public void addStatusEffectToPlayer(PlayerEntity player, World world, ArmorMaterial mapArmorMaterial, ExtendedArmorStatusEffects statusEffects) {

		StatusEffectInstance effectInstance = new StatusEffectInstance(statusEffects.getEffects(),
			statusEffects.getDuration(), statusEffects.getAmplifier(),
			statusEffects.isAmbient(), statusEffects.isShowParticles(), statusEffects.isShowIcon());

		boolean hasPlayerEffect = player.hasStatusEffect(effectInstance.getEffectType());

		if(ExtendedArmorItem._hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect){
			player.addStatusEffect(effectInstance);
		}

	}
}
