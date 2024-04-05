
package org.utl.dsm.Model;

public class Anuncio {
    private int idAnuncio;
    private Oficio oficioOfrecido;
    private Publicacion publicacion;

    public Anuncio() {
        this.publicacion = new Publicacion();
        this.oficioOfrecido = new Oficio();
    }

    public Anuncio(int idAnuncio, Oficio oficioOfrecido, Publicacion publicacion) {
        this.idAnuncio = idAnuncio;
        this.oficioOfrecido = new Oficio();
        this.publicacion = new Publicacion();
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public Oficio getOficioOfrecido() {
        return oficioOfrecido;
    }

    public void setOficioOfrecido(Oficio oficioOfrecido) {
        this.oficioOfrecido = oficioOfrecido;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    
}
