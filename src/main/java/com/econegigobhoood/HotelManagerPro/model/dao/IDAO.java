package com.econegigobhoood.HotelManagerPro.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.econegigobhoood.HotelManagerPro.model.abs.AbstractEntity;

public interface IDAO<T extends AbstractEntity> {
    String getNomeClasse();
    PreparedStatement dbConnect(String sql) throws SQLException;
    void cadastrar(T entidade);
    void atualizar(T entidade);
    void excluir(int id);
    T buscar(int id);
    List<T> listar();
}
