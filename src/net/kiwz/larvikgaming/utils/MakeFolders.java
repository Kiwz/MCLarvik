package net.kiwz.larvikgaming.utils;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class MakeFolders {
	
	public static void makeDirs() {
		
		Plugin larvikGaming = Bukkit.getServer().getPluginManager().getPlugin("LarvikGaming");
		String folderToSave = larvikGaming.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/";
		File file = new File("");
		
		String logFolder = folderToSave + "logs/";
		File logDir = new File(file.getParentFile(), logFolder);
		logDir.mkdirs();

		String bckFolder = folderToSave + "backupp/";
		File bckDir = new File(file.getParentFile(), bckFolder);
		bckDir.mkdirs();
		
		String miscFolder = folderToSave + "misc/";
		File miscDir = new File(file.getParentFile(), miscFolder);
		miscDir.mkdirs();
		
	}
}
