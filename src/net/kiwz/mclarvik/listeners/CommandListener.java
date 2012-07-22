package net.kiwz.mclarvik.listeners;

import net.kiwz.mclarvik.logs.CmdLogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		String player = e.getPlayer().getName();
		String cmd = e.getMessage();
		
		CmdLogger cl = new CmdLogger();
		cl.cmdLogger1(player, cmd);
		cl.cmdLogger2(player, cmd);
		cl.privMsgLogger(player, cmd);
	}
}