package ru.ocelotjungle.guildsandclans.utils;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.ChatColor;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ru.ocelotjungle.guildsandclans.managers.LogManager;

public class Guild {
	private String name, symbol;
	private int maxLevel;
	private ChatColor color;
	private HashMap<String, Levelup> levelups;
	private HashMap<String, Effect> effects;
	private HashMap<String, Variable> variables;
	
	public static HashMap<String, Guild> getAllGuilds(JsonArray jsonGuilds) {
		HashMap<String, Guild> guilds = new HashMap<>(jsonGuilds.size());
		
		Iterator<JsonElement> iterator = jsonGuilds.iterator();
		while(iterator.hasNext()) {
			JsonObject json = iterator.next().getAsJsonObject();
			String name = json.get("name").getAsString();
			String symbol = json.get("symbol").getAsString();
			int maxLevel = json.get("max_level").getAsInt();
			
			ChatColor color = ChatColor.valueOf(json.get("color").getAsString());
			
			HashMap<String, Levelup> levelups = Levelup.getAllLevelups(json.get("levelups").getAsJsonArray());
			HashMap<String, Effect> effects = Effect.getAllEffects(json.get("effects").getAsJsonArray());
			HashMap<String, Variable> variables = Variable.getAllVariables(json.get("variables").getAsJsonArray());
			
			Guild guild = new Guild(name, symbol, color, maxLevel, levelups, effects, variables);
			if(guild.isValid()) {
				guilds.put(guild.getName(), guild);
			}
		}
		
		return guilds;
	}
	
	public Guild(String name, String symbol, ChatColor color, Integer maxLevel, 
				HashMap<String, Levelup> levelups, HashMap<String, Effect> effects, HashMap<String, Variable> variables) {
		this.name = name;
		this.symbol = symbol;
		this.color = color;
		this.maxLevel = maxLevel;
		this.levelups = levelups;
		this.effects = effects;
		this.variables = variables;
	}
	
	public boolean isValid() {
		LogManager.log("Guild validation...");
		boolean valid = true;
		
		if(name != null) {
			LogManager.log("    Loaded name: " + name);
		} else {
			LogManager.err("    Has no name!");
			valid = false;
		}
		
		if(symbol != null) {
			LogManager.log("    Loaded symbol: " + symbol);
		} else {
			LogManager.err("    Has no symbol!");
			valid = false;
		}
		
		if(color != null) {
			LogManager.log("    Loaded color: " + color.name());
		} else {
			LogManager.err("    Has no color!");
			valid = false;
		}
		
		if(maxLevel != 0) {
			LogManager.log("    Loaded max level: " + maxLevel);
		} else {
			LogManager.err("    Has no max level!");
			valid = false;
		}
		
		LogManager.log("    Loaded levelups, count: " + levelups.size());
		LogManager.log("    Loaded effects, count: " + effects.size());
		LogManager.log("    Loaded variables, count: " + variables.size());
		
		return valid;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public ChatColor getColor() {
		return color;
	}
	
	public HashMap<String, Levelup> getLevelups() {
		return levelups;
	}
	
	public HashMap<String, Effect> getEffects() {
		return effects;
	}
	
	public HashMap<String, Variable> getVariables() {
		return variables;
	}
	
	public String toString() {
		return String.format("{%s; %s; %d; %s}", name, symbol, maxLevel, color.name());
	}
}
