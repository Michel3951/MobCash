package eu.leopoldhauptman.mobcash.enums;

import eu.leopoldhauptman.mobcash.Main;
import eu.leopoldhauptman.mobcash.methods.EconomyDealer;
import eu.leopoldhauptman.mobcash.methods.PlMath;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;


public enum RewardType {
	CONSTANT,
	RANDOM,
	PERCENTAGE;
	
	public static RewardType fromString(String input) {
		switch (input.toLowerCase()) {
		case "constant":
			return CONSTANT;
		case "random":
			return RANDOM;
		case "percentage":
			return PERCENTAGE;
		default:
			Main.plugin.getLogger().warning("MessageType shoud be CONSTANT, RANDOM or PERCENTAGE. Currently: \"" + input+"\"! Seting to CONSTANT.");
			return CONSTANT;
		}
	}
	
	public static double getReward(ConfigurationSection section, Boolean pvp, Player killed) {
		RewardType type = fromString(section.getString("type"));
		switch (type) {
		case CONSTANT:
			return section.getDouble("amount");
		case RANDOM:
			return PlMath.getRandomCash(section.getDouble("min"), section.getDouble("max"));
		default:
			if (pvp) return EconomyDealer.getMoney(killed)*section.getDouble("amount")/100;
			else return section.getDouble("amount");
		}
	}
}
