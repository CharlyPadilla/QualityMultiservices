
package org.utl.dsm.Model;

import java.util.Date;

/**
 *
 * @author Milk
 */

public class Mensaje {

    private int idMensaje;
    private int idChat;
    private String mensajeTexto;
    private Date fecha;

    public Mensaje() {
    }

    public Mensaje(int idMensaje, int idChat, String mensajeTexto, Date fecha) {
        this.idMensaje = idMensaje;
        this.idChat = idChat;
        this.mensajeTexto = mensajeTexto;
        this.fecha = fecha;
    }

    public String getMensajeTexto() {
        return mensajeTexto;
    }

    public void setMensajeTexto(String mensajeTexto) {
        this.mensajeTexto = mensajeTexto;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}