/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.Controller;

import org.utl.dsm.ConnectionDB.ConnectioDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Milk
 */
public class ControllerUsuario {

    public void insertarUsuario(String nombreUsuario, String imagenPerfil, String ciudad, String correo, String contrasenia) {
        String query = "INSERT INTO usuario (nombreUsuario, imagenPerfil, ciudad, correo, contrasenia) VALUES (?, ?, ?, ?, ?)";

        try {
            ConnectioDB connMySQL = new ConnectioDB();
            Connection conn = connMySQL.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nombreUsuario);
            pstmt.setString(2, imagenPerfil);
            pstmt.setString(3, ciudad);
            pstmt.setString(4, correo);
            pstmt.setString(5, contrasenia);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            connMySQL.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar usuario en la BD: " + e.getMessage());
        }
    }

    public boolean usuarioExiste(String correo) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM usuario WHERE correo = ?";
        ConnectioDB connMySQL = new ConnectioDB();
        Connection conn = connMySQL.open();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, correo);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int count = rs.getInt("count");
        rs.close();
        pstmt.close();
        conn.close();
        connMySQL.close(conn);
        return count > 0;
    }

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public boolean validarInicioSesion(String correo, String contrasenia) {
        String query = "SELECT contrasenia FROM usuario WHERE correo = ? AND contrasenia = ?";
        boolean loginExitoso = false;

        try {
            ConnectioDB connMySQL = new ConnectioDB();
            Connection conn = connMySQL.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, correo);
            pstmt.setString(2, contrasenia);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                loginExitoso = true;
            }

            rs.close();
            pstmt.close();
            conn.close();
            connMySQL.close(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return loginExitoso;
    }

    public void actualizarToken(String correo, String contrasenia) {
        String query = "UPDATE usuario SET token = ? WHERE correo = ? AND contrasenia = ?";

        try {
            ConnectioDB connMySQL = new ConnectioDB();
            Connection conn = connMySQL.open();
            PreparedStatement pstmt = conn.prepareStatement(query);

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            String tokenString = correo + ": " + contrasenia + ": " + timeStamp;
            byte[] tokenBytes = tokenString.getBytes(java.nio.charset.StandardCharsets.UTF_8);
            String token = bytesToHex(tokenBytes);

            pstmt.setString(1, token);
            pstmt.setString(2, correo);
            pstmt.setString(3, contrasenia);

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            connMySQL.close(conn);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int obtenerIdUsuario(String correo) {
        String query = "SELECT idUsuario FROM usuario WHERE correo = ?";
        int idUsuario = -1;

        try {
            ConnectioDB connMySQL = new ConnectioDB();
            Connection conn = connMySQL.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, correo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idUsuario = rs.getInt("idUsuario");
            }

            rs.close();
            pstmt.close();
            conn.close();
            connMySQL.close(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return idUsuario;
    }
}