package com.econegigobhoood.HotelManagerPro.model.abs;

public abstract class Pessoa extends AbstractEntity {
    private String nome;
    private String cpf;

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
