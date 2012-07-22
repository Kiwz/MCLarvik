package net.kiwz.mclarvik.utils;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class StopServer {
	
	public static void stopServer() {
		Server server = Bukkit.getServer();
		Player[] players = server.getOnlinePlayers();
		for (Player player : players) {
			player.kickPlayer("Dont worry! It is just a restart. Come back in a few minutes");
		}
		server.shutdown();
	}

}
