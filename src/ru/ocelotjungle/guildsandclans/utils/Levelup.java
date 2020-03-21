package ru.ocelotjungle.guildsandclans.utils;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import ru.ocelotjungle.guildsandclans.managers.LogManager;

public class Levelup {
	private String varname;
	private int cost, step;
	
	public static HashMap<String, Levelup> getAllLevelups(JsonArray jsonLevelups) {
		HashMap<String, Levelup> levelups = new HashMap<>(jsonLevelups.size());
		
		Gson gson = new GsonBuilder().create();
		Iterator<JsonElement> iterator = jsonLevelups.iterator();
		while(iterator.hasNext()) {
			JsonElement json = iterator.next();
			Levelup levelup = gson.fromJson(json, Levelup.class);
			if(levelup.isValid()) {
				levelups.put(levelup.getVarname(), levelup);
			}
		}
		
		return levelups;
	}

	public Levelup(String varname, int cost, int step) {
		this.varname = varname;
		this.cost = cost;
		this.step = step;
	}
	
	public boolean isValid() {
		LogManager.log("    Levelup validation...");
		boolean valid = true;
		
		if(varname != null) {
			LogManager.log("        Loaded varname: " + varname);
		} else {
			LogManager.err("        Has no varname!");
			valid = false;
		}
		
		LogManager.log("        Loaded cost: " + cost);
		LogManager.log("        Loaded step: " + step);
		
		return valid;
	}
	
	public String getVarname() {
		return varname;
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getStep() {
		return step;
	}
	
	public String toString() {
		return String.format("{%s; %d; %d}", varname, cost, step);
	}
}