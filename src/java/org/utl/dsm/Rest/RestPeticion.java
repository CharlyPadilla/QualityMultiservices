package org.utl.dsm.Rest;

import com.google.gson.Gson;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.QueryParam;

import org.utl.dsm.Controller.ControllerPeticion;
import org.utl.dsm.Model.FotoPublicacion;
import org.utl.dsm.Model.Peticion;

@Path("administrarPeticion")
public class RestPeticion extends Application {

    @Path("guardarPeticion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarPeticion(@FormParam("peticion") String JSONPeticion,
                                    @FormParam("token") String token) {
        ControllerPeticion cp = new ControllerPeticion();
        Gson gson = new Gson();
        String out = "";
        if (cp.validarToken(token)) {
            try {
                Peticion peticion = gson.fromJson(JSONPeticion, Peticion.class);
                ArrayList<FotoPublicacion> fotoPublicacion = peticion.getPublicacion().getListaFotos();

                System.out.println(fotoPublicacion.size());
                if (fotoPublicacion.size() <= 1) {
                    int idPeticionInsertada = cp.guardarPeticion(peticion.getPublicacion().getUsuario().getIdUsuario(),
                            peticion.getPublicacion().getDescripcion(), fotoPublicacion.get(0).getCadenaFoto(), peticion.getIdOficioBuscado());

                    out = """
                          {"idPeticionInsertada": "%s"} 
                     """;

                    out = String.format(out, idPeticionInsertada);
                } else {
                    out = """
                          {  "idPeticionInsertada": "-3" }
                     """;
                }

            } catch (Exception e) {

                System.out.println("Error en la petición");
                System.out.println(e.getMessage());
                out = """
                      { "idPeticionInsertada": "-2" }
                     """;
            }
        } else {
            out = """
                  { "idPeticionInsertada": "-4" }
                     """;
        }
        return Response.ok(out).build();
        // Si se regresa -4 siginifica que el token no es válido
        // Si se regresa -3 significa que recibió más de una foto
        // Si se regresa -2 siginifica fallo consumiendo el servicio
        // Si se regresa -1 significa fallo haciendo la consulta a la BADA
        // Si se regresa 0 significa que no realizó el registro.
    }

    
    @Path("guardarPeticionConVariasFotos")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarPeticionConVariasFotos(@FormParam("peticion") String JSONPeticion,
                                                    @FormParam("token") String token) {
        ControllerPeticion cp = new ControllerPeticion();
        Gson gson = new Gson();
        String out = "";
        if (cp.validarToken(token)) {
            try {
                Peticion peticion = gson.fromJson(JSONPeticion, Peticion.class);
                ArrayList<FotoPublicacion> fotoPublicacion = peticion.getPublicacion().getListaFotos();

                System.out.println(fotoPublicacion.size());
                if (fotoPublicacion.size() <= 1) {
                    int idPeticionInsertada = cp.guardarPeticion(peticion.getPublicacion().getUsuario().getIdUsuario(),
                            peticion.getPublicacion().getDescripcion(), fotoPublicacion.get(0).getCadenaFoto(), peticion.getIdOficioBuscado());

                    out = """
                          {"idPeticionInsertada": "%s"} 
                     """;

                    out = String.format(out, idPeticionInsertada);
                } else {
                    int idPeticionInsertada = cp.guardarPeticion(peticion.getPublicacion().getUsuario().getIdUsuario(),
                            peticion.getPublicacion().getDescripcion(), fotoPublicacion.get(0).getCadenaFoto(), peticion.getIdOficioBuscado());
                    out = """
                          {"idPeticionInsertada": "%s"} 
                     """;
                    cp.guardarfotosPeticion(fotoPublicacion, idPeticionInsertada);
                    
                }

            } catch (Exception e) {

                System.out.println("Error en la petición");
                System.out.println(e.getMessage());
                out = """
                      { "idPeticionInsertada": "-2" }
                     """;
            }
        } else {
            out = """
                  { "idPeticionInsertada": "-4" }
                     """;
        }
        return Response.ok(out).build();
        // Si se regresa -4 siginifica que el token no es válido
        // Si se regresa -2 siginifica fallo consumiendo el servicio
        // Si se regresa -1 significa fallo haciendo la consulta a la BADA
        // Si se regresa 0 significa que no realizó el registro.
    }

    
    @Path("obtenerPeticiones")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPeticiones(@QueryParam("idUsuario") int idUsuario) {
        ControllerPeticion cp = new ControllerPeticion();
        Gson gson = new Gson();
        String out = "";
        try {
            ArrayList<Peticion> listaPeticiones = cp.obtenerPeticiones(idUsuario);

            if (listaPeticiones != null) {
                out = gson.toJson(listaPeticiones);
            } else {
                out = """
                      { "Mensaje": "Error en la consulta el la BADA"} 
                     """;
            }

        } catch (Exception e) {

            System.out.println("Error en la petición: ");
            System.out.println(e.getMessage());
            out = """
                  { "Mensaje": "Error en la recepción de datos del servicio" }
                     """;
        }
        return Response.ok(out).build();
    }

