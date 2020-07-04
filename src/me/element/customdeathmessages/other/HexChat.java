package me.element.customdeathmessages.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.element.customdeathmessages.CustomDeathMessages;
import me.element.customdeathmessages.enums.VersionEnums;
import net.md_5.bungee.api.ChatColor;

public class HexChat {

	private CustomDeathMessages plugin;

	public HexChat(CustomDeathMessages plugin)
	{
		this.plugin = plugin;
	}

	public String translateHexCodes(String startTag, String endTag, String message)
	{
		if (plugin.getServerVersion().getVersionInt() >= VersionEnums.VERSION_116.getVersionInt())
		{
			final Pattern pattern = Pattern.compile(startTag + "(\\w{6})" + endTag);
			
			Matcher matcher = pattern.matcher(ChatColor.translateAlternateColorCodes('&', message));
			StringBuffer buffer = new StringBuffer();

			while (matcher.find()) 
			{
				matcher.appendReplacement(buffer, ChatColor.of('#' + matcher.group(1)).toString());
			}

			return matcher.appendTail(buffer).toString();
		}
		else
		{
			return ChatColor.translateAlternateColorCodes('&', message);
		}
	}
}
