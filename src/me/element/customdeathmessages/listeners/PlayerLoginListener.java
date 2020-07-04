package me.element.customdeathmessages.listeners;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

import me.element.customdeathmessages.CustomDeathMessages;
import me.element.customdeathmessages.other.CustomConsumer;

public class PlayerLoginListener implements Listener {

	public CustomDeathMessages plugin;

	public PlayerLoginListener (CustomDeathMessages plugin) 
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void onLogin(PlayerLoginEvent event) {

		if (plugin.getConfig().getBoolean("enable-update-messages"))
		{
			new UpdateChecker(plugin, 69605).getVersion(version -> {

				if (!plugin.getDescription().getVersion().equalsIgnoreCase(version.replace("v", ""))) 
				{
					Player player = event.getPlayer();
					if (player.hasPermission("cdm.updates"))
					{
						player.sendMessage(ChatColor.GRAY + "CustomDeathMessages: " + ChatColor.AQUA + "CustomDeathMessages has a newer version available " + ChatColor.WHITE + "(" + version + ")" + ChatColor.AQUA + ". Please update: https://www.spigotmc.org/resources/customdeathmessages-cdm.69605/");
					}
				}
			});
		}
	}

	public class UpdateChecker {

		private Plugin plugin;
		private int resourceId ;

		public UpdateChecker(Plugin plugin, int resourceId) {
			this.plugin = plugin;
			this.resourceId = resourceId;
		}

		public void getVersion(final CustomConsumer<String> consumer) {
			Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
				try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
					if (scanner.hasNext()) {
						consumer.accept(scanner.next());
					}
				} catch (IOException exception) {
					this.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
				}
			});
		}
	}
}
