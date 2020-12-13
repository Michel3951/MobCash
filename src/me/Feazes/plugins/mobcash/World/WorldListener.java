package me.Feazes.plugins.mobcash.World;


import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import me.Feazes.plugins.mobcash.Main;

public class WorldListener implements Listener{

public static Main plugin;


	@EventHandler
	public void spawnEvent(CreatureSpawnEvent e) {
		

		
		World w = e.getLocation().getWorld();
		if (!Main.plugin.getConfig().getList("Mobcash.Worlds").contains(w.getName()) & e.getLocation() != null && e.getEntity() != null) {
			if (!Main.plugin.spawnedMobs.contains(e.getEntity().getUniqueId())) {
				Main.plugin.spawnedMobs.add(e.getEntity().getUniqueId());
			}
		}
	}
}
