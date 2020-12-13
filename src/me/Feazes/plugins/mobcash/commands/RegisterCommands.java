package me.Feazes.plugins.mobcash.commands;

import me.Feazes.plugins.mobcash.Main;

public class RegisterCommands {
	
	public RegisterCommands() {
		Main.plugin.getCommand("mobcash").setExecutor(new Mobcash(){});
	}
} 
