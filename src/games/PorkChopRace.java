package games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.lewys.arcade.GameState;
import com.lewys.arcade.Lobby;
import com.lewys.arcade.Main;
import com.lewys.arcade.util.particles.ParticleEffect;
import com.sk89q.minecraft.util.commands.ChatColor;

public class PorkChopRace implements Listener
{
	static List<Entity> pigs = new ArrayList<Entity>();
	static HashMap<Player, Integer> Amount_killed = new HashMap<Player, Integer>();
	
	static boolean Event = false;
	static boolean canMove = false;
	static int fireworks;
	
	public static void doPorkChopRace()
	{	
		GameState.setState(GameState.GAME_1);
		
		Event = true;
		
		Location loc = new Location(Bukkit.getWorld("world"), -120.5, 7.5,1410.5);
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.teleport(loc);
			p.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD));
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
			Pig p = (Pig) ent;
			
			p.setMaxHealth(30d);
			p.setHealth(30d);
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
	
	@EventHandler
	public void onPigHurt(EntityDamageByEntityEvent e)
	{
		if(Event == true)
		{
			if(e.getEntity() instanceof Pig)
			{
				if(e.getDamager() instanceof Player)
				{
					e.setDamage(10d);
					Pig ent = (Pig) e.getEntity();
					ent.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,120, 8));
				}
			}
		}
	}
	
	@EventHandler
	public void onPigKill(EntityDeathEvent e)
	{
		if(Event == true)
		{
		
		if(e.getEntity() instanceof Pig)
		{
			Player p = e.getEntity().getKiller();
			Amount_killed.put(p, Amount_killed.get(p) +1);
			
			p.getInventory().setItem(8, new ItemStack(Material.PORK, Amount_killed.get(p)));
			
			if(Amount_killed.get(p) >= 3)
			{
				p.sendMessage(ChatColor.BLUE + "PorkChopRace> " + ChatColor.WHITE + "You have collected enough bacon! Now return it to camp!");
			}
			else
			{
				int needed =  3 - Amount_killed.get(p);
				p.sendMessage(ChatColor.BLUE + "PorkChopRace> " + ChatColor.WHITE + "You need " + ChatColor.GOLD + needed + ChatColor.WHITE + " More slices!");
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e)
	{
		if(Event = true)
		{
		
		if(Amount_killed.get(e.getPlayer()) >= 3)
		{
		
		if(e.getPlayer().getLocation().getX() < -116)
		{
			doCelebration();
			Bukkit.broadcastMessage(ChatColor.BLUE + "PorkChopRace >" + ChatColor.WHITE + " Player " + ChatColor.GOLD + e.getPlayer().getName() + ChatColor.WHITE + " Has won!");
			
			for(Player p : Bukkit.getOnlinePlayers())
			{
				BarAPI.setMessage(p, ChatColor.BLUE + "PorkChopRace >" + ChatColor.WHITE + " Player " + ChatColor.GOLD + e.getPlayer().getName() + ChatColor.WHITE + " Has won!", 100f);
			}
			
				}
			}
		}
	}
	
	public static void doCelebration()
	{
		Event = false;
		
		 fireworks = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			   int times = 32;
			   
	      	   public void run() {
	      		   if(times >= 1){
	      			   times--;
	      	  	   int x = (int) (Math.random() * (-6 - -135 +1)) + -134;
	      	       int y = (int) (Math.random() * (45 - 35 + 1)) + 35;
	      	       int z = (int) (Math.random() * (-1379 -  -1453 +1)) + -1453;

	      	       Location meatball = new Location(Bukkit.getWorld("world"), x, y, z);
	      	       Firework f = (Firework) Bukkit.getWorld("world").spawn(meatball, Firework.class);
	      	       
	      	       FireworkMeta fm = f.getFireworkMeta();
	      	       fm.addEffects(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(Color.GREEN).flicker(true).trail(false).withFade(Color.RED).build());
	      	       fm.setPower(4);
	      	       f.setFireworkMeta(fm);
	      	       f.detonate();
	      		   }else{
	      			   doGameEnd();
	      			   Bukkit.getScheduler().cancelTask(fireworks);
	      		   }
	      	   }
	      },0, 5);  
	}
	
	public static void doGameEnd()
	{
		for(Entity ent : pigs)
		{
			ent.remove();
		}
		
		pigs.clear();
		Amount_killed.clear();
		
		Lobby.doLobby(1, "PorkChopRace");
		
		GameState.setState(GameState.IN_LOBBY);
	}
}
