package io.github.crative.extended_armor;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;

public enum ExtendedArmorStatusEffects {
	COPPER(StatusEffects.HASTE, 200, 0, false, false, true),
	OBSIDIAN(StatusEffects.FIRE_RESISTANCE, 200, 0, false, false, true),
	;

	private final StatusEffect effects;
	private final int duration;
	private final int amplifier;
	private final boolean ambient;
	private final boolean showParticles;
	private final boolean showIcon;

	ExtendedArmorStatusEffects(StatusEffect effects, int duration, int amplifier, boolean ambient, boolean showParticles, boolean showIcon){
		this.effects = effects;
		this.duration = duration;
		this.amplifier = amplifier;
		this.ambient = ambient;
		this.showParticles = showParticles;
		this.showIcon = showIcon;
	}

	public StatusEffect getEffects() {
		return effects;
	}

	public int getDuration() {
		return duration;
	}

	public int getAmplifier() {
		return amplifier;
	}

	public boolean isAmbient() {
		return ambient;
	}

	public boolean isShowParticles() {
		return showParticles;
	}

	public boolean isShowIcon() {
		return showIcon;
	}
}
