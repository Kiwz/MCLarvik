package net.kiwz.mclarvik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FileCopy implements CommandExecutor, Runnable {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("mclupdate")) {
			if(sender.hasPermission("mclarvik.update")) {
				fileCopy();
				PluginsList.build();
				sender.sendMessage(ChatColor.DARK_GREEN + "All files are copyed to FileDir!");
			}
			return true;
		}
		return false;
	}
	
	public static void fileCopy() {
		
		Config c = new Config();
		c.fileDirectory();
		c.fileCopy();
		List<String> fileSrc = c.FilesToCopy;
		List<String> fileDest = c.FilesToSave;
		for (int i=0; i < fileSrc.size(); i++) {
			String inPath = fileSrc.get(i);
			String outPath = c.FileDir + fileDest.get(i);
			File inFile = new File(inPath);
			File outFile = new File(outPath);
			Scanner in = null;
			PrintStream out = null;
			try {
				in = new Scanner(inFile);
				out = new PrintStream(outFile);
			}
			catch (FileNotFoundException e) {
			}
			while (in.hasNext()) {
				out.println(in.nextLine());
			}
			out.close();
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}
}