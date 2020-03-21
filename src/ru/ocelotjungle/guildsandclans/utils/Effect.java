package ru.ocelotjungle.guildsandclans.utils;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ru.ocelotjungle.guildsandclans.managers.LogManager;

public class Effect {
	private EffectType effectType;
	private boolean morning;
	private HashMap<Integer, ValueRange> levels;
	
	public static HashMap<String, Effect> getAllEffects(JsonArray jsonEffects) {
		HashMap<String, Effect> effects = new HashMap<>(jsonEffects.size());
		
		Iterator<JsonElement> iterator = jsonEffects.iterator();
		while(iterator.hasNext()) {
			JsonObject json = iterator.next().getAsJsonObject();
			boolean morning = json.get("morning").getAsBoolean();
			
			EffectType effectType = EffectType.getByName(json.get("name").getAsString());
			
			HashMap<Integer, ValueRange> levels = ValueRange.getAllValueRanges(json.get("levels").getAsJsonArray());
			
			Effect effect = new Effect(effectType, morning, levels);
			if(effect.isValid()) {
				effects.put(effect.getEffectType().name(), effect);
			}
		}
		
		return effects;
	}
	
	public Effect(EffectType effectType, boolean morning, HashMap<Integer, ValueRange> levels) {
		this.effectType = effectType;
		this.morning = morning;
		this.levels = levels;
	}
	
	public boolean isValid() {
		LogManager.log("Effect validation...");
		boolean valid = true;
		
		LogManager.log("    Loaded name: " + effectType.name());
		LogManager.log("    Loaded morning: " + morning);
		LogManager.log("    Loaded levels, count: " + levels.size());
		
		return valid;
	}
	
	public EffectType getEffectType() {
		return effectType;
	}
	
	public boolean isMorning() {
		return morning;
	}
	
	public HashMap<Integer, ValueRange> getLevels() {
		return levels;
	}
	
	public String toString() {
		return String.format("{%s; %b}", effectType.name(), morning);
	}
}