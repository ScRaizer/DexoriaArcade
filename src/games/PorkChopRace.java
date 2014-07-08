package games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.lewys.arcade.Main;
import com.lewys.arcade.util.particles.ParticleEffect;
import com.sk89q.minecraft.util.commands.ChatColor;

public class PorkChopRace implements Listener
{
	static List<Entity> pigs = new ArrayList<Entity>();
	static boolean Event = false;
	static boolean canMove = false;
	
	public static void doPorkChopRace()
	{	
		Event = true;
		
		Location loc = new Location(Bukkit.getWorld("world"), -120.5, 7.5,1410.5);
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.teleport(loc);
			ParticleEffect.FLAME.display(loc, 0.3f, 1f, 0.3f, 0.1f, 20);
			
			loc.setX(loc.getX() -2);
			
			p.playSound(loc, Sound.FALL_BIG, 2.0f, 2.0f);
		}	
		
		Bukkit.broadcastMessage(ChatColor.GREEN + "----" + ChatColor.WHITE + "ProkChopRace" + ChatColor.GREEN + "----");
		Bukkit.broadcastMessage(ChatColor.WHITE + "Race to the pen and kill 3 pigs");
		Bukkit.broadcastMessage(ChatColor.WHITE + "Then race back to the camp with the pork!");
		Bukkit.broadcastMessage(ChatColor.WHITE + "First one back with the bacon wins");
		Bukkit.broadcastMessage(ChatColor.AQUA + "Good luck!");
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable(){

			@Override
			public void run() {
				spawnPigs();
				canMove = true;
				Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "GO");
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
				p.playSound(p.getLocation(), Sound.PIG_DEATH, 2.0f, 2.0f);
				}
			}
			
		}, 60);
	}
	
	/* 
	 * This will spawn 30 pigs in the pen
	 * */
	public static void spawnPigs()
	{
		for(int i = 0; i <30; i++)
		{
			int x = (int) (Math.random() * (-20.5 - -48.5 + 1) + -48.5);
			double y = -6.5;
			int z = (int) (Math.random() * (-1406.5  -  -1428.5 + 1) + -1428.5);
			
			Location loc = new Location(Bukkit.getWorld("world"), x,y,z);
			
			Entity ent = Bukkit.getWorld("world").spawnEntity(loc, EntityType.PIG);
			pigs.add(ent);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* 
	 * Events
	 * */
	
	@EventHandler
	public void PlayerMove(PlayerMoveEvent e)
	{
		if(Event == true)
		{
			if(canMove == false)
			{
				e.setCancelled(true);
			}
		}
	}
}
