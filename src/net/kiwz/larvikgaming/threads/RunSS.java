package net.kiwz.larvikgaming.threads;

import net.kiwz.larvikgaming.utils.StopServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;


public class RunSS implements Runnable {
	
	private boolean go = true;
	
	public void setGo(boolean go) {
		this.go = go;
	}

	public void run() {
		Server server = Bukkit.getServer();
		ChatColor purple = ChatColor.DARK_PURPLE;
		Plugin larvikGaming = server.getPluginManager().getPlugin("LarvikGaming");
		int delay = larvikGaming.getConfig().getInt("RestartTimeInHours", 6) * 3600000;
    	if (delay < 1) {
    		return;
    	}
    	try {
			Thread.sleep(20000);
		}
    	catch (InterruptedException e) {
		}
	    while (go) {
			try {
				Thread.sleep(delay - 300000);
	    		if (go) {
	    			server.broadcastMessage(purple + "**Server-Restart in 5min**");
	    		}
				Thread.sleep(240000);
	    		if (go) {
	    			server.broadcastMessage(purple + "**Server-Restart in 1min**");
	    		}
				Thread.sleep(60000);
	    		if (go) {
	    			server.broadcastMessage(purple + "**Server-Restart NOW**");
	    		}
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
			}
    		if (go) {
    			StopServer.stopServer();
    		}
	    }
	}
}