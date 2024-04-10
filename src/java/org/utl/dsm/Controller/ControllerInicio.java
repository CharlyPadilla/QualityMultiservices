package org.utl.dsm.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.utl.dsm.ConnectionDB.ConnectioDB;
import org.utl.dsm.Model.Cliente;
import org.utl.dsm.Model.FotoPublicacion;
import org.utl.dsm.Model.Oficio;
import org.utl.dsm.Model.Publicacion;
import org.utl.dsm.Model.Usuario;
import org.utl.dsm.Model.Vendedor;

public class ControllerInicio {
    public List<Vendedor> searchVendedor(String palabra) throws SQLException {
        //La consulta SQL a ejecutar: 
        String sql = "CALL sp_searchVendedor(?)";
        //Con este objeto nos vamos a conectar a la Base de Datos: 
        ConnectioDB connMySQL = new ConnectioDB();
        //Abrimos la conexión con la Base de Datos: 
        Connection conn = connMySQL.open();
        //Con este objeto ejecutaremos la consulta: 
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //Asignamos el/los parametros necesarios
        pstmt.setString(1, palabra);
        //Aquí guardaremos los resultados de la consulta: 
        ResultSet rs = pstmt.executeQuery();
        List<Vendedor> vendedores = new ArrayList<>();
        while (rs.next()) {
            vendedores.add(fillVendedor(rs));
        }
        rs.close();
        pstmt.close();
        connMySQL.close(conn);
        return vendedores;
    }
    
