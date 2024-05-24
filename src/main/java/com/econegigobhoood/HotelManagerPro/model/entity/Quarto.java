package com.econegigobhoood.HotelManagerPro.model.entity;

import com.econegigobhoood.HotelManagerPro.model.abs.AbstractEntity;

import java.util.List;

public class Quarto extends AbstractEntity {
    private int qtdCamaSolt;
    private int qtdCamaCas;
    private int qtdBanho;
    private String infoAdc;
    private double vlQuarto;
    private TipoQuarto tipo;
    private List<Reserva> reservas;

    // Construtor cadastro
    public Quarto(int qtdCamaSolt, int qtdCamaCas, int qtdBanho, String infoAdc,
                  double vlQuarto, TipoQuarto tipo, List<Reserva> reservas) {
        this.qtdCamaSolt = qtdCamaSolt;
        this.qtdCamaCas = qtdCamaCas;
        this.qtdBanho = qtdBanho;
        this.infoAdc = infoAdc;
        this.vlQuarto = vlQuarto;
        this.tipo = tipo;
        this.reservas = reservas;
    }

    // Construtor consulta
    public Quarto(int id, int qtdCamaSolt, int qtdCamaCas, int qtdBanho,
                  String infoAdc, double vlQuarto, TipoQuarto tipo,
                  List<Reserva> reservas) {
        super.setId(id);
        this.qtdCamaSolt = qtdCamaSolt;
        this.qtdCamaCas = qtdCamaCas;
        this.qtdBanho = qtdBanho;
        this.infoAdc = infoAdc;
        this.vlQuarto = vlQuarto;
        this.tipo = tipo;
        this.reservas = reservas;
    }

    public int getQtdCamaSolt() {
        return qtdCamaSolt;
    }
    public void setQtdCamaSolt(int qtdCamaSolt) {
        this.qtdCamaSolt = qtdCamaSolt;
    }
    public int getQtdCamaCas() {
        return qtdCamaCas;
    }
    public void setQtdCamaCas(int qtdCamaCas) {
        this.qtdCamaCas = qtdCamaCas;
    }
    public int getQtdBanho() {
        return qtdBanho;
    }
    public void setQtdBanho(int qtdBanho) {
        this.qtdBanho = qtdBanho;
    }
    public String getInfoAdc() {
        return infoAdc;
    }
    public void setInfoAdc(String infoAdc) {
        this.infoAdc = infoAdc;
    }
    public double getVlQuarto() {
        return vlQuarto;
    }
    public void setVlQuarto(double vlQuarto) {
        this.vlQuarto = vlQuarto;
    }
    public TipoQuarto getTipo() {
        return tipo;
    }
    public void setTipo(TipoQuarto tipo) {
        this.tipo = tipo;
    }
    public List<Reserva> getReservas() {
        return reservas;
    }
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }


}
