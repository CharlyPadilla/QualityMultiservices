/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.dsm.Model;

import java.util.Date;

/**
 * @author Milk
 */
public class Chat {

    private int idChat;
    private int idCliente;
    private int idVendedor;
    private Date fechaInicioConversacion;

    public Chat() {
    }

    public Chat(int idChat, int idCliente, int idVendedor, Date fechaInicioConversacion) {
        this.idChat = idChat;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.fechaInicioConversacion = fechaInicioConversacion;
    }

    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Date getFechaInicioConversacion() {
        return fechaInicioConversacion;
    }

    public void setFechaInicioConversacion(Date fechaInicioConversacion) {
        this.fechaInicioConversacion = fechaInicioConversacion;
    }
}