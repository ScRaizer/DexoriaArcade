package com.lewys.arcade.game;

import java.util.List;

import org.bukkit.Location;

public class Game {
	
	private String _name;
	private int _minPlayers;
	private int _maxPlayers;
	private long _timer;
	private Location _lobbyLoc;
	private GameType _type;
	private List<GameTeam> _teams;
	private List<Kit> _kits;
	private boolean _beta;
	
	// Constructor
	public Game(String name, int min, int max, Location lob, GameType type, List<GameTeam> teams, List<Kit> kits, long t, boolean b){
		this._name = name;
		this._minPlayers = min;
		this._maxPlayers = max;
		this._lobbyLoc = lob;
		this._type = type;
		this._teams = teams;
		this._kits = kits;
		this._timer = t;
		this._beta = b;
	}
	
	// Getters
	public String getName(){
		return this._name;
	}
	public int getMinPlayers(){
		return this._minPlayers;
	}
	public int getMaxPlayers(){
		return this._maxPlayers;
	}
	public Location getLobbyLocation(){
		return this._lobbyLoc;
	}
	public GameType getType(){
		return this._type;
	}
	public List<GameTeam> getTeams(){
		return this._teams;
	}
	public List<Kit> getKits(){
		return this._kits;
	}
	public long getTimer(){
		return this._timer;
	}
	public boolean isBeta(){
		return this._beta;
	}
	
	// Setters
	public void setName(String s){
		this._name = s;
	}
	public void setMinPlayers(int i){
		this._minPlayers = i;
	}
	public void setMaxPlayers(int i){
		this._maxPlayers = i;
	}
	public void setLobbyLocation(Location l){
		this._lobbyLoc = l;
	}
	public void setType(GameType type){
		this._type = type;
	}
	public void setTeams(List<GameTeam> t){
		this._teams = t;
	}
	public void setKits(List<Kit> k){
		this._kits = k;
	}
	public void setTimer(long l){
		this._timer = l;
	}
	public void setBeta(boolean b){
		this._beta = b;
	}
	
}
