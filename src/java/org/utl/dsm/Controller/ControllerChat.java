/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.Controller;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.utl.dsm.ConnectionDB.ConnectioDB;
import org.utl.dsm.Model.Chat;
import org.utl.dsm.Model.Mensaje;

/**
 *
 * @author Milk
 */
public class ControllerChat {
   
    private Connection conn;
    
    
    public int saveChat(int idCliente, int idVendedor, String fechaInicioConversacion) throws SQLException {
        String insert = "CALL registrarChat(?, ?, ?)";
        ConnectioDB connMySQL = new ConnectioDB();
        Connection conn = connMySQL.open();
        int response = 0;

        try {
            CallableStatement cs = (CallableStatement) conn.prepareCall(insert);
            cs.setInt(1, idCliente);
            cs.setInt(2, idVendedor);
            cs.setString(3, fechaInicioConversacion);

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                int idChatInserted = rs.getInt("idChat");
                System.out.println("id de chat insertado: " + idChatInserted);
                response = idChatInserted;
            }
            cs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar la Base de Datos");
            System.out.println(e.getMessage());
            response = -1;
        }
        return response;
    }

    public ArrayList<Chat> getChats(int idCliente, int idVendedor) throws SQLException {
        String query = "CALL mostrarChats()";
        ConnectioDB connMySQL = new ConnectioDB();
        Connection conn = connMySQL.open();
        ArrayList<Chat> listChats = new ArrayList<>();

        try {
            PreparedStatement executer = conn.prepareStatement(query);

            ResultSet result = executer.executeQuery();
            while (result.next()) {
                Chat chat = new Chat();
                chat.setIdChat(result.getInt("idChat"));
                chat.setIdCliente(result.getInt("idCliente"));
                chat.setIdVendedor(result.getInt("idVendedor"));
                chat.setFechaInicioConversacion(result.getDate("fechaInicioConversacion"));

                listChats.add(chat);
            }

            executer.close();
        } catch (Exception e) {
            System.out.println("Fallo al hacer consulta en la Base de Datos (getChats:)");
            System.out.println(e.getMessage());
            listChats = null;
        }
        return listChats;
    }

    public Chat getChat(int idChat) throws SQLException {
        String query = "CALL mostrarChat(?)";
        ConnectioDB connMySQL = new ConnectioDB();
        Connection conn = connMySQL.open();
        Chat chat = new Chat();

        try {
            PreparedStatement executer = conn.prepareStatement(query);
            executer.setInt(1, idChat);

            ResultSet result = executer.executeQuery();
            if (result.next()) {
                chat.setIdChat(result.getInt("idChat"));
                chat.setIdCliente(result.getInt("idCliente"));
                chat.setIdVendedor(result.getInt("idVendedor"));
                chat.setFechaInicioConversacion(result.getDate("fechaInicioConversacion"));
            }

            executer.close();
        } catch (Exception e) {
            System.out.println("Fallo al hacer consulta en la Base de Datos (getChat:)");
            System.out.println(e.getMessage());
            chat = null;
        }
        return chat;
    }

    public int updateChat(int idChat, int idCliente, int idVendedor, String fechaInicioConversacion) throws SQLException {
        String insert = "CALL actualizarChat(?, ?, ?, ?)";
        ConnectioDB connMySQL = new ConnectioDB();
        Connection conn = connMySQL.open();
        int response = 0;

        try {
            CallableStatement cs = (CallableStatement) conn.prepareCall(insert);
            cs.setInt(1, idChat);
            cs.setInt(2, idCliente);
            cs.setInt(3, idVendedor);
            cs.setString(4, fechaInicioConversacion);

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                int idChatUpdated = rs.getInt("idChat");
                System.out.println("id de chat actualizado: " + idChatUpdated);
                response = idChatUpdated;
            }
            cs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar la Base de Datos");
            System.out.println(e.getMessage());
            response = -1;
        }
        return response;
    }

    public int deleteChat(int idChat) throws SQLException {
        String insert = "CALL eliminarChat(?)";
        ConnectioDB connMySQL = new ConnectioDB();
        Connection conn = connMySQL.open();
        int response = 0;

        try {
            CallableStatement cs = (CallableStatement) conn.prepareCall(insert);
            cs.setInt(1, idChat);

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                int numChatsBeforeDeletion = rs.getInt("numChatsBeforeDeletion");
                int numChatsAfterDeletion = rs.getInt("numChatsAfterDeletion");
                if (numChatsAfterDeletion != numChatsBeforeDeletion) {
                    response = 1;
                } else {
                    response = 0;
                }
            }
            cs.close();
        } catch (Exception e) {
            System.out.println("Error al consultar la Base de Datos");
            System.out.println(e.getMessage());
            response = -1;
        }
        
        return response;
    }
    
    
    public ArrayList<Chat> getChatsByClientId(int idCliente) throws SQLException {
        String query = "CALL mostrarChatsPorCliente(?)";
        ArrayList<Chat> listChats = new ArrayList<>();

        try (PreparedStatement executer = conn.prepareStatement(query)) {
            executer.setInt(1, idCliente);

            try (ResultSet result = executer.executeQuery()) {
                while (result.next()) {
                    Chat chat = new Chat();
                    chat.setIdChat(result.getInt("idChat"));
                    chat.setIdCliente(result.getInt("idCliente"));
                    chat.setIdVendedor(result.getInt("idVendedor"));
                    chat.setFechaInicioConversacion(result.getDate("fechaInicioConversacion"));
                    listChats.add(chat);
                }
            }
        } catch (Exception e) {
            System.out.println("Fallo al hacer consulta en la Base de Datos (getChatsByClientId:)");
            System.out.println(e.getMessage());
            listChats = null;
        }
        return listChats;
    }

    public ArrayList<Chat> getChatsByVendorId(int idVendedor) throws SQLException {
        String query = "CALL mostrarChatsPorVendedor(?)";
        ArrayList<Chat> listChats = new ArrayList<>();

        try (PreparedStatement executer = conn.prepareStatement(query)) {
            executer.setInt(1, idVendedor);

            try (ResultSet result = executer.executeQuery()) {
                while (result.next()) {
                    Chat chat = new Chat();
                    chat.setIdChat(result.getInt("idChat"));
                    chat.setIdCliente(result.getInt("idCliente"));
                    chat.setIdVendedor(result.getInt("idVendedor"));
                    chat.setFechaInicioConversacion(result.getDate("fechaInicioConversacion"));
                    listChats.add(chat);
                }
            }
        } catch (Exception e) {
            System.out.println("Fallo al hacer consulta en la Base de Datos (getChatsByVendorId:)");
            System.out.println(e.getMessage());
            listChats = null;
        }
        return listChats;
    }

    public ArrayList<Mensaje> getChatMessages(int idChat) throws SQLException {
        String query = "CALL mostrarMensajesPorChat(?)";
        ArrayList<Mensaje> listMensajes = new ArrayList<>();

        try (PreparedStatement executer = conn.prepareStatement(query)) {
            executer.setInt(1, idChat);

            try (ResultSet result = executer.executeQuery()) {
                while (result.next()) {
                    Mensaje mensaje = new Mensaje();
                    mensaje.setIdMensaje(result.getInt("idMensaje"));
                    mensaje.setIdChat(result.getInt("idChat"));
                    mensaje.setMensajeTexto(result.getString("mensajeTexto"));
                    mensaje.setFecha(result.getDate("fecha"));
                    listMensajes.add(mensaje);
                }
            }
        } catch (Exception e) {
            System.out.println("Fallo al hacer consulta en la Base de Datos (getChatMessages:)");
            System.out.println(e.getMessage());
            listMensajes = null;
        }
        return listMensajes;
    }

    public int sendMessage(int idChat, String messageText) throws SQLException {
        String insert = "CALL registrarMensaje(?,?,?)";
        int response = 0;

        try (CallableStatement cs = (CallableStatement) conn.prepareCall(insert)) {
            cs.setInt(1, idChat);
            cs.setString(2, messageText);
            cs.setDate(3, (java.sql.Date) new Date(System.currentTimeMillis()));

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    int idMensajeInserted = rs.getInt("idMensaje");
                    System.out.println("id de mensaje insertado: " + idMensajeInserted);
                    response = idMensajeInserted;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar la Base de Datos");
            System.out.println(e.getMessage());
            response = -1;
        }
        return response;
    }

    public int markMessageAsRead(int idMensaje) throws SQLException {
        String update = "CALL marcarMensajeComoLeido(?)";
        int response = 0;

        try (CallableStatement cs = (CallableStatement) conn.prepareCall(update)) {
            cs.setInt(1, idMensaje);

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    int idMensajeUpdated = rs.getInt("idMensaje");
                    System.out.println("id de mensaje actualizado: " + idMensajeUpdated);
                    response = idMensajeUpdated;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar la Base de Datos");
            System.out.println(e.getMessage());
            response = -1;
        }
        return response;
    }

    public int getUnreadMessagesCount(int idUsuario) throws SQLException {
        String query = "CALL contarMensajesNoLeidosPorUsuario(?)";
        int response = 0;

        try (PreparedStatement executer = conn.prepareStatement(query)) {
            executer.setInt(1, idUsuario);

            try (ResultSet result = executer.executeQuery()) {
                if (result.next()) {
                    response = result.getInt("numMensajesNoLeidos");
                }
            }
        } catch (Exception e) {
            System.out.println("Fallo al hacer consulta en la Base de Datos (getUnreadMessagesCount:)");
            System.out.println(e.getMessage());
            response = -1;
        }
        return response;
    }

    public ArrayList<Chat> getChatsWithUnreadMessages(int idUsuario) throws SQLException {
        String query = "CALL mostrarChatsConMensajesNoLeidosPorUsuario(?)";
        ArrayList<Chat> listChats = new ArrayList<>();

        try (PreparedStatement executer = conn.prepareStatement(query)) {
            executer.setInt(1, idUsuario);

            try (ResultSet result = executer.executeQuery()) {
                while (result.next()) {
                    Chat chat = new Chat();
                    chat.setIdChat(result.getInt("idChat"));
                    chat.setIdCliente(result.getInt("idCliente"));
                    chat.setIdVendedor(result.getInt("idVendedor"));
                    chat.setFechaInicioConversacion(result.getDate("fechaInicioConversacion"));
                    listChats.add(chat);
                }
            }
        } catch (Exception e) {
            System.out.println("Fallo al hacer consulta en la Base de Datos (getChatsWithUnreadMessages:)");
            System.out.println(e.getMessage());
            listChats = null;
        }
        return listChats;
    }

    public int deleteMessage(int idMensaje) throws SQLException {
        String delete = "CALL eliminarMensaje(?)";
        int response = 0;

        try (CallableStatement cs = (CallableStatement) conn.prepareCall(delete)) {
            cs.setInt(1, idMensaje);

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    int idMensajeDeleted = rs.getInt("idMensaje");
                    System.out.println("id de mensaje eliminado: " + idMensajeDeleted);
                    response = idMensajeDeleted;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar la Base de Datos");
            System.out.println(e.getMessage());
            response = -1;
        }
        return response;
    }

    public int deleteChatHistory(int idChat) throws SQLException {
        String delete = "CALL eliminarHistorialDeChat(?)";
        int response = 0;

        try (CallableStatement cs = (CallableStatement) conn.prepareCall(delete)) {
            cs.setInt(1, idChat);

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    int idChatDeleted = rs.getInt("idChat");
                    System.out.println("id de chat eliminado: " + idChatDeleted);
                    response = idChatDeleted;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar la Base de Datos");
            System.out.println(e.getMessage());
            response = -1;
        }
        return response;
    }

    public int updateChatDetails(int idChat, int idCliente, int idVendedor, Date fechaInicioConversacion) throws SQLException {
        String update = "CALL actualizarChat(?, ?, ?, ?)";
        int response = 0;

        try (CallableStatement cs = (CallableStatement) conn.prepareCall(update)) {
            cs.setInt(1, idChat);
            cs.setInt(2, idCliente);
            cs.setInt(3, idVendedor);
            cs.setDate(4, new java.sql.Date(fechaInicioConversacion.getTime()));

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    int idChatUpdated = rs.getInt("idChat");
                    System.out.println("id de chat actualizado: " + idChatUpdated);
                    response = idChatUpdated;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar la Base de Datos");
            System.out.println(e.getMessage());
            response = -1;
        }
        return response;
    }
}
