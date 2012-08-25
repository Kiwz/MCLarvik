package net.kiwz.larvikgaming.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class OnlinePlayers implements Runnable {
	
	public void run() {
		makeFile();
	}
	
	public void makeFile() {
		Plugin larvikGaming = Bukkit.getServer().getPluginManager().getPlugin("LarvikGaming");
		String dir = larvikGaming.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/misc/";
		Format  sdf = new SimpleDateFormat(larvikGaming.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		String date = sdf.format(new Date());
		File outFile = new File(dir + "Players.txt");
		FileWriter pw = null;
		try {
			pw = new FileWriter(outFile, true);
			pw.write(date + " " + getPlayers() + "\n");
			pw.close();
		} catch (IOException e) {
		}
	}
	
	public int getPlayers() {
		int players = Bukkit.getServer().getOnlinePlayers().length;
		return players;
	}
}