    @Path("obtenerUnaPeticion")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerUnaPeticion(@QueryParam("idPublicacion") int idPublicacion) {
        ControllerPeticion cp = new ControllerPeticion();
        Gson gson = new Gson();
        String out = "";
        try {
            Peticion peticion = cp.obtenerUnaPeticion(idPublicacion);

            if (peticion != null) {
                out = gson.toJson(peticion);

            } else {
                out = """
                      {"Mensaje": "Error en la consulta el la BADA" }
                     """;
            }

        } catch (Exception e) {

            System.out.println("Error en la petición: ");
            System.out.println(e.getMessage());
            out = """
                  { "Mensaje": "Error en la recepción de datos del servicio" }
                     """;
        }
        return Response.ok(out).build();
    }

    @Path("actualizarPeticion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPeticion(@FormParam("peticion") String JSONPeticion,
            @FormParam("token") String token) {
        ControllerPeticion cp = new ControllerPeticion();
        Gson gson = new Gson();
        String out = "";

        if (cp.validarToken(token)) {
            try {
                Peticion peticion = gson.fromJson(JSONPeticion, Peticion.class);
                ArrayList<FotoPublicacion> fotoPublicacion = peticion.getPublicacion().getListaFotos();

                System.out.println(fotoPublicacion.size());

                if (fotoPublicacion.size() <= 1) {
                    int idPeticionActualizada = cp.actualizarPeticion(peticion.getIdPeticion(),
                            peticion.getPublicacion().getDescripcion(), fotoPublicacion.get(0).getCadenaFoto(), peticion.getOficioBuscado().getIdOficio());

                    out = """
                          { "idPeticionActualizada": "%s" }
                     """;

                    out = String.format(out, idPeticionActualizada);
                } else {
                    out = """
                          { "idPeticionActualizada": "-3" }
                     """;
                }

            } catch (Exception e) {

                System.out.println("Error en la petición");
                System.out.println(e.getMessage());
                out = """
                      {"idPeticionActualizada": "-2" }
                     """;
            }
        } else {
            out = """
                  { "idPeticionActualizada": "-4" }
                     """;
        }
        return Response.ok(out).build();
    }
    // Si se regresa -4 siginifica que el token no es válido

    @Path("eliminarPeticion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarPeticion(@FormParam("idPeticion") int idPeticion,
            @FormParam("token") String token) {
        ControllerPeticion cp = new ControllerPeticion();
        Gson gson = new Gson();
        String out = "";

        if (cp.validarToken(token)) {
            try {
                int peticion = cp.eliminarPeticion(idPeticion);

                if (0 < peticion) {
                    out = """
                     { "Mensaje": "Publicación tipo petición eliminada con éxito" }
                     """;
                } else {
                    out = """
                    {"Mensaje": "Error en la consulta el la BADA" }
                     """;
                }

            } catch (Exception e) {

                System.out.println("Error en la petición: ");
                System.out.println(e.getMessage());
                out = """
                 {"Mensaje": "Error en la recepción de datos del servicio" }
                     """;
            }
        } else {
            out = """
                    {"Mensaje": "Token no validado" }
                     """;
        }
        return Response.ok(out).build();
    }

    
    @Path("obtenerTodasLasPeticiones")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodasLasPeticiones() {
        ControllerPeticion ca = new ControllerPeticion();
        Gson gson = new Gson();
        String out = "";
        try {
            ArrayList<Peticion> listaPeticiones = ca.obtenerTodasLasPeticiones();

            if (listaPeticiones != null) {
                out = gson.toJson(listaPeticiones);
            } else {
                out = """
                      { "Mensaje": "Error en la consulta el la BADA" }
                     """;
            }

        } catch (Exception e) {

            System.out.println("Error en la petición: ");
            System.out.println(e.getMessage());
            out = """
                  { "Mensaje": "Error en la recepción de datos del servicio" }
                     """;
        }
        return Response.ok(out).build();
    }
    
}
