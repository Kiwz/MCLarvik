package net.kiwz.larvikgaming.threads;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import net.kiwz.larvikgaming.LarvikGaming;
import net.kiwz.larvikgaming.utils.MoneyBackupp;

public class RunMB implements Runnable {

	private Plugin larvikGaming = Bukkit.getServer().getPluginManager().getPlugin("LarvikGaming");
	private Logger log = Logger.getLogger("Minecraft");

	public void run() {
		if (larvikGaming.getConfig().getBoolean("AccountBackuppOnServerStart", true)) {
			long time = System.currentTimeMillis();
			MoneyBackupp mb = new MoneyBackupp();
			mb.nationHoldings();
			mb.townHoldings();
			mb.nationAndTownWithSmallAmountOfMoney();
			mb.playerHoldings();
			log.info(LarvikGaming.name + "All Economy accounts where copied to backupp folder, time used: " +
					(System.currentTimeMillis() - time) + "ms.");
		}
	}
}