package net.kiwz.mclarvik.runnables;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class RunPG implements Runnable {
	
	private boolean go = true;
	
	public void setGo(boolean go) {
		this.go = go;
	}

	public void run() {
		Plugin mcLarvik = Bukkit.getServer().getPluginManager().getPlugin("MCLarvik");
		int delay = mcLarvik.getConfig().getInt("RefreshGroupInMin", 10) * 60000;
		long runTime = 0;
    	if (delay < 1) {
    		return;
    	}
    	try {
			Thread.sleep(100);
		}
    	catch (InterruptedException e) {
		}
	    while (go) {
			try {
				Thread.sleep(delay - runTime);
			}
			catch (InterruptedException e) {
			}
    		if (go) {
    			long startTime = System.currentTimeMillis();
    	    	Groups.refreshGroups();
    	    	runTime = System.currentTimeMillis() - startTime;
    		}
	    }
	}
}