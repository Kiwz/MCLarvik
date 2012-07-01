package net.kiwz.mclarvik.runnables;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import net.kiwz.mclarvik.Mclarvik;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;


public class Groups {

	String Group;
	String Name;

	public static String refreshGroups() {
		Logger log = Logger.getLogger("Minecraft");
		Plugin mcLarvik = Bukkit.getServer().getPluginManager().getPlugin("MCLarvik");
		String fileSrc = "plugins/bPermissions/" + mcLarvik.getConfig().getString("WorldToGetGroupFrom", "world") + "/";
		String fileDest = mcLarvik.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/";
		String miscFolder = fileDest + "misc/";
		File origUsers = new File(fileSrc + "users.yml");
		File destUsers = new File(miscFolder + "users.txt");
		boolean logTime = mcLarvik.getConfig().getBoolean("LogGetGroupTime", false);
		long start = System.currentTimeMillis();
		long time = 0;
		List<String> groups = mcLarvik.getConfig().getStringList("Groups");
		String cmdSender = "";
		boolean outException = false;
		boolean inException = false;
		try {
			PrintStream printStream = new PrintStream(destUsers);
			for (int i=0; i < groups.size(); i++) {
				try {
					Scanner scanner = new Scanner(origUsers);
					String playerName = "";
					printStream.println("Group " + groups.get(i) + ":");
					int players = 0;
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						if (line.contains("  ") && !line.contains("   ")) {
							playerName = line;
						}
						if (line.contains("groups:")) {
							if (scanner.nextLine().contains("    - " + groups.get(i))) {
								String trimmed = playerName.replaceAll("[':-]", "");
								String formatted = String.format("%04d", players + 1);
								printStream.println(formatted + ". " + trimmed.trim());
								players++;
							}
						}
					}
					printStream.println();
					scanner.close();
				}
				catch (FileNotFoundException e) {
					inException = true;
				}
			}
			printStream.println("-----------------------------------------------");
			time = System.currentTimeMillis() - start;
			printStream.println("Time used to generate this information: " + time + "ms.");
			printStream.println("-----------------------------------------------");
			printStream.close();
		}
		catch (FileNotFoundException e) {
			outException = true;
		}
		if (outException || inException) {
			cmdSender = ("Syntax error! More information in your server.log");
		}
		if (inException) {
			log.warning(Mclarvik.name + "File: " + origUsers + " not found!");
		}
		if (outException) {
			log.warning(Mclarvik.name + "FileDir: " + miscFolder + " do not excist!");
		}
		else {
			cmdSender = ("Time used to generate this information: " + time + "ms.");
			if (logTime) {
				log.info(Mclarvik.name + "Time used to generate users.txt: " + time + "ms.");
			}
		}
		return cmdSender;
	}
}
