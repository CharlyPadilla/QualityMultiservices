
package org.utl.dsm.Rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.utl.dsm.Controller.ControllerAnuncio;
import org.utl.dsm.Controller.ControllerPeticion;
import org.utl.dsm.Model.Anuncio;
import org.utl.dsm.Model.FotoPublicacion;
import org.utl.dsm.Model.Peticion;
import org.utl.dsm.Model.Publicacion;

@Path("administrarAnuncio")
public class RestAnuncio {
    
        @Path("guardarAnuncio")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarPeticion(
                                       @FormParam("anuncio") String JSONAnuncio){
        ControllerAnuncio cp = new ControllerAnuncio();
        Gson gson = new Gson();
        String out ="";
        try{
            Anuncio anuncio = gson.fromJson(JSONAnuncio, Anuncio.class);
            ArrayList<FotoPublicacion> fotoPublicacion = anuncio.getPublicacion().getListaFotos();
            
            System.out.println(fotoPublicacion.size());
              if(fotoPublicacion.size() <= 1){
               int idAnuncioInsertado = cp.guardarAnuncio(anuncio.getPublicacion().getUsuario().getIdUsuario(), 
                       anuncio.getPublicacion().getDescripcion(), fotoPublicacion.get(0).getCadenaFoto(), anuncio.getOficioOfrecido().getIdOficio()); 
               
               out ="""
                     "idAnuncioInsertado": "%s" 
                     """;
               
               out = String.format(out, idAnuncioInsertado);
            }
                
           }catch(Exception e){
            
            System.out.println("Error en la petición");
            System.out.println(e.getMessage());
            out ="""
                     "idAnuncioInsertado": "-2" 
                     """;
        }
        return Response.ok(out).build();
        // Si se regresa -2 siginifica fallo consumiendo el servicio
        // Si se regresa -1 significa fallo haciendo la consulta a la BADA
        // Si se regresa 0 significa que no realizó el registro.
    }
    
    
    @Path("obtenerAnuncios")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAnuncios(@QueryParam("idUsuario") int idUsuario ){
        ControllerAnuncio ca = new ControllerAnuncio();
        Gson gson = new Gson();
        String out ="";
        try{
            ArrayList<Anuncio> listaAnuncios = ca.obtenerAnuncios(idUsuario);
               
            if(listaAnuncios != null){
                out = gson.toJson(listaAnuncios);
            }else{
               out ="""
                     "Mensaje": "Error en la consulta el la BADA" 
                     """; 
            }
                
           }catch(Exception e){
            
            System.out.println("Error en la petición: ");
            System.out.println(e.getMessage());
            out ="""
                     "Mensaje": "Error en la recepción de datos del servicio" 
                     """;
        }
        return Response.ok(out).build();
    }
    
    
    @Path("actualizarAnuncio")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarAnuncio(
                                       @FormParam("anuncio") String JSONAnuncio    ){
        ControllerAnuncio ca = new ControllerAnuncio();
        Gson gson = new Gson();
        String out ="";
        try{
            Anuncio anuncio = gson.fromJson(JSONAnuncio, Anuncio.class);
            ArrayList<FotoPublicacion> fotoPublicacion = anuncio.getPublicacion().getListaFotos();
            
            System.out.println(fotoPublicacion.size());
              if(fotoPublicacion.size() <= 1){
               int idAnuncioActualizado = ca.actualizarAnuncio(anuncio.getIdAnuncio(), 
                       anuncio.getPublicacion().getDescripcion(),fotoPublicacion.get(0).getCadenaFoto(), anuncio.getOficioOfrecido().getIdOficio());
               
               out ="""
                     "idAnuncioActualizado": "%s" 
                     """;
               
               out = String.format(out, idAnuncioActualizado);
            }
                
           }catch(Exception e){
            
            System.out.println("Error en la petición");
            System.out.println(e.getMessage());
            out ="""
                     "idAnuncioActualizado": "-2" 
                     """;
        }
        return Response.ok(out).build();
    }
    
    
    @Path("eliminarAnuncio")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarAnuncio(@FormParam("idAnuncio") int idAnuncio ){
        ControllerAnuncio ca = new ControllerAnuncio();
        Gson gson = new Gson();
        String out ="";
        try{
            int peticion = ca.eliminarAnuncio(idAnuncio);
               
            if(0 < peticion){
                out ="""
                     { "Mensaje": "Publicación tipo anuncio eliminada con éxito" }
                     """; 
            }else{
               out ="""
                    {"Mensaje": "Error en la consulta el la BADA" }
                     """; 
            }
                
           }catch(Exception e){
            
            System.out.println("Error en la petición: ");
            System.out.println(e.getMessage());
            out ="""
                 {"Mensaje": "Error en la recepción de datos del servicio" }
                     """;
        }
        return Response.ok(out).build();
    }


    @Path("obtenerTodosLosAnuncios")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodosLosAnuncios(){
        ControllerAnuncio ca = new ControllerAnuncio();
        Gson gson = new Gson();
        String out ="";
        try{
            ArrayList<Anuncio> listaAnuncios = ca.obtenerTodosLosAnuncios();
               
            if(listaAnuncios != null){
                out = gson.toJson(listaAnuncios);
            }else{
               out ="""
                     "Mensaje": "Error en la consulta el la BADA" 
                     """; 
            }
                
           }catch(Exception e){
            
            System.out.println("Error en la petición: ");
            System.out.println(e.getMessage());
            out ="""
                     "Mensaje": "Error en la recepción de datos del servicio" 
                     """;
        }
        return Response.ok(out).build();
    }
}
