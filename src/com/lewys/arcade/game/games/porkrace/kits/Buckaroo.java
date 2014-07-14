package com.lewys.arcade.game.games.porkrace.kits;

import org.bukkit.Bukkit;
import org.bukkit.entity.Zombie;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import com.lewys.arcade.game.GameTeam;
import com.lewys.arcade.game.Kit;
import com.lewys.arcade.game.KitAvailability;
import com.lewys.arcade.game.Perk;

public class Buckaroo extends Kit {

	private static Inventory i = Bukkit.createInventory(null, InventoryType.PLAYER);
	private static Zombie z;
	private static GameTeam team;
	
	public Buckaroo(){
		super(KitAvailability.FREE, i, new Perk[]{}, z, team, "Buckaroo");
	}
}
