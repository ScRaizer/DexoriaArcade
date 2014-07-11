package com.lewys.arcade.util.game;

public enum GameType {
	FREE_FOR_ALL,
	SOLO,
	TEAM;
	
	private static GameType _type;
	
	public static void setType(GameType type){
		_type = type;
	}
	public static boolean isType(GameType gt){
		return _type.equals(gt);
	}
}
