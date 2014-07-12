package com.lewys.arcade.util.game;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public enum TeamColor {

	RED(ChatColor.RED, DyeColor.RED),
	BLUE(ChatColor.BLUE, DyeColor.BLUE),
	YELLOW(ChatColor.YELLOW, DyeColor.YELLOW),
	GREEN(ChatColor.GREEN, DyeColor.LIME),
	PINK(ChatColor.LIGHT_PURPLE, DyeColor.MAGENTA),
	ORANGE(ChatColor.GOLD, DyeColor.ORANGE),
	WHITE(ChatColor.WHITE, DyeColor.WHITE),
	GRAY(ChatColor.GRAY, DyeColor.GRAY);
	
	/*
	 * Team colors to help manage stuff. Dye color will mainly be for kit selection (kits?) and stuff like that.
	 * The chat color will be used for chat and killfeeds.
	 */
	
	private ChatColor _color;
	private DyeColor _dye;
	
	private TeamColor(ChatColor color, DyeColor dye){
		this._color = color;
		this._dye = dye;
	}
	
	public ChatColor getChatColor(){
		return this._color;
	}
	public DyeColor getDyeColor(){
		return this._dye;
	}
}
