package me.element.customdeathmessages.listeners;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.element.customdeathmessages.CustomDeathMessages;
import me.element.customdeathmessages.enums.VersionEnums;
import me.element.customdeathmessages.other.HexChat;

public class PlayerDeathListener implements Listener {

	public CustomDeathMessages plugin;

	public PlayerDeathListener (CustomDeathMessages plugin) 
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerDeath (PlayerDeathEvent event) 
	{
		Player victim = event.getEntity();
		Player killer = event.getEntity().getKiller();
		Location playerLocation = victim.getLocation();

		if (victim instanceof Player && killer instanceof Player && plugin.getConfig().getBoolean("enable-pvp-messages"))
		{
			victim.sendMessage(HexChat.translateHexCodes(plugin.getConfig().getString("victim-message")
					.replace("%killer%", victim.getName())
					.replace("%killer-nick%", victim.getDisplayName())
					.replace("%victim-x%", String.valueOf(victim.getLocation().getBlockX()))
					.replace("%victim-y%", String.valueOf(victim.getLocation().getBlockY()))
					.replace("%victim-z%", String.valueOf(victim.getLocation().getBlockZ()))
					.replace("%killer-x%", String.valueOf(killer.getLocation().getBlockX()))
					.replace("%killer-y%", String.valueOf(killer.getLocation().getBlockY()))
					.replace("%killer-z%", String.valueOf(killer.getLocation().getBlockZ())), plugin));


			killer.sendMessage(HexChat.translateHexCodes(plugin.getConfig().getString("killer-message")
					.replace("%victim%", victim.getName())
					.replace("%victim-nick%", victim.getDisplayName()), plugin));
		}

		double percent = plugin.getConfig().getDouble("drop-head-percentage"); // percent to drop head
		{
			Random rand = new Random();
			double randomDouble = rand.nextDouble(); // used to see if percent is bigger

			if (randomDouble <= percent) { // calculate to drop or not

				String headName = HexChat.translateHexCodes(plugin.getConfig().getString("head-name")
								.replace("%victim%", victim.getName()), plugin);

				if (plugin.getConfig().getBoolean("developer-mode")) // for debugging
				{
					Bukkit.broadcastMessage("HEAD DROPPED");
				}

				if (plugin.getServerVersion().getVersionInt() >= VersionEnums.VERSION_113.getVersionInt()) // server is version 1.13 or above
				{
					ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setOwningPlayer(victim);
					meta.setDisplayName(headName);
					skull.setItemMeta(meta);
					playerLocation.getWorld().dropItemNaturally(victim.getLocation(), skull);
				}
				else
				{
					@SuppressWarnings("deprecation") // suppressing warning because old version requires deprecated constructor
					ItemStack skull = new ItemStack(Material.matchMaterial("SKULL_ITEM"), 1, (byte) 3); // must be string due to fnf error
					SkullMeta meta = (SkullMeta) skull.getItemMeta();
					meta.setOwningPlayer(victim);
					meta.setDisplayName(headName);
					skull.setItemMeta(meta);
					playerLocation.getWorld().dropItemNaturally(victim.getLocation(), skull);
				}
			}
		}

		if (plugin.getConfig().getBoolean("do-lightning")) 
		{
			playerLocation.getWorld().strikeLightningEffect(playerLocation);
		}

		if (plugin.getConfig().getBoolean("enable-global-messages")) 
		{
			if (killer instanceof Player)
			{
				ItemStack killWeapon = getKillWeapon(killer);

				if (killWeapon.getType() != Material.AIR)
				{
					if (killWeapon.getItemMeta().hasDisplayName())
					{
						String weaponName = killWeapon.getItemMeta().getDisplayName();
						Random rand = new Random();
						List<String> msgs = plugin.getConfig().getStringList("global-pvp-death-messages");

						String msg = msgs.get(rand.nextInt(msgs.size()))
								.replace("%victim%", victim.getName())
								.replace("%victim-nick%", victim.getDisplayName())
								.replace("%killer%", killer.getName())
								.replace("%killer-nick%", killer.getDisplayName())
								.replace("%kill-weapon%", weaponName)
								.replace("%victim-x%", String.valueOf(victim.getLocation().getBlockX()))
								.replace("%victim-y%", String.valueOf(victim.getLocation().getBlockY()))
								.replace("%victim-z%", String.valueOf(victim.getLocation().getBlockZ()))
								.replace("%killer-x%", String.valueOf(killer.getLocation().getBlockX()))
								.replace("%killer-y%", String.valueOf(killer.getLocation().getBlockY()))
								.replace("%killer-z%", String.valueOf(killer.getLocation().getBlockZ()));

						msg = HexChat.translateHexCodes(msg, plugin);
						event.setDeathMessage(msg);

					} else 

					{
						String weaponName = WordUtils.capitalize(killWeapon.getType().name().replaceAll("_", " ").toLowerCase());
						Random rand = new Random();
						List<String> msgs = plugin.getConfig().getStringList("global-pvp-death-messages");

						String msg = msgs.get(rand.nextInt(msgs.size()))
								.replace("%victim%", victim.getName())
								.replace("%killer%", killer.getName())
								.replace("%kill-weapon%", weaponName)
								.replace("%victim-x%", String.valueOf(victim.getLocation().getBlockX()))
								.replace("%victim-y%", String.valueOf(victim.getLocation().getBlockY()))
								.replace("%victim-z%", String.valueOf(victim.getLocation().getBlockZ()))
								.replace("%killer-x%", String.valueOf(killer.getLocation().getBlockX()))
								.replace("%killer-y%", String.valueOf(killer.getLocation().getBlockY()))
								.replace("%killer-z%", String.valueOf(killer.getLocation().getBlockZ()));
						
						msg = HexChat.translateHexCodes(msg, plugin);
						
						event.setDeathMessage(msg);
					}
				}	
				else
				{
					Random rand = new Random();
					List<String> msgs = plugin.getConfig().getStringList("melee-death-messages");

					String msg = msgs.get(rand.nextInt(msgs.size()))
							.replace("%victim%", victim.getName())
							.replace("%killer%", killer.getName())
							.replace("%victim-x%", String.valueOf(victim.getLocation().getBlockX()))
							.replace("%victim-y%", String.valueOf(victim.getLocation().getBlockY()))
							.replace("%victim-z%", String.valueOf(victim.getLocation().getBlockZ()))
							.replace("%killer-x%", String.valueOf(killer.getLocation().getBlockX()))
							.replace("%killer-y%", String.valueOf(killer.getLocation().getBlockY()))
							.replace("%killer-z%", String.valueOf(killer.getLocation().getBlockZ()));

					msg = HexChat.translateHexCodes(msg, plugin);
					event.setDeathMessage(msg);
				}
			} 
			else
			{
				int versionInt = plugin.getServerVersion().getVersionInt();

				DamageCause cause = victim.getLastDamageCause().getCause();
				String path = null;

				if (cause == DamageCause.CUSTOM) {
					path = "unknown-messages";
				} else if (cause == DamageCause.FALL) {
					path = "fall-damage-messages";
				} else if (cause == DamageCause.DROWNING) { // added before supported v
					path = "drowning-messages";
				} else if (cause == DamageCause.LAVA) { // added before supported v
					path = "lava-messages";   		
				} else if (cause == DamageCause.SUFFOCATION) { // added before supported v
					path = "suffocation-messages";
				} else if (cause == DamageCause.WITHER) { // added before supported v
					path = "wither-messages";
				} else if (cause == DamageCause.FIRE_TICK) { // added before supported v
					path = "fire-tick-messages";
				} else if (cause == DamageCause.FIRE) { // added before supported v
					path = "fire-messages";
				} else if (cause == DamageCause.STARVATION) { // added before supported v
					path = "starvation-messages";
				} else if (cause == DamageCause.CONTACT) { // added before supported v
					path = "cactus-messages";
				} else if (cause == DamageCause.MAGIC) { // added before supported v
					path = "potion-messages";
				} else if (cause == DamageCause.VOID) { // added before supported v
					path = "void-messages";
				} else if (cause == DamageCause.LIGHTNING) { // added before supported v
					path = "lightning-messages";
				} else if (versionInt >= VersionEnums.VERSION_19.getVersionInt() && cause == DamageCause.FLY_INTO_WALL) { // 1.9
					path = "elytra-messages";
				} else if (versionInt >= VersionEnums.VERSION_110.getVersionInt() && cause == DamageCause.HOT_FLOOR) { // 1.10
					path = "magma-block-messages";
				} else if (cause == DamageCause.SUICIDE) { // added before supported v
					path = "suicide-messages";
				} 

				if (path == null)
				{
					if (plugin.deathMessage.get(victim.getName()) != null)
					{
						String msg = HexChat.translateHexCodes(plugin.deathMessage.get(victim.getName()), plugin);
						plugin.deathMessage.clear();

						event.setDeathMessage(msg);
					}
					else
					{
						Random rand = new Random();
						List<String> msgs = plugin.getConfig().getStringList("unknown-messages");
						String msg = msgs.get(rand.nextInt(msgs.size()))
								.replace("%victim%", victim.getName())
								.replace("%victim-nick%", victim.getDisplayName())
								.replace("%victim-x%", String.valueOf(victim.getLocation().getBlockX()))
								.replace("%victim-y%", String.valueOf(victim.getLocation().getBlockY()))
								.replace("%victim-z%", String.valueOf(victim.getLocation().getBlockZ()));
						msg = HexChat.translateHexCodes(msg, plugin);
						event.setDeathMessage(msg);
					}
				}
				else
				{
					Random rand = new Random();
					List<String> msgs = plugin.getConfig().getStringList(path);
					String msg = msgs.get(rand.nextInt(msgs.size()))
							.replace("%victim%", victim.getName())
							.replace("%victim-nick%", victim.getDisplayName())
							.replace("%victim-x%", String.valueOf(victim.getLocation().getBlockX()))
							.replace("%victim-y%", String.valueOf(victim.getLocation().getBlockY()))
							.replace("%victim-z%", String.valueOf(victim.getLocation().getBlockZ()));
					msg = HexChat.translateHexCodes(msg, plugin);
					event.setDeathMessage(msg);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public ItemStack getKillWeapon(Player killer)
	{
		if (plugin.getServerVersion().getVersionInt() >= VersionEnums.VERSION_19.getVersionInt())
		{
			return killer.getInventory().getItemInMainHand();
		}
		else
		{
			return killer.getInventory().getItemInHand();
		}
	}
}
