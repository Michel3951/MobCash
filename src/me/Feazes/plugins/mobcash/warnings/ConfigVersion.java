package me.Feazes.plugins.mobcash.warnings;

import java.io.File;

import org.bukkit.ChatColor;

import me.Feazes.plugins.mobcash.Main;

public class ConfigVersion {
	File datafolder = Main.plugin.getDataFolder();
	File config = new File(datafolder, "config.yml");
	File newConfig = new File(datafolder, "oldconfig.yml");
	
	public void configVersionCheck(String version) {
		
		if (!(Main.plugin.getConfig().getString("Mobcash.Version").equalsIgnoreCase(version))) {
			
			
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
}
