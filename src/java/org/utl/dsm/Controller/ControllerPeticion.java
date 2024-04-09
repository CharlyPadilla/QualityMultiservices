
package org.utl.dsm.Controller;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import org.utl.dsm.ConnectionDB.ConnectioDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.utl.dsm.Model.FotoPublicacion;
import org.utl.dsm.Model.Peticion;

public class ControllerPeticion {
    
    
    public int guardarPeticion(int idUsuario, String descripcion, String foto, int idOficio) throws SQLException{
        String insert = "CALL registrarPublicacionPeticion(?, ?, ?, ?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        int respuesta=0;
        
        try{
            CallableStatement ejecutor = (CallableStatement) conn.prepareCall(insert);
            ejecutor.setInt(1, idUsuario);
            ejecutor.setString(2, descripcion);
            ejecutor.setString(3, foto);
            ejecutor.setInt(4, idOficio);
            
            ResultSet resultado = ejecutor.executeQuery();
            if (resultado.next()){
                int idPeticionInsertada = resultado.getInt("idPeticion");
                System.out.println("id de petición insertada: "+idPeticionInsertada);
                respuesta = idPeticionInsertada;
            }
           ejecutor.close();
        }catch(Exception e){
            System.out.println("Error al consultar la Bada");
            System.out.println(e.getMessage());
            respuesta = -1;
        }
        connMySQL.close(conn);
        conn.close();
        return respuesta;
    }
    
    
    

