package me.Feazes.plugins.mobcash;

public class KillAmount {

public static Main plugin;
	
	public KillAmount(Main pl) {
		plugin = pl;
	}
	
	public static Double getMoney(String m) {
		if (plugin.getConfig().getBoolean("Mobcash.Mobs.Group Setting.Enabled") == true) {
			if (plugin.getConfig().contains("Mobcash.Mobs.Friendly Mobs." + m)) {
				int d = plugin.getConfig().getInt("Mobcash.Mobs.Group Settings.Friendly Mobs");
				return (double)d;
			} else if (plugin.getConfig().contains("Mobcash.Mobs.Agressive Mobs." + m)) {
				int d = plugin.getConfig().getInt("Mobcash.Mobs.Group Settings.Agressive Mobs");
				return (double)d;
			} else if (plugin.getConfig().contains("Mobcash.Mobs.Bosses." + m)) {
				int d = plugin.getConfig().getInt("Mobcash.Mobs.Group Settings.Bosses");
				return (double)d;
			}
			return null;
		} else if (plugin.getConfig().getBoolean("Mobcash.Mobs.Group Setting.Enabled") == false) {
			if (plugin.getConfig().contains("Mobcash.Mobs.Friendly Mobs." + m)) {
				int d = plugin.getConfig().getInt("Mobcash.Mobs.Friendly Mobs." + m);
				return (double)d;
			} else if (plugin.getConfig().contains("Mobcash.Mobs.Agressive Mobs." + m)) {
				int d = plugin.getConfig().getInt("Mobcash.Mobs.Agressive Mobs." + m);
				return (double)d;
			} else if (plugin.getConfig().contains("Mobcash.Mobs.Bosses." + m)) {
				int d = plugin.getConfig().getInt("Mobcash.Mobs.Bosses." + m);
				return (double)d;
			}
			return null;
		}
		return null;
	}
}
