package net.kiwz.mclarvik;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.dynmap.DynmapAPI;

import me.desmin88.mobdisguise.api.MobDisguiseAPI;

public class Disguise implements CommandExecutor
{
	private Mclarvik plugin;
	private DynmapAPI DynmapAPI;
	
	
	public Disguise(Mclarvik plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = null;
		
		if (sender instanceof Player)
		{
			player = (Player) sender;
		}

		if (player == null)
		{
			sender.sendMessage("[MCLarvik] This command can only be run by a player!");
			return true;
		}
		
		if (player != null)
		{
			
			//Disguise as another player Commands:
			if (cmd.getName().equalsIgnoreCase("j"))
			{
				
				if (args.length == 1)
				{
					args[0].equals(cmd.getName());
					
					if (player.hasPermission("mclarvik.j") || player.hasPermission("mclarvik.*"))
					{
						
						Player[] playerServerList = Bukkit.getOnlinePlayers();
						for (int i=0; i < playerServerList.length; i++)
						{
							
							if (!playerServerList[i].getName().equalsIgnoreCase(args[0]))
							{
								
								if (player.getName() == player.getDisplayName() && !MobDisguiseAPI.isDisguised(player))
								{
									Bukkit.getServer().broadcastMessage("\u00A7e" + args[0] + " joined the game.");
									player.setPlayerListName(args[0]);
									MobDisguiseAPI.disguisePlayerAsPlayer(player, args[0]);
									
								}
								
								else if (player.getName() != player.getDisplayName() && MobDisguiseAPI.isDisguised(player))
								{
									player.sendMessage("\u00A7cYou are another player! Type </q>");
								}
								
								if (player.getName() == player.getDisplayName() && MobDisguiseAPI.isDisguised(player))
								{
									player.sendMessage("\u00A7cYou are a Mob! Type </mob>");
								}
							}
							
							if (playerServerList[i].getName().equalsIgnoreCase(args[0]))
							{
								player.sendMessage(Strings.LightRed + "You cannot be the same as another Online player!");
							}	
						}
					}
				}
				return true;
			}
			
			if (cmd.getName().equalsIgnoreCase("q"))
			{
				
				if (player.hasPermission("mclarvik.q") || player.hasPermission("mclarvik.*"))
				{
					
					if (player.getName() != player.getDisplayName())
					{
						Bukkit.getServer().broadcastMessage("\u00A7e" + player.getDisplayName() + " left the game.");
						player.setPlayerListName(player.getName());
						MobDisguiseAPI.undisguisePlayerAsPlayer(player, player.getName());
					}
					
					else if (player.getName() == player.getDisplayName() && !MobDisguiseAPI.isDisguised(player))
					{
						player.sendMessage("\u00A7cYou are you! Type </j PlayerName>");
					}
					
					if (player.getName() == player.getDisplayName() && MobDisguiseAPI.isDisguised(player))
					{
						player.sendMessage("\u00A7cYou are a Mob! Type </mob>");
					}
				}
				return true;
			}
			
			//Disguise as a mob Commands:
			if (cmd.getName().equalsIgnoreCase("mob"))
			{
				
				if (args.length == 1)
				{
					args[0].equals(cmd.getName());
					
					if (player.hasPermission("mclarvik.mob") || player.hasPermission("mclarvik.*"))
					{
						
						if (player.getName() == player.getDisplayName() && !MobDisguiseAPI.isDisguised(player))
						{
							player.sendMessage("\u00A7aYour MobDisguise is: <" + args[0] + "> if it excist.");
							player.sendMessage("\u00A7aFor more information use the </md stats> command!");
							MobDisguiseAPI.disguisePlayer(player, args[0]);
							
							//If Dynmap then hide from Dynmap
							Plugin Dynmap = Bukkit.getServer().getPluginManager().getPlugin("dynmap");
							
							if (Dynmap != null)
							{
								DynmapAPI.setPlayerVisiblity(player, false);
								sender.sendMessage(Strings.Turquoise + "Hiding you from Dynmap!");
							}
						}
						
						else if (player.getName() == player.getDisplayName() && MobDisguiseAPI.isDisguised(player))
						{
							player.sendMessage("\u00A7cYou are allready a Mob! Type </mob>");
						}
						
						if (player.getName() != player.getDisplayName() && MobDisguiseAPI.isDisguised(player))
						{
							player.sendMessage("\u00A7cYou are another player! Type </q>");
						}
					}
				}
				
				if (args.length == 0)
				{
					
					if (player.hasPermission("mclarvik.mob") || player.hasPermission("mclarvik.*"))
					{
						
						if (player.getName() == player.getDisplayName() && MobDisguiseAPI.isDisguised(player))
						{
							player.sendMessage("\u00A7aYou are you again!");
							MobDisguiseAPI.undisguisePlayer(player);
							
							//If Dynmap then show on Dynmap
							Plugin Dynmap = Bukkit.getServer().getPluginManager().getPlugin("dynmap");
							
							if (Dynmap != null)
							{
								DynmapAPI.setPlayerVisiblity(player, true);
								sender.sendMessage(Strings.Turquoise + "You are visibil on Dynmap!");
							}
						}
						
						else if (player.getName() == player.getDisplayName() && !MobDisguiseAPI.isDisguised(player))
						{
							player.sendMessage("\u00A7cYou are you! Type </mob MobName>");
						}
						
						if (player.getName() != player.getDisplayName() && MobDisguiseAPI.isDisguised(player))
						{
							player.sendMessage("\u00A7cYou are another player! Type </q>");
						}
					}
				}
				return true;
			}
		}
		return false;
	}
}
