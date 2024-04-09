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
import java.util.ArrayList;
import org.utl.dsm.Controller.ControllerCliente;
import org.utl.dsm.Model.Cliente;

/**
 *
 * @author adria
 */
@Path("administrarCliente")
public class RestCliente {

    @Path("updateCliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("datosCliente") @DefaultValue("{}") String datosCliente) {
        String out;
        System.out.println("Datos cliente: " + datosCliente);
        ControllerCliente cc = new ControllerCliente();
        Gson gson = new Gson();
        try {
            Cliente cliente = gson.fromJson(datosCliente, Cliente.class);
            System.out.println("Nombre: " + cliente.getUsuario().getNombreUsuario());
            cc.modificarDatosCliente(cliente);
            out = "{\"result\":\"Cliente modificado exitosamente\"}";
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
            out = "{\"result\":\"Error en el servidor, favor de intentarlo de nuevo mas tarde\"}";
        }
        return Response.ok(out).build();
    }

    @Path("obtenerTodoClientes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodoClientes() {
        String out = "";

        try {
            // Instanciar el controlador de clientes
            ControllerCliente controller = new ControllerCliente();
            
            // Obtener la lista de clientes
            ArrayList<Cliente> listaClientes = controller.mostrarClientes();

            // Convertir la lista de clientes a formato JSON
            Gson gson = new Gson();
            out = gson.toJson(listaClientes);

            // Devolver una respuesta HTTP 200 OK con la lista de clientes en formato JSON
            return Response.ok(out).build();
        } catch (Exception e) {
            // En caso de excepción, generar un mensaje genérico y devolver una respuesta HTTP 500 Internal Server Error
            out = "{\"Algo malo ha pasado\":\"0\"}";
            System.out.println("Hola" + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(out).build();
        }
    }
}
