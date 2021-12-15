package com.gmail.arieldeleonhernandez123.paintball;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("You must be a player to use this command!");
            return false;
        }
        Player player = (Player) sender;
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("create")) {
                // /paintt create <name>
                if (player.isOp() || player.hasPermission("paintball.*")) {
                    if (args.length >= 2) {


                    }else{
                        player.sendMessage(ChatColor.RED + "You must specify a name for the arena!");
                    }

                }else{
                    player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                }
            }

            return true;
        }
        return true;
    }
}


