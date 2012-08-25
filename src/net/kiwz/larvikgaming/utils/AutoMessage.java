package net.kiwz.larvikgaming.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import org.bukkit.ChatColor;

public class AutoMessage {
	
	public static File message;
	public static Vector<String> messageList = new Vector<String>();
	
	public static void makeMessageABFile() {
		message = new File("plugins/LarvikGaming/Messages.txt");
		if (!message.exists())
	    	try {
	    		message.createNewFile();
	    	}
	    catch (IOException localIOException) {
	    }
		getMessages();
	}
	
	public static void getMessages() {
		messageList.clear();
		try {
			Scanner scanner = new Scanner(message);
			while (scanner.hasNextLine()) {
					String in = "" + scanner.nextLine().trim() + ChatColor.WHITE;
					messageList.add(in);
	        }
		}
		catch (FileNotFoundException localFileNotFoundException) {
	    }
	}
}
