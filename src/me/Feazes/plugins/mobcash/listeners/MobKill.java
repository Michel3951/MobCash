package me.Feazes.plugins.mobcash.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.Feazes.plugins.mobcash.EntityName;
import me.Feazes.plugins.mobcash.Main;
import me.Feazes.plugins.mobcash.methods.EconomyDealer;
import me.Feazes.plugins.mobcash.methods.Format;
import me.Feazes.plugins.mobcash.methods.PlMath;

public class MobKill implements Listener {
	
public static Main plugin;

public List<UUID> spawnedMobs = new ArrayList<UUID>();

	@EventHandler
public void playerKill(EntityDeathEvent event) {
		Entity et = event.getEntity();
		Entity p = event.getEntity().getKiller();
		PlMath pm = new PlMath();
		FileConfiguration fc = Main.plugin.getConfig();
		if (p instanceof Player) {
			Player player = (Player)p;
				if (!Main.plugin.spawnedMobs.contains(event.getEntity().getUniqueId())) {
					String mobName = EntityName.readEntity(et);
					if (mobName != null) {
                    double cash = 0;
                    double min = 0;
                    double max = 0;
					if (fc.getBoolean("Mobcash.Mobs.Group Settings.Enabled") == true) {
						if (fc.contains("Mobcash.Mobs.Friendly Mobs." + mobName)) {
							
							min = fc.getDouble("Mobcash.Mobs.Group Settings.Friendly Mobs.min");
							max = fc.getDouble("Mobcash.Mobs.Group Settings.Friendly Mobs.max");
							cash = pm.setRandomCash(min, max);
							
						} else if (fc.contains("Mobcash.Mobs.Agressive Mobs." + mobName)) {
							min = fc.getDouble("Mobcash.Mobs.Group Settings.Agressive Mobs.min");
							max = fc.getDouble("Mobcash.Mobs.Group Settings.Agressive Mobs.max");
							cash = pm.setRandomCash(min, max);
							
						} else if (fc.contains("Mobcash.Mobs.Bosses." + mobName)) {
							min = fc.getDouble("Mobcash.Mobs.Group Settings.Bosses.min");
							max = fc.getDouble("Mobcash.Mobs.Group Settings.Bosses.max");
							cash = pm.setRandomCash(min, max);
						}
					} else if (fc.getBoolean("Mobcash.Mobs.Group Settings.Enabled") == false) {
						if (fc.contains("Mobcash.Mobs.Friendly Mobs." + mobName)) {
							min = fc.getDouble("Mobcash.Mobs.Friendly Mobs." + mobName + ".min");
							max = fc.getDouble("Mobcash.Mobs.Friendly Mobs." + mobName + ".max");
							cash = pm.setRandomCash(min, max);
							
						} else if (fc.contains("Mobcash.Mobs.Agressive Mobs." + mobName)) {
							min = fc.getDouble("Mobcash.Mobs.Agressive Mobs." + mobName + ".min");
							max = fc.getDouble("Mobcash.Mobs.Agressive Mobs." + mobName + ".max");
							cash = pm.setRandomCash(min, max);
							
						} else if (fc.contains("Mobcash.Mobs.Bosses." + mobName)) {
							min = fc.getDouble("Mobcash.Mobs.Bosses." + mobName + ".min");
							max = fc.getDouble("Mobcash.Mobs.Bosses." + mobName + ".max");
							cash = pm.setRandomCash(min, max);
						}
						
						
					}						
						 if (cash != 0.0) {
							EconomyDealer ed = new EconomyDealer();
							ed.addMoney(player, cash);
							 
			                    if (fc.getBoolean("Mobcash.Message-enabled")) {
			                    	String str = String.valueOf(cash);
			                    	String pmessage = fc.getString("Mobcash.Message");
			                      
			                   	String playermessage = Format.mobkill(pmessage, player, mobName, str);
			                   	Format.sendMessage(playermessage, player, Format.getMessageTypeFromString(fc.getString("Mobcash.Message-Type")));
			    }
						 
					}
				}
				}
			}

}
}