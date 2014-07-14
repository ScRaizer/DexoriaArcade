package com.lewys.arcade.util.game;

import java.util.Arrays;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.Inventory;

public class Kit {
	
	private KitAvailability _kitavail;
	private Inventory _inv;
	private Perk[] _perks;
	private LivingEntity _entity;
	private GameTeam _team;
	private String _name;
	
	public Kit(KitAvailability ka, Inventory i, Perk[] p, LivingEntity e, GameTeam team, String s){
		this._kitavail = ka;
		this._inv = i;
		this._perks = p;
		this._entity = e;
		this._team = team;
		this._name = ka.getChatColor() + s;
	}
	
	public KitAvailability getKitAvailability(){
		return this._kitavail;
	}
	public Inventory getInventory(){
		return this._inv;
	}
	public Perk[] getPerks(){
		return this._perks;
	}
	public LivingEntity getLivingEntity(){
		return this._entity;
	}
	public GameTeam getTeam(){
		return this._team;
	}
	public String getName(){
		return this._name;
	}
	
	public void setKitAvailability(KitAvailability k){
		this._kitavail = k;
	}
	public void setInventory(Inventory i){
		this._inv = i;
	}
	public void setPerks(Perk[] p){
		this._perks = p;
	}
	public void addPerk(Perk p){
		Arrays.asList(this._perks).add(p);
	}
	public void setLivingEntity(LivingEntity e){
		this._entity = e;
	}
	public void setTeam(GameTeam t){
		this._team = t;
	}
	public void setName(String s){
		this._name = this._kitavail.getChatColor() + s;
	}
}
