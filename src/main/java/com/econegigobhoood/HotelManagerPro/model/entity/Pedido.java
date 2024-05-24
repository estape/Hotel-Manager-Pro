package com.econegigobhoood.HotelManagerPro.model.entity;

import java.time.LocalDate;
import java.util.List;

import com.econegigobhoood.HotelManagerPro.model.abs.AbstractEntity;

public class Pedido extends AbstractEntity {
    private LocalDate dtPedido;
    private double vlTotalPedido;
    private Hospede hospede;
    private Funcionario funcionario;
    private List<Reserva> reservas;
    
    // Construtor cadastro
    public Pedido (LocalDate dtPedido, Hospede hospede,
            Funcionario funcionario) {
        this.dtPedido = dtPedido;
        this.hospede = hospede;
        this.funcionario = funcionario;
    }

    // Construtor consulta
    public Pedido (int id, LocalDate dtPedido, double vlTotalPedido,
            Hospede hospede, Funcionario funcionario, List<Reserva> reservas) {
        super.setId(id);
        this.dtPedido = dtPedido;
        this.vlTotalPedido = vlTotalPedido;
        this.hospede = hospede;
        this.funcionario = funcionario;
        this.reservas = reservas;
    }
    
    // Get n Set
    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(LocalDate dtPedido) {
        this.dtPedido = dtPedido;
    }

    public double getVlTotalPedido() {
        return vlTotalPedido;
    }

    public void setVlTotalPedido(double vlTotalPedido) {
        this.vlTotalPedido = vlTotalPedido;
    }
}
