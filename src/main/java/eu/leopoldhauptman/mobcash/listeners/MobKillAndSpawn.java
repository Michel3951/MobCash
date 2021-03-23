package eu.leopoldhauptman.mobcash.listeners;

import eu.leopoldhauptman.mobcash.Main;
import eu.leopoldhauptman.mobcash.enums.MessageType;
import eu.leopoldhauptman.mobcash.enums.RewardType;
import eu.leopoldhauptman.mobcash.methods.EconomyDealer;
import eu.leopoldhauptman.mobcash.methods.Format;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Arrays;
import java.util.List;

public class MobKillAndSpawn implements Listener {

@EventHandler
public void playerKill(EntityDeathEvent e) {
		if (Main.plugin.getConfig().getList("Mobcash.DisabledWorlds").contains(e.getEntity().getWorld().getName())) return;
		Entity p = e.getEntity().getKiller();
		if (p instanceof Player) {
			Entity ent = e.getEntity();
			if (!Main.spawnedMobs.contains(ent.getUniqueId())) {
				String mobName = e.getEntityType().name().toLowerCase();
				ConfigurationSection f = Main.plugin.getConfig().getConfigurationSection("Mobcash");
				ConfigurationSection fc = f.getConfigurationSection("Mobs");
				ConfigurationSection fc2;
				Player player = (Player)p;
				DamageCause cause = e.getEntity().getLastDamageCause().getCause();

				DamageCause[] prohibited = {
						DamageCause.CRAMMING,
					DamageCause.FALL
				};

				List<DamageCause> l = Arrays.asList(prohibited);

				if (l.contains(cause)) {
					return;
				}

		        
		        if (fc.contains("CustomMobSettings." + mobName)) fc2 = fc.getConfigurationSection("CustomMobSettings." + mobName);
		        
		        else if (ent instanceof Creature || ent.getType()== EntityType.ENDER_DRAGON) {
		        	if (ent instanceof Boss) fc2 = fc.getConfigurationSection("GroupSettings.Bosses");
		        	else if (ent instanceof Monster)fc2 = fc.getConfigurationSection("GroupSettings.Monsters");
		        	else fc2 = fc.getConfigurationSection("GroupSettings.PassiveMobs");	
		        }
		        else return;
		        
		        double cash = RewardType.getReward(fc2, false, null);
			        
				if (cash > 0.0) {
					EconomyDealer.addMoney(player, cash);
                    if (f.getBoolean("Message-enabled")) {
	                   	String playermessage = Format.mobkill(f.getString("Message"),player, mobName, cash+"");
	                   	Format.sendMessage(playermessage, player, MessageType.fromString(f.getString("Message-Type")));
                    }
				}
			}
			else Main.spawnedMobs.remove(e.getEntity().getUniqueId());
		}
	
	}

	@EventHandler
	public void SpawnEvent(CreatureSpawnEvent e) {
		if (Main.plugin.getConfig().getStringList("Mobcash.Disable-Spawns").contains(e.getSpawnReason().name().toLowerCase())
		&& !Main.spawnedMobs.contains(e.getEntity().getUniqueId())) Main.spawnedMobs.add(e.getEntity().getUniqueId());
	}
}