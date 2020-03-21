package ru.ocelotjungle.guildsandclans.managers;

import java.util.logging.Logger;

import ru.ocelotjungle.guildsandclans.GuildsAndClans;

public class LogManager {
	private static Logger logger;
	
	public static void init() {
		logger = GuildsAndClans.plugin.getLogger();
	}
	
	public static void log(Object toLog) {
		logger.info("[GuildsAndClans] " + (toLog == null ? "null" : toLog.toString()));
	}
	
	public static void err(Object toErr) {
		logger.severe("[GuildsAndClans] " + (toErr == null ? "null" : toErr.toString()));
	}
}
