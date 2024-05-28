package com.econegigobhoood.HotelManagerPro.controller;

import com.econegigobhoood.HotelManagerPro.model.dao.DAOPedido;
import com.econegigobhoood.HotelManagerPro.model.dao.IDAO;
import com.econegigobhoood.HotelManagerPro.model.entity.Pedido;
import com.econegigobhoood.HotelManagerPro.model.entity.Reserva;

import java.util.List;

import com.econegigobhoood.HotelManagerPro.model.abs.AbstractEntity;

public class ControlPedido<T extends AbstractEntity> extends Controller<T> {

    public ControlPedido(IDAO<T> dao) {
        super(dao);
    }

    public Pedido buscarInicial(int id) {
        return ((DAOPedido) super.getDAOEntidade()).buscarInicial(id);
    }

    // MANO, ISSO DEU UM TRABALHÃO
    public Pedido buscarCompleto(int id) {
        Pedido pedido = ((DAOPedido) super.getDAOEntidade()).buscarInicial(id);
        List<Reserva> reservas = ((DAOPedido) super.getDAOEntidade()).getListReserva(id);
        pedido.setReservas(reservas);
        return pedido;
    }
}
