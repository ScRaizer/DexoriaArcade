package com.lewys.arcade.util;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import com.lewys.arcade.Main;

@SuppressWarnings("unused")
public class UtilPlayer implements Listener {
	private static Plugin _plugin = Main.instance;
	private static BukkitScheduler _scheduler = _plugin.getServer()
			.getScheduler();

	public static void message(Player p, String msg) {
		if(msg.contains("\n")){
			p.sendMessage(msg.split("\n"));
		}else{
		p.sendMessage(msg);
		}
	}
	public static void health(Player p, double d) {
		p.setHealth(d);
	}
	public static void hunger(Player p, int i) {
		p.setSaturation(i);
	}
	public static void sound(Player p, Sound s, float pitch, float volume){
		p.playSound(p.getLocation(), s, pitch, volume);
	}
	public static boolean hasRank(Player p, Rank r){
		return p.hasPermission(r.getPermission());
	}
	public static boolean hasRank(Player p, Rank r, boolean inform){
		if(p.hasPermission(r.getPermission())){
			return true;
		}
		
		if (inform) {
			p.sendMessage(F.main("Permissions",
					"You must have the rank " + F.elem(r.getTag())
							+ " to do that!"));
		}
		return false;
	}
}
