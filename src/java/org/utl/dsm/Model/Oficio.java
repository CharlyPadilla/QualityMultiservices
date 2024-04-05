
package org.utl.dsm.Model;

public class Oficio {
    private int idOficio;
    private String nombreOficio;

    public Oficio(int idOficio, String nombreOficio) {
        this.idOficio = idOficio;
        this.nombreOficio = nombreOficio;
    }

    public Oficio() {
    }

    public int getIdOficio() {
        return idOficio;
    }

    public void setIdOficio(int idOficio) {
        this.idOficio = idOficio;
    }

    public String getNombreOficio() {
        return nombreOficio;
    }

    public void setNombreOficio(String nombreOficio) {
        this.nombreOficio = nombreOficio;
    }
    
    
}
