package net.kiwz.larvikgaming.utils;

public class ConfigHeader {
	
	public static String header() {
		
		String n = "\n";
		
		String s01 = "Configurations for LarvikGaming plugin!" + n;
		String s02 = "Author: Kiwz!" + n;
		String s03 = "Remember to keep it in yml format!" + n + n;
		String s04 = "</mcl> <mclarvik.info> Displayes  some information about this plugin." + n;
		String s05 = "</mclreload> <mclrvik.reload> Reloads this plugin." + n;
		String s06 = "</mclrestart> <mclarvik.restart> Restarts the server completly." + n;
		String s07 = "</mclcopy> <mclarvik.copy> Copies all the files in 'FilesToCopy'." + n;
		String s08 = "</mclgroups> <mclarvik.groups> Generates a txt file with bPerm's users sortet into groups." + n + n;
		String s081 = "</mclmoney> <mclarvik.money> Generates a txt file with a list of all bank accounts that has more than 500Dollars." + n + n;
		String s09 = "autoMsgDelayInMin = How many minutes between each line in the 'messages.txt' is broadcastet to all players. 0 to disable this feature." + n;
		String s10 = "autoMsgToConsole = If 'true' all messages will also be broadcastet in your console" + n;
		String s11 = "autoMsgPrefix = This text will be added in front of each broadcastet message, you can use regular color codes here." + n;
		String s12 = "RestartTimeInHours = Time in hours between each server restart, remember to make a loop in your 'start.bat' file as this will just stop the server! 0 to disable this feature." + n;
		String s13 = "FileDir = Where you want this plugin to store all the files it handles. I.e. 'C:/whatever/you/like'." + n;
		String s14 = "PluginsList = If 'true' it will make a plugins.html file that contains a list of all your plugins." + n;
		String s15 = "FileCopyDelayInMin = How many minutes between each time it will copy the files in 'FilesToCopy'. 0 to disable this feature." + n;
		String s16 = "LogFileCopyTime = If 'true' it will log the time it takes to copy each file in 'FilesToCopy'." + n;
		String s17 = "FilesToCopy = A list of all the files you want to copy, remember to format the list the same way as the default list." + n;
		String s18 = "FilesToSave = A list containing the new name of the copyed files, remember that the order is important." + n;
		String s19 = "LogGetGroupTime = If 'true' it will log the time it takes to generate the 'users.txt' file." + n;
		String s20 = "RefreshGroupInMin = How many minutes between each time the 'users.txt' file will be uppdated.  0 to disable this feature. Set to 0 if you do not have bPermissions!" + n;
		String s21 = "WorldToGetGroupFrom = I am sorry to tell that this is not a multiworld plugin, so choose what world you want to get groups from." + n;
		String s22 = "Groups = A list of what groups you care about." + n;
		String s221 = "AccountBackuppOnServerStart = If 'true' it will make a backupp file of all your players Economy accounts and town/nation accounts if you have Towny." + n;
		String s23 = "TimeFormat = This is how you want the time stamp to look like in front of all lines in the next logging files. See http://docs.oracle.com/javase/1.4.2/docs/api/java/text/SimpleDateFormat.html" + n;
		String s24 = "CmdToLog1 = A list of all the commands you want to log to 'Cmd-Logger1.txt'." + n;
		String s25 = "CmdToLog2 = A list of all the commands you want to log to 'Cmd-Logger2.txt'." + n;
		String s26 = "PrivMsgToLog = A list of all the commands your players use to whisper, this will be logged to 'Private-Msg.txt'." + n;
		String s27 = "Chat = If 'true' it will log the chat from your players to 'Chat.txt'." + n;
		String s28 = "Full-Logger = If 'true' it will log everything to 'Full-Logger.txt'" + n;
		String s29 = "Fine-Logger = If 'true' it will log all finest, finer and fine levels to 'Fine-Logger.txt' (theese levels are almost never in use)" + n;
		String s30 = "Info-Logger = If 'true' it will log all info levels to 'Info-Logger.txt'" + n;
		String s31 = "Warning-Logger = If 'true' it will log all warning levels to 'Warning-Logger.txt'" + n;
		String s32 = "Severe-Logger = If 'true' it will log all severe levels to 'Severe-Logger.txt'" + n;
		
		String header = s01 + s02 + s03 + s04 + s05 + s06 + s07 + s08 + s081 + s09 + s10 +
				s11 + s12 + s13 + s14 + s15 + s16 + s17 + s18 + s19 + s20 +
				s21 + s22 + s221 + s23 + s24 + s25 + s26 + s27 + s28 + s29 + s30 +
				s31 + s32;
		
		return header;
	}
}