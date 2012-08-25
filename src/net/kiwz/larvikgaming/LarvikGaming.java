package net.kiwz.larvikgaming;

import java.util.logging.Logger;

import net.kiwz.larvikgaming.listeners.ChatListener;
import net.kiwz.larvikgaming.listeners.CommandListener;
import net.kiwz.larvikgaming.logs.LogHandlers;
import net.kiwz.larvikgaming.threads.RunAM;
import net.kiwz.larvikgaming.threads.RunFC;
import net.kiwz.larvikgaming.threads.RunMB;
import net.kiwz.larvikgaming.threads.RunPG;
import net.kiwz.larvikgaming.threads.RunSS;
import net.kiwz.larvikgaming.threads.Threads;
import net.kiwz.larvikgaming.utils.ConfigHeader;
import net.kiwz.larvikgaming.utils.MakeFolders;
import net.kiwz.larvikgaming.utils.OnlinePlayers;
import net.kiwz.larvikgaming.utils.PluginsList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class LarvikGaming extends JavaPlugin {

	public static String name = "[LarvikGaming] ";
	private Logger log = Logger.getLogger("Minecraft");
	private PluginManager pm = Bukkit.getServer().getPluginManager();
	private RunAM abm;
	private RunFC fc;
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
		getCommand("lginfo").setExecutor(cmds);
		getCommand("lgreload").setExecutor(cmds);
		getCommand("lgrestart").setExecutor(cmds);
	    getCommand("lgcopy").setExecutor(cmds);
	    getCommand("lggroups").setExecutor(cmds);
	    getCommand("lgmoney").setExecutor(cmds);
	    getCommand("lgtest").setExecutor(cmds);
	    ChatListener chat = new ChatListener();
	    pm.registerEvents(chat, this);
	    CommandListener cmd = new CommandListener();
	    pm.registerEvents(cmd, this);
	    this.getServer().getScheduler().scheduleAsyncDelayedTask(this, new RunMB(), 1);
	    //Next line is for the online players graph
	    this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new OnlinePlayers(), 200, 200);
	    
	    PluginsList.build();
	    abm = Threads.threadABM();
	    fc = Threads.threadFC();
	    pg = Threads.threadPG();
	    ss = Threads.threadSS();
		log.info(name + "ENABLED!");
	}

	public void onDisable() {
		if (abm != null) {
			abm.setGo(false);
		}
		if (fc != null) {
			fc.setGo(false);
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