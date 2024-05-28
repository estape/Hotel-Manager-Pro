package com.econegigobhoood.HotelManagerPro.model.dao;

import com.econegigobhoood.HotelManagerPro.model.abs.Pessoa;

public interface IDAOPessoa<T extends Pessoa> extends IDAO<T> {
    T buscarCpf(String cpf);
}