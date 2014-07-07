package com.lewys.arcade;

import java.util.HashMap;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Lobby 
{
	static HashMap<Player, BukkitRunnable> countdown = new HashMap<Player, BukkitRunnable>();
	
	
	private static int Task1;
	
	private static int timer = 20;
	
	/* Next Games int IDs  
	 * 1 = PorkChopRace
	 * 2 = Wolfs
	 * 3 = null
	 * 4 = null
	 * */ 
	
	public static void doLobby(final int next_game,final String Game_Name)
	{
		
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
			countdown.put(p, new BukkitRunnable(){

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
							PorkChopRace.start();
						}
						
						cancel();
					}
				}
				
			});
			
			Bukkit.getScheduler().runTaskTimer(Main.getPluginInstance(), countdown.get(p), 0, 20);
		}	
	}
}
