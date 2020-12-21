package eu.leopoldhauptman.mobcash;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import eu.leopoldhauptman.mobcash.Metrics.MetricsHandler;
import eu.leopoldhauptman.mobcash.commands.MobCash;
import eu.leopoldhauptman.mobcash.listeners.KilledPlayer;
import eu.leopoldhauptman.mobcash.listeners.MobKillAndSpawn;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main plugin;

	public static Economy economy;
	public static List<UUID> spawnedMobs = new ArrayList<UUID>();

	@Override
	public void onEnable() {
		try {
			plugin = this;
			getLogger().info(" has been enabled!");
			getConfig().options().copyDefaults();
			saveDefaultConfig();
			new Configuration().configVersionCheck(8.2);
			
			setupEconomy();

			PluginManager pm = Bukkit.getPluginManager();
			
			pm.registerEvents(new KilledPlayer(), Main.plugin);
			pm.registerEvents(new MobKillAndSpawn(), Main.plugin);
			
			getCommand("mobcash").setExecutor(new MobCash());

			new MetricsHandler();

		}
		catch (NullPointerException e) {
			this.getLogger().warning("Something went wrong :( !");
			this.getLogger().warning("Disabling...");
		}
	}
	
	@Override
	public void onDisable() {
		plugin = null;
	}
		

	private boolean setupEconomy()
    {	
        RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        
        if (economyProvider != null) {        
        		economy = economyProvider.getProvider();
        	
        	if(economy == null) {
        		this.getLogger().warning("Vault doesn't have an economy plugin connected.");
        		this.getServer().getPluginManager().disablePlugin(this); 
        	}   		
        	else {
				this.getLogger().info("Vault has made economy available for this plugin.");
            }
        	  return (economy != null);
        }
        else {
        	this.getLogger().warning("Vault doesn't have an economy plugin connected.");
        	this.getServer().getPluginManager().disablePlugin(this);
    		return (economy == null);
        }
      
    }

}
