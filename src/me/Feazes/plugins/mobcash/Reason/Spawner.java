package me.Feazes.plugins.mobcash.Reason;


import me.Feazes.plugins.mobcash.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class Spawner  implements Listener{
	
	public static Main plugin;

	@EventHandler
	public void spawnEvent(CreatureSpawnEvent e) {
		if (e.getSpawnReason() == SpawnReason.SPAWNER) {
			if (Main.plugin.getConfig().getBoolean("Mobcash.Disable.Mob Spawners") == true) {
				if (!Main.plugin.spawnedMobs.contains(e.getEntity().getUniqueId())) {
					Main.plugin.spawnedMobs.add(e.getEntity().getUniqueId());
				}
			}
		}
	}
}
