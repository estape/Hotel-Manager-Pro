package com.econegigobhoood.HotelManagerPro.model.entity;

import com.econegigobhoood.HotelManagerPro.model.abs.AbstractEntity;

import java.time.LocalDate;


public class Reserva extends AbstractEntity {
    private double vlReserva;
    private LocalDate dtEntrada;
    private LocalDate dtSaida;
    private Status status;
    private Pedido pedido;
    private Quarto quarto;

    // Construtor para cadastro
    public Reserva(LocalDate dtEntrada, LocalDate dtSaida, Pedido pedido,
                   Quarto quarto) {
        this.dtEntrada = dtEntrada;
        this.dtSaida = dtSaida;
        this.pedido = pedido;
        this.quarto = quarto;
        this.status.setId(1);
    }
    
    // Construtor para consulta
    public Reserva(int id, double vlReserva, LocalDate dtEntrada,
                   LocalDate dtSaida, Status status, Pedido pedido,
                   Quarto quarto) {
        super.setId(id);
        this.vlReserva = vlReserva;
        this.dtEntrada = dtEntrada;
        this.dtSaida = dtSaida;
        this.status = status;
        this.pedido = pedido;
        this.quarto = quarto;
    }

    // Get n set
    public LocalDate getDtEntrada() {
        return dtEntrada;
    }

    public void setDtEntrada(LocalDate dtEntrada) {
        this.dtEntrada = dtEntrada;
    }

    public LocalDate getDtSaida() {
        return dtSaida;
    }

    public void setDtSaida(LocalDate dtSaida) {
        this.dtSaida = dtSaida;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public double getVlReserva() {
        return vlReserva;
    }

    public void setVlReserva(double vlReserva) {
        this.vlReserva = vlReserva;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
