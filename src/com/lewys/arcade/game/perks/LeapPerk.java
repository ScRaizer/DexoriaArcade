package com.lewys.arcade.game.perks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.lewys.arcade.Main;
import com.lewys.arcade.game.Kit;
import com.lewys.arcade.game.Perk;
import com.lewys.arcade.util.F;
import com.lewys.arcade.util.ISF;

public class LeapPerk extends Perk implements Listener {

	private static String[] _desc = new String[]{"Right-click with your axe to leap!"};
	private static ItemStack _display = ISF.rename(new ItemStack(Material.STONE_AXE), "&eLeap");
	private List<String> _inCd = new ArrayList<String>();
	final private int _cooldown;
	
	public LeapPerk(Kit k, int c){
		super("Leap", _desc, _display, k, false);
		this._cooldown = c;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onInteract(PlayerInteractEvent event){
		Player ply = event.getPlayer();
		
		if(!this.getHost().hasKit(ply)) return;
		
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(!_inCd.contains(ply.getName())){
				ply.setVelocity(ply.getLocation().getDirection().multiply(1.0F).setY(0.5));
				ply.playSound(ply.getLocation(), Sound.GHAST_FIREBALL, 1, 1);
				ply.sendMessage(F.main("Leap", "You used " + F.item("Leap")));
			}else{
				ply.sendMessage(F.main("Leap", "You are too exhausted!"));
			}
		}else return;
	}
	
	public void startCooldown(final Player p){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new BukkitRunnable(){
			@Override
			public void run(){
				int i = _cooldown;
				_inCd.add(p.getName());
				
				if(i == 0){
					this.cancel();
					_inCd.remove(p.getName());
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1F, 1F);
					p.sendMessage(F.main("Leap", "You can use ") + F.elem("Leap"));
				}
				i--;
			}
			
		}, 0L, 20L);
	}
}
