package net.kiwz.mclarvik;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Online implements CommandExecutor
{
	private Mclarvik plugin;
	
	public Online(Mclarvik plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		
		if (cmd.getName().equalsIgnoreCase("online"))
		{
			
			if(sender.hasPermission("mclarvik.online") || sender.hasPermission("mclarvik.*"))
			{
				getOnlinePlayers(sender);
				getName(sender);
			}
			
			else
			{
				getOnlinePlayers(sender);
				getDisplayName(sender);
			}
			return true;
		}
		return false;
	}
	
	private static void getOnlinePlayers(CommandSender sender)
	{
		int maxplayers = Bukkit.getMaxPlayers();
		int onlinePlayers = 0;
		Player[] playerServerList = Bukkit.getOnlinePlayers();
		Player player = null;
		
		if (sender instanceof Player)
		{
			player = (Player) sender;
		}
		
		for (int i=0; i < playerServerList.length; i++)
		{
			
			if (player != null)
			{
				
				if (player.canSee(playerServerList[i].getPlayer()))
				{
					onlinePlayers++;
				}
			}

			else
			{
				onlinePlayers++;
			}
		}
		String PlayerOfMaxPlayers = Strings.LightYellow + onlinePlayers + "/" + maxplayers + ":";
		sender.sendMessage(PlayerOfMaxPlayers);
	}
	
	private static void getName(CommandSender sender)
	{
		Player[] playerServerList = Bukkit.getOnlinePlayers();
		String names = Strings.LightYellow + "Connected Players: " + Strings.Yellow;
		
		for (int i = 0; i < playerServerList.length; i++)
		{
			
			if (!names.equals(Strings.LightYellow + "Connected Players: " + Strings.Yellow))
			{
				names += ", ";
			}
			
			if (!playerServerList[i].getName().equals
			   (playerServerList[i].getDisplayName()))
			{
				names += Strings.Turquoise + playerServerList[i].getName() + " <" +
									 playerServerList[i].getDisplayName() + ">" + Strings.Yellow;
			}
			
			else
			{
				names += playerServerList[i].getName();
			}
		}
		sender.sendMessage(names);
	}
	
	private static void getDisplayName(CommandSender sender)
	{
		Player[] playerServerList = Bukkit.getServer().getOnlinePlayers();
		String names = Strings.LightYellow + "Connected Players: " + Strings.Yellow;
		Player player = null;
		
		if (sender instanceof Player)
		{
			player = (Player) sender;
		}
		
		for (int i = 0; i < playerServerList.length; i++)
		{
			
			if (player.canSee(playerServerList[i].getPlayer()))
			{
				
				if (!names.equals(Strings.LightYellow + "Connected Players: " + Strings.Yellow))
				{
					names += ", ";
				}
				names += playerServerList[i].getDisplayName();
			}
		}
		sender.sendMessage(names);
	}
}