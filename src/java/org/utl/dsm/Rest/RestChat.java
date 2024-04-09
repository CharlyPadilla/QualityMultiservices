/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.Rest;

import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.utl.dsm.Controller.ControllerChat;
import org.utl.dsm.Model.Chat;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.utl.dsm.Model.Mensaje;

@Path("administrarChat")
public class RestChat {

    @Path("eliminarChat")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteChat(@FormParam("idChat") int idChat) {
        String out;

        ControllerChat cc = new ControllerChat();
        try {
            int rowsAffected = cc.deleteChat(idChat);
            if (rowsAffected > 0) {
                out = "{\"result\":\"Chat eliminado exitosamente\"}";
            } else {
                out = "{\"result\":\"Error al eliminar el chat\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    @Path("guardarChat")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveChat(@FormParam("idCliente") int idCliente, @FormParam("idVendedor") int idVendedor, @FormParam("fechaInicioConversacion") String fechaInicioConversacion) {
        String out;

        ControllerChat cc = new ControllerChat();
        try {
            int idChat = cc.saveChat(idCliente, idVendedor, fechaInicioConversacion);
            if (idChat > 0) {
                out = String.format("{\"result\":\"Chat guardado exitosamente\", \"idChat\": %d}", idChat);
            } else {
                out = "{\"result\":\"Error al guardar el chat\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    @Path("obtenerChats")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChats(@DefaultValue("0") @QueryParam("idCliente") int idCliente, @DefaultValue("0") @QueryParam("idVendedor") int idVendedor) {
        String out;

        ControllerChat cc = new ControllerChat();
        try {
            ArrayList<Chat> listChats = cc.getChats(idCliente, idVendedor);
            if (listChats != null) {
                Gson gson = new Gson();
                out = gson.toJson(listChats);
            } else {
                out = "{\"result\":\"No se encontraron chats\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    @Path("obtenerChat")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChat(@QueryParam("idChat") int idChat) {
        String out;

        ControllerChat cc = new ControllerChat();
        try {
            Chat chat = cc.getChat(idChat);
            if (chat != null) {
                Gson gson = new Gson();
                out = gson.toJson(chat);
            } else {
                out = "{\"result\":\"No se encontró el chat\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    @Path("actualizarChat")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateChat(@FormParam("idChat") int idChat, @FormParam("idCliente") int idCliente, @FormParam("idVendedor") int idVendedor, @FormParam("fechaInicioConversacion") String fechaInicioConversacion) {
        String out;

        ControllerChat cc = new ControllerChat();
        try {
            int rowsAffected = cc.updateChat(idChat, idCliente, idVendedor, fechaInicioConversacion);
            if (rowsAffected > 0) {
                out = "{\"result\":\"Chat actualizado exitosamente\"}";
            } else {
                out = "{\"result\":\"Error al actualizar el chat\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    // Retrieves all chats associated with a specific client ID
    @Path("chatsByClientId")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChatsByClientId(@DefaultValue("0") @QueryParam("idCliente") int idCliente) {
        String out;

        ControllerChat cc = new ControllerChat();
        try {
            ArrayList<Chat> listChats = cc.getChatsByClientId(idCliente);
            if (listChats != null) {
                Gson gson = new Gson();
                out = gson.toJson(listChats);
            } else {
                out = "{\"result\":\"No se encontraron chats\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    // Retrieves all chats associated with a specific vendor ID
    @Path("chatsByVendorId")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChatsByVendorId(@DefaultValue("0") @QueryParam("idVendedor") int idVendedor) {
        String out;

        ControllerChat cc = new ControllerChat();
        try {
            ArrayList<Chat> listChats = cc.getChatsByVendorId(idVendedor);
            if (listChats != null) {
                Gson gson = new Gson();
                out = gson.toJson(listChats);
            } else {
                out = "{\"result\":\"No se encontraron chats\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    // Retrieves all messages associated with a specific chat ID
    @Path("chatMessages")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChatMessages(@DefaultValue("0") @QueryParam("idChat") int idChat) {
        String out;

        ControllerChat cc = new ControllerChat();
        try {
            ArrayList<Mensaje> listMensajes = cc.getChatMessages(idChat);
            if (listMensajes != null) {
                Gson gson = new Gson();
                out = gson.toJson(listMensajes);
            } else {
                out = "{\"result\":\"No se encontraron mensajes\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    // Sends a new message to a specific chat
    @Path("sendMessage")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMessage(@FormParam("idChat") int idChat, @FormParam("messageText") String messageText) {
        String out;

        ControllerChat cc = new ControllerChat();
        try {
            int idMensaje = cc.sendMessage(idChat, messageText);
            if (idMensaje > 0) {
                out = String.format("{\"result\":\"Mensaje enviado exitosamente\", \"idMensaje\": %d}", idMensaje);
            } else {
                out = "{\"result\":\"Error al enviar el mensaje\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    // Marks a specific message as read
    @Path("markMessageAsRead")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response markMessageAsRead(@FormParam("idMensaje") int idMensaje) {
        String out;

        ControllerChat cc = new ControllerChat();
        try {
            int rowsAffected = cc.markMessageAsRead(idMensaje);
            if (rowsAffected > 0) {
                out = "{\"result\":\"Mensaje marcado como leido exitosamente\"}";
            } else {
                out = "{\"result\":\"Error al marcar el mensaje como leido\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    // Retrieves the number of unread messages for a specific user
    @Path("unreadMessagesCount")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnreadMessagesCount(@DefaultValue("0") @QueryParam("idUsuario") int idUsuario) {
        String out;

        ControllerChat cc = new ControllerChat();
        try {
            int count = cc.getUnreadMessagesCount(idUsuario);
            if (count >= 0) {
                out = String.format("{\"result\":\"Numero de mensajes no leidos\", \"count\": %d}", count);
            } else {
                out = "{\"result\":\"Error al obtener el numero de mensajes no leidos\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    // Retrieves all chats with unread messages for a specific user
    @Path("chatsWithUnreadMessages")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChatsWithUnreadMessages(@DefaultValue("0") @QueryParam("idUsuario") int idUsuario) {
        String out;

        ControllerChat cc = new ControllerChat();
        try {
            ArrayList<Chat> listChats = cc.getChatsWithUnreadMessages(idUsuario);
            if (listChats != null) {
                Gson gson = new Gson();
                out = gson.toJson(listChats);
            } else {
                out = "{\"result\":\"No se encontraron chats con mensajes no leidos\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    // Deletes all messages associated with a specific chat
    @Path("deleteChatHistory")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteChatHistory(@FormParam("idChat") int idChat) {
        String out;

        ControllerChat cc = new ControllerChat();
        try {
            int rowsAffected = cc.deleteChatHistory(idChat);
            if (rowsAffected > 0) {
                out = "{\"result\":\"Historial de chat eliminado exitosamente\"}";
            } else {
                out = "{\"result\":\"Error al eliminar el historial de chat\"}";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    // Updates the details of a specific chat
    @Path("updateChatDetails")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateChatDetails(@FormParam("idChat") int idChat, @FormParam("idCliente") int idCliente, @FormParam("idVendedor") int idVendedor, @FormParam("fechaInicioConversacion") String fechaInicioConversacion) throws ParseException, SQLException {
        String out;

        ControllerChat cc = new ControllerChat();
        int rowsAffected = cc.updateChatDetails(idChat, idCliente, idVendedor, new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicioConversacion));
        if (rowsAffected > 0) {
            out = "{\"result\":\"Detalles del chat actualizados exitosamente\"}";
        } else {
            out = "{\"result\":\"Error al actualizar los detalles del chat\"}";
        }
        return Response.ok(out).build();
    }
}