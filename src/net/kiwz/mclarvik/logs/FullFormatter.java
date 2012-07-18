package net.kiwz.mclarvik.logs;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.regex.Pattern;


import net.kiwz.mclarvik.utils.Ip;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;


public class FullFormatter extends Formatter {

	private Plugin mcLarvik = Bukkit.getServer().getPluginManager().getPlugin("MCLarvik");
	private Pattern pattern = Pattern.compile("\\x1B\\[([0-9]{1,2}(;[0-9]{1,2})?)?[m|K]");
	private String n = "\n";

	public String format(LogRecord rec) {
		Format  sdf = new SimpleDateFormat(mcLarvik.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		StringBuffer buf = new StringBuffer(1000);
		buf.append(sdf.format(rec.getMillis()));
		buf.append(" [");
		buf.append(rec.getLevel());
		buf.append("] ");
		buf.append(formatMessage(rec));
		buf.append(n);
		String line = pattern.matcher(buf.toString()).replaceAll("");
		return line;
	}
	
	public String getHead(Handler h) {
		Format  sdf = new SimpleDateFormat(mcLarvik.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		String time = sdf.format(new Date());
		String bVersion = Bukkit.getVersion();
		String[] bVersionSplit = bVersion.split("MC: ");
		String mcVersion = bVersionSplit[1].replace(")", "");
		String ip = Ip.getWanIp();
		int port = Bukkit.getPort();
		String apiVersion = Bukkit.getBukkitVersion();
		String mclVersion = mcLarvik.getDescription().getVersion();
		String line1 = time + " [START] Starting minecraft server version " + mcVersion + n;
		String line2 = time + " [INFO] Loading properties" + n;
		String line3 = time + " [INFO] Starting Minecraft server on " + ip + ":" + port + n;
		String line4 = time + " [INFO] This server is running CraftBukkit version " + bVersion +
				" (Implementing API version " + apiVersion + ")" + n;
		String line5 = time + " [INFO] [MCLarvik] Loading MCLarvik v" + mclVersion + n;
		String head = line1 + line2 + line3 + line4 + line5; 
		return head;
	}
}