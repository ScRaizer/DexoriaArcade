package com.lewys.arcade.util;

import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.scheduler.BukkitRunnable;

import com.lewys.arcade.Main;

public class UtilBlock {

	public static void regen(final List<BlockState> blocks, final boolean effect,
			final int speed) {

		new BukkitRunnable() {
			int i = -1;

			@SuppressWarnings("deprecation")
			public void run() {
				if (i != blocks.size() - 1) {
					i++;
					BlockState bs = blocks.get(i);
					bs.getBlock().setType(bs.getType());
					bs.getBlock().setData(bs.getBlock().getData());
					if (effect)
						bs.getBlock()
								.getWorld()
								.playEffect(bs.getLocation(),
										Effect.STEP_SOUND,
										bs.getBlock().getType());
				} else {
					for (BlockState bs : blocks) {
						bs.getBlock().setType(bs.getType());
						bs.getBlock().setData(bs.getBlock().getData());
					}
					blocks.clear();
					this.cancel();
				}
			}
		}.runTaskTimer(Main.instance, speed, speed);
	}
	
	public static boolean solid(Block b){
		return b.getType().isSolid();
	}
	public static boolean water(Block b){
		return b.getType() == Material.WATER;
	}
	public static boolean lava(Block b){
		return b.getType() == Material.LAVA;
	}
}
