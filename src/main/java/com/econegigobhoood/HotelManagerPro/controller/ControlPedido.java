package com.econegigobhoood.HotelManagerPro.controller;

import com.econegigobhoood.HotelManagerPro.model.dao.DAOPedido;
import com.econegigobhoood.HotelManagerPro.model.dao.IDAO;
import com.econegigobhoood.HotelManagerPro.model.abs.AbstractEntity;

public class ControlPedido<T extends AbstractEntity> extends Controller<T> {

    public ControlPedido(IDAO<T> dao) {
        super(dao);
    }

    public void buscarCpf() {
        ((DAOPedido) super.getDAOEntidade()).buscaEspecifica();
    }
}
