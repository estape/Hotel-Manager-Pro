package com.econegigobhoood.HotelManagerPro.controller;

import com.econegigobhoood.HotelManagerPro.model.dao.IDAOPessoa;
import com.econegigobhoood.HotelManagerPro.model.abs.Pessoa;

public class ControlPessoa<T extends Pessoa> extends Controller<T> {

    public ControlPessoa(IDAOPessoa<T> dao) {
        super(dao);
    }

    public T buscarCpf(String cpf) {
        return ((IDAOPessoa<T>) super.getDAOEntidade()).buscarCpf(cpf);
    }
}
