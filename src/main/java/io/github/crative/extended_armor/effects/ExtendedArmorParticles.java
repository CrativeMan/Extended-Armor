package io.github.crative.extended_armor.effects;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;

public enum ExtendedArmorParticles {
	COPPER(ParticleTypes.ASH, 2, 1F,
		(float) Math.random(), (float) Math.random(), (float) Math.random()),
	OBSIDIAN(ParticleTypes.FALLING_OBSIDIAN_TEAR, 1, 1f,
		(float) Math.random()/4, (float) Math.random()/2, (float) Math.random()/4)
	;

	private final DefaultParticleType particles;
	private final int count;
	private final float speed;
	private final float deltaX;
	private final float deltaY;

	private final float deltaZ;

	ExtendedArmorParticles(DefaultParticleType particles, int count, float speed, float deltaX, float deltaY, float deltaZ) {
		this.particles = particles;
		this.count = count;
		this.speed = speed;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.deltaZ = deltaZ;
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

	public float getDeltaZ() {
		return deltaZ;
	}

	public float getDeltaY() {
		return deltaY;
	}

	public float getDeltaX() {
		return deltaX;
	}
}
