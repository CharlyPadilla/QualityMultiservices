
package org.utl.dsm.Model;


public class Peticion {
    private int idPeticion;
    private Oficio oficioBuscado;
    private Publicacion publicacion;

    public Peticion() {
        this.publicacion = new Publicacion();
        this.oficioBuscado = new Oficio();
    }

    public Peticion(int idPeticion, int idOficioBuscado, Publicacion publicacion) {
        this.idPeticion = idPeticion;
        this.oficioBuscado = new Oficio();
        this.oficioBuscado.setIdOficio(idOficioBuscado);
        this.publicacion = new Publicacion();
    }
    
    public Peticion(int idPeticion, int idOficioBuscado, int idPublicacion, String nombreUsuario, // Constructor para inicializar una publicación petición
                        String descripcionPublicacion, String fechCreacion, String fechaEdicion, String nombreOficio) {
        this.idPeticion = idPeticion;
        this.oficioBuscado = new Oficio();
        this.oficioBuscado.setIdOficio(idOficioBuscado);
        this.oficioBuscado.setNombreOficio(nombreOficio);
        
        this.publicacion = new Publicacion();
        this.publicacion.setIdPublicacion(idPublicacion);
        this.publicacion.setDescripcion(descripcionPublicacion);
        this.publicacion.setFechaCreacion(fechCreacion);
        this.publicacion.setFechaEdicion(fechaEdicion);
        this.publicacion.getUsuario().setNombreUsuario(nombreUsuario);
    }

    public int getIdPeticion() {
        return idPeticion;
    }

    public void setIdPeticion(int idPeticion) {
        this.idPeticion = idPeticion;
    }

    public Oficio getOficioBuscado() {
        return oficioBuscado;
    }

     public int getIdOficioBuscado() {
        return oficioBuscado.getIdOficio();
    }

    
    public void setOficioBuscado(Oficio oficioBuscado) {
        this.oficioBuscado = oficioBuscado;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    @Override
    public String toString() {
        return "Peticion{" + "idPeticion=" + idPeticion + ", oficioBuscado=" + oficioBuscado + ", publicacion=" + publicacion.toString() + '}';
    }
    
    
}
