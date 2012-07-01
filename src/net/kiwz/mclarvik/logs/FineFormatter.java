package net.kiwz.mclarvik.logs;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import net.kiwz.mclarvik.Ip;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class FineFormatter extends Formatter {

	private Plugin mcLarvik = Bukkit.getServer().getPluginManager().getPlugin("MCLarvik");
	private String n = "\n";

	public String format(LogRecord rec) {
		Format  sdf = new SimpleDateFormat(mcLarvik.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		StringBuffer buf = new StringBuffer(1000);
		if (rec.getLevel().intValue() <= Level.CONFIG.intValue()) {
			buf.append(n);
			buf.append(sdf.format(rec.getMillis()));
			buf.append(" [");
			buf.append(rec.getLevel());
			buf.append("] ");
			buf.append(formatMessage(rec));
		}
		return buf.toString();
	}
	
	public String getHead(Handler h) {
		Format  sdf = new SimpleDateFormat(mcLarvik.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		String time = sdf.format(new Date());
		String version = Bukkit.getVersion();
		int port = Bukkit.getPort();
		String head = time + " [STARTUP] CraftBukkit Version: " + version + ". Hosting @ " + Ip.getWanIp() + ":" + port; 
		return head;
	}
	
	public String getTail(Handler h) {
		Format  sdf = new SimpleDateFormat(mcLarvik.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		String time = sdf.format(new Date());
		String tail = time + " [SHUTDOWN] MineCraft Server stopped!";
		return n + tail + n;
	}
}