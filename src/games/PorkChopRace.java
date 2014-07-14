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

import com.lewys.arcade.BarManager;
import com.lewys.arcade.GameState;
import com.lewys.arcade.Lobby;
import com.lewys.arcade.Main;
import com.lewys.arcade.util.particles.ParticleEffect;
import com.sk89q.minecraft.util.commands.ChatColor;

public class PorkChopRace implements Listener
{
	static List<Entity> pigs = new ArrayList<Entity>();
	static HashMap<Player, Integer> Amount_killed = new HashMap<Player, Integer>();
	
	static boolean event = false;
	static boolean slowness = false;
	static boolean canMove = false;
	static int fireworks;
	
	public static void doPorkChopRace()
	{	
		canMove = false;
		slowness = false;
		
		GameState.setState(GameState.GAME_1);
		
		event = true;
		
		Location loc = new Location(Bukkit.getWorld("world"), -120.5, 7, -1410.5);
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			
			p.teleport(loc);
			p.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD));
			ParticleEffect.FLAME.display(loc, 0.3f, 1f, 0.3f, 0.1f, 20);
			
			loc.setZ(loc.getZ() -2);
			
			p.playSound(loc, Sound.FALL_BIG, 2.0f, 2.0f);
			
			Amount_killed.put(p, 0);
		}	
		
		Bukkit.broadcastMessage(ChatColor.GREEN + "----" + ChatColor.WHITE + "ProkChopRace" + ChatColor.GREEN + "----");
		Bukkit.broadcastMessage(ChatColor.WHITE + "Race to the pen and kill 3 pigs");
		Bukkit.broadcastMessage(ChatColor.WHITE + "Then race back to the camp with the pork!");
		Bukkit.broadcastMessage(ChatColor.WHITE + "First one back with the bacon wins");
		Bukkit.broadcastMessage(ChatColor.AQUA + "Good luck!");
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
		{

			@Override
			public void run() 
			{
				spawnPigs();
				canMove = true;
				slowness = true;
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
		for(int i = 0; i < 30; i++)
		{
			int x = (int) (Math.random() * (-20.5 - -48.5 + 1) + -48.5);
			double y = -6.5;
			int z = (int) (Math.random() * (-1406.5  -  -1428.5 + 1) + -1428.5);
			
			Location loc = new Location(Bukkit.getWorld("world"), x,y,z);
			
			Entity ent = Bukkit.getWorld("world").spawnEntity(loc, EntityType.PIG);
			Pig p = (Pig) ent;
			
			p.setMaxHealth(30);
			p.setHealth(30);
			pigs.add(ent);

		}
	}

	
	/* 
	 * Events
	 * */
		
	@EventHandler
	public void onPigHurt(EntityDamageByEntityEvent e)
	{
		if(event == true)
		{
			if((e.getEntity() instanceof Pig) && (e.getDamager() instanceof Player))
			{
					e.setDamage(10d);
					Pig ent = (Pig) e.getEntity();
					ent.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,120, 8));
			}
			
			if((e.getEntity() instanceof Player) && (e.getDamager() instanceof Player))
			{
					if(slowness == true)
					{
					
					Player p = (Player) e.getEntity();
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW ,20, 1));
					}
					
					e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPigKill(EntityDeathEvent e)
	{
		if(event == true)
		{

		if(e.getEntity() instanceof Pig)
		{
			
			e.getDrops().clear();
			e.setDroppedExp(0);
			
			Player p = e.getEntity().getKiller();
			Amount_killed.put(p, Amount_killed.get(p) +1);
			
			p.getInventory().setItem(8, new ItemStack(Material.PORK, Amount_killed.get(p)));
			
			if(Amount_killed.get(p) >= 3)
			{
				p.sendMessage(ChatColor.BLUE + "PorkChopRace > " + ChatColor.WHITE + "You have collected enough bacon! Now return it to camp!");
			}
			else
			{
				int needed =  3 - Amount_killed.get(p);
				p.sendMessage(ChatColor.BLUE + "PorkChopRace > " + ChatColor.WHITE + "You need " + ChatColor.GOLD + needed + ChatColor.WHITE + " More slice(s)!");
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e)
	{
		if(event == true)
		{
			if(!canMove)
			{
				Location fromLoc = e.getFrom();
				Location toLoc = e.getTo();
				
				if(fromLoc.getX() != toLoc.getX() || fromLoc.getZ() != toLoc.getZ())
				{
					e.getPlayer().teleport(fromLoc); //No need to cancel it, just teleport them back nub.
					e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.NOTE_STICKS, 1.0F, 1.0F);
				}
			}
		
		if(((Amount_killed.get(e.getPlayer()) >= 3)) && (e.getPlayer().getLocation().getX() < -116))
		{
			doCelebration();
			Bukkit.broadcastMessage(ChatColor.BLUE + "PorkChopRace >" + ChatColor.WHITE + " Player " + ChatColor.GOLD + e.getPlayer().getName() + ChatColor.WHITE + " Has won!");
			
			for(Player p : Bukkit.getOnlinePlayers())
			{
				BarManager.setBar(p, ChatColor.BLUE + "PorkChopRace >" + ChatColor.WHITE + " Player " + ChatColor.GOLD + e.getPlayer().getName() + ChatColor.WHITE + " Has won!");
			}
			
		}
	}
}
	
	public static void doCelebration()
	{
		event = false;
		
		 fireworks = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() 
		 {
			   int times = 62;
			   
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
	      },0, 2);  
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