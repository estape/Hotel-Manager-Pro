package com.econegigobhoood.HotelManagerPro.controller;

import com.econegigobhoood.HotelManagerPro.model.abs.AbstractEntity;

import java.util.List;

public interface IController<T extends AbstractEntity> {
    int cadastrar(T entidade);
    String atualizar(T entidade);
    String excluir(int id);
    T buscar(int id);
    List<T> listar();
}
