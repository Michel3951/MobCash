package me.Feazes.plugins.mobcash.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.Feazes.plugins.mobcash.Main;
import me.Feazes.plugins.mobcash.methods.EconomyDealer;
import me.Feazes.plugins.mobcash.methods.Format;
import me.Feazes.plugins.mobcash.methods.PlMath;
import me.Feazes.plugins.mobcash.warnings.Configuration;

public class KilledPlayer implements Listener {

	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		Player killer = e.getEntity().getKiller();
		String playermessage;
		FileConfiguration fc = Main.plugin.getConfig();
		
		if (killer instanceof Player && fc.getBoolean("Mobcash.PVP.Enabled")) {
			Player victim = e.getEntity();

			Double gmax = fc.getDouble("Mobcash.PVP.Killer-takes.max");
			Double gmin = fc.getDouble("Mobcash.PVP.Killer-takes.min");
			Double gained = Math.abs(PlMath.setRandomCash(gmin, gmax));
			
			Double killedHas = Main.economy.getBalance(victim);
			
			if (killedHas < gained) gained = killedHas;
			
			if (fc.getBoolean("Mobcash.PVP.Messages-Enabled")) {
				playermessage = Format.playerkill(fc.getString("Mobcash.PVP.KillerMessage"), killer, victim, gained);
				Format.sendMessage(playermessage, killer, Configuration.getMessageType());
				
				playermessage = Format.playerkill(fc.getString("Mobcash.PVP.VictimMessage"), killer, victim, gained);
				Format.sendMessage(playermessage, victim, Configuration.getMessageType());
			}
			 if (gained != 0) {
				EconomyDealer.addMoney(killer, gained);
				EconomyDealer.takeMoney(victim, gained);
			}
		}
		
	}
	
}
