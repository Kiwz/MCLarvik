package net.kiwz.mclarvik;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class PluginsList {
	
	public static PluginsList list = new PluginsList();
	int N = 500;
	Plugin[] plugin = new Plugin[this.N];
	int pos = 0;

	public static void build() {
		
		Config c = new Config();
		c.pl();
		c.fileDirectory();
		list.plugin = Bukkit.getServer().getPluginManager().getPlugins();
		list.pos = Bukkit.getServer().getPluginManager().getPlugins().length;
		
		if (c.PluginsList) {
			try {
				class Line implements Comparable<Line> {
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
				Line[] lines = new Line[list.pos];
				for (int i = 0; i < lines.length; i++) {
					lines[i] = new Line(list.plugin[i].getDescription().getName(),
										list.plugin[i].getDescription().getVersion());
				}
				Arrays.sort(lines);
				FileOutputStream plugins = new FileOutputStream(c.FileDir + "plugins.html");
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
			catch (IOException e) {
				System.out.println("Error: " + e);
				System.exit(1);
			}
		}
	}
}