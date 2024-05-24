package com.econegigobhoood.HotelManagerPro.model.dao;


import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.model.entity.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class DAOPedido implements IDAO<Pedido> {
    private String lembreteSQLExcept = "O tratamento deste erro, em aplicações que"
            + " não sejam CLI (tipo web), deve ser feito em outro lugar, tipo na"
            + " View. Deixando o tratamento aqui e retornando nulo, se não for CLI,"
            + " nunca saberemos que o erro aconteceu.";

    @Override
    public String getNomeClasse() {
        return Pedido.class.getSimpleName();
    }

    @Override
    public PreparedStatement dbConnect(String sql) throws SQLException {
        Connection connection = DBConfig.getCon();
        return connection.prepareStatement(sql);
    }

    @Override
    public int getStmtId(PreparedStatement stmt) throws SQLException {
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                return generatedId;
            } else {
                throw new SQLException("Falha no ID gerado");
            }
        }
    }

    @Override
    public int cadastrar(Pedido entidade) {
        String sql = "INSERT INTO pedido (dt_pedido, id_hosp_fk, id_func_fk)"
                     + " VALUES(?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setObject(1, LocalDate.now());
            // Foreign Keys
            stmt.setInt(3, entidade.getHospede().getId());
            stmt.setInt(4, entidade.getFuncionario().getId());

            stmt.executeUpdate();
            int generatedId = getStmtId(stmt);
            return generatedId;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
        return 0;
    }

    @Override
    public void atualizar(Pedido entidade) {
        String sql = "UPDATE pedido SET nome = ?, cpf = ?, cargo = ? WHERE id = ?";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getCpf());
            stmt.setString(3, entidade.getCargo());
            stmt.setInt(4, entidade.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM pedido WHERE id = ?";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
    }

    @Override
    public Pedido buscar(int id) {
        String sql = "SELECT * FROM pedido WHERE id = ?";

        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String cargo = rs.getString("cargo");
                Pedido entidade = new Pedido(id, nome, cpf, cargo);

                return entidade;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
        return null;
    }

    @Override
    public List<Pedido> listar() {
        List<Pedido> entidades = new ArrayList<Pedido>();
        String sql = "SELECT * FROM pedido";

        try (PreparedStatement stmt = dbConnect(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String cargo = rs.getString("cargo");
                int id = rs.getInt("id");
                Pedido entidade = new Pedido(id, nome, cpf, cargo);
                entidades.add(entidade);
            }
            return entidades;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
        return null;
    }
}
