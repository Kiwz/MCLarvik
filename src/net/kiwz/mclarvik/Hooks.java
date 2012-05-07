package net.kiwz.mclarvik;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Hooks
{
	private static Plugin Dynmap = Bukkit.getServer().getPluginManager().getPlugin("dynmap");
	private static Plugin MobDisguise = Bukkit.getServer().getPluginManager().getPlugin("MobDisguise");
	
	public static void HookMsg()
	{
		if (MobDisguise != null)
		{
			Mclarvik.log.info("[MCLarvik] You have MobDisguise! Hooking enabled!");
		}
		if (MobDisguise == null)
		{
			Mclarvik.log.info("[MCLarvik] You do not have MobDisguise! Hooking disabled!");
		}
		
		if (Dynmap != null)
		{
			Mclarvik.log.info("[MCLarvik] You have Dynmap! Hooking enabled!");
		}
		if (Dynmap == null)
		{
			Mclarvik.log.info("[MCLarvik] You do not have Dynmap! Hooking disabled!");
		}
	}
}
