package com.lewys.arcade;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Lobby 
{
	HashMap<Player, BukkitRunnable> countdown = new HashMap<Player, BukkitRunnable>();
	
	private static int nextGame = 1;
	
	private static int Task1;
	
	public static void doLobby(int next_game)
	{
		nextGame = next_game;
		
		
		
	}
}
