package com.lewys.arcade.util;

import java.util.Arrays;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ISF {

	public static ItemStack create(ItemStack i, String name, String[] lores) {
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList(lores));
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack rename(ItemStack i, String name) {
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		return i;
	}

	public static ItemStack setLore(ItemStack i, String[] lores) {
		ItemMeta im = i.getItemMeta();
		im.setLore(Arrays.asList(lores));
		i.setItemMeta(im);
		return i;
	}

}
