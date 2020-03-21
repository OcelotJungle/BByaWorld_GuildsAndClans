package ru.ocelotjungle.guildsandclans;

import java.util.logging.Level;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import ru.ocelotjungle.guildsandclans.managers.LogManager;

public class GuildsAndClans extends JavaPlugin {
	public static Server server;
	public static Scoreboard scoreboard;
	public static GuildsAndClans plugin;
	
	public GuildsAndClans() {
		getLogger().setLevel(Level.OFF);
	}
	
	public void onEnable() {
		initialize();
		LogManager.log("[BByaWorld_GuildsAndClans] Loaded and enabled.");
	}
	
	private void initialize() {
		LogManager.init();
	}
	
	public void onDisable() {
		server.getScheduler().cancelTasks(plugin);
		LogManager.log("[BByaWorld_GuildsAndClans] Unloaded and disabled.");
	}
}
