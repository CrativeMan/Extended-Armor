package io.github.crative.extended_armor;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;

public enum ExtendedArmorParticles {
	COPPER(ParticleTypes.ASH, 2, 1),
	OBSIDIAN(ParticleTypes.HAPPY_VILLAGER, 10, 10)
	;

	private final DefaultParticleType particles;
	private final int count;
	private final float speed;

	ExtendedArmorParticles(DefaultParticleType particles, int count, float speed) {
		this.particles = particles;
		this.count = count;
		this.speed = speed;
	}

	public DefaultParticleType getParticles() {
		return this.particles;
	}

	public int getCount() {
		return this.count;
	}

	public float getSpeed() {
		return this.speed;
	}
}
