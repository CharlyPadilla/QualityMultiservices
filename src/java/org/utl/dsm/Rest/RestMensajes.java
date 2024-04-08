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
import org.utl.dsm.Controller.ControllerMensaje;
import org.utl.dsm.Model.Mensaje;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Milk
 */

@Path("administrarMensaje")
public class RestMensajes {
    
    //Rest para eliminar un mensaje
    @Path("eliminarMensaje")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@FormParam("idMensaje") int idMensaje) {
        String out;

        ControllerMensaje cm = new ControllerMensaje();
        try {
            int rowsAffected = cm.deleteMessage(idMensaje);
            if (rowsAffected > 0) {
                out = """
                        {"result":"Mensaje eliminado exitosamente"}
                      """;
            } else {
                out = """
                        {"result":"Error al eliminar el mensaje"}
                      """;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = """
                        {"result":"Error en el servidor, favor de intentarlo de nuevo mas tarde"}
                      """;
        }
        return Response.ok(out).build();
    }

    //Rest para guardar un mensaje
    @Path("guardarMensaje")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@FormParam("idUsuario") int idUsuario, @FormParam("mensajeTexto") String mensajeTexto, @FormParam("idChat") int idChat) {
        String out;

        ControllerMensaje cm = new ControllerMensaje();
        try {
            int idMessage = cm.saveMessage(idUsuario, mensajeTexto, idChat);
            if (idMessage > 0) {
                out = """
                        {"result":"Mensaje guardado exitosamente", "idMensaje": %d}
                      """.formatted(idMessage);
            } else {
                out = """
                        {"result":"Error al guardar el mensaje"}
                      """;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = """
                        {"result":"Error en el servidor, favor de intentarlo de nuevo mas tarde"}
                      """;
        }
        return Response.ok(out).build();
    }

    //Rest para obtener los mensajes de un chat
    @Path("obtenerMensajes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessages(@QueryParam("idChat") int idChat) {
        String out;

        ControllerMensaje cm = new ControllerMensaje();
        try {
            ArrayList<Mensaje> listMessages = cm.getMessages(idChat);
            if (listMessages != null) {
                Gson gson = new Gson();
                out = gson.toJson(listMessages);
            } else {
                out = """
                        {"result":"No se encontraron mensajes"}
                      """;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = """
                        {"result":"Error en el servidor, favor de intentarlo de nuevo mas tarde"}
                      """;
        }
        return Response.ok(out).build();
    }

    //Rest para obtener un mensaje en específico
    @Path("obtenerMensaje")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage(@QueryParam("idMensaje") int idMensaje) {
        String out;

        ControllerMensaje cm = new ControllerMensaje();
        try {
            Mensaje message = cm.getMessage(idMensaje);
            if (message != null) {
                Gson gson = new Gson();
                out = gson.toJson(message);
            } else {
                out = """
                        {"result":"No se encontró el mensaje"}
                      """;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = """
                        {"result":"Error en el servidor, favor de intentarlo de nuevo mas tarde"}
                      """;
        }
        return Response.ok(out).build();
    }

    //Rest para actualizar un mensaje
    @Path("actualizarMensaje")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("idMensaje") int idMensaje, @FormParam("mensajeTexto") String mensajeTexto) {
        String out;

        ControllerMensaje cm = new ControllerMensaje();
        try {
            int rowsAffected = cm.updateMessage(idMensaje, mensajeTexto);
            if (rowsAffected > 0) {
                out = """
                        {"result":"Mensaje actualizado exitosamente"}
                      """;
            } else {
                out = """
                        {"result":"Error al actualizar el mensaje"}
                      """;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out = """
                        {"result":"Error en el servidor, favor de intentarlo de nuevo mas tarde"}
                      """;
        }
        return Response.ok(out).build();
    }
}