    public ArrayList<Peticion> obtenerPeticiones(int idUsuario) throws SQLException {
        String query = "CALL mostrarPublicacionesPeticion(?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        ArrayList<Peticion> listaPeticiones = new ArrayList<>();
       
        try{
            PreparedStatement executer = conn.prepareStatement(query);
            executer.setInt(1, idUsuario);
            
            ResultSet result = executer.executeQuery();
            while(result.next()){
                ArrayList<FotoPublicacion> listaFotosPeticion = new ArrayList<>();
                 
                Peticion peticion = new Peticion();
                FotoPublicacion fotosPeticion = new FotoPublicacion();
                peticion.setIdPeticion(result.getInt("idPeticion"));
                peticion.getPublicacion().setIdPublicacion(result.getInt("idPublicacion"));
                peticion.getPublicacion().getUsuario().setNombreUsuario(result.getString("nombreUsuario"));
                peticion.getPublicacion().setDescripcion(result.getString("descripcionPublicacion"));
                peticion.getPublicacion().setFechaCreacion(result.getString("fechaCreacion"));
                peticion.getPublicacion().setFechaEdicion(result.getString("fechaEdicion"));
                peticion.getOficioBuscado().setNombreOficio(result.getString("nombreOficio"));
                fotosPeticion.setCadenaFoto(result.getString("fotoPublicacion"));
                
                listaFotosPeticion.add(fotosPeticion);
                peticion.getPublicacion().setListaFotos(listaFotosPeticion);
                listaPeticiones.add(peticion);
            }
            
            executer.close();
        }catch(Exception e){
            System.out.println("Fallo al hacer consulta en la BADA (obtenerPeticiones:)");
            System.out.println(e.getMessage());
            listaPeticiones = null;
        }
        conn.close();
        connMySQL.close(conn);
        return listaPeticiones;
    }
    
    
    public Peticion obtenerUnaPeticion(int idPublicacion) throws SQLException {
        String query = "CALL mostrarPublicacionPeticion(?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        Peticion peticion = new Peticion();
        ArrayList<FotoPublicacion> listaFotosPeticion = new ArrayList<>();
        try{
            PreparedStatement executer = conn.prepareStatement(query);
            executer.setInt(1, idPublicacion);
            
            ResultSet result = executer.executeQuery();
            if(result.next()){
                FotoPublicacion fotoPeticion = new FotoPublicacion();
                peticion.setIdPeticion(result.getInt("idPeticion"));
                peticion.getPublicacion().setIdPublicacion(result.getInt("idPublicacion"));
                peticion.getPublicacion().getUsuario().setNombreUsuario(result.getString("nombreUsuario"));
                peticion.getPublicacion().setDescripcion(result.getString("descripcionPublicacion"));
                peticion.getPublicacion().setFechaCreacion(result.getString("fechaCreacion"));
                peticion.getPublicacion().setFechaEdicion(result.getString("fechaEdicion"));
                peticion.getOficioBuscado().setNombreOficio(result.getString("nombreOficio"));
                fotoPeticion.setCadenaFoto(result.getString("fotoPublicacion"));
                listaFotosPeticion.add(fotoPeticion);
                
                peticion.getPublicacion().setListaFotos(listaFotosPeticion);
                System.out.println(peticion.toString());
            }
            
            executer.close();
        }catch(Exception e){
            System.out.println("Fallo al hacer consulta en la BADA (obtenerPeticiones:)");
            System.out.println(e.getMessage());
            peticion= null;
            listaFotosPeticion = null;
        }
        conn.close();
        connMySQL.close(conn);
        return peticion;
    }
    
    
     public int actualizarPeticion(int idPeticion, String descripcion, String foto, int idOficio) throws SQLException{
        String insert = "CALL actualizarPublicacionPeticion(?, ?, ?, ?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        int respuesta=0;
        
        try{
            CallableStatement ejecutor = (CallableStatement) conn.prepareCall(insert);
            ejecutor.setInt(1, idPeticion);
            ejecutor.setString(2, descripcion);
            ejecutor.setString(3, foto);
            ejecutor.setInt(4, idOficio);
            
            ResultSet resultado = ejecutor.executeQuery();
            if (resultado.next()){
                int idPeticionInsertada = resultado.getInt("in_idPeticion");
                System.out.println("id de petición actualizada: "+idPeticionInsertada);
                respuesta = idPeticionInsertada;
            }
           ejecutor.close();
        }catch(Exception e){
            System.out.println("Error al consultar la Bada");
            System.out.println(e.getMessage());
            respuesta = -1;
        }
        connMySQL.close(conn);
        conn.close();
        return respuesta;
    }
        
     
     public int eliminarPeticion(int idPeticion) throws SQLException{
          String insert = "CALL eliminarPublicacionPeticion(?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        int respuesta=0;
        
        try{
            CallableStatement ejecutor = (CallableStatement) conn.prepareCall(insert);
            ejecutor.setInt(1, idPeticion);
            
            ResultSet resultado = ejecutor.executeQuery();
            if (resultado.next()){
                int numPubAntesEliminacion = resultado.getInt("numPubAntesEliminacion");
                int numPubDespuesEliminacion = resultado.getInt("numPubDespuesEliminacion");
                if(numPubDespuesEliminacion != numPubAntesEliminacion){
                    respuesta = 1;
                }else{
                    respuesta = 0;
                }
                
            }
           ejecutor.close();
        }catch(Exception e){
            System.out.println("Error al consultar la Bada");
            System.out.println(e.getMessage());
            respuesta = -1;
        }
        connMySQL.close(conn);
        conn.close();
        return respuesta;
        // Si se regresa 0 siginifica que NO se eliminó ningún registro
        // Si se regresa -1 significa fallo haciendo la consulta a la BADA
        // Si se regresa 1 significa que realizó la eliminación exitosamente.
     }
     
     
     
     
     // Este método no se está utilizando 
    public ArrayList<FotoPublicacion> obtenerFotosPeticion(int idPublicacion) throws SQLException {
        String query = "CALL mostrarPublicacionPeticion(?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
    
        ArrayList<FotoPublicacion> listaFotosPeticion = new ArrayList<>();
        try{
            PreparedStatement executer = conn.prepareStatement(query);
            executer.setInt(1, idPublicacion);
            
            ResultSet result = executer.executeQuery();
            while(result.next()){
                FotoPublicacion fotosPeticion = new FotoPublicacion();
                fotosPeticion.setCadenaFoto(result.getString("fotoPublicacion"));
                listaFotosPeticion.add(fotosPeticion);
            }
            
            executer.close();
        }catch(Exception e){
            System.out.println("Fallo al hacer consulta en la BADA (obtenerFotosPeticion:)");
            System.out.println(e.getMessage());
            listaFotosPeticion = null;
        }
        conn.close();
        connMySQL.close(conn);
        return listaFotosPeticion;
    }  
    
    public boolean validarToken(String token) {
         boolean resul;
            String query = "SELECT * FROM usuario WHERE token = ?";
        try {
            ConnectioDB connMySQL = new ConnectioDB();
            java.sql.Connection conn = connMySQL.open();
            PreparedStatement ejecutor = conn.prepareStatement(query);

            ejecutor.setString(1, token);
           
            // Ejecutamos la consulta
            ResultSet resultado = ejecutor.executeQuery();
         
            if (resultado.next()){
                resul = true;
            }else {
                resul = false;
            }

            ejecutor.close();
            conn.close();
            connMySQL.close(conn);
            return resul;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
