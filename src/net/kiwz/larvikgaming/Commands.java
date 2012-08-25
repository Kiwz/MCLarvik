package net.kiwz.larvikgaming;

import java.util.List;

import net.kiwz.larvikgaming.utils.FileCopy;
import net.kiwz.larvikgaming.utils.MoneyBackupp;
import net.kiwz.larvikgaming.utils.PlayerGroups;
import net.kiwz.larvikgaming.utils.StopServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
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
		
		Plugin larvikGaming = Bukkit.getServer().getPluginManager().getPlugin("LarvikGaming");
		ChatColor color = ChatColor.DARK_GREEN;
		
		if (cmd.getName().equalsIgnoreCase("lginfo") && sender.hasPermission("larvikgaming.info")) {
			PluginDescriptionFile pdFile = larvikGaming.getDescription();
			sender.sendMessage(color + "The [" + pdFile.getName() + "] plugin is running version: " + pdFile.getVersion());
			sender.sendMessage(color + "Here is a list of commands to use:");
			sender.sendMessage(color + "</mcl> Shows this information.");
			sender.sendMessage(color + "</help larvikgaming> Show the help entries.");
			sender.sendMessage(color + "</lgrestart> Will stop the server!");
			sender.sendMessage(color + "</lgreload> Reloads this plugin.");
			sender.sendMessage(color + "</lgcopy> Copies all the files listed in the config.");
			sender.sendMessage(color + "</lggroups> Create a user.txt file that contains groups of players.");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("lgreload") && sender.hasPermission("larvikgaming.reload")) {
			Bukkit.getPluginManager().disablePlugin(larvikGaming);
			larvikGaming.reloadConfig();
			Bukkit.getPluginManager().enablePlugin(larvikGaming);
			if (sender instanceof Player) {
				sender.sendMessage(color + "The LarivkGaming plugin is reloaded!");
			}
			else {
				sender.sendMessage(LarvikGaming.name + color + "Plugin is reloaded!");
			}
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("lgrestart") && sender.hasPermission("larvikgaming.restart")) {
			StopServer.stopServer();
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("lgcopy") && sender.hasPermission("larvikgaming.copy")) {
			String message = "";
			message = FileCopy.fileCopy();
			if (sender instanceof Player) {
				sender.sendMessage(color + message);
			}
			else {
				if (!larvikGaming.getConfig().getBoolean("LogFileCopyTime", false)) {
					sender.sendMessage(LarvikGaming.name + color + message);
				}
			}
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("lggroups") && sender.hasPermission("larvikgaming.groups")) {
			String message = "";
			message = PlayerGroups.refreshGroups();
			if (sender instanceof Player) {
				sender.sendMessage(color + message);
			}
			else {
				if (!larvikGaming.getConfig().getBoolean("LogGetGroupTime", false)) {
					sender.sendMessage(LarvikGaming.name + color + message);
				}
			}
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("lgmoney") && sender.hasPermission("larvikgaming.money")) {
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
					sender.sendMessage(LarvikGaming.name + color + message);
				}
			}
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("lgtest")) {
			long start = System.currentTimeMillis();
			List<World> worlds = Bukkit.getServer().getWorlds();
			for (World world : worlds) {
				world.save();
				sender.sendMessage(world.getName() + " is saved!");
			}
			sender.sendMessage("" + (System.currentTimeMillis() - start));
			sender.sendMessage("Dette er 'test' kommandoen til larvikgaming!");
			return true;
		}
		return false;
	}
}