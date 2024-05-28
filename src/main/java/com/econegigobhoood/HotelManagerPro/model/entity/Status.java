package com.econegigobhoood.HotelManagerPro.model.entity;

import com.econegigobhoood.HotelManagerPro.model.abs.AbstractEntity;

public class Status extends AbstractEntity {
    private String nome;

    // Construtor sem ID
    public Status(String nome) {
        this.nome = nome;
    }

    // Construtor com ID
    public Status(int id, String nome) {
        super.setId(id);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
