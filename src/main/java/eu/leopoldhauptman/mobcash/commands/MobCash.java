package eu.leopoldhauptman.mobcash.commands;


import eu.leopoldhauptman.mobcash.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MobCash implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender.hasPermission("mobcash.admin")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GOLD + "--------[Mobcash]--------");
				sender.sendMessage(ChatColor.GRAY+"v8.2 created by Feazes & EonZombie");
				sender.sendMessage(ChatColor.GRAY+"- /mobcash reload");
				
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reload")) {
					Main.plugin.reloadConfig();
					}
				}
			}
		return false;
	}
	
}
