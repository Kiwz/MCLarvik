package net.kiwz.mclarvik;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class Config implements CommandExecutor {
	
	int autoMsgDelayInSec;
	boolean autoMsgToConsole;
	String autoMsgPrefix;
	String FileDir;
	boolean PluginsList;
	boolean AutoFileCopy;
	int FileCopyDelayInMin;
	List<String> FilesToCopy;
	List<String> FilesToSave;
	boolean GetGroups;
	String WorldToGetGroupFrom;
	List<String> Groups;
	String TimeFormat;
	boolean UseCmdToLog1;
	List<String> CmdToLog1;
	boolean UseCmdToLog2;
	List<String> CmdToLog2;
	boolean UsePrivMsgToLog;
	List<String> PrivMsgToLog;
	boolean Chat;
	boolean TownyChat;
	boolean WarningAndSevere;
	boolean XtraServerLog;
	
	Plugin MCLarvik = Bukkit.getServer().getPluginManager().getPlugin("MCLarvik");
	FileConfiguration getC = MCLarvik.getConfig();
	
	public void saveConfig() {
	    
		getC.options().copyDefaults(true);
		MCLarvik.saveConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("mclreload")) {
			
			if(sender.hasPermission("mclarvik.reload")) {
				MCLarvik.reloadConfig();
				Autobroadcast.getMessages();
				autoMsg();
				sender.sendMessage(ChatColor.GREEN + "The MCLarvik plugin is reloaded!");
			}
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("mcl")) {
			
			if(sender.hasPermission("mclarvik.info")) {
				PluginDescriptionFile pdFile = MCLarvik.getDescription();
				sender.sendMessage(ChatColor.DARK_GREEN + "The [" + pdFile.getName() + "] plugin is running version: " + pdFile.getVersion());
			}
			return true;
		}
		
		return false;
	}
	
	public void autoMsg() {
		this.autoMsgDelayInSec = getC.getInt("autoMsgDelayInSec", 300);
		this.autoMsgToConsole = getC.getBoolean("autoMsgToConsole", false);
		this.autoMsgPrefix = getC.getString("autoMsgPrefix", "'&d[AutoMessage]'");
	}
	
	public void fileDirectory() {
		this.FileDir = getC.getString("FileDir", "plugins/MCLarvik/");
	}
	
	public void pl() {
		this.PluginsList = getC.getBoolean("PluginsList", false);
	}
	
	public void fileCopy() {
		this.AutoFileCopy = getC.getBoolean("AutoFileCopy", false);
		this.FileCopyDelayInMin = getC.getInt("FileCopyDelayInMin", 60);
		this.FilesToCopy = getC.getStringList("FilesToCopy");
		this.FilesToSave = getC.getStringList("FilesToSave");
	}
	
	public void groups() {
		this.GetGroups = getC.getBoolean("GetGroups", false);
		this.WorldToGetGroupFrom = getC.getString("WorldToGetGroupFrom", "world");
		this.Groups = getC.getStringList("Groups");
	}
	
	public void tFormat() {
		this.TimeFormat = getC.getString("TimeFormat", "YYYY-MM-dd HH:mm:ss");
	}
	
	public void logcmd1() {
		this.UseCmdToLog1 = getC.getBoolean("UseCmdToLog1", false);
		this.CmdToLog1 = getC.getStringList("CmdToLog1");
	}
	
	public void logcmd2() {
		this.UseCmdToLog2 = getC.getBoolean("UseCmdToLog2", false);
		this.CmdToLog1 = getC.getStringList("CmdToLog2");
	}
	
	public void logPriv() {
		this.UsePrivMsgToLog = getC.getBoolean("UsePrivMsgToLog", false);
		this.PrivMsgToLog = getC.getStringList("PrivMsgToLog");
	}
	
	public void logs() {
		this.Chat = getC.getBoolean("Chat", false);
		this.TownyChat = getC.getBoolean("TownyChat", false);
		this.WarningAndSevere = getC.getBoolean("WarningAndSevere", false);
		this.XtraServerLog = getC.getBoolean("XtraServerLog", false);
	}
}
