package com.br.incraft.blue.addons;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Tag implements Listener, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String arg2, String[] args) {
        if(!(Sender instanceof Player)){
            return true;
        }
        Player p = (Player)Sender;
        if(cmd.getLabel().equalsIgnoreCase("tag")){
            if(args.length == 0) {
                p.sendMessage("§6InCraft Tags \n §cUse /tag <Tag>");
                    return true;
            }
            if(p.hasPermission("membro")) {
                if(args[0].equalsIgnoreCase("membro")){ //use /tag (nomedatag) para muda a permissão
                    p.sendMessage("§6§lInCraft Tags \n §2Tag alterada com sucesso para §f[§2membro§f]"); //env
                    p.setDisplayName("§f[§2membro§f ]" + p.getName()); //setando vizualmente
                    p.setPlayerListName("§f[§2membro§f]" + p.getName()); //setando no player
                }
            }else {
                p.sendMessage("§6§lInCraft Tags \n §4Você não tem permissão para isso"); //sem permissão
            }return true;};
        if(p.hasPermission("dono")) {
            if(args[0].equalsIgnoreCase("dono")){
                p.sendMessage("§6§lInCraft Tags \n §2Tag alterada com sucesso para §f[§9Dono§f]");
                p.setDisplayName("§f[§9Dono§f ] " + p.getName());
                p.setPlayerListName("§f[§9Dono§f]" + p.getName());
            }
        }else p.sendMessage("§6§lInCraft Tags \n §4Você não tem permissão para isso");
        return true;
    }
}
