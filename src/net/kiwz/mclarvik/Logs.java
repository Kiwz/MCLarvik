package net.kiwz.mclarvik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Logs implements CommandExecutor
{
	private Mclarvik plugin;
	
	public Logs(Mclarvik plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("uppdate"))
		{
			if(sender.hasPermission("mclarvik.uppdate") || sender.hasPermission("mclarvik.*"))
			{
				serverlog();
				bans();
				sender.sendMessage(Strings.Green + "All logs has been uppdated to the homepage!");
				
			}
			return true;
		}
		return false;
	}
	
	public static void serverlog()
	{
		String inPath = "server.log";
		String outPath = Mclarvik.savedir + "server.txt";
		
		File inFile = new File(inPath);
		File outFile = new File(outPath);
		Scanner in = null;
		PrintStream out = null;
	
		try
		{
			in = new Scanner(inFile);
			out = new PrintStream(outFile);
		}
		
		catch (FileNotFoundException e)
		{
			
		}
		
		while (in.hasNext())
		{
			out.println(in.nextLine());
		}
	}
	
	public static void bans()
	{
		String inPath = "plugins/CommandBook/bans.csv";
		String outPath = Mclarvik.savedir + "bans.txt";
		
		File inFile = new File(inPath);
		File outFile = new File(outPath);
		Scanner in = null;
		PrintStream out = null;
	
		try
		{
			in = new Scanner(inFile);
			out = new PrintStream(outFile);
		}
		
		catch (FileNotFoundException e)
		{
			
		}
		
		while (in.hasNext())
		{
			out.println(in.nextLine());
		}
	}
}
