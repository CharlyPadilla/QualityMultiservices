/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.Rest;

import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import org.utl.dsm.ConnectionDB.ConnectioDB;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.utl.dsm.Controller.ControllerUsuario;

/**
 *
 * @author milto
 */
@Path("usuario")
public class RestUsuario {

    @Path("insertarUsuario")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarUsuario(@FormParam("nombreUsuario") String nombreUsuario,
            @FormParam("imagenPerfil") String imagenPerfil,
            @FormParam("ciudad") String ciudad,
            @FormParam("correo") String correo,
            @FormParam("contrasenia") String contrasenia) throws SQLException {
        ControllerUsuario cl = new ControllerUsuario();

        // Verificar si el usuario ya existe
        if (cl.usuarioExiste(correo)) {
            String out = "{\"response\":\"El usuario ya existe\"}";
            return Response.ok(out).build();
        } else {
            // Insertar usuario
            cl.insertarUsuario(nombreUsuario, imagenPerfil, ciudad, correo, contrasenia);

            // Generar token
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String tokenString = nombreUsuario + ":" + contrasenia + timeStamp;
            byte[] tokenBytes = tokenString.getBytes(StandardCharsets.UTF_8);
            String token = cl.bytesToHex(tokenBytes);

            // Asignar token al usuario
            cl.actualizarToken(correo, contrasenia);

            String out = String.format("{\"response\":\"Usuario insertado correctamente. Se le asignó el token: %s\"}", token);
            return Response.ok(out).build();
        }
    }

    @Path("iniciarSesion")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response iniciarSesion(@QueryParam("correo") String correo,
            @QueryParam("contrasenia") String contrasenia) {
        ControllerUsuario cu = new ControllerUsuario();
        boolean loginExitoso = cu.validarInicioSesion(correo, contrasenia);
        int idUsuario = -1;

        String mensaje = loginExitoso ? "Inicio de sesión exitoso" : "Inicio de sesión fallido";

        if (loginExitoso) {
            idUsuario = cu.obtenerIdUsuario(correo);
            cu.actualizarToken(correo, contrasenia);
        }

        return Response.ok("{\"success\":" + loginExitoso + ", \"idUsuario\":" + idUsuario + "}\n").build();
    }

    @Path("validarLogin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response validarLoginConToken(@QueryParam("correo") String correo,
            @QueryParam("contrasenia") String contrasenia) {
        String out = "";
        ControllerUsuario cl = new ControllerUsuario();

        String query = "UPDATE usuario SET token = ? WHERE correo = ? AND contrasenia = ?;";

        try {
            ConnectioDB connMySQL = new ConnectioDB();
            Connection conn = connMySQL.open();

            PreparedStatement pstmt = conn.prepareStatement(query);

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String tokenString = correo + ":" + contrasenia + ":" + timeStamp;
            byte[] tokenBytes = tokenString.getBytes(StandardCharsets.UTF_8);
            String token = cl.bytesToHex(tokenBytes);

            pstmt.setString(1, token);
            pstmt.setString(2, correo);
            pstmt.setString(3, contrasenia);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                out = String.format("{\"token\": \"%s\"}\n", token);
            } else {
                out = "{\"token\":\"INVALIDO\"}\n";
            }

            pstmt.close();
            conn.close();
            connMySQL.close(conn);

        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"Ha ocurrido un error al procesar la solicitud\"}\n";
        }

