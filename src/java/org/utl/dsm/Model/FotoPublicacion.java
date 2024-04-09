
package org.utl.dsm.Model;

public class FotoPublicacion {
    private int idFotoPublicacion;
    private String cadenaFoto;
    private int idPublicacion;
    
    public FotoPublicacion(int idFotoPublicacion, String cadenaFoto, int idPublicacion) {
        this.idFotoPublicacion = idFotoPublicacion;
        this.cadenaFoto = cadenaFoto;
        this.idPublicacion = idPublicacion;
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

    public int getPublicacion() {
        return idPublicacion;
    }

    public void setPublicacion(int publicacion) {
        this.idPublicacion = publicacion;
    }
    
    
}
