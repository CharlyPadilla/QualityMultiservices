
package org.utl.dsm.Model;

public class Cliente {
    private int idCliente;
    private Usuario usuario;

    public Cliente(int idCliente, Usuario usuario) {
        this.idCliente = idCliente;
        this.usuario = usuario;
    }

    public Cliente() {
        this.usuario = new Usuario();
    }

    public Cliente(int idUsuario, String nombreUsuario, String imagenPerfil, String ciudad, String correo) {
        this.usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setImagenPerfil(imagenPerfil);
        usuario.setCiudad(ciudad);
        usuario.setCorreo(correo);
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
