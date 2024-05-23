package com.econegigobhoood.HotelManagerPro.controller;

import java.util.List;

import com.econegigobhoood.HotelManagerPro.model.dao.IDAO;
import com.econegigobhoood.HotelManagerPro.model.abs.Pessoa;

/*
 * Para criar um Controller que implemente uma interface flexível,
 * parametrizamos uma classe genérica para CONPessoa que extenda a
 * Superclasse, chamando-a de T, e em seguida passando-a como classe
 * genérica para a interface IController tornando possível a
 * flexibilização do controller funcionando para cada subclasse da
 * superclasse que está sendo extendida na classe genérica de CONPessoa. 
 */
public class CONPessoa<T extends Pessoa> implements IController<T> {
    private final IDAO<T> DAOPessoa;
    private final String entidade;

    // Construtor
    public CONPessoa (IDAO<T> DAOPessoa) {
        this.DAOPessoa = DAOPessoa;
        this.entidade = DAOPessoa.getNomeClasse();
    }

    // Métodos de controle
    @Override
    public String cadastrar(T pessoa) {
        String operacao = "Cadastro";
        DAOPessoa.cadastrar(pessoa);
        return operacao + " de " + this.entidade + " realizado com sucesso!";
    }

    @Override
    public String atualizar(T pessoa) {
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
    public T buscar(int id) {
        return (T) DAOPessoa.buscar(id);
    }

    @Override
    public List<T> listar() {
        return (List<T>) DAOPessoa.listar();
    }
}
