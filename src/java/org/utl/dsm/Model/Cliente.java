
package org.utl.dsm.Model;

public class Cliente {
    private int idCliente;
    private Usuario usuario;

    public Cliente(int idCliente, Usuario usuario) {
        this.idCliente = idCliente;
        this.usuario = usuario;
    }

    public Cliente() {
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
