package org.utl.dsm.Controller;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import org.utl.dsm.ConnectionDB.ConnectioDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.utl.dsm.Model.Mensaje;

/**
 *
 * @author Milk
 */

public class ControllerMensaje {

    public int saveMessage(int idUsuario, String mensajeTexto, int idChat) throws SQLException {
        String insert = "CALL registrarMensaje(?, ?, ?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        int response = 0;

        try {
            CallableStatement cs = (CallableStatement) conn.prepareCall(insert);
            cs.setInt(1, idUsuario);
            cs.setString(2, mensajeTexto);
            cs.setInt(3, idChat);

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                int idMessageInserted = rs.getInt("idMensaje");
                System.out.println("id de mensaje insertado: " + idMessageInserted);
                response = idMessageInserted;
            }
            cs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar la Bada");
            System.out.println(e.getMessage());
            response = -1;
        }
        connMySQL.close(conn);
        conn.close();
        return response;
    }

    public ArrayList<Mensaje> getMessages(int idChat) throws SQLException {
        String query = "CALL mostrarMensajes(?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        ArrayList<Mensaje> listMessages = new ArrayList<>();

        try {
            PreparedStatement executer = conn.prepareStatement(query);
            executer.setInt(1, idChat);

            ResultSet result = executer.executeQuery();
            while (result.next()) {
                Mensaje message = new Mensaje();
                message.setIdMensaje(result.getInt("idMensaje"));
                message.setIdChat(result.getInt("idChat"));
                message.setFecha(result.getDate("fecha"));

                listMessages.add(message);
            }

            executer.close();
        } catch (Exception e) {
            System.out.println("Fallo al hacer consulta en la BADA (getMessages:)");
            System.out.println(e.getMessage());
            listMessages = null;
        }
        conn.close();
        connMySQL.close(conn);
        return listMessages;
    }

   public Mensaje getMessage(int idMensaje) throws SQLException {
        String query = "CALL mostrarMensaje(?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        Mensaje message = new Mensaje();

        try {
            PreparedStatement executer = conn.prepareStatement(query);
            executer.setInt(1, idMensaje);

            ResultSet result = executer.executeQuery();
            if (result.next()) {
                message.setIdMensaje(result.getInt("idMensaje"));
                message.setIdChat(result.getInt("idChat"));
                message.setFecha(result.getDate("fecha"));
            }

            executer.close();
        } catch (Exception e) {
            System.out.println("Fallo al hacer consulta en la BADA (getMessage:)");
            System.out.println(e.getMessage());
            message = null;
        }
        conn.close();
        connMySQL.close(conn);
        return message;
    }

    public int updateMessage(int idMensaje, String mensajeTexto) throws SQLException {
        String insert = "CALL actualizarMensaje(?, ?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        int response = 0;

        try {
            CallableStatement cs = (CallableStatement) conn.prepareCall(insert);
            cs.setInt(1, idMensaje);
            cs.setString(2, mensajeTexto);

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                int idMessageUpdated = rs.getInt("idMensaje");
                System.out.println("id de mensaje actualizado: " + idMessageUpdated);
                response = idMessageUpdated;
            }
            cs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar la Bada");
            System.out.println(e.getMessage());
            response = -1;
        }
        connMySQL.close(conn);
        conn.close();
        return response;
    }
        
    public int deleteMessage(int idMensaje) throws SQLException {
        String insert = "CALL eliminarMensaje(?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        int response = 0;

        try {
            CallableStatement cs = (CallableStatement) conn.prepareCall(insert);
            cs.setInt(1, idMensaje);

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                int numMsgBeforeDeletion = rs.getInt("numMsgBeforeDeletion");
                int numMsgAfterDeletion = rs.getInt("numMsgAfterDeletion");
                if (numMsgAfterDeletion != numMsgBeforeDeletion) {
                    response = 1;
                } else {
                    response = 0;
                }
            }
            cs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar la Bada");
            System.out.println(e.getMessage());
            response = -1;
        }
        connMySQL.close(conn);
        conn.close();
        return response;
    }
}