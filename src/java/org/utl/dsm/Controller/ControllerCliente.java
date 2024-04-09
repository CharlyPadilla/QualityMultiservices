package org.utl.dsm.Controller;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.utl.dsm.ConnectionDB.ConnectioDB;
import org.utl.dsm.Model.Cliente;
import org.utl.dsm.Model.Usuario;

public class ControllerCliente {

    public Cliente modificarDatosCliente(Cliente cliente) {

        String query = "CALL modificarDatosCliente(?,?,?,?,?,?)";
        try {
            ConnectioDB connMySQL = new ConnectioDB();
            //Abrimos la conexion con la base de datos
            Connection conn = connMySQL.open();
            //Con este objeto invocaremos al asistente para llenar el query
            CallableStatement cstmt = (CallableStatement) conn.prepareCall(query);  // El objeto de tipo CallableStatement: se utiliza para ejecutar procedimientos almacenados

            cstmt.setInt(1, cliente.getUsuario().getIdUsuario());
            cstmt.setString(2, cliente.getUsuario().getNombreUsuario());
            cstmt.setString(3, cliente.getUsuario().getImagenPerfil());
            cstmt.setString(4, cliente.getUsuario().getCiudad());
            cstmt.setString(5, cliente.getUsuario().getNumeroCelular());
            cstmt.setString(6, cliente.getUsuario().getContrasenia());
            cstmt.execute();
            // Cerrar todas las instancias abiertas hacia la base de datos (bd)
            cstmt.close();
            conn.close(); // Cerrar la conexion (despues de alguna accion, es recomendable cerrar la conexion)
            connMySQL.close(conn);
            return cliente;
        } catch (Exception ex) {
            System.out.println("Error al ejecutar el procedimiento almacenado: " + ex.getMessage());
        }
        return null;
    }

    public ArrayList<Cliente> mostrarClientes() {
        String query = "SELECT idUsuario, nombreUsuario, imagenPerfil, ciudad, correo FROM Usuario";
        try {
            ConnectioDB connMysql = new ConnectioDB();
            Connection conn = connMysql.open();

            // Se utiliza un PreparedStatement para ejecutar la consulta SELECT
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet resultado = pstm.executeQuery();

            ArrayList<Cliente> listaClientes = new ArrayList<>();

            while (resultado.next()) {
                
                int idUsuario = resultado.getInt("idUsuario");
                String nombreUsuario = resultado.getString("nombreUsuario");
                System.out.println(nombreUsuario);
                String imagenPerfil = resultado.getString("imagenPerfil");
                String ciudad = resultado.getString("ciudad");
                String correo = resultado.getString("correo");

                // Crea un objeto Cliente con todos los datos
                Cliente cliente = new Cliente(idUsuario, nombreUsuario, imagenPerfil, ciudad, correo);
                
                System.out.println(idUsuario);
                // Agrega el cliente a la lista
                listaClientes.add(cliente);
            }

            // Cierre de recursos
            resultado.close();
            pstm.close();
            connMysql.close();

            return listaClientes;
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
            return new ArrayList<>(); // Devuelve una lista vacía en caso de error
        }

    }
}
