package net.kiwz.mclarvik.threads;

import net.kiwz.mclarvik.utils.StopServer;

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
		Plugin mcLarvik = server.getPluginManager().getPlugin("MCLarvik");
		int delay = mcLarvik.getConfig().getInt("RestartTimeInHours", 6) * 3600000;
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