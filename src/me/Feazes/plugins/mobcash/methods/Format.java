package me.Feazes.plugins.mobcash.methods;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.Feazes.plugins.mobcash.Main;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.ChatMessageType;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_16_R3.PacketPlayOutChat;
public class Format {
	
	public enum MessageType {
		ACTONBAR,
		MESSAGE;
		
		public static MessageType fromString(String input) {
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
	}
	
	public static String color(String message) {
		try {
			final Pattern pattern = Pattern.compile("&#[A-Fa-f0-9]{6}");
			Matcher match = pattern.matcher(message);
			while (match.find()) {
				String color = message.substring(match.start(),match.end());
				message= message.replace(color,ChatColor.of(color.replace("&#","#"))+"");
				match=pattern.matcher(message);
			}
			return  ChatColor.translateAlternateColorCodes('&', message);
		}
		catch (NullPointerException e)
		{
			return "";
		}
	}	

	public static String playerkill(String str, Player killer, Player victim, double money) {
		return color(str).replace("<killer>", killer.getName())
				.replace("<victim>", victim.getName())
				.replace("<money>", money+"");
	}
	
	public static String mobkill(String str, Player player, String et, String money) {
		String colored = color(str);
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
	    PacketPlayOutChat bar = new PacketPlayOutChat(barmsg, ChatMessageType.GAME_INFO, p.getUniqueId());
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
			sendActionBar(player, message);
			break;
		}	
	}
	
	
}
