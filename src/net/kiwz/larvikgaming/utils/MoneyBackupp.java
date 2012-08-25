package net.kiwz.larvikgaming.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

import com.nijikokun.register.payment.Methods;

public class MoneyBackupp {

	private boolean towny = Bukkit.getServer().getPluginManager().isPluginEnabled("Towny");
	private boolean register = Bukkit.getServer().getPluginManager().isPluginEnabled("Register");
	//private boolean hasMethod = Methods.hasMethod();
	private Plugin larvikGaming = Bukkit.getServer().getPluginManager().getPlugin("LarvikGaming");
	private String fileDir = larvikGaming.getConfig().getString("FileDir", "plugins/dynmap/web/admin") + "/backupp/";
	private DecimalFormat form = new DecimalFormat("#");
	
	public void nationHoldings() {
		
		if (!towny || !register || !Methods.hasMethod()) {
			return;
		}
		Format  sdf = new SimpleDateFormat(larvikGaming.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		Date date = new Date();
		String timeStamp = sdf.format(date);
		File inFile = new File("plugins/Towny/data/nations.txt");
		File outFile = new File(fileDir + "Nation-Holdings.txt");
		Scanner scanner = null;
		PrintStream printStream = null;
		try {
			scanner = new Scanner(inFile);
			printStream = new PrintStream(outFile);
		} catch (FileNotFoundException e) {
		}
		long timeStart = System.currentTimeMillis();
		printStream.println("========================================");
		printStream.println("Nations money as of: " + timeStamp);
		printStream.println("========================================");
		while (scanner.hasNextLine()) {
			String name = scanner.nextLine();
			String accountName = "nation-" + name;
			double balance = 0;
			if (Methods.getMethod().hasAccount(accountName.toLowerCase())) {
				balance = Methods.getMethod().getAccount(accountName.toLowerCase()).balance();
				if (balance > 100.0) {
					printStream.println(name + ": " + form.format(balance));
				}
			}
		}
		printStream.println("-----------------------------------------------");
		long time = System.currentTimeMillis() - timeStart;
		printStream.println("Time used to generate this information: " + time + "ms.");
		printStream.println("-----------------------------------------------");
		scanner.close();
		printStream.close();
	}
	
	public void townHoldings() {
		
		if (!towny || !register || !Methods.hasMethod()) {
			return;
		}
		Format  sdf = new SimpleDateFormat(larvikGaming.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		Date date = new Date();
		String timeStamp = sdf.format(date);
		File inFile = new File("plugins/Towny/data/towns.txt");
		File outFile = new File(fileDir + "Town-Holdings.txt");
		Scanner scanner = null;
		PrintStream printStream = null;
		try {
			scanner = new Scanner(inFile);
			printStream = new PrintStream(outFile);
		} catch (FileNotFoundException e) {
		}
		long timeStart = System.currentTimeMillis();
		printStream.println("======================================");
		printStream.println("Towns money as of: " + timeStamp);
		printStream.println("======================================");
		while (scanner.hasNextLine()) {
			String name = scanner.nextLine();
			String accountName = "town-" + name;
			double balance = 0;
			if (Methods.getMethod().hasAccount(accountName.toLowerCase())) {
				balance = Methods.getMethod().getAccount(accountName.toLowerCase()).balance();
				if (balance > 100.0) {
					printStream.println(name + ": " + form.format(balance));
				}
			}
		}
		printStream.println("-----------------------------------------------");
		long time = System.currentTimeMillis() - timeStart;
		printStream.println("Time used to generate this information: " + time + "ms.");
		printStream.println("-----------------------------------------------");
		scanner.close();
		printStream.close();
	}
	
	public void nationAndTownWithSmallAmountOfMoney() {
		
		if (!towny || !register || !Methods.hasMethod()) {
			return;
		}
		File inFile1 = new File("plugins/Towny/data/nations.txt");
		File inFile2 = new File("plugins/Towny/data/towns.txt");
		File outFile = new File(fileDir + "Poor-Nations-Towns.txt");
		Scanner scanner1 = null;
		Scanner scanner2 = null;
		PrintStream printStream = null;
		try {
			scanner1 = new Scanner(inFile1);
			scanner2 = new Scanner(inFile2);
			printStream = new PrintStream(outFile);
		} catch (FileNotFoundException e) {
		}
		long timeStart = System.currentTimeMillis();
		Format  sdf = new SimpleDateFormat(larvikGaming.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		Date date = new Date();
		String timeStamp = sdf.format(date);
		printStream.println("============================================================");
		printStream.println("Nations with less than 1000Dollar as of: " + timeStamp);
		printStream.println("============================================================");
		while (scanner1.hasNextLine()) {
			String name = scanner1.nextLine();
			String accountName = "nation-" + name;
			double balance = 0;
			if (Methods.getMethod().hasAccount(accountName.toLowerCase())) {
				balance = Methods.getMethod().getAccount(accountName.toLowerCase()).balance();
				if (balance < 1000.0) {
					printStream.println(name + ": " + form.format(balance));
				}
			}
		}
		printStream.println("");
		printStream.println("==========================================================");
		printStream.println("Towns with less than 1000Dollar as of: " + timeStamp);
		printStream.println("==========================================================");
		while (scanner2.hasNextLine()) {
			String name = scanner2.nextLine();
			String accountName = "town-" + name;
			double balance = 0;
			if (Methods.getMethod().hasAccount(accountName.toLowerCase())) {
				balance = Methods.getMethod().getAccount(accountName.toLowerCase()).balance();
				if (balance < 1000.0) {
					printStream.println(name + ": " + form.format(balance));
				}
			}
		}
		printStream.println("-----------------------------------------------");
		long time = System.currentTimeMillis() - timeStart;
		printStream.println("Time used to generate this information: " + time + "ms.");
		printStream.println("-----------------------------------------------");
		scanner1.close();
		scanner2.close();
		printStream.close();
	}
	
	public void playerHoldings() {
		
		if (!register || !Methods.hasMethod()) {
			return;
		}
		Format  sdf = new SimpleDateFormat(larvikGaming.getConfig().getString("TimeFormat", "yyyy-MM-dd HH:mm:ss"));
		Date date = new Date();
		String timeStamp = sdf.format(date);
		File outFile = new File(fileDir + "Player-Holdings.txt");
		PrintStream printStream = null;
		try {
			printStream = new PrintStream(outFile);
		} catch (FileNotFoundException e) {
		}
		long timeStart = System.currentTimeMillis();
		printStream.println("========================================");
		printStream.println("Players money as of: " + timeStamp);
		printStream.println("========================================");
		OfflinePlayer[] p = Bukkit.getOfflinePlayers();
		for (int i = 0; i < p.length; i++) {
			String name = p[i].getName();
			double balance = 0;
			if (Methods.getMethod().hasAccount(name.toLowerCase())) {
				balance = Methods.getMethod().getAccount(name.toLowerCase()).balance();
				if (balance > 100.0) {
					printStream.println(name + ": " + form.format(balance));
				}
			}
		}
		printStream.println("-----------------------------------------------");
		long time = System.currentTimeMillis() - timeStart;
		printStream.println("Time used to generate this information: " + time + "ms.");
		printStream.println("-----------------------------------------------");
		printStream.close();
	}
}
