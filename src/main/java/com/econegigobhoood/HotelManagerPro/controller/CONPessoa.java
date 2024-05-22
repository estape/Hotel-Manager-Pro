package com.econegigobhoood.HotelManagerPro.controller;

import java.util.List;

import com.econegigobhoood.HotelManagerPro.model.dao.IDAO;
import com.econegigobhoood.HotelManagerPro.model.abs.Pessoa;

public class CONPessoa implements IController<Pessoa> {
    private final IDAO<Pessoa> DAOPessoa;
    private final String entidade;

    // Construtor
    public CONPessoa (IDAO<Pessoa> DAOPessoa) {
        this.DAOPessoa = DAOPessoa;
        this.entidade = DAOPessoa.getNomeClasse();
    }

    // Métodos de controle
    @Override
    public String cadastrar(Pessoa pessoa) {
        String operacao = "Cadastro";
        DAOPessoa.cadastrar(pessoa);
        return operacao + " de " + this.entidade + " realizado com sucesso!";
    }

    @Override
    public String atualizar(Pessoa pessoa) {
        String operacao = "Atualização";
        DAOPessoa.atualizar(pessoa);
        return operacao + " de " + this.entidade + " realizado com sucesso!";
    }

    @Override
    public String excluir(int id) {
        String operacao = "Exclusão";
        DAOPessoa.excluir(id);
        return operacao + " de " + this.entidade + " realizado com sucesso!";
    }

    @Override
    public Pessoa buscar(int id) {
        return (Pessoa) DAOPessoa.buscar(id);
    }

    @Override
    public List<Pessoa> listar() {
        return (List<Pessoa>) DAOPessoa.listar();
    }
}
