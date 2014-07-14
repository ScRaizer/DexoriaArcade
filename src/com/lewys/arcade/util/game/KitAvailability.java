package com.lewys.arcade.util.game;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public enum KitAvailability {

	FREE(ChatColor.YELLOW, DyeColor.YELLOW),
	GREEN(ChatColor.GREEN, DyeColor.GREEN),
	BLUE(ChatColor.AQUA, DyeColor.LIGHT_BLUE),
	HIDDEN(ChatColor.RED, DyeColor.RED),
	NULL(ChatColor.BLACK, DyeColor.BLACK);
	
	private ChatColor _chatColor;
	private DyeColor _dyeColor;
	
	private KitAvailability(ChatColor c, DyeColor d){
		this._chatColor = c;
		this._dyeColor = d;
	}
	
	public ChatColor getChatColor(){
		return this._chatColor;
	}
	public DyeColor getDyeColor(){
		return this._dyeColor;
	}
}
