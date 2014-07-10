package com.lewys.arcade;

import games.PorkChopRace;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import com.sk89q.minecraft.util.commands.CommandUsageException;
import com.sk89q.minecraft.util.commands.CommandsManager;
import com.sk89q.minecraft.util.commands.MissingNestedCommandException;
import com.sk89q.minecraft.util.commands.WrappedCommandException;


public class Main extends JavaPlugin implements Listener

// Steven is super muscular

{
	public static Main instance;
	private CommandsManager<CommandSender> commands;
	
	public void onEnable()
	{
		GameState.setState(GameState.IN_LOBBY);
		Lobby.doLobby(1, "PorkChopRace");
		
		instance = this;
		
		reg(new Lobby());
		reg(new PorkChopRace());
	}
	public void onDisable()
	{
		instance = null;
	}
	
	// sk89q's command framework
		@SuppressWarnings("unused")
		private void setupCommands() {
			this.commands = new CommandsManager<CommandSender>() {
				@Override
				public boolean hasPermission(CommandSender sender, String perm) {
					return sender instanceof ConsoleCommandSender
							|| sender.hasPermission(perm);
				}
			};
			CommandsManagerRegistration cmdRegister = new CommandsManagerRegistration(
					this, this.commands);
			// cmdRegister.register(SomeClass.class);
		}

		@Override
		public boolean onCommand(CommandSender sender, Command cmd,
				String commandLabel, String[] args) {
			try {
				this.commands.execute(cmd.getName(), args, sender, sender);
			} catch (CommandPermissionsException e) {
				sender.sendMessage(ChatColor.RED + "You don't have permission.");
			} catch (MissingNestedCommandException e) {
				sender.sendMessage(ChatColor.RED + e.getUsage());
			} catch (CommandUsageException e) {
				sender.sendMessage(ChatColor.RED + e.getMessage());
				sender.sendMessage(ChatColor.RED + e.getUsage());
			} catch (WrappedCommandException e) {
				if (e.getCause() instanceof NumberFormatException) {
					sender.sendMessage(ChatColor.RED
							+ "Number expected, string received instead.");
				} else {
					sender.sendMessage(ChatColor.RED
							+ "An error has occurred. See console.");
					e.printStackTrace();
				}
			} catch (CommandException e) {
				sender.sendMessage(ChatColor.RED + e.getMessage());
			}
			return true;
		}
		
		private void reg(Listener... listeners){
			for(Listener l : listeners){
				instance.getServer().getPluginManager().registerEvents(l, instance);
			}
		}
 
}
