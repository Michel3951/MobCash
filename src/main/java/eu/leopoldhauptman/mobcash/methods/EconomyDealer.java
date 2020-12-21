package eu.leopoldhauptman.mobcash.methods;

import eu.leopoldhauptman.mobcash.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;


public class EconomyDealer {
	public static void addMoney(Player player, Double money) {
		Main.economy.depositPlayer((OfflinePlayer)player,money);		
	}
	
	public static void takeMoney(OfflinePlayer player, Double money) {
		Main.economy.withdrawPlayer((OfflinePlayer)player.getPlayer(), money);
	}
	public static double getMoney(OfflinePlayer p) {
		return Main.economy.getBalance(p);
	}
}
