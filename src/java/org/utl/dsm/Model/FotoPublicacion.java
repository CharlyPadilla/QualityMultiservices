
package org.utl.dsm.Model;

public class FotoPublicacion {
    private int idFotoPublicacion;
    private String cadenaFoto;
    private Publicacion publicacion;
    
    public FotoPublicacion(int idFotoPublicacion, String cadenaFoto, Publicacion publicacion) {
        this.idFotoPublicacion = idFotoPublicacion;
        this.cadenaFoto = cadenaFoto;
        this.publicacion = publicacion;
    }

    public FotoPublicacion(){}
    
    public int getIdFotoPublicacion() {
        return idFotoPublicacion;
    }

    public void setIdFotoPublicacion(int idFotoPublicacion) {
        this.idFotoPublicacion = idFotoPublicacion;
    }

    public String getCadenaFoto() {
        return cadenaFoto;
    }

    public void setCadenaFoto(String cadenaFoto) {
        this.cadenaFoto = cadenaFoto;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
    
    
}
