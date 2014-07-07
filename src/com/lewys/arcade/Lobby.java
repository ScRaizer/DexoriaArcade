package com.lewys.arcade;

import java.util.HashMap;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Lobby implements Listener
{
	static HashMap<Player, BukkitRunnable> countdown = new HashMap<Player, BukkitRunnable>();
	
	
	private static int Task1;
	
	private static int timer = 20;
	
	private static String GameName;
	
	/* Next Games int IDs  
	 * 1 = PorkChopRace
	 * 2 = Wolfs
	 * 3 = null
	 * 4 = null
	 * */ 
	
	public static void doLobby(final int next_game,final String Game_Name)
	{
		
		GameName = Game_Name;
		
		Task1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPluginInstance(), new Runnable()
		{

			@Override
			public void run() 
			{
				if(timer == 0)
				{
				timer = 20;
				Bukkit.getScheduler().cancelTask(Task1);
				}
				
				timer --;
			}
			
		}, 0, 20);
		
		for(final Player p : Bukkit.getOnlinePlayers())
		{
			countdown.put(p, new BukkitRunnable()
			{

				@Override
				public void run() 
				{
					BarAPI.setMessage(p, ChatColor.BLACK + "" + ChatColor.BOLD +  Game_Name 
							+ ChatColor.WHITE + " Starting in " + timer , 100);
					
					if(timer == 0)
					{
						countdown.remove(p);
						
						if(next_game == 1)
						{
							//PorkChopRace.start();
							GameState.setState(GameState.GAME_1);
						}
						
						cancel();
					}
				}
				
			});
			
			Bukkit.getScheduler().runTaskTimer(Main.getPluginInstance(), countdown.get(p), 0, 20);
		}	
	}
	
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent e)
	{
		if(GameState.isState(GameState.IN_LOBBY))
		{
			countdown.put(e.getPlayer(), new BukkitRunnable(){

				@Override
				public void run() 
				{
					BarAPI.setMessage(e.getPlayer(), ChatColor.BLACK + "" + ChatColor.BOLD +  GameName 
							+ ChatColor.WHITE + " Starting in " + timer , 100);
				}
			});
			
			Bukkit.getScheduler().runTaskTimer(Main.getPluginInstance(), countdown.get(e.getPlayer()), 0, 20);
		}
	}
}
