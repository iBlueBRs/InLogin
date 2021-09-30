package com.br.login;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class recuperarsenha implements CommandExecutor {
    @Override

    public boolean onCommand(CommandSender Sender, Command cmd, String label, String[] args) {
        if (Sender instanceof Player) {
            Player p = (Player) Sender;
            if (loginAPI.estaLogado(p)) {

                if (args.length == 0) {
                    return true;
                }else{
                    String novaSenha = args[0];
                    if (novaSenha.equals(loginAPI.getSenha(p))) {
                        p.sendMessage("§aSua senha é exatamente a antiga, coloque sua nova senha!");
                    }else{
                        loginAPI.recuperarSenha(p, novaSenha);
                        p.sendMessage("§aSua senha foi alterada com sucesso");
                    }
                }
            }else{
                p.sendMessage("§cVocê só pode trocar senha estando logado!");
            }
        }

        return false;
    }
}
