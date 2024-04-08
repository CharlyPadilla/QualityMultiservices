
package org.utl.dsm.Model;

public class Usuario {
   private int idUsuario; 
   private String nombreUsuario;
   private String imagenPerfil;
   private String ciudad;
   private String correo;
   private String contrasenia;

    public Usuario(int idUsuario, String nombreUsuario, String imagenPerfil, String ciudad, String correo, String contrasenia) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.imagenPerfil = imagenPerfil;
        this.ciudad = ciudad;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }
    
    public Usuario(){}

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
