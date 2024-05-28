package com.econegigobhoood.HotelManagerPro.model.abs;

public abstract class Pessoa extends AbstractEntity {
    private String nome;
    private String cpf;

    // Construtor sem ID
    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // Construtor com ID, para informação em BD
    public Pessoa(int id, String nome, String cpf) {
        super.setId(id);
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() { 
        return nome;
    }

    public void setNome(String nome) { 
        this.nome = nome;
    }

    public String getCpf() { 
        return cpf;
    }
    
    public void setCpf(String cpf) { 
        this.cpf = cpf;
    }
}
