package com.br.incraft.blue.eventos;

import com.br.incraft.blue.Main;
import com.br.login.loginAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Eventos implements Listener {
    public static Object getInstance() {
        return null;
    }

    @EventHandler
    public void aoEntrar(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!loginAPI.EstaRegistrado(p)) {
            p.sendMessage("§f[§6§lLogin InCraft§f] \n§aRegistre-se usando §c/registrar <Senha> <Senha> \n" +
                    "§cTodas suas informações pessoais são criptogradas. \n Nós prezamos por sua segurança" +
                    "\n §Por isso use uma senha forte.");
        } else {
            p.sendMessage("§f[§6§lLogin InCraft§f] \n§aEntre por-favor usando §c/login <Senha> ");
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!loginAPI.estaLogado(p)) {
                    p.kickPlayer("§6§InCraft \n §eVocê demorou muito para logar");
                }

            }
        }.runTaskLater(Main.plugin.getInstance(), 20*45);
    }

    @EventHandler
    public void aoSair(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(null);
        if (p.hasPermission("staff"))
            Bukkit.broadcastMessage(" §f[§c-§f]§e " + p.getName() + " §fSaiu");
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        e.setFormat("&a[G]" + e.getPlayer().getPlayerListName() + "§7- >" + e.getMessage());
    }

    @EventHandler
    public void naoMover(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!loginAPI.estaLogado(p)) {
            p.teleport(e.getFrom());
        }
    }
}


