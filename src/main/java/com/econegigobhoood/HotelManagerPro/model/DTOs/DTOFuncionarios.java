package com.econegigobhoood.HotelManagerPro.model.DTOs;

import com.econegigobhoood.HotelManagerPro.model.DAOs.DAOFuncionarios;

public class DTOFuncionarios extends DAOFuncionarios {
    private int idFuncionario;
    private String nome;
    private String cargo; 
    private String login;
    private String senha;
    

    public DTOFuncionarios(){

    }

    public DTOFuncionarios(int idFuncionario,String nome,String cargo,String login,String senha){
      this.idFuncionario = idFuncionario;
      this.nome = nome;
      this.cargo = cargo;
      this.login = login;
      this.senha = senha;

    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public int setIdFuncionario(int idFuncionario) {
        return this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public String setNome(String nome) {
        return this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public String setCargo(String cargo) {
        return this.cargo = cargo;
    }

    public String getLogin() {
        return login;
    }

    public String setLogin(String login) {
        return this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public String setSenha(String senha) {
        return this.senha = senha;
    }

     @Override
    public void incluirPessoa(){
        this.insertarPessoa(this.getIdFuncionario(),this.getNome(),this.getCargo(),this.getLogin(),this.getSenha());
    }


    
}
