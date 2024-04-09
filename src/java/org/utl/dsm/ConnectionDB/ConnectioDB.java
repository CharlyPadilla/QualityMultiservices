    
package org.utl.dsm.ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectioDB {
    Connection conn;
            
    public Connection open(){
        
        String user = "root";
        String password = "2024865Ss";
        String url = "jdbc:mysql://127.0.0.1:3306/qualityMultiservices";
        String params = "?useSSL=false&useUnicode=true&characterEnconding=utf-8";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+params, user, password);
            return conn;
        }catch(Exception e){
            e.getMessage();
            throw new RuntimeException(e);
        }
    }
    
    public void close(Connection conn1){
        if(conn == null){
            try{
                conn.close();
            }catch(Exception e){
                e.getMessage();
                System.out.println("Excepción controlada.");
            }
        }
    }
}
