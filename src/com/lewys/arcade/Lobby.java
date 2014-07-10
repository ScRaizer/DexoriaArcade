package com.lewys.arcade;

import games.PorkChopRace;

import java.util.HashMap;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
	
	static /* Next Games int IDs  
	 * 1 = PorkChopRace
	 * 2 = Wolfs
	 * 3 = Explosive Spleef
	 * 4 = Color Tag
	 * */ 
	boolean removebar = false;
	
	public static void doLobby(final int next_game,final String Game_Name)
	{
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.getInventory().clear();
			p.getInventory().setHelmet(null);
			p.getInventory().setChestplate(null);
			p.getInventory().setLeggings(null);
			p.getInventory().setBoots(null);
			
			p.teleport(new Location(Bukkit.getWorld("world"), -201, 5.5, -1345));
		}
		
		GameName = Game_Name;
		Bukkit.broadcastMessage(ChatColor.BLUE + "Arcade >" + ChatColor.WHITE + "The next game is:" + ChatColor.GOLD + Game_Name);
		
		Task1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable()
		{

			@Override
			public void run() 
			{
				if(timer == 0)
				{
				
				if(Bukkit.getOnlinePlayers().length < 2)
				{
					
					Bukkit.broadcastMessage(ChatColor.GRAY + "Not enough players! Reseting the timer.");
					
					timer = 20;
					return;
				}
				else
				{
					
				removebar = true;	
					
				timer = 20;
				
				for(Player p :Bukkit.getOnlinePlayers())
				{
					BarAPI.removeBar(p);
				}
				
				if(next_game == 1)
				{
					PorkChopRace.doPorkChopRace();
				}
				
				Bukkit.getScheduler().cancelTask(Task1);
				
					}
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
					if(removebar == true)
					{
						BarAPI.removeBar(p);
						cancel();
					}
					
					BarAPI.setMessage(p, ChatColor.BLACK + "" + ChatColor.BOLD +  Game_Name 
							+ ChatColor.WHITE + " Starting in " + timer , 100f);	
				}
				
			});
			
			Bukkit.getScheduler().runTaskTimer(Main.instance, countdown.get(p), 0, 20);
		}	
	}
	
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent e)
	{
		if(GameState.isState(GameState.IN_LOBBY))
		{
			countdown.put(e.getPlayer(), new BukkitRunnable()
			{

				@Override
				public void run() 
				{
					BarAPI.setMessage(e.getPlayer(), ChatColor.BLACK + "" + ChatColor.BOLD +  GameName 
							+ ChatColor.WHITE + " Starting in " + timer , 100);
				}
			});
			
			Bukkit.getScheduler().runTaskTimer(Main.instance, countdown.get(e.getPlayer()), 0, 20);
		}
	}
}
