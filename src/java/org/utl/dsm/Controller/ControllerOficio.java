
package org.utl.dsm.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.utl.dsm.ConnectionDB.ConnectioDB;
import org.utl.dsm.Model.Oficio;

public class ControllerOficio {
    
    
    

    public ArrayList<Oficio> obtenerOficios() throws SQLException {
        String query = "SELECT * FROM oficio";
        ConnectioDB connMySQL = new ConnectioDB();
        java.sql.Connection conn = connMySQL.open();
        ArrayList<Oficio> listaOficios = new ArrayList<>();
        try{
            PreparedStatement executer = conn.prepareStatement(query);
            
            ResultSet result = executer.executeQuery();
            while(result.next()){
                Oficio oficio = new Oficio(result.getInt("idOficio"), result.getString("nombreOficio"));
                
                listaOficios.add(oficio);
            }
            
            executer.close();
        }catch(Exception e){
            System.out.println("Fallo al hacer consulta en la BADA (obtenerPeticiones:)");
            System.out.println(e.getMessage());
            listaOficios = null;
        }
        connMySQL.close(conn);
        conn.close();
        connMySQL.close(conn);
        return listaOficios;
    }
}
