package ru.ocelotjungle.guildsandclans.api;

import ru.ocelotjungle.guildsandclans.managers.ConfigManager;
import ru.ocelotjungle.guildsandclans.utils.Clan;
import ru.ocelotjungle.guildsandclans.utils.Guild;
import ru.ocelotjungle.guildsandclans.utils.PlayerInfo;

/**
 * Class for interaction with plugin API
 * 
 * @author OcelotJungle
 *
 */
public class API {

	/**
	 * Return clan by it's name
	 * 
	 * @param name the label of clan you need
	 * @return the clan object
	 * 
	 */
	public static Clan getClan(String name) {
		return ConfigManager.getInstance().getClans().get(name);
	}

	/**
	 * Return guild by it's name
	 * 
	 * @param name the name of guild you need
	 * @return the guild object
	 * 
	 */
	public static Guild getGuild(String name) {
		return ConfigManager.getInstance().getGuilds().get(name);
	}

	/**
	 * Return player's info by his nickname
	 * 
	 * @param name the nickname of player you need
	 * @return the player info object
	 * 
	 */
	public static PlayerInfo getPlayerInfo(String name) {
		return ConfigManager.getInstance().getPlayers().get(name.toLowerCase());
	}

}
