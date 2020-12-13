package me.Feazes.plugins.mobcash.warnings;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import me.Feazes.plugins.mobcash.Main;
import me.Feazes.plugins.mobcash.methods.Format.MessageType;

public class Configuration {
	File datafolder = Main.plugin.getDataFolder();
	File config = new File(datafolder, "config.yml");
	File newConfig = new File(datafolder, "oldconfig.yml");
	
	public void configVersionCheck(double version) {
		
		if (Main.plugin.getConfig().getDouble("Mobcash.Version") != version) {
			
			Main.plugin.saveDefaultConfig();
			Main.plugin.getLogger().warning(ChatColor.RED + "-------------[Mobcash]-------------");
			Main.plugin.getLogger().warning(ChatColor.RED + "----------Version update-----------");
			Main.plugin.getLogger().warning(ChatColor.RED + "You need to update your config file");
			Main.plugin.getLogger().warning(ChatColor.RED + "Version of your config is: " + Main.plugin.getConfig().getString("Mobcash.Version") + ".");
			Main.plugin.getLogger().warning(ChatColor.RED + "This plugin uses config version: " + version + ".");
			
			Main.plugin.getLogger().warning(ChatColor.RED + "Renaming 'config.yml' to 'oldconfig.yml'");
			if (newConfig.exists()) newConfig.delete();
			config.renameTo(newConfig);
			Main.plugin.getLogger().warning(ChatColor.RED + "Generating new Config");
			Main.plugin.getConfig().options().copyDefaults();
			Main.plugin.getLogger().warning(ChatColor.RED + "-----------------------------------");
	
		}
		else {Main.plugin.getLogger().info(ChatColor.GREEN + "Config version ok.");}
	
	}
	
	static FileConfiguration config() {
		return Main.plugin.getConfig();
	}
	
	public static MessageType getMessageType() {
		return MessageType.fromString(config().getString("Mobcash.Message-Type"));
	}
	
	
}
