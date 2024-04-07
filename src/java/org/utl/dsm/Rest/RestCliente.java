/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.Rest;

import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
            System.out.println("Nombre: "+ cliente.getUsuario().getNombreUsuario());
            cc.modificarDatosCliente(cliente);
            out = """
                    {"result":"Cliente modificado exitosamente"}
                  """;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            out = """
                    {"result":"Error en el servidor, favor de intentarlo de nuevo mas tarde"}
                  """;
        }
        return Response.ok(out).build();
    }
}
