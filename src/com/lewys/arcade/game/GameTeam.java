package com.lewys.arcade.game;

import java.util.Arrays;

public class GameTeam {

	private String _name;
	private TeamColor _color;
	private Game _game;
	private Kit[] _kits;
	
	public GameTeam(String name, TeamColor color, Game game, Kit[] kits){
		this._name = name;
		this._color = color;
		this._game = game;
		this._kits = kits;
	}
	
	public String getName(){
		return this._name;
	}
	public TeamColor getColor(){
		return this._color;
	}
	public Game getGame(){
		return this._game;
	}
	public Kit[] getKits(){
		return this._kits;
	}
	public void setName(String s){
		this._name = s;
	}
	public void setColor(TeamColor c){
		this._color = c;
	}
	public void setGame(Game g){
		this._game = g;
	}
	public void setKits(Kit[] k){
		this._kits = k;
	}
	public void addKit(Kit k){
		Arrays.asList(this._kits).add(k);
	}
}
