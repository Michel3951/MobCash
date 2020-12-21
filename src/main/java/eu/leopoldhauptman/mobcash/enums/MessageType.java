package eu.leopoldhauptman.mobcash.enums;


import eu.leopoldhauptman.mobcash.Main;

public enum MessageType {
	ACTIONBAR,
	MESSAGE;
	
	public static MessageType fromString(String input) {
		switch (input.toLowerCase()) {
		case "message":
			return MESSAGE;
		case "actionbar":
			return ACTIONBAR;
		default:
			Main.plugin.getLogger().warning("MessageType shoud be MESSAGE or ACTIONBAR. Currently: \"" + input+"\"! Seting to ACTIONBAR.");
			return ACTIONBAR;
		}
	}
}