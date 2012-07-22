package net.kiwz.mclarvik;

import net.kiwz.mclarvik.utils.FileCopy;
import net.kiwz.mclarvik.utils.MoneyBackupp;
import net.kiwz.mclarvik.utils.PlayerGroups;
import net.kiwz.mclarvik.utils.StopServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.nijikokun.register.payment.Methods;


public class Commands implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Plugin mcLarvik = Bukkit.getServer().getPluginManager().getPlugin("MCLarvik");
		ChatColor color = ChatColor.DARK_GREEN;
		
		if (cmd.getName().equalsIgnoreCase("mclinfo") && sender.hasPermission("mclarvik.info")) {
			PluginDescriptionFile pdFile = mcLarvik.getDescription();
			sender.sendMessage(color + "The [" + pdFile.getName() + "] plugin is running version: " + pdFile.getVersion());
			sender.sendMessage(color + "Here is a list of commands to use:");
			sender.sendMessage(color + "</mcl> Shows this information.");
			sender.sendMessage(color + "</help mclarvik> Show the help entries.");
			sender.sendMessage(color + "</mclrestart> Will stop the server!");
			sender.sendMessage(color + "</mclreload> Reloads this plugin.");
			sender.sendMessage(color + "</mclcopy> Copies all the files listed in the config.");
			sender.sendMessage(color + "</mclgroups> Create a user.txt file that contains groups of players.");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("mclreload") && sender.hasPermission("mclarvik.reload")) {
			Bukkit.getPluginManager().disablePlugin(mcLarvik);
			mcLarvik.reloadConfig();
			Bukkit.getPluginManager().enablePlugin(mcLarvik);
			if (sender instanceof Player) {
				sender.sendMessage(color + "The MCLarvik plugin is reloaded!");
			}
			else {
				sender.sendMessage(Mclarvik.name + color + "Plugin is reloaded!");
			}
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("mclrestart") && sender.hasPermission("mclarvik.restart")) {
			StopServer.stopServer();
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("mclcopy") && sender.hasPermission("mclarvik.copy")) {
			String message = "";
			message = FileCopy.fileCopy();
			if (sender instanceof Player) {
				sender.sendMessage(color + message);
			}
			else {
				if (!mcLarvik.getConfig().getBoolean("LogFileCopyTime", false)) {
					sender.sendMessage(Mclarvik.name + color + message);
				}
			}
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("mclgroups") && sender.hasPermission("mclarvik.groups")) {
			String message = "";
			message = PlayerGroups.refreshGroups();
			if (sender instanceof Player) {
				sender.sendMessage(color + message);
			}
			else {
				if (!mcLarvik.getConfig().getBoolean("LogGetGroupTime", false)) {
					sender.sendMessage(Mclarvik.name + color + message);
				}
			}
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("mclmoney") && sender.hasPermission("mclarvik.money")) {
			boolean register = Bukkit.getServer().getPluginManager().isPluginEnabled("Register");
			boolean hasMethod = Methods.hasMethod();
			long timeStart = System.currentTimeMillis();
			MoneyBackupp mb = new MoneyBackupp();
			mb.nationHoldings();
			mb.townHoldings();
			mb.nationAndTownWithSmallAmountOfMoney();
			mb.playerHoldings();
			String error = "This command is disabled since you arent using any Economy plugin AND Register!";
			String message = "A backupp of all economy accounts was made in: " + (System.currentTimeMillis() - timeStart) + "ms.";
			if (sender instanceof Player) {
				if (!register || !hasMethod) {
					sender.sendMessage(color + error);
				}
				else {
					sender.sendMessage(color + message);
				}
			}
			else {
				if (!register || !hasMethod) {
					sender.sendMessage(color + error);
				}
				else {
					sender.sendMessage(Mclarvik.name + color + message);
				}
			}
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("mcltest")) {
			sender.sendMessage("Dette er 'test' kommandoen til mclarvik!");
			return true;
		}
		return false;
	}
}