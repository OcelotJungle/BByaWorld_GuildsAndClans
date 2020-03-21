package ru.ocelotjungle.guildsandclans.utils;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ru.ocelotjungle.guildsandclans.managers.ConfigManager;
import ru.ocelotjungle.guildsandclans.managers.LogManager;

public class PlayerInfo {
	private String name;
	private Clan clan;
	private Guild guild;
	private int level;
	
	public static HashMap<String, PlayerInfo> getAllPlayers(JsonArray jsonPlayers) {
		HashMap<String, PlayerInfo> players = new HashMap<>(jsonPlayers.size());
		
		Iterator<JsonElement> iterator = jsonPlayers.iterator();
		HashMap<String, Clan> clans = ConfigManager.getInstance().getClans();
		HashMap<String, Guild> guilds = ConfigManager.getInstance().getGuilds();
		while(iterator.hasNext()) {
			JsonObject json = iterator.next().getAsJsonObject();
			String name = json.get("name").getAsString();
			int level = json.get("name").getAsInt();
			
			String sclan = json.get("name").getAsString();
			Clan clan = clans.get(sclan);
			String sguild = json.get("name").getAsString();
			Guild guild = guilds.get(sguild);
			
			PlayerInfo playerInfo = new PlayerInfo(name, clan, guild, level);
			if(playerInfo.isValid()) {
				players.put(playerInfo.getName(), playerInfo);
			}
		}
		
		return players;
	}

	public PlayerInfo(String name, Clan clan, Guild guild, int level) {
		this.name = name.toLowerCase();
		this.clan = clan;
		this.guild = guild;
		this.level = level;
	}
	
	public boolean isValid() {
		String name = this.name != null ? this.name : "<null>";
		String clan = this.clan != null ? this.clan.getLabel() : "<null>";
		String guild = this.guild != null ? this.guild.getName() : "<null>";
		String record = String.format("{Name: %s; Clan: %s; Guild: %s; Level: %d}", name, clan, guild, level);
		if(name != null && clan != null && guild != null) {
			LogManager.log("Successfully loaded player: " + record);
			return true;
		} else {
			LogManager.err("Invalid player record: " + record);
			return false;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public Clan getClan() {
		return clan;
	}
	
	public Guild getGuild() {
		return guild;
	}
	
	public int getLevel() {
		return level;
	}
	
	public String toString() {
		return String.format("{%s; %s; %s; %d}", name, clan.getLabel(), guild.getName(), level);
	}
}
