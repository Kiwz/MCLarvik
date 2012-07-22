package net.kiwz.mclarvik.threads;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import net.kiwz.mclarvik.Mclarvik;
import net.kiwz.mclarvik.utils.MoneyBackupp;

public class RunMB implements Runnable {

	private Plugin mcLarvik = Bukkit.getServer().getPluginManager().getPlugin("MCLarvik");
	private Logger log = Logger.getLogger("Minecraft");

	public void run() {
		if (mcLarvik.getConfig().getBoolean("AccountBackuppOnServerStart", true)) {
			long time = System.currentTimeMillis();
			MoneyBackupp mb = new MoneyBackupp();
			mb.nationHoldings();
			mb.townHoldings();
			mb.nationAndTownWithSmallAmountOfMoney();
			mb.playerHoldings();
			log.info(Mclarvik.name + "All Economy accounts where copied to backupp folder, time used: " +
					(System.currentTimeMillis() - time) + "ms.");
		}
	}
}