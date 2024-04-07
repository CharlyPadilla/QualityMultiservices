
package org.utl.dsm.Rest;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import org.utl.dsm.Controller.ControllerOficio;
import org.utl.dsm.Model.Oficio;

@Path("administrarOficio")
public class RestOficio {
    
    @Path("obtenerOficios")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerOficios(){
        ControllerOficio co = new ControllerOficio();
        Gson gson = new Gson();
        String out ="";
        try{
            ArrayList<Oficio> listaOficios = co.obtenerOficios();
               
            if(listaOficios != null){
                out = gson.toJson(listaOficios);
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
