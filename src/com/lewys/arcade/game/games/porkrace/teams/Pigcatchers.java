package com.lewys.arcade.game.games.porkrace.teams;

import com.lewys.arcade.game.GameTeam;
import com.lewys.arcade.game.Kit;
import com.lewys.arcade.game.TeamColor;
import com.lewys.arcade.game.games.porkrace.PorkChopRace;
import com.lewys.arcade.game.games.porkrace.kits.Buckaroo;

public class Pigcatchers extends GameTeam {

	public Pigcatchers(){
		super("Pigcatchers", TeamColor.YELLOW, new PorkChopRace(), new Kit[]{new Buckaroo()});
	}
}
