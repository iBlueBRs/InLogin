//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.br;

import org.bukkit.entity.Player;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class MySQL {
    public MySQL() {
    }

    public static void criarTabela() {
        try {
            Connection con = AbrirConecao();
            PreparedStatement st = con.prepareStatement("CREATE TABLE LOGIN(ID VARCHAR(100) NOT NULL PRIMARY KEY,SENHA VARCHAR(100));");
            st.executeUpdate();
            con.close();
        } catch (Exception var2) {
            System.out.println("§c§lOcorreu um erro ao criar tabela no MYSQL.");
        }

    }

    public static void addJogador(Player player) {
        UUID id = player.getUniqueId();

        try {
            Connection con = AbrirConecao();
            PreparedStatement st = con.prepareStatement("INSERT INTO SENHA VALUE (?)");
            st.setString(1, id.toString());
            st.executeUpdate();
            con.close();
        } catch (Exception var4) {
        }

    }

    public static boolean jodadorCadastrado(Player player) {
        UUID id = player.getUniqueId();

        try {
            Connection con = AbrirConecao();
            PreparedStatement st = con.prepareStatement("select count(*) from LOGIN where id = '" + id + "'");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            } else {
                return false;
            }
        } catch (Exception var5) {
            System.out.println("§c§lOcorreu um erro ao conectar ao jogadorCadastrado MYSQL: " + var5.toString());
            return false;
        }
    }

    public static String getJogador(Player player) {
        try {
            UUID id = player.getUniqueId();
            Connection con = AbrirConecao();
            PreparedStatement st = con.prepareStatement("select senha from LOGIN where id = '" + id + "'");
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getString("senha") : "traveiiiiiiiiiiii";
        } catch (Exception var5) {
            System.out.println("§c§lOcorreu um erro ao conectar ao verificar a senha do jogador: " + var5.toString());
            return "";
        }
    }

    public static void setJogador(Player player, String senha) {
        UUID id = player.getUniqueId();

        try {
            Connection con = AbrirConecao();
            con.setAutoCommit(false);
            String senhaCrypto = MD5(senha);
            String query = "INSERT INTO LOGIN (ID, SENHA) VALUES('" + id.toString() + "', '" + senhaCrypto + "') ON DUPLICATE KEY UPDATE SENHA = '" + senhaCrypto + "'";
            System.out.println(query);
            PreparedStatement st = con.prepareStatement(query);
            st.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            con.close();
        } catch (Exception var7) {
            System.out.println("§c§lOcorreu um erro ao conectar ao setJogador MYSQL: " + var7.toString());
        }

    }

    public static Connection AbrirConecao() {
        try {
            String password = Main.plugin.getConfig().getString("senha");
            String user = Main.plugin.getConfig().getString("user");
            String host = Main.plugin.getConfig().getString("ip");
            String port = Main.plugin.getConfig().getString("porta");
            String database = Main.plugin.getConfig().getString("data");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            return DriverManager.getConnection(url, user, password);
        } catch (Exception var6) {
            System.out.println("§c§lOcorreu um erro ao conectar ao MYSQL: " + var6.toString());
            return null;
        }
    }

    public static String MD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString(array[i] & 255 | 256).substring(1, 3));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException var5) {
            return null;
        }
    }
}
