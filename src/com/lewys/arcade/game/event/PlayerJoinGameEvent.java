package com.lewys.arcade.game.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.lewys.arcade.game.Game;

public class PlayerJoinGameEvent extends Event {
	private HandlerList _handlers = new HandlerList();
	private Player _player;
	private Game _game;
	
	public PlayerJoinGameEvent(Player p, Game g){
		this._player = p;
		this._game = g;
	}
	
	public Player getPlayer(){
		return this._player;
	}
	public Game getGame(){
		return this._game;
	}
	public HandlerList getHandlers() {
		return this._handlers;
	}

}
