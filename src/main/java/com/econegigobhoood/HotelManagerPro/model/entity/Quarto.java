package com.econegigobhoood.HotelManagerPro.model.entity;

import com.econegigobhoood.HotelManagerPro.model.abs.AbstractEntity;

public class Quarto extends AbstractEntity {
    private int numQuarto;
    private int qtdCamaSolt;
    private int qtdCamaCas;
    private int qtdBanho;
    private String infoAdc;
    private TipoQuarto tipo;
    private double vlQuarto;

    // Construtor cadastro
    public Quarto(int numQuarto, int qtdCamaSolt, int qtdCamaCas,
                  int qtdBanho, String infoAdc, TipoQuarto tipo) {
        this.numQuarto = numQuarto;
        this.qtdCamaSolt = qtdCamaSolt;
        this.qtdCamaCas = qtdCamaCas;
        this.qtdBanho = qtdBanho;
        this.infoAdc = infoAdc;
        this.tipo = tipo;
    }

    // Construtor consulta
    public Quarto(int id, int numQuarto, int qtdCamaSolt, int qtdCamaCas,
                  int qtdBanho, String infoAdc, double vlQuarto,
                  TipoQuarto tipo) {
        super.setId(id);
        this.numQuarto = numQuarto;
        this.qtdCamaSolt = qtdCamaSolt;
        this.qtdCamaCas = qtdCamaCas;
        this.qtdBanho = qtdBanho;
        this.infoAdc = infoAdc;
        this.vlQuarto = vlQuarto;
        this.tipo = tipo;
    }

    // Getter n setter
    public int getNumQuarto() {
        return numQuarto;
    }
    public void setNumQuarto(int numQuarto) {
        this.numQuarto = numQuarto;
    }
    public double getVlQuarto() {
        return vlQuarto;
    }
    public void setVlQuarto(double vlQuarto) {
        this.vlQuarto = vlQuarto;
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
    public TipoQuarto getTipo() {
        return tipo;
    }
    public void setTipo(TipoQuarto tipo) {
        this.tipo = tipo;
    }
}
