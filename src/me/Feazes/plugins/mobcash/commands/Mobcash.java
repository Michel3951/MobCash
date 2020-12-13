package me.Feazes.plugins.mobcash.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Feazes.plugins.mobcash.Main;

public abstract class Mobcash implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command,	String label, String[] args) {
	
		
		if (sender.hasPermission("mobcash.admin") || !(sender instanceof Player)) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GOLD + "--------[Mobcash]--------");
				sender.sendMessage(ChatColor.GRAY+"v3.5 created by Feazes & EonZombiecrafter");
				sender.sendMessage(ChatColor.GRAY+"- /mobcash reload");
				
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reload")) {
					Main.plugin.reloadConfig();
					sender.sendMessage(ChatColor.GOLD + "[Mobcash]"+ ChatColor.GRAY+" reloaded config!");
					//System.out.println("!xnt ,vedkcin");
					//SPECIAL THANKS TO NickDEV FOR HELPING ME MAKING PLUGINS LIKE A PRO :D!!!
					}
				}
			}
		return false;
	}
}