    public List<Cliente> searchCliente(String palabra) throws SQLException {
        //La consulta SQL a ejecutar: 
        String sql = "CALL sp_searchCliente(?)";
        //Con este objeto nos vamos a conectar a la Base de Datos: 
        ConnectioDB connMySQL = new ConnectioDB();
        //Abrimos la conexión con la Base de Datos: 
        Connection conn = connMySQL.open();
        //Con este objeto ejecutaremos la consulta: 
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //Asignamos el/los parametros necesarios
        pstmt.setString(1, palabra);
        //Aquí guardaremos los resultados de la consulta: 
        ResultSet rs = pstmt.executeQuery();
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            clientes.add(fillClientes(rs));
        }
        rs.close();
        pstmt.close();
        connMySQL.close(conn);
        return clientes;
    }
    
    public List<Vendedor> getAllVendedor() throws SQLException {
        //La consulta SQL a ejecutar: 
        String sql = "SELECT * FROM view_vendedor";
        //Con este objeto nos vamos a conectar a la Base de Datos: 
        ConnectioDB connMySQL = new ConnectioDB();
        //Abrimos la conexión con la Base de Datos: 
        Connection conn = connMySQL.open();
        //Con este objeto ejecutaremos la consulta: 
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //Aquí guardaremos los resultados de la consulta: 
        ResultSet rs = pstmt.executeQuery();
        List<Vendedor> vendedores = new ArrayList<>();
        while (rs.next()) {
            vendedores.add(fillVendedor(rs));
        }
        rs.close();
        pstmt.close();
        connMySQL.close(conn);
        return vendedores;
    }
    
    public Vendedor fillVendedor(ResultSet rs) throws SQLException {
        Vendedor v = new Vendedor();
        Usuario u = new Usuario();
        Oficio o = new Oficio();
        u.setIdUsuario(rs.getInt("idUsuario"));
        u.setNombreUsuario(rs.getString("nombreUsuario"));
        u.setImagenPerfil(rs.getString("imagenPerfil"));
        u.setCiudad(rs.getString("ciudad"));
        u.setCorreo(rs.getString("correo"));
        
        o.setIdOficio(rs.getInt("idOficio"));
        o.setNombreOficio(rs.getString("nombreOficio"));
        
        v.setIdVendedor(rs.getInt("idVendedor"));
        v.setOficio(o);
        v.setAniosExperiencia(rs.getInt("aniosExperiencia"));
        v.setUsuario(u);
        return v;
    }
    
    public List<Cliente> getAllCliente() throws SQLException {
        //La consulta SQL a ejecutar: 
        String sql = "SELECT * FROM view_cliente";
        //Con este objeto nos vamos a conectar a la Base de Datos: 
        ConnectioDB connMySQL = new ConnectioDB();
        //Abrimos la conexión con la Base de Datos: 
        Connection conn = connMySQL.open();
        //Con este objeto ejecutaremos la consulta: 
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //Aquí guardaremos los resultados de la consulta: 
        ResultSet rs = pstmt.executeQuery();
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            clientes.add(fillClientes(rs));
        }
        rs.close();
        pstmt.close();
        connMySQL.close(conn);
        return clientes;
    }
    
    public Cliente fillClientes(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("idUsuario"));
        u.setNombreUsuario(rs.getString("nombreUsuario"));
        u.setImagenPerfil(rs.getString("imagenPerfil"));
        u.setCiudad(rs.getString("ciudad"));
        u.setCorreo(rs.getString("correo"));
        u.setNumeroCelular(rs.getString("numeroCelular"));
        
        c.setIdCliente(rs.getInt("idCliente"));
        c.setUsuario(u);
        return c;
    }
    
    public List<Publicacion> getAllPublicacion() throws SQLException {
        //La consulta SQL a ejecutar: 
        String sql = "SELECT * FROM view_publicacion";
        //Con este objeto nos vamos a conectar a la Base de Datos: 
        ConnectioDB connMySQL = new ConnectioDB();
        //Abrimos la conexión con la Base de Datos: 
        Connection conn = connMySQL.open();
        //Con este objeto ejecutaremos la consulta: 
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //Aquí guardaremos los resultados de la consulta: 
        ResultSet rs = pstmt.executeQuery();
        List<Publicacion> publicaciones = new ArrayList<>();
        while (rs.next()) {
            publicaciones.add(fillPublicaciones(rs));
        }
        rs.close();
        pstmt.close();
        connMySQL.close(conn);
        return publicaciones;
    }
    
    public Publicacion fillPublicaciones(ResultSet rs) throws SQLException {
        Publicacion p = new Publicacion();
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("idUsuario"));
        u.setNombreUsuario(rs.getString("nombreUsuario"));
        u.setImagenPerfil(rs.getString("imagenPerfil"));
        u.setCiudad(rs.getString("ciudad"));
        u.setCorreo(rs.getString("correo"));
        u.setNumeroCelular(rs.getString("numeroCelular"));
        
        p.setIdPublicacion(rs.getInt("idPublicacion"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setTitulo(rs.getString("titulo"));
        p.setFechaCreacion(rs.getString("fechaCreacion"));
        p.setFechaEdicion(rs.getString("fechaEdicion"));
        p.setUsuario(u);
        return p;
    }
    
    public List<FotoPublicacion> getAllFotosPublicacion(int numero) throws SQLException {
        //La consulta SQL a ejecutar: 
        String sql = "SELECT * FROM fotoPublicacion WHERE idPublicacion = ?";
        //Con este objeto nos vamos a conectar a la Base de Datos: 
        ConnectioDB connMySQL = new ConnectioDB();
        //Abrimos la conexión con la Base de Datos: 
        Connection conn = connMySQL.open();
        //Con este objeto ejecutaremos la consulta: 
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //Asignamos el/los parametros necesarios
        pstmt.setInt(1, numero);
        //Aquí guardaremos los resultados de la consulta: 
        ResultSet rs = pstmt.executeQuery();
        List<FotoPublicacion> fotos = new ArrayList<>();
        while (rs.next()) {
            fotos.add(fillFotosPublicaciones(rs));
        }
        rs.close();
        pstmt.close();
        connMySQL.close(conn);
        return fotos;
    }
    
    public FotoPublicacion fillFotosPublicaciones(ResultSet rs) throws SQLException {
        FotoPublicacion fp = new FotoPublicacion();
        
        fp.setCadenaFoto(rs.getString("cadenaFoto"));
        fp.setIdFotoPublicacion(rs.getInt("idFotoPublicacion"));
        fp.setPublicacion(rs.getInt("idPublicacion"));
        return fp;
    }
}