package com.br.incraft.blue;

import com.br.incraft.blue.addons.Tag;
import com.br.incraft.blue.commands.CommandsAjuda;
import com.br.incraft.blue.commands.CommandsDiscord;
import com.br.incraft.blue.eventos.Eventos;
import com.br.login.login;
import com.br.login.recuperarsenha;
import com.br.login.registrar;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main plugin;
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        instance = this;
        plugin = this;
        Bukkit.getConsoleSender().sendMessage("§6PLUGIN InCraft ligado com sucesso");
        Bukkit.getPluginManager().registerEvents(new Tag(), this);
        saveDefaultConfig();
        registerComands();
        registerEvents();
        getCommand("tag").setExecutor(new Tag());
        getCommand("login").setExecutor(new login());
        getCommand("registrar").setExecutor(new registrar());
        getCommand("recuperarsenha").setExecutor(new recuperarsenha());
    }
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§4PLUGIN InCraft desligado");
    }
    public void registerComands(){
        getCommand("ajuda").setExecutor(new CommandsAjuda());
        getCommand("discord").setExecutor(new CommandsDiscord());
    }
    public void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Eventos(), this);
    }
}
