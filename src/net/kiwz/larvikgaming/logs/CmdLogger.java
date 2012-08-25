package net.kiwz.larvikgaming.logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class CmdLogger {

	public void cmdLogger1(String player, String message) {

		Plugin larvikGaming = Bukkit.getServer().getPluginManager().getPlugin("LarvikGaming");
		String fileName = larvikGaming.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/logs/Cmd-Logger1.txt";
		Format sdf = new SimpleDateFormat(larvikGaming.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		Date date = new Date();
		String time = sdf.format(date);
		List<String> cmdsToLog = larvikGaming.getConfig().getStringList("CmdToLog1");
		String[] msg = message.split(" ");
		String cmdToLog1 = "";
		String msg1 = "";
		
		for (int i = 0; i < cmdsToLog.size(); i++) {
			if (cmdsToLog.get(i).contains("*")) {
				String[] cmdToLog = cmdsToLog.get(i).split(" ");
				if (msg.length > (cmdToLog.length - 1)) {
					for (int ii = 0; ii < cmdToLog.length; ii++) {
						if (!cmdToLog[ii].equals("*")) {
							cmdToLog1 = cmdToLog1 + cmdToLog[ii];
							msg1 = msg1 + msg[ii];
						}
					}
				}
			}
			if (cmdToLog1.equals(msg1) && cmdToLog1 != "" && msg1 != "") {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
					bw.write(time + " [" + player + "] " + message);
					bw.write("\n");
					bw.close();
				} catch (Exception e) {
				}
			}
			if (!cmdsToLog.get(i).contains("*") && cmdsToLog.get(i).equals(message)) {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
					bw.write(time + " [" + player + "] " + message);
					bw.write("\n");
					bw.close();
				} catch (Exception e) {
				}
			}
			cmdToLog1 = "";
			msg1 = "";
		}
	}
	
	public void cmdLogger2(String player, String message) {

		Plugin larvikGaming = Bukkit.getServer().getPluginManager().getPlugin("LarvikGaming");
		String fileName = larvikGaming.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/logs/Cmd-Logger2.txt";
		Format sdf = new SimpleDateFormat(larvikGaming.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		Date date = new Date();
		String time = sdf.format(date);
		List<String> cmdsToLog = larvikGaming.getConfig().getStringList("CmdToLog2");
		String[] msg = message.split(" ");
		String cmdToLog1 = "";
		String msg1 = "";
		
		for (int i = 0; i < cmdsToLog.size(); i++) {
			if (cmdsToLog.get(i).contains("*")) {
				String[] cmdToLog = cmdsToLog.get(i).split(" ");
				if (msg.length > (cmdToLog.length - 1)) {
					for (int ii = 0; ii < cmdToLog.length; ii++) {
						if (!cmdToLog[ii].equals("*")) {
							cmdToLog1 = cmdToLog1 + cmdToLog[ii];
							msg1 = msg1 + msg[ii];
						}
					}
				}
			}
			if (cmdToLog1.equals(msg1) && cmdToLog1 != "" && msg1 != "") {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
					bw.write(time + " [" + player + "] " + message);
					bw.write("\n");
					bw.close();
				} catch (Exception e) {
				}
			}
			if (!cmdsToLog.get(i).contains("*") && cmdsToLog.get(i).equals(message)) {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
					bw.write(time + " [" + player + "] " + message);
					bw.write("\n");
					bw.close();
				} catch (Exception e) {
				}
			}
			cmdToLog1 = "";
			msg1 = "";
		}
	}

	public void privMsgLogger(String player, String message) {

		Plugin larvikGaming = Bukkit.getServer().getPluginManager().getPlugin("LarvikGaming");
		String fileName = larvikGaming.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/logs/Private-Msg.txt";
		Format sdf = new SimpleDateFormat(larvikGaming.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		Date date = new Date();
		String time = sdf.format(date);
		List<String> privMsgToLog = larvikGaming.getConfig().getStringList("PrivMsgToLog");
		String[] msg = message.split(" ");
		
		for (int i = 0; i < privMsgToLog.size(); i++) {
			if (privMsgToLog.get(i).equals(msg[0])) {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
					bw.write(time + " [" + player + "] " + message);
					bw.write("\n");
					bw.close();
				} catch (Exception e) {
				}
			}
		}
	}
}