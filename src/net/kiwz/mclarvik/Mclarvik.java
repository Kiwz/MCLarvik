package net.kiwz.mclarvik;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Mclarvik extends JavaPlugin implements Listener
{
	public static String savedir = "plugins/dynmap/web/admin/";
	public static Logger log = Logger.getLogger("Minecraft");

	public void onEnable()
	{
		PluginDescriptionFile pdFile = getDescription();
		log.info("[" + pdFile.getName() + "]" + " v" + pdFile.getVersion() + " ENABLED! created by: Kiwz");
		
		getServer().getPluginManager().registerEvents(this, this);
		
		Pluginslist.build();
		Tablist.removeName();
	}

	public void onDisable()
	{
		PluginDescriptionFile pdFile = getDescription();
		log.info("[" + pdFile.getName() + "] v" + pdFile.getVersion() + " DISABLED!");
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		
		if ((player.hasPermission("mclarvik.tablist")) || (player.hasPermission("mclarvik.*")))
		{
			event.getPlayer().sendMessage("hei");
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("online"))
		{
			if((sender.hasPermission("mclarvik.online")) || (sender.hasPermission("mclarvik.*")))
			{
				Online.getOnlinePlayers(sender);
				Online.getPlayerListName(sender);
			}
			else
			{
				Online.getOnlinePlayers(sender);
				Online.getDisplayName(sender);
			}
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("join"))
		{
			if(args.length == 1)
			{
				args[0].equals(cmd.getName());
				
				if((sender.hasPermission("mclarvik.join")) || (sender.hasPermission("mclarvik.*")))
				{
				getServer().broadcastMessage("\u00A7e" + args[0] + " joined the game.");
				}
			}
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("quit"))
		{
			if(args.length == 1)
			{
				args[0].equals(cmd.getName());
				
				if((sender.hasPermission("mclarvik.quit")) || (sender.hasPermission("mclarvik.*")))
				{
				getServer().broadcastMessage("\u00A7e" + args[0] + " left the game.");
				}
			}
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("uppdate"))
		{
			if((sender.hasPermission("mclarvik.uppdate")) || (sender.hasPermission("mclarvik.*")))
			{
				Bannedplayers.bans(args);
				Serverlog.serverlog(args);
				sender.sendMessage("\u00A72All logs has been uppdated to the homepage!");
			}
			return true;
		}
		return false;
	}
}