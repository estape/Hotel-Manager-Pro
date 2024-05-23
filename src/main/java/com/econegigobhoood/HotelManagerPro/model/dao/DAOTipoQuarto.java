package com.econegigobhoood.HotelManagerPro.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.model.entity.TipoQuarto;

public class DAOTipoQuarto implements IDAO<TipoQuarto> {

    @Override
    public String getNomeClasse() {
        return TipoQuarto.class.getSimpleName();
    }

    @Override
    public PreparedStatement dbConnect(String sql) throws SQLException {
        Connection connection = DBConfig.getCon();
        return connection.prepareStatement(sql);
    }

    @Override
    public void cadastrar(TipoQuarto entidade) {
        String sql = "INSERT INTO tipo_quarto (nome, descricao, valor)"
                     + " VALUES(?, ?, ?)";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getDesc());
            stmt.setDouble(3, entidade.getValor());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void atualizar(TipoQuarto entidade) {
        String sql = "UPDATE tipo_quarto SET nome = ?, descricao = ?, valor = ?"
                     + " WHERE id = ?";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getDesc());
            stmt.setDouble(3, entidade.getValor());
            stmt.setInt(4, entidade.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM tipo_quarto WHERE id = ?";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TipoQuarto buscar(int id) {
        String sql = "SELECT * FROM tipo_quarto WHERE id = ?";

        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                String nome = rs.getString("nome");
                String desc = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                TipoQuarto entidade = new TipoQuarto(id, nome, desc, valor);

                return entidade;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TipoQuarto> listar() {
        List<TipoQuarto> entidades = new ArrayList<TipoQuarto>();
        String sql = "SELECT * FROM tipo_quarto";

        try (PreparedStatement stmt = dbConnect(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String nome = rs.getString("nome");
                String desc = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                int id = rs.getInt("id");
                TipoQuarto entidade = new TipoQuarto(id, nome, desc, valor);
                entidades.add(entidade);
            }
            return entidades;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}