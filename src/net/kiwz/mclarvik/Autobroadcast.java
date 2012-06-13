package net.kiwz.mclarvik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class Autobroadcast implements Runnable {
	
	static File messages;
	static Vector<String> messageList = new Vector<String>();
	Thread bc;
	boolean stop = true;
	
	public void makeMessageFile() {
		
		messages = new File("plugins/MCLarvik/Messages.txt");
		
	    if (!messages.exists())
	    	try {
	    		messages.createNewFile();
	    	}
	    catch (IOException localIOException) {
	    }
	    bc = new Thread(this, "Broadcaster");
	    bc.start();
	}
	
	public static void getMessages() {
		
		messageList.clear();
		
		try {
			Scanner scanner = new Scanner(messages);
			while (scanner.hasNextLine()) {
				String in = " " + scanner.nextLine().trim() + ChatColor.WHITE;
				messageList.add(in);
	        }
		}
		catch (FileNotFoundException localFileNotFoundException) {
	    }
	}
	
	public void run() {
		
		int i = 0;
		
		while (stop)
	    	try {
	    		Config c = new Config();
	    		c.autoMsg();
	    		Server server = Bukkit.getServer();
	    		
	    		if (i < messageList.size()) {
		        	String in = c.autoMsgPrefix + ChatColor.WHITE + messageList.get(i);
		        	String out = in.replaceAll("(&([a-f0-9]))", "\u00A7$2");
		        	if (!c.autoMsgToConsole) {
		        		for (Player p : server.getOnlinePlayers())
		        			p.sendMessage(out);
		        	}
		        	if (c.autoMsgToConsole) {
		        		server.broadcastMessage(out);
		        	}
		        	i++;
			        Thread.sleep(c.autoMsgDelayInSec * 1000);
		        }
	        	else {
		        	i = 0;
	        	}
	        }
	    catch (InterruptedException localInterruptedException) {	
	    }
	}
}
