package com.econegigobhoood.HotelManagerPro.model.dao;


import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.model.entity.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOFuncionario implements IDAOPessoa<Funcionario> {
    private String lembreteSQLExcept = "O tratamento deste erro, em aplicações que"
            + " não sejam CLI (tipo web), deve ser feito em outro lugar, tipo na"
            + " View. Deixando o tratamento aqui e retornando nulo, se não for CLI,"
            + " nunca saberemos que o erro aconteceu.";

    @Override
    public String getNomeClasse() {
        return Funcionario.class.getSimpleName();
    }

    @Override
    public PreparedStatement dbConnect(String sql) throws SQLException {
        Connection connection = DBConfig.getCon();
        return connection.prepareStatement(sql,
                PreparedStatement.RETURN_GENERATED_KEYS);
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
    public int cadastrar(Funcionario entidade) {
        String sql = "INSERT INTO funcionario (nome, cpf, cargo) VALUES(?, ?, ?)";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getCpf());
            stmt.setString(3, entidade.getCargo());

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
    public void atualizar(Funcionario entidade) {
        String sql = "UPDATE funcionario SET nome = ?, cpf = ?, cargo = ? WHERE id = ?";
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
        String sql = "DELETE FROM funcionario WHERE id = ?";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
    }

    @Override
    public Funcionario buscar(int id) {
        String sql = "SELECT * FROM funcionario WHERE id = ?";

        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    String nome = rs.getString("nome");
                    String cpf = rs.getString("cpf");
                    String cargo = rs.getString("cargo");
                    Funcionario entidade = new Funcionario(id, nome, cpf, cargo);

                    return entidade;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
        return null;
    }

    @Override
    public Funcionario buscarCpf(String cpf) {
        String sql = "SELECT * FROM funcionario WHERE cpf = ?";

        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setString(1, cpf);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String cargo = rs.getString("cargo");
                    Funcionario entidade = new Funcionario(id, nome, cpf, cargo);

                    return entidade;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
        return null;
    }

    @Override
    public List<Funcionario> listar() {
        List<Funcionario> entidades = new ArrayList<Funcionario>();
        String sql = "SELECT * FROM funcionario";

        try (PreparedStatement stmt = dbConnect(sql);
                ResultSet rs = stmt.executeQuery();) {
            while(rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String cargo = rs.getString("cargo");
                int id = rs.getInt("id");
                Funcionario entidade = new Funcionario(id, nome, cpf, cargo);
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
