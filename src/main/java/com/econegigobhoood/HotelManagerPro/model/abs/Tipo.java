package com.econegigobhoood.HotelManagerPro.model.abs;

public abstract class Tipo extends AbstractEntity {
    private String nome;
    private String desc;
    private double valor;

    public Tipo (String nome, String desc, double valor) {
        this.nome = nome;
        this.desc = desc;
        this.valor = valor;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
}
