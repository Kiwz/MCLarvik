package net.kiwz.larvikgaming.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;

import net.kiwz.larvikgaming.LarvikGaming;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class FileCopy {
	
	public static String fileCopy() {
		Logger log = Logger.getLogger("Minecraft");
		Plugin larvikGaming = Bukkit.getServer().getPluginManager().getPlugin("LarvikGaming");
		String folderToSave = larvikGaming.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/";
		String bckFolder = folderToSave + "backupp/";
		boolean logTime = larvikGaming.getConfig().getBoolean("LogFileCopyTime", false);
		List<String> fileSrc = larvikGaming.getConfig().getStringList("FilesToCopy");
		List<String> fileDest = larvikGaming.getConfig().getStringList("FilesToSave");
		String cmdSender = "";
		File dir = new File(bckFolder);
		boolean exception = false;
		if (fileSrc.size() == fileDest.size() && dir.exists()) {
			long startAll = System.currentTimeMillis();
			int iAll = 0;
			for (int i=0; i < fileSrc.size(); i++) {
				long start = System.currentTimeMillis();
				File file = new File(fileSrc.get(i));
				if (file.exists()) {
					InputStream in;
					try {
						in = new FileInputStream(fileSrc.get(i));
						OutputStream out;
						out = new FileOutputStream(bckFolder + fileDest.get(i));
						byte[] buf = new byte[10*1024];
						int len;
						while ((len = in.read(buf)) > 0) {
							out.write(buf, 0, len);
						}
						in.close();
						out.close();
						iAll++;
					}
					catch (IOException e) {
						exception = true;
					}
				}
				if (!file.exists()) {
					log.warning(LarvikGaming.name + "File: " + fileSrc.get(i) + " dont excist!");
				}
				long stopp = System.currentTimeMillis();
				long timeElapsed = stopp - start;
				if (file.exists() && logTime) {
					log.info(LarvikGaming.name + "File: " + fileSrc.get(i) + " copied in: " + timeElapsed + "ms.");
				}
			}
			long stoppAll = System.currentTimeMillis();
			long timeElapsedAll = stoppAll - startAll;
			if (exception) {
				cmdSender = "Syntax error! More information in your server.log";
			}
			if (!exception) {
				cmdSender = iAll + " file(s) copied in: " + timeElapsedAll + "ms.";
			}
		}
		if (!dir.exists()) {
			log.warning(LarvikGaming.name + "FileDir: " + bckFolder + " do not excist!");
			cmdSender = "Syntax error! More information in your server.log";
		}
		if (fileSrc.size() != fileDest.size()) {
			log.warning(LarvikGaming.name + "FilesToCopy has " + fileSrc.size() + " files, FilesToSave has " + fileDest.size() + " files.");
			log.warning(LarvikGaming.name + "Number of FilesToCopy must equal number of FilesToSave!");
			cmdSender = "Syntax error! More information in your server.log";
		}
	return cmdSender;
	}
}