package me.Feazes.plugins.mobcash.Metrics;

import java.io.IOException;

import me.Feazes.plugins.mobcash.Main;

public class TryMetrics {
	
	public static void tryMetrics() {
		
		try {
	        Metrics metrics = new Metrics(Main.plugin);
	        metrics.start();
	        Main.plugin.getLogger().info("Now tracking stats!");
	    } catch (IOException e) {
	        // Failed to submit the stats :-(
	    }
		
	}
	
}
