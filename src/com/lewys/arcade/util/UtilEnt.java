package com.lewys.arcade.util;

import org.bukkit.entity.Entity;

public class UtilEnt {

	
	public static boolean ground(Entity e){
		return e.isOnGround();
	}
	
	public static void remove(Entity e){
		e.remove();
	}
}
