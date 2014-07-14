package com.lewys.arcade;

import me.confuser.barapi.BarAPI;

import org.bukkit.entity.Player;

public class BarManager {

	
	/*
	 * Maybe if we keep all the bar shit in one class, we will solve the bugs.
	 */
	
	public static void setBar(Player p, String s, float time)
	{
		BarAPI.setMessage(p, s, 100F);
	}
	public static void setBar(Player p, String s){
		BarAPI.setMessage(p, s);
	}
	public static void setGlobalBar(String s){
		BarAPI.setMessage(s);
	}
	public static void removeBar(Player p)
	{
		BarAPI.removeBar(p);
	}
}
