package com.br;

import com.br.login.loginAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
            p.sendMessage(Main.plugin.getConfig().getString("registrar"));
        } else {
            p.sendMessage(Main.plugin.getConfig().getString("login"));
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!loginAPI.estaLogado(p)) {
                    p.kickPlayer(Main.plugin.getConfig().getString("demorou-Login"));
                }

            }
        }.runTaskLater(Main.plugin.getInstance(), 20*45);
    }

    @EventHandler
    public void aoSair(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        String name = p.getName();
        e.setQuitMessage(null);
        if (p.hasPermission("staff"))
            Bukkit.broadcastMessage(Main.plugin.getConfig().getString("staff-saiu".replaceAll("%player%", name)));
    }
    @EventHandler
    public void naoMover(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!loginAPI.estaLogado(p)) {
            p.teleport(e.getFrom());
        }
    }
    @EventHandler
    public void entrou(PlayerJoinEvent join) {
        Player p = join.getPlayer();
        String name = p.getName();
        join.setJoinMessage(null);
        Bukkit.broadcastMessage(Main.plugin.getConfig().getString("staff-entrou".replaceAll("%player%", p.getName())));
    }
}