        return Response.ok(out).build();
    }
    
    @Path("insertarUsuarioCliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarUsuarioCliente(@FormParam("nombreUsuario") String nombreUsuario,
                                            @FormParam("imagenPerfil") String imagenPerfil,
                                            @FormParam("ciudad") String ciudad,
                                            @FormParam("correo") String correo,
                                            @FormParam("contrasenia") String contrasenia) throws SQLException {
        ControllerUsuario cl = new ControllerUsuario();
        String out="";
        Gson gson = new Gson();

        // Verificar si el usuario ya existe
        if (cl.usuarioExiste(correo)) {
            out = """
                      { "response": "Ya existe una cuenta con el correo ingreasado" }
                     """;
        } else {
            // Insertar usuario
            int idUsuario = cl.insertarUsuarioCliente(nombreUsuario, imagenPerfil, ciudad, correo, contrasenia);

            // Generar token
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String tokenString = nombreUsuario + ":" + contrasenia + timeStamp;
            byte[] tokenBytes = tokenString.getBytes(StandardCharsets.UTF_8);
            String token = cl.bytesToHex(tokenBytes);

            // Asignar token al usuario
            cl.actualizarTokenConIdUsuario(idUsuario, token);
           
            Usuario usuario = cl.buscarUsuario(correo, contrasenia);
            out = gson.toJson(usuario);
             out = String.format(out, token); 
        }
        return Response.ok(out).build();
    }
    
    
    @Path("insertarUsuarioVendedor")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarUsuarioVendedor(@FormParam("nombreUsuario") String nombreUsuario,
                                            @FormParam("imagenPerfil") String imagenPerfil,
                                            @FormParam("ciudad") String ciudad,
                                            @FormParam("correo") String correo,
                                            @FormParam("contrasenia") String contrasenia,
                                            @FormParam("idOficio") int idOficio,
                                            @FormParam("aniosExperiencia") int aniosExperiencia) throws SQLException {
        ControllerUsuario cl = new ControllerUsuario();
        String out="";
        Gson gson = new Gson();

        // Verificar si el usuario ya existe
        if (cl.usuarioExiste(correo)) {
            out = """
                      { "response": "Ya existe una cuenta con el correo ingreasado" }
                     """;
        } else {
            // Insertar usuario
            int idUsuario = cl.insertarUsuarioVendedor(nombreUsuario, imagenPerfil, ciudad, correo, contrasenia, idOficio, aniosExperiencia);

            // Generar token
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String tokenString = nombreUsuario + ":" + contrasenia + timeStamp;
            byte[] tokenBytes = tokenString.getBytes(StandardCharsets.UTF_8);
            String token = cl.bytesToHex(tokenBytes);

            // Asignar token al usuario
            cl.actualizarTokenConIdUsuario(idUsuario, token);
            
            Usuario usuario = cl.buscarUsuario(correo, contrasenia);
            out = gson.toJson(usuario);
        }
        return Response.ok(out).build();
    }
    
    
     @Path("loginConToken")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response validarLoginConToken_BD(@QueryParam("correo") @DefaultValue("") String correo,
                                            @QueryParam("contrasenia") @DefaultValue("") String contrasenia) {
        String out = "{}";
        Usuario us = new Usuario();
        ControllerUsuario cu = new ControllerUsuario();

        try {
            us = cu.buscarUsuario(correo, contrasenia );
            Gson gson = new Gson();

            if (us == null) {
                out = """
                      {"Mensaje": "Credenciales no válidas"}
                      """;
                 System.out.println("Credenciales no válidas");
            } else {
                
                 ControllerUsuario cl = new ControllerUsuario();
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            String tokenString = correo + ":"
                    + contrasenia + ""
                    + timeStamp;
            
            byte[] tokenBytes = tokenString.getBytes(java.nio.charset.StandardCharsets.UTF_8);     
                
            String token = cl.bytesToHex(tokenBytes);  
                  
            us.setToken(token);
                System.out.println("");
                int actualizacion = cu.actualizarTokenConIdUsuario(us.getIdUsuario(), us.getToken());
                
                System.out.println("¿Se actualizó el token?: "+ actualizacion);
                
                out = gson.toJson(us);
            }

        } catch (Exception e) {
            System.out.println("NO SE INSERTÓ EN LA BD");
            System.out.println(e.getMessage());
             out = """
                      {"Mensaje": "ERROR"}
                      """;
        }
        return Response.status(Response.Status.OK).entity(out).build();

    }
    
    
    @Path("cerrarSesion")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response cerrarSesion(@QueryParam("idUsuario") @DefaultValue("0") int idUsuario) {
        
        String out = """
                  { }
                  """;
            ControllerUsuario cl = new ControllerUsuario();
            
            int resultado = cl.cerrarSesionToken(idUsuario);
            if(resultado < 1) {
                out = """
                  { "Mensaje": "ERROR" }
                  """;
            } else {
                out = """
                  { "Mensaje": "Sesión cerrada" }
                  """;
                System.out.println("Sesión cerrada con éxito");
            }
      
        return Response.ok(out).build();
    }

}