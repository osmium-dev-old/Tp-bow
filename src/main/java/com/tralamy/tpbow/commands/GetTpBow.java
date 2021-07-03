package com.tralamy.tpbow.commands;

import com.tralamy.tpbow.Main;
import com.tralamy.tpbow.items.TPBow;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class GetTpBow implements CommandExecutor {
	public static final String PERMISSION = "polaki.command.tpbow";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!sender.hasPermission(PERMISSION)) {
			sender.sendMessage(ChatColor.RED + "Insufficient privilege. (" + PERMISSION + ")");
			return false;
		}

		Player player = GetTarget(args, sender);
		if (player == null) return false;

		player.getInventory().addItem(new TPBow().get());
		if (sender != player) {
			String s = Main.getPlugin(Main.class).getConfig().getString("sender-give-bow-message");
			assert s != null;
			s = s.replaceAll("%player%", player.getDisplayName());
			sender.sendMessage(s);
		};
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Main.getPlugin(Main.class).getConfig().getString("player-get-bow-message"))));


		return true;
	}

	public static Player GetTarget(String[] args, CommandSender sender) {
		String playerName = "";
		if (args.length == 0) {
			if (sender instanceof Player) {
				playerName = ((Player) sender).getDisplayName();
			} else {
				System.err.println("Player only command or specify player!");
				return null;
			}
		} else {
			playerName = args[0];
		}

		Player player = Bukkit.getPlayer(playerName);
		if (player == null) {
			sender.sendMessage(ChatColor.RED + "Player " + playerName + " not found!");
			return null;
		}

		return player;
	}
}
