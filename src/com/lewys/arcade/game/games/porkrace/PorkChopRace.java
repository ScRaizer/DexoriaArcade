package com.lewys.arcade.game.games.porkrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lewys.arcade.game.Game;
import com.lewys.arcade.game.GameTeam;
import com.lewys.arcade.game.GameType;
import com.lewys.arcade.game.Kit;
import com.lewys.arcade.game.games.porkrace.kits.Buckaroo;
import com.lewys.arcade.game.games.porkrace.teams.Pigcatchers;

public class PorkChopRace extends Game {

	private static List<GameTeam> _teams = new ArrayList<GameTeam>(Arrays.asList(new Pigcatchers()));
	private static List<Kit> _kits = new ArrayList<Kit>(Arrays.asList(new Buckaroo()));
	
	public PorkChopRace() {		
		super("Pork Chop Race", 8, 16, null, GameType.FREE_FOR_ALL, _teams, _kits, 6000L, true);
	}

}
