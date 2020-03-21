package ru.ocelotjungle.guildsandclans.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import ru.ocelotjungle.guildsandclans.GuildsAndClans;
import ru.ocelotjungle.guildsandclans.utils.Clan;
import ru.ocelotjungle.guildsandclans.utils.Guild;
import ru.ocelotjungle.guildsandclans.utils.PlayerInfo;

public class ConfigManager {
	private static ConfigManager instance;
	private GuildsAndClans plugin;
	private HashMap<String, Clan> clans;
	private HashMap<String, Guild> guilds;
	private HashMap<String, PlayerInfo> players;
	private Gson gson;
	
	
	public static ConfigManager getInstance() {
		if(instance == null) {
			instance = new ConfigManager();
		}
		return instance;
	}
	
	public ConfigManager() {
		this.plugin = GuildsAndClans.plugin;
		load();
	}
	
	private void load() {
		gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			savePlayerConfig();
			
			loadClanConfig();
			loadGuildConfig();
			loadPlayerList();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void savePlayerConfig() throws IOException {
		File file = new File(plugin.getDataFolder(), "players.json");
		file.delete();
		Files.write(file.toPath(), gson.toJson(players.values()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
	}

	private void loadClanConfig() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		plugin.saveResource("clans.json", false);
		File file = new File(plugin.getDataFolder(), "clans.json");
		JsonArray jsonClans = gson.fromJson(new FileReader(file), JsonArray.class);
		this.clans = Clan.getAllClans(jsonClans);
	}

	private void loadGuildConfig() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		plugin.saveResource("guilds.json", false);
		File file = new File(plugin.getDataFolder(), "guilds.json");
		JsonArray jsonGuilds = gson.fromJson(new FileReader(file), JsonArray.class);
		this.guilds = Guild.getAllGuilds(jsonGuilds);
	}

	private void loadPlayerList() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		plugin.saveResource("players.json", false);
		File file = new File(plugin.getDataFolder(), "players.json");
		JsonArray jsonPlayers = gson.fromJson(new FileReader(file), JsonArray.class);
		this.players = PlayerInfo.getAllPlayers(jsonPlayers);
	}
	
	public HashMap<String, Clan> getClans() {
		return clans;
	}
	
	public HashMap<String, Guild> getGuilds() {
		return guilds;
	}
	
	public HashMap<String, PlayerInfo> getPlayers() {
		return players;
	}
}
