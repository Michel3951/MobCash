package me.Feazes.plugins.mobcash.methods;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.Feazes.plugins.mobcash.Main;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;

public class Format {
	
	enum MessageType {
		ACTONBAR,
		MESSAGE
	}
	
	public static MessageType getMessageTypeFromString(String input) {
		switch (input) {
		case "MESSAGE":
			return MessageType.MESSAGE;
		case "ACTIONBAR":
			return MessageType.ACTONBAR;	
		default:
			Main.plugin.getLogger().warning("MessageType shoud be MESSAGE or ACTIONBAR. Currently: \"" + input+"\"! Seting to ACTIONBAR.");
			return MessageType.ACTONBAR;
		}
	}
	
	public static String color(String str) {
		String colored = ChatColor.translateAlternateColorCodes('&', str);
		return colored;
	}
	
	
	public static String playerkill(String str, Player killer, Player victim, String money) {
		String colored = color(str);
		String edit1 = colored.replace("<killer>", killer.getName());
		String edit2 = edit1.replace("<victim>", victim.getName());
		String finish1 = edit2.replace("<money>", money);
		
		return finish1;
	}
	
	public static String mobkill(String str, Player player, String et, String money) {
		
		String colored = ChatColor.translateAlternateColorCodes('&', str);
		String edit1 = colored.replace("<player>", player.getName());
		String edit2 = edit1.replace("<mob>", et);
		String finish = edit2.replace("<money>", money);
		return finish;
	}
	
	public static String toJSON(String text) {
		return "{\"text\":\"" + text + "\"}";
	}
	
	static void sendActionBar(Player p, String message){
		IChatBaseComponent barmsg = ChatSerializer.a(toJSON(message));
	    PacketPlayOutChat bar = new PacketPlayOutChat(barmsg, ChatMessageType.GAME_INFO);
	    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
	}
	
	public static void sendMessage(String message, Player player, MessageType type) {
		switch (type) {
		case ACTONBAR:
			sendActionBar(player, message);
			break;
			
		case MESSAGE:
			player.sendMessage(message);
			break;
			
		default:
			Main.plugin.getLogger().warning("MessageType shoud be MESSAGE or ACTIONBAR. Currently: \"" + type+"\"! Seting to ACTIONBAR.");
			sendActionBar(player, message);
			break;
		}	
	}
	
	
}
