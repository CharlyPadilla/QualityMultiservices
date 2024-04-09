/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.Controller;

import com.mysql.cj.jdbc.CallableStatement;
import org.utl.dsm.ConnectionDB.ConnectioDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import org.utl.dsm.Model.Usuario;

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
    
    
     public int insertarUsuarioCliente(String nombreUsuario, String imagenPerfil, String ciudad, String correo, String contrasenia) {
        String query = "CALL insertarCliente(?,?,?,?,?)";
            ConnectioDB connMySQL = new ConnectioDB();
            Connection conn = connMySQL.open();
            int respuesta=0;
        try {
            
            CallableStatement ejecutor = (CallableStatement) conn.prepareCall(query);
            ejecutor.setString(1, nombreUsuario);
            ejecutor.setString(2, imagenPerfil);
            ejecutor.setString(3, ciudad);
            ejecutor.setString(4, correo);
            ejecutor.setString(5, contrasenia);
            
            ResultSet resultado =  ejecutor.executeQuery();
            if (resultado.next()){
                int idUsuarioInsertado = resultado.getInt("idUsuario");
                System.out.println("id de usuario insertado: "+idUsuarioInsertado);
                respuesta = idUsuarioInsertado;
            }
            ejecutor.close();
            conn.close();
            connMySQL.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar usuario en la BD: " + e.getMessage());
            respuesta = -1;
        }
        return respuesta;
    }
     
     
     public int insertarUsuarioVendedor(String nombreUsuario, String imagenPerfil, String ciudad, String correo, String contrasenia, int idOficio, int aniosExperiencia) {
        String query = "CALL insertarVendedor (?,?,?,?,?,?,?)";
            ConnectioDB connMySQL = new ConnectioDB();
            Connection conn = connMySQL.open();
            int respuesta=0;
        try {
            
            CallableStatement ejecutor = (CallableStatement) conn.prepareCall(query);
            ejecutor.setString(1, nombreUsuario);
            ejecutor.setString(2, imagenPerfil);
            ejecutor.setString(3, ciudad);
            ejecutor.setString(4, correo);
            ejecutor.setString(5, contrasenia);
            ejecutor.setInt(4, idOficio);
            ejecutor.setInt(5, aniosExperiencia);
            
            ResultSet resultado =  ejecutor.executeQuery();
            if (resultado.next()){
                int idUsuarioInsertado = resultado.getInt("idUsuario");
                System.out.println("id de usuario insertado: "+idUsuarioInsertado);
                respuesta = idUsuarioInsertado;
            }
            ejecutor.close();
            conn.close();
            connMySQL.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar usuario en la BD: " + e.getMessage());
            respuesta = -1;
        }
        return respuesta;
    }
     
     
     public Usuario buscarUsuario(String correoIngresado, String contrasenia) {
        String query = "SELECT * FROM usuario WHERE correo = ? AND contrasenia = ?";
            ConnectioDB connMySQL = new ConnectioDB();
            Connection conn = connMySQL.open();
            Usuario usuario = null;
        try {
            
            PreparedStatement ejecutor = conn.prepareStatement(query);
            ejecutor.setString(1, correoIngresado);
            ejecutor.setString(2, contrasenia);
            
            ResultSet resultado =  ejecutor.executeQuery();
            if (resultado.next()){
                int idUsuario = resultado.getInt("idUsuario");
                String nombreUsuario = resultado.getString("nombreUsuario");
                String imagenPerfil = resultado.getString("imagenPerfil");
                String ciudad = resultado.getString("ciudad");
                String correo = resultado.getString("correo");
                String token = resultado.getString("token");
                
                usuario = new Usuario(idUsuario, nombreUsuario, imagenPerfil, ciudad, correo, token);
            }
            ejecutor.close();
            conn.close();
            connMySQL.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar usuario en la BD: " + e.getMessage());
        }
        return usuario;
    }
     
     
     public int actualizarTokenConIdUsuario(int idUsuario, String token) {
        String query = "UPDATE usuario SET token = ? WHERE idUsuario=?";
        int result=0;
        try {
            ConnectioDB connMySQL = new ConnectioDB();
            Connection conn = connMySQL.open();
            PreparedStatement pstmt = conn.prepareStatement(query);
            

            pstmt.setInt(2, idUsuario);
            pstmt.setString(1, token);

             result = pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            connMySQL.close(conn);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
     
      
    public int cerrarSesionToken(int idUsuario) {
        String query = "UPDATE usuario SET token ='' WHERE idUsuario = ?";

        try {
            ConnectioDB connMySQL = new ConnectioDB();
            Connection conn = connMySQL.open();
            // Con este objeto invocaremos al asistente para llenar el query:
            PreparedStatement cstmt =  conn.prepareStatement(query);


            // Pasar los parametros al query:
            cstmt.setInt(1, idUsuario);
                
            // Ejecutar la llamada al procedimiento almacenado:
            int resultado = cstmt.executeUpdate();
            System.out.println("Número de registros actualizadas: "+resultado);
            
                // Cerrar todas las instancias abiertas hacia la base de datos
                cstmt.close();
                conn.close();
                connMySQL.close(conn);
                return resultado;
        } catch (Exception e) {
            e.getMessage();
            System.out.println("NO SE REALIZÓ LA CONSULTA EN LA BD");
            return -1;
        }
        
    }
}