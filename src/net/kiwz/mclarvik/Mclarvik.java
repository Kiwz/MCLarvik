package net.kiwz.mclarvik;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Mclarvik extends JavaPlugin
{
	private Disguise DisguiseCmds;
	private Logs UppdateCmd;
	private Online OnlineCmd;
	public static String savedir = "plugins/dynmap/web/admin/";
	public static Logger log = Logger.getLogger("Minecraft");
	

	public void onEnable()
	{
		PluginDescriptionFile pdFile = getDescription();
		log.info("[" + pdFile.getName() + "]" + " v" + pdFile.getVersion() + " ENABLED! created by: Kiwz");
		
		getServer().getPluginManager().registerEvents(new ChatPrefix(), this);
		
		Hooks.HookMsg();
		
		PluginsList.build();
		
		DisguiseCmds = new Disguise(this);
		getCommand("j").setExecutor(DisguiseCmds);
		getCommand("q").setExecutor(DisguiseCmds);
		getCommand("mob").setExecutor(DisguiseCmds);
		
		UppdateCmd = new Logs(this);
		getCommand("uppdate").setExecutor(UppdateCmd);
		
		OnlineCmd = new Online(this);
		getCommand("online").setExecutor(OnlineCmd);
	}

	public void onDisable()
	{
		PluginDescriptionFile pdFile = getDescription();
		log.info("[" + pdFile.getName() + "] v" + pdFile.getVersion() + " DISABLED!");
	}
	
/*	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Player sender = null;
		
		if (sender instanceof Player)
		{
			player = (Player) sender;
		}
		
		if (cmd.getName().equalsIgnoreCase("online"))
		{
			Online.cmdOnline(sender);
			return true;
		}
		
		if (sendingplayer != null)
		{
			if (cmd.getName().equalsIgnoreCase("j"))
			{
				if (args.length == 1)
				{
					args[0].equals(cmd.getName());
					
					if (sender.hasPermission("mclarvik.j") || sender.hasPermission("mclarvik.*"))
					{
						if (sendingplayer.getName() == sendingplayer.getDisplayName() && !MobDisguiseAPI.isDisguised(sendingplayer))
						{
							getServer().broadcastMessage("\u00A7e" + args[0] + " joined the game.");
							sendingplayer.setPlayerListName(args[0]);
							MobDisguiseAPI.disguisePlayerAsPlayer(sendingplayer, args[0]);
							
						}
						else if (sendingplayer.getName() != sendingplayer.getDisplayName() && MobDisguiseAPI.isDisguised(sendingplayer))
						{
							sender.sendMessage("\u00A7cYou are another player! Type </q>");
						}
						if (sendingplayer.getName() == sendingplayer.getDisplayName() && MobDisguiseAPI.isDisguised(sendingplayer))
						{
							sender.sendMessage("\u00A7cYou are a Mob! Type </mob>");
						}
					}
				}
				return true;
			}
			
			if (cmd.getName().equalsIgnoreCase("q"))
			{
				if (sender.hasPermission("mclarvik.q") || sender.hasPermission("mclarvik.*"))
				{
					if (sendingplayer.getName() != sendingplayer.getDisplayName())
					{
						getServer().broadcastMessage("\u00A7e" + sendingplayer.getDisplayName() + " left the game.");
						sendingplayer.setPlayerListName(sendingplayer.getName());
						MobDisguiseAPI.undisguisePlayerAsPlayer(sendingplayer, getName());
					}
					else if (sendingplayer.getName() == sendingplayer.getDisplayName() && !MobDisguiseAPI.isDisguised(sendingplayer))
					{
						sender.sendMessage("\u00A7cYou are you! Type </j PlayerName>");
					}
					if (sendingplayer.getName() == sendingplayer.getDisplayName() && MobDisguiseAPI.isDisguised(sendingplayer))
					{
						sender.sendMessage("\u00A7cYou are a Mob! Type </mob>");
					}
				}
				return true;
			}
			
			if (cmd.getName().equalsIgnoreCase("mob"))
			{
				if (args.length == 1)
				{
					args[0].equals(cmd.getName());
					
					if (sender.hasPermission("mclarvik.mob") || sender.hasPermission("mclarvik.*"))
					{
						if (sendingplayer.getName() == sendingplayer.getDisplayName() && !MobDisguiseAPI.isDisguised(sendingplayer))
						{
							sender.sendMessage("\u00A7aYour MobDisguise is: <" + args[0] + "> if it excist.");
							sender.sendMessage("\u00A7aFor more information use the </md stats> command!");
							MobDisguiseAPI.disguisePlayer(sendingplayer, args[0]);
							dynmap.setPlayerVisiblity(sendingplayer, false);
						}
						else if (sendingplayer.getName() == sendingplayer.getDisplayName() && MobDisguiseAPI.isDisguised(sendingplayer))
						{
							sender.sendMessage("\u00A7cYou are allready a Mob! Type </mob>");
						}
						if (sendingplayer.getName() != sendingplayer.getDisplayName() && MobDisguiseAPI.isDisguised(sendingplayer))
						{
							sender.sendMessage("\u00A7cYou are another player! Type </q>");
						}
					}
				}
				if (args.length == 0)
				{
					if (sender.hasPermission("mclarvik.mob") || sender.hasPermission("mclarvik.*"))
					{
						if (sendingplayer.getName() == sendingplayer.getDisplayName() && MobDisguiseAPI.isDisguised(sendingplayer))
						{
							sendingplayer.sendMessage("\u00A7aYou are you again!");
							MobDisguiseAPI.undisguisePlayer(sendingplayer);
							dynmap.setPlayerVisiblity(sendingplayer, true);
						}
						else if (sendingplayer.getName() == sendingplayer.getDisplayName() && !MobDisguiseAPI.isDisguised(sendingplayer))
						{
							sender.sendMessage("\u00A7cYou are you! Type </mob MobName>");
						}
						if (sendingplayer.getName() != sendingplayer.getDisplayName() && MobDisguiseAPI.isDisguised(sendingplayer))
						{
							sender.sendMessage("\u00A7cYou are another player! Type </q>");
						}
					}
				}
				return true;
			}
		}
		
		if (sendingplayer == null)
		{
			sender.sendMessage("You need to be a player!");
			return true;
		}
		return false;
	}*/
}