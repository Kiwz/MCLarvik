package net.kiwz.mclarvik;

import java.util.logging.Logger;

import net.kiwz.mclarvik.logs.LogHandlers;
import net.kiwz.mclarvik.runnables.RunABM;
import net.kiwz.mclarvik.runnables.RunFC;
import net.kiwz.mclarvik.runnables.RunPG;
import net.kiwz.mclarvik.runnables.Threads;

import org.bukkit.plugin.java.JavaPlugin;

public class Mclarvik extends JavaPlugin {

	private Logger log = Logger.getLogger("Minecraft");
	public static String name = "[MCLarvik] ";
	private RunABM abm;
	private RunFC fc;
	private RunPG pg;
	
	public void onLoad() {
		this.getConfig().options().copyDefaults(true);
		//this.getConfig().options().header(ConfigHeader.start());
		//this.saveDefaultConfig();
		this.saveConfig();
		MakeFolders.makeDirs();
		LogHandlers.log();
	}
	
	public void onEnable() {
		Commands cmds = new Commands();
		getCommand("mclinfo").setExecutor(cmds);
		getCommand("mclreload").setExecutor(cmds);
	    getCommand("mclcopy").setExecutor(cmds);
	    getCommand("mclgroups").setExecutor(cmds);
	    PluginsList.build();
	    abm = Threads.threadABM();
	    fc = Threads.threadFC();
	    pg = Threads.threadPG();
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
		log.info(name + "DISABLED!");
	}
}