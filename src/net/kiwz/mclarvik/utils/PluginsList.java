package net.kiwz.mclarvik.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.logging.Logger;

import net.kiwz.mclarvik.Mclarvik;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class PluginsList {
	
	private static Logger log = Logger.getLogger("Minecraft");
	private static PluginsList list = new PluginsList();
	int N = 500;
	Plugin[] plugin = new Plugin[N];
	int pos = 0;
	
	public static void build() {
		Plugin mcLarvik = Bukkit.getServer().getPluginManager().getPlugin("MCLarvik");
		list.plugin = Bukkit.getServer().getPluginManager().getPlugins();
		list.pos = Bukkit.getServer().getPluginManager().getPlugins().length;
		String fileDest = mcLarvik.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/";
		boolean exception = false;
		if (!mcLarvik.getConfig().getBoolean("PluginsList", true)) {
			return;
		}
		Line[] lines = new Line[list.pos];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = new Line(list.plugin[i].getDescription().getName(), list.plugin[i].getDescription().getVersion());
		}
		Arrays.sort(lines);
		FileOutputStream plugins = null;
		try {
			plugins = new FileOutputStream(fileDest + "misc/plugins.html");
		}
		catch (FileNotFoundException e) {
			exception = true;
		}
		if (exception) {
			log.warning(Mclarvik.name + "FileDir: " + fileDest + "misc/" + " do not excist!");
			log.warning(Mclarvik.name + "Plugins.html will not be created!");
			return;
		}
		PrintStream build = new PrintStream(plugins);
		build.println("<html>");
		build.println("<body bgcolor='#cccccc'>");
		for (Line line : lines) {
			build.println("");
			build.println("<font size='3' color='#0BA613' face='verdana'>");
			build.println("<br /><b>" + line.name + "</b></font>");
			build.println("<font size='2' color='#0BA613' face='verdana'>");
			build.println("<br />Version: " + line.version + "</font>");
			build.println("<br />");
		}
		build.println("");
		build.println("<br />");
		build.println("<br />");
		build.println("<font size='3' color='#0BA613' face='verdana'>");
		build.println("Number of Plugins: ");
		build.println("<b>" + list.pos + "</b></font>");
		build.println("");
		build.println("<br />");
		build.println("<br />");
		build.println("<font size='1' color='#0BA613' face='verdana'>");
		build.println("Created by Kiwz</font>");
		build.println("");
		build.println("</body>");
		build.println("</html>");
		build.close();
	}
	
	static class Line implements Comparable<Line> {
		String name;
		String version;
		Line(String name, String version) {
			this.name = name;
			this.version = version;
		}
		public int compareTo(Line line) {
			return this.name.toUpperCase().compareTo(line.name.toUpperCase());
		}
	}
}