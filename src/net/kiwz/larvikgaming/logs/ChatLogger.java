package net.kiwz.larvikgaming.logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class ChatLogger {
	
	public void chatLogger(String player, String message) {

		Plugin larvikGaming = Bukkit.getServer().getPluginManager().getPlugin("LarvikGaming");
		if (!larvikGaming.getConfig().getBoolean("Chat", true)) {
			return;
		}
		String fileDest = larvikGaming.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/";
		String logFolder = fileDest + "logs/";
		String chat = logFolder + "Chat.txt";
		Format  sdf = new SimpleDateFormat(larvikGaming.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		Date date = new Date();
		String time = sdf.format(date);
		String out = time + " [" + player + "] " + message;
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(chat, true));
			bw.write(out);
			bw.write("\n");
			bw.close();
		} catch (Exception e) {
		}
	}
}
