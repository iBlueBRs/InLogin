package com.br.login;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class registrar implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String label, String[] args) {
        if (Sender instanceof Player) {
            Player p = (Player) Sender;
            if (args.length == 0) {
                return false;
            } else {
                if (!loginAPI.EstaRegistrado(p)) {
                    String senhaInicial = args[0];
                    if (args.length < 2) {
                        p.sendMessage("§2§lVocê precisa repetir duas vezesa senha para confirmar.\n" +
                                "§2/register <Senha> <Senha novamente>");
                    } else {
                        String confirmaSenha = args[1];
                        if (senhaInicial.equals(confirmaSenha)) {
                            loginAPI.Registrar(p, confirmaSenha);
                            p.sendMessage("§2Você se registrou com sucesso.");
                            loginAPI.Logar(p);
                        } else {
                            p.sendMessage("§2As senhas estão diferentes? \n §cExemplo §2/registrar teste1 teste1" +
                                    "\n §cA senha ea confirmação devem ser exatamente iguais.");
                        }
                    }
                }
            }
        }
        return true;
    }
}