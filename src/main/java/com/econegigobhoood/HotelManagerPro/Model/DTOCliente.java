package com.econegigobhoood.HotelManagerPro.Model;

public class DTOCliente {
    
    private String Nome;
    private String Rg;
    private int Idade;

    public DTOCliente(){

    }

    public DTOCliente(String Nome,String Rg, int Idade){
      this.Nome = Nome;
      this.Rg = Rg;
      this.Idade = Idade;
    }

    public String getNome() {
        return Nome;
    }

    public String setNome(String nome) {
        return this.Nome = nome;
    }

    public String getRg() {
        return Rg;
    }

    public String setRg(String rg) {
        return this.Rg = rg;
    }

    public int getIdade() {
        return Idade;
    }

    public int setIdade(int idade) {
        return this.Idade = idade;
    }
}
