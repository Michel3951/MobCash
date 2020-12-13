package me.Feazes.plugins.mobcash.Metrics;

import java.io.IOException;

import me.Feazes.plugins.mobcash.Main;

public class TryMetrics {

public static Main plugin;
	
	public TryMetrics(Main instance) {
		plugin = instance;
	}
	
	public void tryMetrics() {
		
		try {
	        Metrics metrics = new Metrics(plugin);
	        metrics.start();
            plugin.getLogger().info("Now tracking stats!");
	    } catch (IOException e) {
	        // Failed to submit the stats :-(
	    }
		
	}
	
}
