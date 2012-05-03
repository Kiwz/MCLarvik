package net.kiwz.mclarvik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Serverlog
{
	public static void serverlog(String[] args)
	{
		String inPath = "C:/Users/Thomas/Desktop/MC Server/server.log"; // Input file path
		String outPath = (Mclarvik.savedir) + "server.txt"; // Output file path
		
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
		
		// Copy content in inFile to outFile
		while (in.hasNext())
		{
			out.println(in.nextLine());
		}
	}
}
