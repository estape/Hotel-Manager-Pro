package com.econegigobhoood.HotelManagerPro.model;

public class DTOFuncionarios {
    private String Nome;
    private String Rg;
    private int Idade;
    private String Cargo;

    public DTOFuncionarios(){

    }

    public DTOFuncionarios(String Nome,String Rg, int Idade,String Cargo){
      this.Nome = Nome;
      this.Rg = Rg;
      this.Idade = Idade;
      this.Cargo = Cargo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getRg() {
        return Rg;
    }

    public void setRg(String rg) {
        Rg = rg;
    }

    public int getIdade() {
        return Idade;
    }

    public void setIdade(int idade) {
        Idade = idade;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }

    
}
