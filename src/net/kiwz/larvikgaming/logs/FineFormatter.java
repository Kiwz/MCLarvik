package net.kiwz.larvikgaming.logs;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class FineFormatter extends Formatter {

	private Plugin larvikGaming = Bukkit.getServer().getPluginManager().getPlugin("LarvikGaming");
	private Pattern pattern = Pattern.compile("\\x1B\\[([0-9]{1,2}(;[0-9]{1,2})?)?[m|K]");

	public String format(LogRecord rec) {
		Format  sdf = new SimpleDateFormat(larvikGaming.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		StringBuffer buf = new StringBuffer(1000);
		String line = "";
		if (rec.getLevel().intValue() <= Level.CONFIG.intValue()) {
			buf.append(sdf.format(rec.getMillis()));
			buf.append(" [");
			buf.append(rec.getLevel());
			buf.append("] ");
			buf.append(formatMessage(rec));
			buf.append("\n");
			line = pattern.matcher(buf.toString()).replaceAll("");
		}
		return line;
	}
}