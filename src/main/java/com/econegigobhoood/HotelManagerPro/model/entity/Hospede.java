package com.econegigobhoood.HotelManagerPro.model.entity;

import com.econegigobhoood.HotelManagerPro.model.abs.Pessoa;
public class Hospede extends Pessoa {
    private String telefone;

    // Construtor sem ID
    public Hospede(String nome, String cpf, String telefone) {
        super.setNome(nome);
        super.setCpf(cpf);
        this.telefone = telefone;
    }

    // Construtor com ID, para informação em BD
    public Hospede(int id, String nome, String cpf, String telefone) {
        super.setId(id);
        super.setNome(nome);
        super.setCpf(cpf);
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
