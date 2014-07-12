package com.lewys.arcade.util.game;

public enum Ability {

	REGEN_1(true),
	REGEN_2(true),
	REGEN_3(true),
	BARRAGE(false),
	BARRAGE_2(false),
	BARRAGE_3(false),
	LEAP(false),
	WALL_JUMP(false),
	LEAP_2(false),
	LEAP_3(false),
	INVISIBILITY(false),
	BRUTE(true),
	BACKSTAB(true),
	SLOW(true),
	SPEED(true),
	SPEED_2(true),
	SPEED_3(true),
	DOUBLE_JUMP(false),
	BLOCK_TOSS(false),
	STURDY(true),
	FLASH(false),
	BLINK(false),
	GRAPPLING_HOOK(false),
	REPULSE(false),
	SMOKE_BOMB(false);
	
	/*
	 *  To add more, just type the name and 'true' or 'false', which states if the ability is passive or not.
	 *  Passive abilities are activated automagically.
	 */
	
	private boolean _passive;
	
	private Ability(boolean pass){
		this._passive = pass;
	}
	
	public boolean isPassive(){
		return this._passive;
	}
}
