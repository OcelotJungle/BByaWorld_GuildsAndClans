package ru.ocelotjungle.guildsandclans.utils;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ru.ocelotjungle.guildsandclans.managers.LogManager;

public class ValueRange {
	private int value, min, max;
	
	public static HashMap<Integer, ValueRange> getAllValueRanges(JsonArray jsonValueRanges) {
		HashMap<Integer, ValueRange> valueRanges = new HashMap<>(jsonValueRanges.size());
		
		Iterator<JsonElement> iterator = jsonValueRanges.iterator();
		while(iterator.hasNext()) {
			JsonObject json = iterator.next().getAsJsonObject();
			int value = json.get("value").getAsInt();
			int min = Integer.MIN_VALUE;
			int max = Integer.MAX_VALUE;
			
			JsonObject range = json.get("range").getAsJsonObject();
			if(range.isJsonPrimitive()) {
				min = range.getAsInt();
			} else if(range.isJsonArray()) {
				JsonArray values = range.getAsJsonArray();
				if(values.size() == 1) {
					max = values.get(0).getAsInt();
				} else if(values.size() >= 2) {
					min = values.get(0).getAsInt();
					max = values.get(1).getAsInt();
				}
			}
			
			ValueRange valueRange = new ValueRange(value, min, max);
			if(valueRange.isValid()) {
				valueRanges.put(valueRange.value, valueRange);
			}
		}
		
		return valueRanges;
	}
	
	public ValueRange(int value, int min, int max) {
		this.value = value;
		this.min = min;
		this.max = max;
	}
	
	public boolean isValid() {
		LogManager.log("    Value range validation...");
		boolean valid = true;
		
		LogManager.log("        Loaded value: " + value);
		LogManager.log("        Loaded range: [" + min + "; " + max + "]");
		
		return valid;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getMin() {
		return min;
	}
	
	public int getMax() {
		return max;
	}
	
	public String toString() {
		return String.format("{%d; %d; %d}", value, min, max);
	}
}
