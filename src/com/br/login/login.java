package com.br.login;

import com.br.incraft.blue.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class login implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String label, String[] args) {
        if (Sender instanceof Player) {
            Player p = (Player) Sender;
            if (args.length == 0){
                return true;
            }else {
                if (!loginAPI.estaLogado(p)) {
                String senha = args[0];
                if (senha.equals(loginAPI.getSenha(p))) {
                    loginAPI.Logar(p);

                    p.sendMessage(Main.plugin.getConfig().getString("entrou"));
                    }else {
                    p.kickPlayer(Main.plugin.getConfig().getString("erroSenha"));
                }
                }else {
                    p.sendMessage(Main.plugin.getConfig().getString("estaLogado"));
                }
            }
        }
        return true;
    }
}
