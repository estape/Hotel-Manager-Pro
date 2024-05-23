package com.econegigobhoood.HotelManagerPro.model.DTOs;

import java.sql.Date;

public class DTOPedido {
    private int idPedido;
    private Date dtEntrada; 
    private Date dtSaida;
    private int idReserva;
    private int idQuarto;


    public DTOPedido(int idPedido,Date dtEntrada,Date dtSaida,int idReserva,int idQuarto){
       this.idPedido = idPedido;
       this.dtEntrada = dtEntrada;
       this.dtSaida = dtSaida;
       this.idReserva = idReserva;
       this.idQuarto = idQuarto;
    }


    public int getIdPedido() {
        return idPedido;
    }


    public int setIdPedido(int idPedido) {
        return this.idPedido = idPedido;
    }


    public Date getDtEntrada() {
        return dtEntrada;
    }


    public Date setDtEntrada(Date dtEntrada) {
        return this.dtEntrada = dtEntrada;
    }


    public Date getDtSaida() {
        return dtSaida;
    }


    public Date setDtSaida(Date dtSaida) {
        return this.dtSaida = dtSaida;
    }


    public int getIdReserva() {
        return idReserva;
    }


    public int setIdReserva(int idReserva) {
        return this.idReserva = idReserva;
    }


    public int getIdQuarto() {
        return idQuarto;
    }


    public int setIdQuarto(int idQuarto) {
        return this.idQuarto = idQuarto;
    }    


    
}
