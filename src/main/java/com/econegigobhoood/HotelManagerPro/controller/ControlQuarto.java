package com.econegigobhoood.HotelManagerPro.controller;

import com.econegigobhoood.HotelManagerPro.model.dao.DAOQuarto;
import com.econegigobhoood.HotelManagerPro.model.dao.IDAO;
import com.econegigobhoood.HotelManagerPro.model.entity.Quarto;
import com.econegigobhoood.HotelManagerPro.model.abs.AbstractEntity;

public class ControlQuarto<T extends AbstractEntity> extends Controller<T> {

    public ControlQuarto(IDAO<T> dao) {
        super(dao);
    }

    public Quarto buscarCpf(int numQuarto) {
        return ((DAOQuarto) super.getDAOEntidade()).buscarNum(numQuarto);
    }
}
