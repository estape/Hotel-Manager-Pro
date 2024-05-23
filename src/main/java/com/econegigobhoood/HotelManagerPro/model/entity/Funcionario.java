package com.econegigobhoood.HotelManagerPro.model.entity;

import com.econegigobhoood.HotelManagerPro.model.abs.Pessoa;

public class Funcionario extends Pessoa {
    private String cargo; 
    
    // Construtor sem ID
    public Funcionario(String nome, String cpf, String cargo) {
        super(nome, cpf);
        this.cargo = cargo;
    }

    // Construtor com ID, para informação em BD
    public Funcionario(int id, String nome, String cpf, String cargo) {
        super(id, nome, cpf);
        this.cargo = cargo;
    }
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
