package com.econegigobhoood.HotelManagerPro.model.entity;

import com.econegigobhoood.HotelManagerPro.model.abs.Tipo;

public class TipoQuarto extends Tipo {
    // Construtor sem ID
    public TipoQuarto(String nome, String desc, double valor) {
        super(nome, desc, valor);
    }

    // Construtor com ID, para informação em BD
    public TipoQuarto(int id, String nome, String desc, double valor) {
        super(nome, desc, valor);
        super.setId(id);
    }
}