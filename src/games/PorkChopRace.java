package games;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.lewys.arcade.util.particles.ParticleEffect;

public class PorkChopRace implements Listener
{
	List<Entity> pigs = new ArrayList<Entity>();
	
	public void doPorkChopRace()
	{
		Location loc = new Location(Bukkit.getWorld("world"), -120.5, 7.5,1410.5);
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.teleport(loc);
			ParticleEffect.FLAME.display(loc, 0.3f, 1f, 0.3f, 0.1f, 20);
			
			loc.setX(loc.getX() -2);

		}		
	}
	
	/* 
	 * This will spawn 30 pigs in the pen
	 * */
	public void spawnPigs()
	{
		for(int i = 0; i <30; i++)
		{
			int x = (int) (Math.random() * (-20.5 - -48.5 + 1) + -48.5);
			double y = -6.5;
			int z = (int) (Math.random() * (-1406.5  -  -1428.5 + 1) + -1428.5);
			
			Location loc = new Location(Bukkit.getWorld("world"), x,y,z);
			
			Bukkit.getWorld("world").spawnEntity(loc, EntityType.PIG);
		}
	}
}
