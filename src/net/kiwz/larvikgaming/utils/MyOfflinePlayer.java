package net.kiwz.larvikgaming.utils;

import java.io.File;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.ItemInWorldManager;
import net.minecraft.server.MinecraftServer;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Player;

public class MyOfflinePlayer {
	/**
	 * Gets a player object by the given username
	 * <p />
	 * This method may not return objects for players that never played on this server before
	 * 
	 * @param name Name to look up
	 * 
	 * @return Player as long as the player has played here before, otherwise null
	 */
    public Player getOfflinePlayer(String name) {
    	File playerfolder = null;
        Player player = null;
        Player onlinePlayer = Bukkit.getServer().getPlayer(name);
        if (onlinePlayer != null) {
        	player = onlinePlayer;
            return player;
        }
        try {
            playerfolder = new File(Bukkit.getWorlds().get(0).getWorldFolder(), "players");
        } catch (Exception e) {
        }
        for (File playerfile : playerfolder.listFiles()) {
            String filename = playerfile.getName();
            String playername = filename.substring(0, filename.length() - 4);

            if (playername.trim().equalsIgnoreCase(name)) {
                final MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
                final EntityPlayer entity = new EntityPlayer(server, server.getWorldServer(0), playername, new ItemInWorldManager(server.getWorldServer(0)));
                player = (entity == null) ? null : (Player) entity.getBukkitEntity();
                if (player != null) {
                	player.loadData();
                }
            }
        }
        return player;
    }
}