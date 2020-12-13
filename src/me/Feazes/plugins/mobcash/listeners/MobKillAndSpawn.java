package me.Feazes.plugins.mobcash.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import me.Feazes.plugins.mobcash.Main;
import me.Feazes.plugins.mobcash.methods.EconomyDealer;
import me.Feazes.plugins.mobcash.methods.Format;
import me.Feazes.plugins.mobcash.methods.Format.MessageType;
import me.Feazes.plugins.mobcash.methods.PlMath;

public class MobKillAndSpawn implements Listener {

@EventHandler
public void playerKill(EntityDeathEvent e) {
		if (Main.plugin.getConfig().getList("Mobcash.Worlds").contains(e.getEntity().getWorld().getName())) return;
		Entity p = e.getEntity().getKiller();
		if (p instanceof Player) {
			Entity ent = e.getEntity();
			if (!Main.spawnedMobs.contains(ent.getUniqueId())) {
				String mobName = e.getEntityType().name().toLowerCase();
				if (mobName == null) return;
				
				FileConfiguration fc = Main.plugin.getConfig();
				Player player = (Player)p;
				
				double cash = 0;
		        double min = 0;
		        double max = 0;
		        
		        if (fc.contains("Mobcash.Mobs.CustomMobSettings." + mobName)) {
		        	min = fc.getDouble("Mobcash.Mobs.CustomMobSettings."+mobName+".min");
					max = fc.getDouble("Mobcash.Mobs.CustomMobSettings."+mobName+".max");
		        }
		        else if (ent instanceof Creature) {
		        	if (ent instanceof Monster) {
		        		min = fc.getDouble("Mobcash.Mobs.GroupSettings.Bosses.min");
						max = fc.getDouble("Mobcash.Mobs.GroupSettings.Bosses.max");
		        	}
		        	else if (ent instanceof Boss) {
		        		min = fc.getDouble("Mobcash.Mobs.GroupSettings.Monsters.min");
						max = fc.getDouble("Mobcash.Mobs.GroupSettings.Monsters.max");
		        	}
		        	else {
		        		min = fc.getDouble("Mobcash.Mobs.GroupSettings.PassiveMobs.min");
						max = fc.getDouble("Mobcash.Mobs.GroupSettings.PassiveMobs.max");
		        	}
		        }
		        else return;
		        
		        cash = PlMath.setRandomCash(min, max);
				
				if (cash > 0.0) {
					EconomyDealer.addMoney(player, cash);
                    if (fc.getBoolean("Mobcash.Message-enabled")) {
	                   	String playermessage = Format.mobkill(fc.getString("Mobcash.Message"),player, mobName, cash+"");
	                   	Format.sendMessage(playermessage, player,MessageType.fromString(fc.getString("Mobcash.Message-Type")));
                    }
				}
			}
		}
	
	}

	@EventHandler
	public void SpawnEvent(CreatureSpawnEvent e) {
		if (Main.plugin.getConfig().getStringList("").contains(e.getSpawnReason().name().toLowerCase())
		&& !Main.spawnedMobs.contains(e.getEntity().getUniqueId())) Main.spawnedMobs.add(e.getEntity().getUniqueId());
		
	}
}