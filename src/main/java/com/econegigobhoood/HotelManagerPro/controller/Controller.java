package com.econegigobhoood.HotelManagerPro.controller;

import java.util.List;

import com.econegigobhoood.HotelManagerPro.model.dao.IDAO;
import com.econegigobhoood.HotelManagerPro.model.abs.AbstractEntity;

/*
 * Para criar um Controller que implemente uma interface flexível,
 * parametrizamos uma classe genérica para Controller que extenda uma
 * Superclasse, chamando-a de T, e em seguida passando-a como classe
 * genérica para a interface IController tornando possível a
 * flexibilização do controller funcionando para cada subclasse da
 * superclasse que está sendo extendida na classe genérica de Controller.
 * 
 * Caso queira granular, <T extends AbstractEntity> pode assumir qualquer
 * Superclasse, como <T extends Pessoa>. Assim, podemos criar regras
 * específicas para as subclasses de Pessoa.
 */
public class Controller<T extends AbstractEntity> implements IController<T> {
    private final IDAO<T> DAOEntidade;
    private final String entidade;

    // Construtor
    public Controller (IDAO<T> DAOEntidade) {
        this.DAOEntidade = DAOEntidade;
        this.entidade = DAOEntidade.getNomeClasse();
    }

    // Métodos de controle
    @Override
    public int cadastrar(T entidade) {
        String operacao = "Cadastro";
        int generatedId = DAOEntidade.cadastrar(entidade);
        System.out.println(operacao + " de " + this.entidade +
                           " realizado com sucesso!");
        return generatedId;
    }

    @Override
    public String atualizar(T entidade) {
        String operacao = "Atualização";
        DAOEntidade.atualizar(entidade);
        return operacao + " de " + this.entidade + " realizado com sucesso!";
    }

    @Override
    public String excluir(int id) {
        String operacao = "Exclusão";
        DAOEntidade.excluir(id);
        return operacao + " de " + this.entidade + " realizado com sucesso!";
    }

    @Override
    public T buscar(int id) {
        return (T) DAOEntidade.buscar(id);
    }

    @Override
    public List<T> listar() {
        return (List<T>) DAOEntidade.listar();
    }

    // Getters (não tem setter por serem final)
    public IDAO<T> getDAOEntidade() {
        return DAOEntidade;
    }

    public String getEntidade() {
        return entidade;
    }
}
