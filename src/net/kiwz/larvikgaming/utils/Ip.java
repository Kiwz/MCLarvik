package net.kiwz.larvikgaming.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
/**
 * Represents ip-addresses.
 */
public class Ip {
    /**
     * Get your current WAN IP address.
     * @return Your IP as String.
     */
	public static String getWanIp() {
		String ip = "*";
		System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:12.0) Gecko/20100101 Firefox/12.0");
		try {
			URL whatismyip = new URL("http://automation.whatismyip.com/n09230945.asp");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			ip = in.readLine();
		}
		catch (IOException e) {
		}
		return ip;
	}
}
