package me.Feazes.plugins.mobcash.Reason;

import me.Feazes.plugins.mobcash.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class Breeding  implements Listener{
	
	public static Main plugin;

	@EventHandler
	public void SpawnEvent(CreatureSpawnEvent e) {
		if (e.getSpawnReason() == SpawnReason.BREEDING) {
			if (Main.plugin.getConfig().getBoolean("Mobcash.Disable.Breeding")) {
				if (!Main.plugin.spawnedMobs.contains(e.getEntity().getUniqueId())) {
					Main.plugin.spawnedMobs.add(e.getEntity().getUniqueId());
				}
			}
		}
	}
}
