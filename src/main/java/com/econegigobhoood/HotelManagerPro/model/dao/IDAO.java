package com.econegigobhoood.HotelManagerPro.model.dao;

import java.util.List;
import com.econegigobhoood.HotelManagerPro.model.abs.AbstractEntity;

public interface IDAO<T extends AbstractEntity> {
    void cadastrar(T entidade);
    void atualizar(T entidade);
    void excluir(int id);
    T buscar(int id);
    List<T> listar();
    String getNomeClasse();
}
