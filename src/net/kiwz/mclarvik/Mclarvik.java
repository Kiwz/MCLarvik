package net.kiwz.mclarvik;

import java.util.logging.Logger;

import net.kiwz.mclarvik.listeners.ChatListener;
import net.kiwz.mclarvik.listeners.CommandListener;
import net.kiwz.mclarvik.logs.LogHandlers;
import net.kiwz.mclarvik.threads.RunAM;
import net.kiwz.mclarvik.threads.RunFC;
import net.kiwz.mclarvik.threads.RunPG;
import net.kiwz.mclarvik.threads.RunSS;
import net.kiwz.mclarvik.threads.Threads;
import net.kiwz.mclarvik.utils.ConfigHeader;
import net.kiwz.mclarvik.utils.MakeFolders;
import net.kiwz.mclarvik.utils.PluginsList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Mclarvik extends JavaPlugin {

	public static String name = "[MCLarvik] ";
	private Logger log = Logger.getLogger("Minecraft");
	private PluginManager pm = Bukkit.getServer().getPluginManager();
	private RunAM abm;
	private RunFC fc;
	private RunPG mb;
	private RunPG pg;
	private RunSS ss;
	
	public void onLoad() {
		this.getConfig().options().copyDefaults(true);
		this.getConfig().options().header(ConfigHeader.header());
		this.saveConfig();
		MakeFolders.makeDirs();
		LogHandlers.log();
	}
	
	public void onEnable() {
		Commands cmds = new Commands();
		getCommand("mclinfo").setExecutor(cmds);
		getCommand("mclreload").setExecutor(cmds);
		getCommand("mclrestart").setExecutor(cmds);
	    getCommand("mclcopy").setExecutor(cmds);
	    getCommand("mclgroups").setExecutor(cmds);
	    getCommand("mclmoney").setExecutor(cmds);
	    getCommand("mcltest").setExecutor(cmds);
	    ChatListener chat = new ChatListener();
	    pm.registerEvents(chat, this);
	    CommandListener cmd = new CommandListener();
	    pm.registerEvents(cmd, this);
	    
	    PluginsList.build();
	    abm = Threads.threadABM();
	    fc = Threads.threadFC();
	    mb = Threads.threadPG();
	    pg = Threads.threadPG();
	    ss = Threads.threadSS();
	    /*denne if'en funker ikke fordi register, towny og economy ikke er enabled enda!
		if (this.getConfig().getBoolean("AccountBackuppOnServerStart", true)) {
			MoneyBackupp mb = new MoneyBackupp();
			mb.nationHoldings();
			mb.townHoldings();
			mb.playerHoldings();
			mb.nationAndTownWithSmallAmountOfMoney();
		}*/
		log.info(name + "ENABLED!");
	}

	public void onDisable() {
		if (abm != null) {
			abm.setGo(false);
		}
		if (fc != null) {
			fc.setGo(false);
		}
		if (mb != null) {
			mb.setGo(false);
		}
		if (pg != null) {
			pg.setGo(false);
		}
		if (ss != null) {
			ss.setGo(false);
		}
		log.info(name + "DISABLED!");
	}
}