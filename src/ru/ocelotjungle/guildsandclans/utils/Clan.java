package ru.ocelotjungle.guildsandclans.utils;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.ChatColor;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ru.ocelotjungle.guildsandclans.managers.LogManager;

public class Clan {
	private String label;
	private ChatColor color;
	
	public static HashMap<String, Clan> getAllClans(JsonArray jsonClans) {
		HashMap<String, Clan> clans = new HashMap<>(jsonClans.size());
		
		Iterator<JsonElement> iterator = jsonClans.iterator();
		while(iterator.hasNext()) {
			JsonObject json = iterator.next().getAsJsonObject();
			String label = json.get("label").getAsString();
			
			ChatColor color = ChatColor.valueOf(json.get("color").getAsString());
			
			Clan clan = new Clan(label, color);
			if(clan.isValid()) {
				clans.put(clan.getLabel(), clan);
			}
		}
		
		return clans;
	}
	
	public Clan(String label, ChatColor color) {
		this.label = label;
		this.color = color;
	}
	
	public boolean isValid() {
		LogManager.log("Clan validation...");
		boolean valid = true;
		
		if(label != null) {
			LogManager.log("    Loaded label: " + label);
		} else {
			LogManager.err("    Has no name!");
			valid = false;
		}
		
		if(color != null) {
			LogManager.log("    Loaded color: " + color.name());
		} else {
			LogManager.err("    Has no color!");
			valid = false;
		}
		
		return valid;
	}
	
	public String getLabel() {
		return label;
	}
	
	public ChatColor getColor() {
		return color;
	}
	
	public String toString() {
		return String.format("{%s; %s}", label, color.name());
	}
}
