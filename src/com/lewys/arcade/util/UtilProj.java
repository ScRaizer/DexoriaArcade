package com.lewys.arcade.util;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Projectile;
import org.bukkit.util.BlockIterator;

public class UtilProj {

	public static Block getHitBlock(Projectile p){
		BlockIterator iterate = new BlockIterator(p.getWorld(), p.getLocation().toVector(), p.getVelocity().normalize(), 0.0D, 4);
		Block hit = null;
		while(iterate.hasNext()){
			hit = iterate.next();
			
			if(hit.getType() != Material.AIR) break;
		}
		return hit;
	}
}
