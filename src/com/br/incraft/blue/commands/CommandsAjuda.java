package com.br.incraft.blue.commands;

import com.br.incraft.blue.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsAjuda implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage("");
            player.sendMessage(Main.plugin.getConfig().getString("ajuda").replace("&", "§"));
            return true;
        }
        return true;
    }
}