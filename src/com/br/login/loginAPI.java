package com.br.login;

import com.br.incraft.blue.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public final class loginAPI {
    private static Main login;
    private static FileConfiguration config;
    static {
        login = Main.plugin.getInstance();
    }
    private static List<Player> JogadoresLogados = new ArrayList<>();
    public static boolean estaLogado(Player player) {
        return JogadoresLogados.contains(player);
    }
    public static void Logar(Player player) {
        JogadoresLogados.add(player);
    }
    public static void Deslogar(Player player) {
        JogadoresLogados.remove(player);
    }
    private static List<Player> getJogadoresLogados() {
        return JogadoresLogados;
    }
    public static void JogadoresLogados(List<Player> JogadoresLogados) {
        loginAPI.JogadoresLogados = JogadoresLogados;
    }
    public static void RemoverConta(String conta) {
        Main.plugin.getConfig().set(conta.toLowerCase(), null);
        Main.plugin.saveConfig();
    }
    public static boolean EstaRegistrado(Player player) {
        return Main.plugin.getConfig().contains(player.getName().toLowerCase());
    }
    public static String getSenha(Player player){
        return login.getConfig().getString(player.getName().toLowerCase());
    }
    public static void Registrar(Player player, String senha) {
        Main.plugin.getConfig().set(player.getName().toLowerCase(), senha);
        Main.plugin.saveConfig();
    }
    public static void recuperarSenha(Player player,String novaSenha) {
        Main.plugin.getConfig().set(player.getName().toLowerCase(), novaSenha);
        Main.plugin.saveConfig();
    }
}
