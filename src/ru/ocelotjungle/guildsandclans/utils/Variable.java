package ru.ocelotjungle.guildsandclans.utils;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ru.ocelotjungle.guildsandclans.managers.LogManager;

public class Variable {
	private String varname;
	private HashMap<Integer, ValueRange> values;
	
	public static HashMap<String, Variable> getAllVariables(JsonArray jsonVariables) {
		HashMap<String, Variable> variables = new HashMap<>(jsonVariables.size());
		
		Iterator<JsonElement> iterator = jsonVariables.iterator();
		while(iterator.hasNext()) {
			JsonObject json = iterator.next().getAsJsonObject();
			String varname = json.get("varname").getAsString();
			
			HashMap<Integer, ValueRange> values = ValueRange.getAllValueRanges(json.get("values").getAsJsonArray());
			
			Variable variable = new Variable(varname, values);
			if(variable.isValid()) {
				variables.put(variable.getVarname(), variable);
			}
		}
		
		return variables;
	}

	public Variable(String varname, HashMap<Integer, ValueRange> values) {
		this.varname = varname;
		this.values = values;
	}
	
	public boolean isValid() {
		LogManager.log("Variable validation...");
		boolean valid = true;
		
		if(varname != null) {
			LogManager.log("    Loaded varname: " + varname);
		} else {
			LogManager.err("    Has no varname!");
			valid = false;
		}
		
		LogManager.log("    Loaded values, count: " + values.size());
		
		return valid;
	}
	
	public String getVarname() {
		return varname;
	}
	
	public HashMap<Integer, ValueRange> getValues() {
		return values;
	}
	
	public String toString() {
		return String.format("{%s}", varname);
	}
}
