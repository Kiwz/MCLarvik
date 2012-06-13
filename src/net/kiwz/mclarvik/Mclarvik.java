package net.kiwz.mclarvik;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Mclarvik extends JavaPlugin {
	
	public static Logger log = Logger.getLogger("Minecraft");
	Config c = new Config();
	Autobroadcast ab = new Autobroadcast();
	FileCopy fc = new FileCopy();
	
	public void onEnable() {
		
		c.saveConfig();
		getCommand("mclreload").setExecutor(c);
		getCommand("mcl").setExecutor(c);
	    getCommand("mclupdate").setExecutor(fc);
	    PluginsList.build();
	    ab.makeMessageFile();
		Autobroadcast.getMessages();
	    PluginDescriptionFile pdFile = getDescription();
		log.info("[" + pdFile.getName() + "]" + " v" + pdFile.getVersion() + " ENABLED! Created by: Kiwz");
		
	}

	public void onDisable() {
		
		ab.stop = false;
		PluginDescriptionFile pdFile = getDescription();
		log.info("[" + pdFile.getName() + "] v" + pdFile.getVersion() + " DISABLED!");
		
	}
}