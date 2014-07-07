package com.lewys.arcade.util;

import org.bukkit.ChatColor;

public enum Rank {
	OWNER("OWNER", "dex.owner", ChatColor.DARK_RED), DEV("DEV", "dex.dev", ChatColor.DARK_PURPLE), ADMIN("ADMIN", "dex.admin", ChatColor.BLUE)
			,MOD("MOD", "dex.mod", ChatColor.GOLD), HELPER("HELPER", "dex.helper", ChatColor.GREEN), DEFAULT("", "dex.default", ChatColor.YELLOW);

	private String _tag;
	private String _perm;
	private ChatColor _color;

	private Rank(String tag, String perm, ChatColor color) {
		this._tag = tag;
		this._perm = perm;
		this._color = color;
	}

	public String getTag() {
		return this._tag;
	}

	public String getPermission() {
		return this._perm;
	}

	public ChatColor getColor() {
		return this._color;
	}
}
