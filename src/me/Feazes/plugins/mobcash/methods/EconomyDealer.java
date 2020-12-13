package me.Feazes.plugins.mobcash.methods;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.Feazes.plugins.mobcash.Main;

public class EconomyDealer {
	public void addMoney(Player player, Double money) {
		Main.economy.depositPlayer((OfflinePlayer)player,money);		
	}
	
	public void takeMoney(OfflinePlayer player, Double money) {
		Main.economy.withdrawPlayer((OfflinePlayer)player.getPlayer(), money);
	}
	
}
