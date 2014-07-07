package com.lewys.arcade;

public enum GameState {
	
	IN_LOBBY, GAME_1, GAME_2, GAME_3, GAME_4;
	
	private static GameState currentState;
	
	public static void setState(GameState state) {
		GameState.currentState = state;
	}
	
	public static boolean isState(GameState state){
		if(currentState.equals(state))
			return true;
		return false;
	}
}
