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

public class KilledPlayer implements Listener {
	
	public static Main plugin;


	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		Player killer = e.getEntity().getKiller();
		Player victim = e.getEntity();
		EconomyDealer ed = new EconomyDealer();
		PlMath pm = new PlMath();
		String playermessage;
		FileConfiguration fc = Main.plugin.getConfig();
		
		if (killer instanceof Player) {
			
			boolean enabled = fc.getBoolean("Mobcash.Mobs.Player.Set-enabled");
			boolean isKillerMessageEnabled = fc.getBoolean("Mobcash.Mobs.Player.Killer-Message.Enabled");
			boolean isVictimMessageEnabled = fc.getBoolean("Mobcash.Mobs.Player.Victim-Message.Enabled");
			Double gmax = fc.getDouble("Mobcash.Mobs.Player.Killer-recieves.max");
			Double gmin = fc.getDouble("Mobcash.Mobs.Player.Killer-recieves.min");
			Double gained = pm.setRandomCash(gmin, gmax);
			
			Double lmax = fc.getDouble("Mobcash.Mobs.Player.Victim-loses.max");
			Double lmin = fc.getDouble("Mobcash.Mobs.Player.Victim-loses.min");
			Double lost = pm.setRandomCash(lmin, lmax);
			
			Double killedHas = Main.economy.getBalance(victim);
			
			if (killedHas < lost) {
				lost = killedHas;
				gained = killedHas;
			}
			
			String g =String.valueOf(gained);
			String l =String.valueOf(lost);	
			
			if (enabled == true && !(lost == 0 && gained == 0)) {
				if (isKillerMessageEnabled == true) {
					playermessage = Format.playerkill(fc.getString("Mobcash.Mobs.Player.Killer-Message.Message"), killer, victim, g);
					Format.sendMessage(playermessage, killer, Format.getMessageTypeFromString(fc.getString("Mobcash.Mobs.Player.Killer-Message.Message-Type")));
				}
				if (isVictimMessageEnabled == true) {
					playermessage = Format.playerkill(fc.getString("Mobcash.Mobs.Player.Victim-Message.Message"), killer, victim, l);
					Format.sendMessage(playermessage, victim, Format.getMessageTypeFromString(fc.getString("Mobcash.Mobs.Player.Killer-Message.Message-Type")));
				}
				
				//Money
				
				 if (gained != 0 || lost != 0) {
					
					if (gained != 0) {
						ed.addMoney(killer, gained);
					}
					
					if (lost != 0) {
				ed.takeMoney(victim, lost);
					}
				}
				
				
			}
			
		}
		
	}
	
}
