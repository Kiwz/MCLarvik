package net.kiwz.mclarvik.runnables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class RunABM implements Runnable {
	
	private boolean go = true;
	
	public void setGo(boolean go) {
		this.go = go;
	}

	public void run() {
		Plugin mcLarvik = Bukkit.getServer().getPluginManager().getPlugin("MCLarvik");
    	Server server = Bukkit.getServer();
		int delay = mcLarvik.getConfig().getInt("autoMsgDelayInMin", 15) * 60000;
		if (delay < 1) {
			return;
		}
		boolean toConsole = mcLarvik.getConfig().getBoolean("autoMsgToConsole", false);
		String mPre = mcLarvik.getConfig().getString("autoMsgPrefix", "'&d[Viktig Beskjed] '");
		int line = 0;
		long runTime = 0;
		MessageToAB.makeMessageABFile();
		int messages = MessageToAB.messageList.size();
		if (messages == 0) {
			return;
		}
    	try {
			Thread.sleep(1);
		}
    	catch (InterruptedException e) {
		}
	    while (go) {
			if (line < messages) {
				try {
					Thread.sleep(delay - runTime);
				}
				catch (InterruptedException e) {
				}
				if (go) {
	    			long startTime = System.currentTimeMillis();
					String in = mPre + ChatColor.WHITE + MessageToAB.messageList.get(line);
					String replace = in.replaceAll("(&([a-f0-9]))", "\u00A7$2");
					String[] out = replace.split("//n");
					int outSize = out.length;
					if (!toConsole) {
						for (Player p : server.getOnlinePlayers()) {
							for (int i = 0; outSize > i; i++) {
								p.sendMessage(out[i]);
							}
						}
					}
					if (toConsole) {
						for (int i = 0; outSize > i; i++) {
							server.broadcastMessage(out[i]);
						}
					}
					line++;
	    			runTime = System.currentTimeMillis() - startTime;
				}
			}
			else {
				line = 0;
			}
		}
	}
}
