package me.Feazes.plugins.mobcash.Reason;

import me.Feazes.plugins.mobcash.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class Egg  implements Listener{
	
	public static Main plugin;
	
	@EventHandler
	public void spawnEvent(CreatureSpawnEvent e) {
		if (e.getSpawnReason() == SpawnReason.SPAWNER_EGG) {
			if (Main.plugin.getConfig().getBoolean("Mobcash.Disable.Spawn Egg") == true) {
				if (!Main.plugin.spawnedMobs.contains(e.getEntity().getUniqueId())) {
					Main.plugin.spawnedMobs.add(e.getEntity().getUniqueId());
				}
			}
		}
	}
}
