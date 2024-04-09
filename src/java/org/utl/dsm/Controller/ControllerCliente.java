package org.utl.dsm.Controller;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import org.utl.dsm.ConnectionDB.ConnectioDB;
import org.utl.dsm.Model.Cliente;

public class ControllerCliente {

    public Cliente modificarDatosCliente(Cliente cliente) {

        String query = "{CALL modificarDatosCliente(?,?,?,?,?,?)}";
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
}
