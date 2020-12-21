package eu.leopoldhauptman.mobcash.methods;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.leopoldhauptman.mobcash.enums.MessageType;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Format {
	
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
		return edit2.replace("<money>", money);
	}

	public static void sendMessage(String message, Player player, MessageType type) {
		if (type == MessageType.MESSAGE) player.sendMessage(message);
		else player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
	}
	
	
}
