package eu.leopoldhauptman.mobcash.Metrics;

import eu.leopoldhauptman.mobcash.Main;


public class MetricsHandler {
    public MetricsHandler() {
        new Metrics(Main.plugin, 9714);
        // You can find the plugin ids of your plugins on the page https://bstats.org/what-is-my-plugin-id
    }
}
