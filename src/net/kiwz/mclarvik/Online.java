package net.kiwz.mclarvik;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Online
{	
	public static void getOnlinePlayers(CommandSender sender)
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
		String PlayerOfMaxPlayers = "\u00A7e" + onlinePlayers + "/" + maxplayers + ":";
		sender.sendMessage(PlayerOfMaxPlayers);
	}
	
	public static void getPlayerListName(CommandSender sender)
	{
		Player[] playerServerList = Bukkit.getOnlinePlayers();
		String names = "\u00A7eConnected Players:\u00A76 ";
		
		for (int i = 0; i < playerServerList.length; i++)
		{
			if (!names.equals("\u00A7eConnected Players:\u00A76 "))
			{
				names += ", ";
			}
			if (!playerServerList[i].getPlayerListName().equals
			   (playerServerList[i].getDisplayName()))
			{
				names += "\u00A73" + playerServerList[i].getPlayerListName() + " (" +
									 playerServerList[i].getDisplayName() + ")\u00A76";
			}
			else
			{
				names += playerServerList[i].getPlayerListName();
			}
		}
		sender.sendMessage(names);
	}
	
	public static void getDisplayName(CommandSender sender)
	{
		Player[] playerServerList = Bukkit.getServer().getOnlinePlayers();
		String names = "\u00A7eConnected Players:\u00A76 ";
		Player player = null;
		
		if (sender instanceof Player)
		{
			player = (Player) sender;
		}
		
		for (int i = 0; i < playerServerList.length; i++)
		{
			if (player.canSee(playerServerList[i].getPlayer()))
			{
				if (!names.equals("\u00A7eConnected Players:\u00A76 "))
				{
					names += ", ";
				}
				names += playerServerList[i].getDisplayName();
			}
		}
		sender.sendMessage(names);
	}
}