package net.kiwz.mclarvik;

import net.kiwz.mclarvik.runnables.FileCopy;
import net.kiwz.mclarvik.runnables.Groups;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

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
			message = Groups.refreshGroups();
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
		return false;
	}
}