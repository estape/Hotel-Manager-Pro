package com.econegigobhoood.HotelManagerPro.model.dao;


import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.model.entity.Hospede;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOHospede implements IDAO<Hospede> {

    @Override
    public String getNomeClasse() {
        return Hospede.class.getSimpleName();
    }

    @Override
    public PreparedStatement dbConnect(String sql) throws SQLException {
        Connection connection = DBConfig.getCon();
        return connection.prepareStatement(sql);
    }

    @Override
    public void cadastrar(Hospede entidade) {
        String sql = "INSERT INTO hospede (nome, cpf, telefone) VALUES(?, ?, ?)";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getCpf());
            stmt.setString(3, entidade.getTelefone());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Hospede entidade) {
        String sql = "UPDATE hospede SET nome = ?, cpf = ?, telefone = ? WHERE id = ?";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getCpf());
            stmt.setString(3, entidade.getTelefone());
            stmt.setInt(4, entidade.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM hospede WHERE id = ?";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Hospede buscar(int id) {
        String sql = "SELECT * FROM hospede WHERE id = ?";

        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String telefone = rs.getString("telefone");
                Hospede entidade = new Hospede(id, nome, cpf, telefone);

                return entidade;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Hospede> listar() {
        List<Hospede> entidades = new ArrayList<Hospede>();
        String sql = "SELECT * FROM hospede";

        try (PreparedStatement stmt = dbConnect(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String telefone = rs.getString("telefone");
                int id = rs.getInt("id");
                Hospede entidade = new Hospede(id, nome, cpf, telefone);
                entidades.add(entidade);
            }
            return entidades;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
