package com.lewys.arcade;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
	public static Main instance; 
	
	public void onEnable()
	{
		instance = this;
		
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new Lobby(), this);
	}
	
	public static Main getPluginInstance()
	{
		return instance;
	}
}
