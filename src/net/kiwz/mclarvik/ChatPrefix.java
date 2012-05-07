package net.kiwz.mclarvik;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatPrefix implements Listener
{
	
	@EventHandler
	public void FormatChat(PlayerChatEvent chat)
	{
		Player ChattingPlayer = chat.getPlayer();
		String PlayerName = ChattingPlayer.getName();
		String DisplayName = ChattingPlayer.getDisplayName();
		String Msg = chat.getMessage();
		
		String builder = "[Builder] ";
		String vip = "[VIP] ";
		String admin = "[Admin] ";
		String owner = "[Owner] ";
		String colon = ": ";
		String white = Strings.White;
		String green = Strings.Green;
		String red = Strings.Red;
		String yellow = Strings.Yellow;
		
		chat.setFormat(builder + DisplayName + colon + Msg);
		
		if (ChattingPlayer.hasPermission("mclarvik.vip"))
		{
			chat.setFormat(green + vip + DisplayName + colon + white + Msg);
		}
		
		if (ChattingPlayer.hasPermission("mclarvik.admin"))
		{
			chat.setFormat(red + admin + DisplayName + colon + white + Msg);
			
			if (PlayerName != DisplayName)
			{
				chat.setFormat(builder + DisplayName + colon + white + Msg);
			}
		}
		
		if (ChattingPlayer.hasPermission("mclarvik.owner"))
		{
			chat.setFormat(yellow + owner + DisplayName + colon + white + Msg);
			
			if (PlayerName != DisplayName)
			{
				chat.setFormat(builder + DisplayName + colon + white + Msg);
			}
		}
	}
}
