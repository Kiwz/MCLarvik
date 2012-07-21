package net.kiwz.mclarvik.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.Plugin;

public class KickListener implements Listener {

	Plugin mcLarvik = Bukkit.getServer().getPluginManager().getPlugin("MCLarvik");
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e) {
		Player player = e.getPlayer();
		if (mcLarvik.getConfig().getBoolean("LightningOnKick", true)) {
			player.getWorld().strikeLightning(player.getLocation());
		}
	}
}
