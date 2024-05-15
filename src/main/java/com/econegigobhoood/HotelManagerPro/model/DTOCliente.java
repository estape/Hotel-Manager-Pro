package com.econegigobhoood.HotelManagerPro.model;

public class DTOCliente extends DAOCliente {
    
    private String Nome;
    private String Rg;
    private int Idade;
    private int IdCliente;

    public DTOCliente(){

    }

    public DTOCliente(int IdCliente,String Nome,String Rg, int Idade){
      this.IdCliente = IdCliente;
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


    public int getIdCliente() {
        return IdCliente;
    }

    public int setIdCliente(int IdCliente) {
        return this.IdCliente = IdCliente;
    }


    public void incluircliente(){
        this.insertarCliente(this.getNome(),this.getRg(),this.getIdade(),this.getIdCliente());
    }
}
