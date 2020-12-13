package me.Feazes.plugins.mobcash.listeners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import me.Feazes.plugins.mobcash.Main;
import me.Feazes.plugins.mobcash.Reason.Breeding;
import me.Feazes.plugins.mobcash.Reason.Egg;
import me.Feazes.plugins.mobcash.Reason.Natural;
import me.Feazes.plugins.mobcash.Reason.Slime_Split;
import me.Feazes.plugins.mobcash.Reason.Spawner;
import me.Feazes.plugins.mobcash.World.WorldListener;

public class RegisterListeners {
		
	public RegisterListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new KilledPlayer(), Main.plugin);
		pm.registerEvents(new MobKill(), Main.plugin);
		pm.registerEvents(new Egg(), Main.plugin); 
		pm.registerEvents(new Natural(), Main.plugin); 
		pm.registerEvents(new Slime_Split(), Main.plugin); 
		pm.registerEvents(new Spawner(), Main.plugin); 
		pm.registerEvents(new WorldListener(), Main.plugin); 
		pm.registerEvents(new Breeding(), Main.plugin);
	}
}
