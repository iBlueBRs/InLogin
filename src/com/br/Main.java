package com.br;

import com.br.login.login;
import com.br.login.registrar;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main plugin;
    private static Main instance;


    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        plugin = this;
        Bukkit.getConsoleSender().sendMessage("§6PLUGIN InCraft ligado com sucesso");
        this.saveDefaultConfig();
        this.registerComands();
        this.registerEvents();
        MySQL.criarTabela();
        Filter filter = new Filter() {
            public Result getOnMismatch() {
                return Result.ACCEPT;
            }

            public Result getOnMatch() {
                return Result.DENY;
            }

            public Result filter(Logger logger, Level level, Marker marker, String s, Object... objects) {
                return !s.contains("issued server command: /login") && !s.contains("issued server command: /register") && !s.contains("issued server command: /registrar") && !s.contains("issued server command: /logar") && !s.contains("issued server command: /pin") ? Result.ACCEPT : Result.DENY;
            }

            public Result filter(Logger logger, Level level, Marker marker, Object o, Throwable throwable) {
                return Result.NEUTRAL;
            }

            public Result filter(Logger logger, Level level, Marker marker, Message message, Throwable throwable) {
                return !message.getFormattedMessage().contains("issued server command: /login") && !message.getFormattedMessage().contains("issued server command: /registrar") && !message.getFormattedMessage().contains("issued server command: /register") && !message.getFormattedMessage().contains("issued server command: /logar") && !message.getFormattedMessage().contains("issued server command: /pin") ? Result.ACCEPT : Result.DENY;
            }

            public Result filter(LogEvent logEvent) {
                return !logEvent.getMessage().getFormattedMessage().contains("issued server command: /login") && !logEvent.getMessage().getFormattedMessage().contains("issued server command: /logar") && !logEvent.getMessage().getFormattedMessage().contains("issued server command: /register") && !logEvent.getMessage().getFormattedMessage().contains("issued server command: /registrar") && !logEvent.getMessage().getFormattedMessage().contains("issued server command: /pin") ? Result.ACCEPT : Result.DENY;
            }
        };
        ((Logger)LogManager.getRootLogger()).addFilter(filter);
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§4PLUGIN InCraft desligado");
    }

    public void registerComands() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        this.getCommand("login").setExecutor(new login());
        this.getCommand("registrar").setExecutor(new registrar());
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        PluginManager pluginManager = Bukkit.getPluginManager();
        pm.registerEvents(new Eventos(), this);
    }

    public void getConfig(FileConfiguration config) {
    }
}
