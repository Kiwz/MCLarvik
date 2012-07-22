package net.kiwz.mclarvik.listeners;

import net.kiwz.mclarvik.logs.ChatLogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener implements Listener {
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {
		String player = e.getPlayer().getName();
		String message = e.getMessage();
		
		ChatLogger cl = new ChatLogger();
		cl.chatLogger(player, message);
	}
}