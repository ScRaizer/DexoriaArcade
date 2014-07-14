package com.lewys.arcade;

import me.confuser.barapi.BarAPI;

import org.bukkit.entity.Player;

public class BarManager {

	
	/*
	 * Maybe if we keep all the bar shit in one class, we will solve the bugs.
	 */
	
	public static void setBar(Player p, String Message)
	{
		BarAPI.setMessage(p, Message, 100f);
	}
	
	public static void removeBar(Player p)
	{
		BarAPI.removeBar(p);
	}
}
