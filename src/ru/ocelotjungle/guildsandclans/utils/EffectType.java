package ru.ocelotjungle.guildsandclans.utils;

import org.bukkit.potion.PotionEffectType;

public enum EffectType {
	SPEED			(PotionEffectType.SPEED),
	SLOWNESS		(PotionEffectType.SLOW),
	HASTE			(PotionEffectType.FAST_DIGGING),
	MINING_FATIGUE	(PotionEffectType.SLOW_DIGGING),
	STRENGTH		(PotionEffectType.INCREASE_DAMAGE),
	INSTANT_HEALTH	(PotionEffectType.HEAL),
	INSTANT_DAMAGE	(PotionEffectType.HARM),
	JUMP_BOOST		(PotionEffectType.JUMP),
	NAUSEA			(PotionEffectType.CONFUSION),
	REGENERATION	(PotionEffectType.REGENERATION),
	RESISTANCE		(PotionEffectType.DAMAGE_RESISTANCE),
	FIRE_RESISTANCE	(PotionEffectType.FIRE_RESISTANCE),
	WATER_BREATHING	(PotionEffectType.WATER_BREATHING),
	INVISIBILITY	(PotionEffectType.INVISIBILITY),
	BLINDNESS		(PotionEffectType.BLINDNESS),
	NIGHT_VISION	(PotionEffectType.NIGHT_VISION),
	HUNGER			(PotionEffectType.HUNGER),
	WEAKNESS		(PotionEffectType.WEAKNESS),
	POISON			(PotionEffectType.POISON),
	WITHER			(PotionEffectType.WITHER),
	HEALTH_BOOST	(PotionEffectType.HEALTH_BOOST),
	ABSORPTION		(PotionEffectType.ABSORPTION),
	SATURATION		(PotionEffectType.SATURATION),
	GLOWING			(PotionEffectType.GLOWING),
	LEVITATION		(PotionEffectType.LEVITATION),
	LUCK			(PotionEffectType.LUCK),
	UNLUCK			(PotionEffectType.UNLUCK),
	SLOW_FALLING	(PotionEffectType.GLOWING), // SLOW_FALLING),
	CONDUIT_POWER	(PotionEffectType.GLOWING), // CONDUIT_POWER),
	DOLPHINS_GRACE	(PotionEffectType.GLOWING); // DOPLHINS_GRACE);
	
	private final PotionEffectType spigotPotionEffectType;
	
	private EffectType(PotionEffectType type) {
		this.spigotPotionEffectType = type;
	}
	
	public static EffectType getByName(String name) {
		try {
			return valueOf(name.toUpperCase());
		} catch (IllegalArgumentException iae) {
			return GLOWING;
		}
	}
	
	public PotionEffectType getEffectType() {
		return spigotPotionEffectType;
	}
}