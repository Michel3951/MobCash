package me.Feazes.plugins.mobcash.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Feazes.plugins.mobcash.Main;

public class Mobcash implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command,	String label, String[] args) {
		
		if (sender.hasPermission("mobcash.admin")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GOLD + "--------[Mobcash]--------");
				sender.sendMessage(ChatColor.GRAY+"v8.0 created by Feazes & EonZombie");
				sender.sendMessage(ChatColor.GRAY+"- /mobcash reload");
				
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reload")) {
					Main.plugin.reloadConfig();
					sender.sendMessage(ChatColor.GOLD + "[Mobcash]"+ ChatColor.GRAY+" reloaded config!");
					//System.out.println("!xnt ,vedkcin");
					//SPECIAL THANKS TO NickDEV FOR HELPING ME MAKING PLUGINS LIKE A PRO :D!!! <-From 2016 Me
					//2020 Me: LOL, that 2016 me, what an idiot, WTF was he doing? I had to clean up behind him
					//Damn, those years are passing fast
					}
				}
			}
		return false;
	}
}
