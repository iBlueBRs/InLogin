//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
    public Eventos() {
    }

    public static Object getInstance() {
        return null;
    }

    @EventHandler
    public void aoEntrar(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (!loginAPI.EstaRegistrado(p)) {
            p.sendMessage(Main.plugin.getConfig().getString("registrar"));
        } else {
            p.sendMessage(Main.plugin.getConfig().getString("login"));
        }

        BukkitRunnable var10000 = new BukkitRunnable() {
            public void run() {
                if (!loginAPI.estaLogado(p)) {
                    p.kickPlayer(Main.plugin.getConfig().getString("demorou-Login"));
                }

            }
        };
        Main var10001 = Main.plugin;
        var10000.runTaskLater(Main.getInstance(), 900L);
    }

    @EventHandler
    public void aoSair(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        String name = p.getName();
        e.setQuitMessage((String)null);
        if (p.hasPermission("staff")) {
            Bukkit.broadcastMessage(Main.plugin.getConfig().getString("staff-saiu".replaceAll("%player%", name)));
        }

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
        join.setJoinMessage((String)null);
        Bukkit.broadcastMessage(Main.plugin.getConfig().getString("staff-entrou".replaceAll("%player%", p.getName())));
    }
}
