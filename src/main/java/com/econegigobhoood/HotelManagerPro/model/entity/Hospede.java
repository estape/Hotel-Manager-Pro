package com.econegigobhoood.HotelManagerPro.model.entity;



import com.econegigobhoood.HotelManagerPro.model.dao.DAOHospede;


public class Hospede extends DAOHospede {
    private int idHospede;
    private String nome;
    private String cpf;
    private String telefone;
    
  

    publicHospedee(){

    }

    publicHospedee(int idHospede,String nome, String cpf,String telefone){
      this.idHospede = idHospede;
      this.nome = nome;
      this.cpf = cpf;
      this.telefone = telefone;
    }

    public int getIdHospede() {
        return idHospede;
    }

    public int setIdHospede(int idHospede) {
        return this.idHospede = idHospede;
    }

    public String getNome() {
        return nome;
    }

    public String setNome(String nome) {
        return this.nome = nome;
    }
     

    public String getCpf() {
        return cpf;
    }

    public String setCpf(String cpf) {
        return this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String setTelefone(String telefone) {
        return this.telefone = telefone;
    }

   @Override
    public void incluirPessoa(){
        this.insertarPessoa(this.getIdHospede(),this.getNome(),this.getCpf(),this.getTelefone());
    }

    
}
