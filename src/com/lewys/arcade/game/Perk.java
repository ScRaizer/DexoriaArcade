package com.lewys.arcade.game;

import org.bukkit.inventory.ItemStack;

public class Perk {
	
	private String name;
	private String[] desc;
	private ItemStack displayItem;
	private Kit host;
	private boolean isPassive;
	
	public Perk(String n, String[] d, ItemStack di, Kit k, boolean p){
		this.name = n;
		this.desc = d;
		this.displayItem = di;
		this.host = k;
		this.isPassive = p;
	}
	
	public String getName(){
		return this.name;
	}
	public String[] getDescription(){
		return this.desc;
	}
	public ItemStack getDisplayItem(){
		return this.displayItem;
	}
	public Kit getHost(){
		return this.host;
	}
	public boolean isPassive(){
		return this.isPassive;
	}
	
	public void setName(String s){
		this.name = s;
	}
	public void setDescription(String[] s){
		this.desc = s;
	}
	public void setDisplayItem(ItemStack i){
		this.displayItem = i;
	}
	public void setHost(Kit k){
		this.host = k;
	}
}
