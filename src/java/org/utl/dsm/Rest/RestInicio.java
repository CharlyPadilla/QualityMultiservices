package org.utl.dsm.Rest;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.dsm.Controller.ControllerInicio;
import org.utl.dsm.Model.Cliente;
import org.utl.dsm.Model.FotoPublicacion;
import org.utl.dsm.Model.Publicacion;
import org.utl.dsm.Model.Vendedor;

@Path("inicio")
public class RestInicio {
    
    @Path("getAllVendedor")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAllVendedor() {
        String out = "";
        List<Vendedor> vendedores = null;
        ControllerInicio ci = new ControllerInicio();
        try {
            vendedores = ci.getAllVendedor();
            out = new Gson().toJson(vendedores);
        } catch (Exception e) {
            e.printStackTrace();
            out = """ 
                    {"error":"Ocurrio un error. Intente mas tarde."} 
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllCliente")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAllCliente() {
        String out = "";
        List<Cliente> clientes = null;
        ControllerInicio ci = new ControllerInicio();
        try {
            clientes = ci.getAllCliente();
            out = new Gson().toJson(clientes);
        } catch (Exception e) {
            e.printStackTrace();
            out = """ 
                    {"error":"Ocurrio un error. Intente mas tarde."} 
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("searchVendedor")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response searchVendedor(@QueryParam("palabra") String palabra) {
        String out = "";
        List<Vendedor> vendedores = null;
        ControllerInicio ci = new ControllerInicio();
        try {
            vendedores = ci.searchVendedor(palabra);
            out = new Gson().toJson(vendedores);
        } catch (Exception e) {
            e.printStackTrace();
            out = """ 
                    {"error":"Ocurrio un error. Intente mas tarde."} 
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("searchCliente")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response searchCliente(@QueryParam("palabra") String palabra) {
        String out = "";
        List<Cliente> clientes = null;
        ControllerInicio ci = new ControllerInicio();
        try {
            clientes = ci.searchCliente(palabra);
            out = new Gson().toJson(clientes);
        } catch (Exception e) {
            e.printStackTrace();
            out = """ 
                    {"error":"Ocurrio un error. Intente mas tarde."} 
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllPublicacion")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAllPublicacion() {
        String out = "";
        List<Publicacion> publicaciones = null;
        ControllerInicio ci = new ControllerInicio();
        try {
            publicaciones = ci.getAllPublicacion();
            out = new Gson().toJson(publicaciones);
        } catch (Exception e) {
            e.printStackTrace();
            out = """ 
                    {"error":"Ocurrio un error. Intente mas tarde."} 
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllFotosPublicacion")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAllFotosPublicacion(@QueryParam("numero") int numero) {
        String out = "";
        List<FotoPublicacion> fotos = null;
        ControllerInicio ci = new ControllerInicio();
        try {
            fotos = ci.getAllFotosPublicacion(numero);
            out = new Gson().toJson(fotos);
        } catch (Exception e) {
            e.printStackTrace();
            out = """ 
                    {"error":"Ocurrio un error. Intente mas tarde."} 
                  """;
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}