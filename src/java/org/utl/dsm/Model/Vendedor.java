
package org.utl.dsm.Model;

public class Vendedor {
    private int idVendedor;
    private Oficio oficio;
    private int aniosExperiencia;
    private Usuario usuario;

    public Vendedor(int idVendedor, int idOficio, int aniosExperiencia, Usuario usuario) {
        this.idVendedor = idVendedor;
        this.oficio = new Oficio();
        this.oficio.setIdOficio(idOficio);
        this.aniosExperiencia = aniosExperiencia;
        this.usuario = usuario;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Oficio getOficio() {
        return oficio;
    }

    public void setOficio(int idOficio) {
        this.oficio = new Oficio();
        this.oficio.setIdOficio(idOficio);
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
