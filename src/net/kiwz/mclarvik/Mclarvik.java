package net.kiwz.mclarvik;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Mclarvik extends JavaPlugin {
	
	public static Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		Config c = new Config();
		c.saveConfig();
		getCommand("mclreload").setExecutor(c);
		getCommand("mcl").setExecutor(c);
	    
		FileCopy UpdateCmd = new FileCopy();
		getCommand("mclupdate").setExecutor(UpdateCmd);
	    
	    PluginsList.build();
	    
	    Autobroadcast ab = new Autobroadcast();
	    ab.makeMessageFile();
		Autobroadcast.getMessages();
	    
		PluginDescriptionFile pdFile = getDescription();
		log.info("[" + pdFile.getName() + "]" + " v" + pdFile.getVersion() + " ENABLED! Created by: Kiwz");
	}

	public void onDisable() {
		PluginDescriptionFile pdFile = getDescription();
		log.info("[" + pdFile.getName() + "] v" + pdFile.getVersion() + " DISABLED!");
	}
}