package com.econegigobhoood.HotelManagerPro.controller;

import com.econegigobhoood.HotelManagerPro.model.dao.DAOQuarto;
import com.econegigobhoood.HotelManagerPro.model.dao.IDAO;
import com.econegigobhoood.HotelManagerPro.model.entity.Quarto;

import java.time.LocalDate;
import java.util.List;

import com.econegigobhoood.HotelManagerPro.model.abs.AbstractEntity;

public class ControlQuarto<T extends AbstractEntity> extends Controller<T> {

    public ControlQuarto(IDAO<T> dao) {
        super(dao);
    }

    public Quarto buscarNum(int numQuarto) {
        return ((DAOQuarto) super.getDAOEntidade()).buscarNum(numQuarto);
    }

    public List<Quarto> listarQuartoDisp(LocalDate dtEntrada, LocalDate dtSaida) {
        return ((DAOQuarto) super.getDAOEntidade()).listarDisponivel(dtEntrada, dtSaida);
    }
}
