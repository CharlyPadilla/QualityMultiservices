
package org.utl.dsm.Model;

import java.util.ArrayList;

public class Publicacion {
    private int idPublicacion;
    private String titulo;
    private Usuario usuario;
    private String descripcion;
    private String fechaCreacion;
    private String fechaEdicion;
    private ArrayList<FotoPublicacion> listaFotos; 

    public Publicacion(int idPublicacion, String titulo, Usuario usuario, String descripcion, String fechaCreacion, String fechaEdicion, ArrayList<FotoPublicacion> listaFotos) {
        this.idPublicacion = idPublicacion;
        this.titulo = titulo;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaEdicion = fechaEdicion;
        this.listaFotos = listaFotos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    public Publicacion(int idPublicacion, int idUsuario, String descripcion, String fechaCreacion, String fechaEdicion) {
        this.idPublicacion = idPublicacion;
        this.usuario = new Usuario();
        this.usuario.setIdUsuario(idUsuario);
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaEdicion = fechaEdicion;
    }
    
     public Publicacion(int idUsuario, String descripcion, String fechaCreacion, String fechaEdicion) {
        this.usuario = new Usuario();
        this.usuario.setIdUsuario(idUsuario);
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaEdicion = fechaEdicion;
    }

    public Publicacion() {}

     public ArrayList<FotoPublicacion> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(ArrayList<FotoPublicacion> listaFotos) {
        this.listaFotos = listaFotos;
    }
    
    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(String fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    @Override
    public String toString() {
        return "Publicacion{" + "idPublicacion=" + idPublicacion + ", usuario=" + usuario + ", descripcion=" + descripcion + ", fechaCreacion=" + fechaCreacion + ", fechaEdicion=" + fechaEdicion + ", listaFotos=" + listaFotos.toString() + '}';
    }
    
    
    
    
}
