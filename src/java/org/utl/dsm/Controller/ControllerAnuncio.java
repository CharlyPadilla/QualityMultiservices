
package org.utl.dsm.Controller;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.utl.dsm.ConnectionDB.ConnectioDB;
import org.utl.dsm.Model.Anuncio;
import org.utl.dsm.Model.FotoPublicacion;

public class ControllerAnuncio {
 
    
    public int guardarAnuncio(int idUsuario, String descripcion, String foto, int idOficio) throws SQLException{
        String insert = "CALL registrarPublicacionAnuncio(?, ?, ?, ?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        int respuesta=0;
        
        System.out.println("idUsuario: "+idUsuario);
        System.out.println("descripcion: "+descripcion);
        System.out.println("Foto: "+foto);
        System.out.println("idOficio: "+idOficio);
        try{
            CallableStatement ejecutor = (CallableStatement) conn.prepareCall(insert);
            ejecutor.setInt(1, idUsuario);
            ejecutor.setString(2, descripcion);
            ejecutor.setString(3, foto);
            ejecutor.setInt(4, idOficio);
            
            ResultSet resultado = ejecutor.executeQuery();
            if (resultado.next()){
                int idPeticionInsertada = resultado.getInt("idAnuncio");
                System.out.println("id de anuncio insertado: "+idPeticionInsertada);
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
    
    
    public ArrayList<Anuncio> obtenerAnuncios(int idUsuario) throws SQLException {
        String query = "CALL mostrarPublicacionesAnuncio(?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        ArrayList<Anuncio> listaAnuncio = new ArrayList<>();
        
        try{
            PreparedStatement executer = conn.prepareStatement(query);
            executer.setInt(1, idUsuario);
            
            ResultSet result = executer.executeQuery();
            while(result.next()){
                ArrayList<FotoPublicacion> listaFotosPeticion = new ArrayList<>();
                
                Anuncio anuncio = new Anuncio();
                FotoPublicacion fotosPeticion = new FotoPublicacion();
                anuncio.setIdAnuncio(result.getInt("idAnuncio"));
                anuncio.getPublicacion().setIdPublicacion(result.getInt("idPublicacion"));
                anuncio.getPublicacion().getUsuario().setNombreUsuario(result.getString("nombreUsuario"));
                anuncio.getPublicacion().setDescripcion(result.getString("descripcionPublicacion"));
                anuncio.getPublicacion().setFechaCreacion(result.getString("fechaCreacion"));
                anuncio.getPublicacion().setFechaEdicion(result.getString("fechaEdicion"));
                anuncio.getOficioOfrecido().setNombreOficio(result.getString("nombreOficio"));
                fotosPeticion.setCadenaFoto(result.getString("fotoPublicacion"));
                
                listaFotosPeticion.add(fotosPeticion);
                anuncio.getPublicacion().setListaFotos(listaFotosPeticion);
                listaAnuncio.add(anuncio);
            }
            
            executer.close();
        }catch(Exception e){
            System.out.println("Fallo al hacer consulta en la BADA (obtenerAnuncios):");
            System.out.println(e.getMessage());
            listaAnuncio = null;
        }
        conn.close();
        connMySQL.close(conn);
        return listaAnuncio;
    }
    
    
    public int actualizarAnuncio(int idAnuncio, String descripcion, String foto, int idOficio) throws SQLException{
        String insert = "CALL actualizarPublicacionAnuncio(?, ?, ?, ?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        int respuesta=0;
        
        try{
            CallableStatement ejecutor = (CallableStatement) conn.prepareCall(insert);
            ejecutor.setInt(1, idAnuncio);
            ejecutor.setString(2, descripcion);
            ejecutor.setString(3, foto);
            ejecutor.setInt(4, idOficio);
            
            ResultSet resultado = ejecutor.executeQuery();
            if (resultado.next()){
                int idAnuncioActualizado = resultado.getInt("in_idAnuncio");

                System.out.println("id de anuncio actualizado: "+idAnuncioActualizado);
                respuesta = idAnuncioActualizado;
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


    public int eliminarAnuncio(int idAnucnio) throws SQLException{
          String insert = "CALL eliminarPublicacionAnuncio(?)";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        int respuesta=0;
        
        try{
            CallableStatement ejecutor = (CallableStatement) conn.prepareCall(insert);
            ejecutor.setInt(1, idAnucnio);
            
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
            System.out.println("Error al consultar la Bada:");
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
    
    public ArrayList<Anuncio> obtenerTodosLosAnuncios() throws SQLException {
        String query = "SELECT * FROM mostrarTodosLosAnuncios;";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        ArrayList<Anuncio> listaAnuncio = new ArrayList<>();
        ArrayList<FotoPublicacion> listaFotosPeticion = new ArrayList<>();
        try{
            PreparedStatement executer = conn.prepareStatement(query);
            
            ResultSet result = executer.executeQuery();
            while(result.next()){
                Anuncio anuncio = new Anuncio();
                FotoPublicacion fotosPeticion = new FotoPublicacion();
                anuncio.setIdAnuncio(result.getInt("idAnuncio"));
                anuncio.getPublicacion().setIdPublicacion(result.getInt("idPublicacion"));
                anuncio.getPublicacion().getUsuario().setNombreUsuario(result.getString("nombreUsuario"));
                anuncio.getPublicacion().setDescripcion(result.getString("descripcionPublicacion"));
                anuncio.getPublicacion().setFechaCreacion(result.getString("fechaCreacion"));
                anuncio.getPublicacion().setFechaEdicion(result.getString("fechaEdicion"));
                anuncio.getOficioOfrecido().setNombreOficio(result.getString("nombreOficio"));
                fotosPeticion.setCadenaFoto(result.getString("fotoPublicacion"));
                
                listaFotosPeticion.add(fotosPeticion);
                anuncio.getPublicacion().setListaFotos(listaFotosPeticion);
                listaAnuncio.add(anuncio);
            }
            
            executer.close();
        }catch(Exception e){
            System.out.println("Fallo al hacer consulta en la BADA (obtenerAnuncios):");
            System.out.println(e.getMessage());
            listaFotosPeticion = null;
        }
        conn.close();
        connMySQL.close(conn);
        return listaAnuncio;
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
