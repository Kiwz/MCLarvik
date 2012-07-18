package net.kiwz.mclarvik.logs;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class LogHandlers {

	private static Logger log = Logger.getLogger("Minecraft");
	private static Logger global = Logger.getLogger("");
	private static Plugin mcLarvik = Bukkit.getServer().getPluginManager().getPlugin("MCLarvik");

	public static void log() {
		String folderToSave = mcLarvik.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/";
		String logFolder = folderToSave + "logs/";
		boolean full = mcLarvik.getConfig().getBoolean("Full-Logger", true);
		boolean fine = mcLarvik.getConfig().getBoolean("Fine-Logger", false);
		boolean info = mcLarvik.getConfig().getBoolean("Info-Logger", false);
		boolean warning = mcLarvik.getConfig().getBoolean("Warning-Logger", true);
		boolean severe = mcLarvik.getConfig().getBoolean("Severe-Logger", true);
		if ((new File(logFolder + "Full-Logger.txt.lck")).exists()) {
			return;
		}
		log.setLevel(Level.ALL);
		Handler[] mcHandler = log.getHandlers();
		mcHandler[1].setLevel(Level.INFO);
		if (full)
			full();
		if (fine)
			fine();
		if (info)
			info();
		if (warning)
			warning();
		if (severe)
			severe();
	}
	
	private static void full() {
		String folderToSave = mcLarvik.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/";
		String logFolder = folderToSave + "logs/";
		FileHandler fullHandler = null;
		try {
			fullHandler = new FileHandler(logFolder + "Full-Logger.txt", true);
		} catch (IOException e) {
		}
		log.addHandler(fullHandler);
		global.addHandler(fullHandler);
		fullHandler.setLevel(Level.ALL);
		fullHandler.setFormatter(new FullFormatter());
	}
	
	private static void fine() {
		String folderToSave = mcLarvik.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/";
		String logFolder = folderToSave + "logs/";
		FileHandler fineHandler = null;
		try {
			fineHandler = new FileHandler(logFolder + "Fine-Logger.txt", true);
		} catch (IOException e) {
		}
		log.addHandler(fineHandler);
		global.addHandler(fineHandler);
		fineHandler.setLevel(Level.FINEST);
		fineHandler.setFormatter(new FineFormatter());
	}
	
	private static void info() {
		String folderToSave = mcLarvik.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/";
		String logFolder = folderToSave + "logs/";
		FileHandler infoHandler = null;
		try {
			infoHandler = new FileHandler(logFolder + "Info-Logger.txt", true);
		} catch (IOException e) {
		}
		log.addHandler(infoHandler);
		global.addHandler(infoHandler);
		infoHandler.setLevel(Level.INFO);
		infoHandler.setFormatter(new InfoFormatter());
	}
	
	private static void warning() {
		String folderToSave = mcLarvik.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/";
		String logFolder = folderToSave + "logs/";
		FileHandler warningHandler = null;
		try {
			warningHandler = new FileHandler(logFolder + "Warning-Logger.txt", true);
		} catch (IOException e) {
		}
		log.addHandler(warningHandler);
		global.addHandler(warningHandler);
		warningHandler.setLevel(Level.WARNING);
		warningHandler.setFormatter(new WarningFormatter());
	}
	
	private static void severe() {
		String folderToSave = mcLarvik.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/";
		String logFolder = folderToSave + "logs/";
		FileHandler severeHandler = null;
		try {
			severeHandler = new FileHandler(logFolder + "Severe-Logger.txt", true);
		} catch (IOException e) {
		}
		log.addHandler(severeHandler);
		global.addHandler(severeHandler);
		severeHandler.setLevel(Level.SEVERE);
		severeHandler.setFormatter(new SevereFormatter());
	}
}
