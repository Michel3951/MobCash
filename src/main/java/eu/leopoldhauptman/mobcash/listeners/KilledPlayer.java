package eu.leopoldhauptman.mobcash.listeners;

import eu.leopoldhauptman.mobcash.Configuration;
import eu.leopoldhauptman.mobcash.Main;
import eu.leopoldhauptman.mobcash.enums.RewardType;
import eu.leopoldhauptman.mobcash.methods.EconomyDealer;
import eu.leopoldhauptman.mobcash.methods.Format;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KilledPlayer implements Listener {

	@EventHandler
	public void onKill(PlayerDeathEvent e) {
		Player killer = e.getEntity().getKiller();
		String playermessage;
		ConfigurationSection fc = Main.plugin.getConfig().getConfigurationSection("Mobcash.PVP");
		
		if (killer instanceof Player && fc.getBoolean("Enabled")) {
			Player victim = e.getEntity();
			
			double gained = RewardType.getReward(fc.getConfigurationSection("Killer-takes"), true, victim);
			
			Double killedHas = Main.economy.getBalance(victim);
			
			if (killedHas < gained) gained = killedHas;
			
			if (fc.getBoolean("Messages-Enabled")) {
				playermessage = Format.playerkill(fc.getString("KillerMessage"), killer, victim, gained);
				Format.sendMessage(playermessage, killer, Configuration.getMessageType());
				
				playermessage = Format.playerkill(fc.getString("VictimMessage"), killer, victim, gained);
				Format.sendMessage(playermessage, victim, Configuration.getMessageType());
			}
			 if (gained != 0) {
				EconomyDealer.addMoney(killer, gained);
				EconomyDealer.takeMoney(victim, gained);
			}
		}
		
	}
	
}
