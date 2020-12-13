package me.Feazes.plugins.mobcash;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.Feazes.plugins.mobcash.Metrics.TryMetrics;
import me.Feazes.plugins.mobcash.commands.RegisterCommands;
import me.Feazes.plugins.mobcash.listeners.RegisterListeners;
import me.Feazes.plugins.mobcash.warnings.ConfigVersion;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	public static Main plugin;

	public static Economy economy = null;
	String version = "7.2";
	public List<UUID> spawnedMobs = new ArrayList<UUID>();


	
	@Override
	public void onEnable() {
		try {
			plugin = this;
			this.getLogger().info(" has been enabled!");
		
			new ConfigVersion().configVersionCheck(version);
			setupEconomy();
			new RegisterListeners();
			new RegisterCommands();
			new TryMetrics(this);
		
			this.getConfig().options().copyDefaults(true);
			saveDefaultConfig();
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
