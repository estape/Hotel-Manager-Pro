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
    private String lembreteSQLExcept = "O tratamento deste erro, em aplicações que"
            + " não sejam CLI (tipo web), deve ser feito em outro lugar, tipo na"
            + " View. Deixando o tratamento aqui e retornando nulo, se não for CLI,"
            + " nunca saberemos que o erro aconteceu.";

    @Override
    public String getNomeClasse() {
        return TipoQuarto.class.getSimpleName();
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
    public int cadastrar(TipoQuarto entidade) {
        String sql = "INSERT INTO tipo_quarto (nome, descricao, valor_unit) " +
                     "VALUES (?, ?, ?)";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getDesc());
            stmt.setDouble(3, entidade.getValor());

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
    public void atualizar(TipoQuarto entidade) {
        String sql = "UPDATE tipo_quarto SET nome = ?, descricao = ?, valor_unit = ?"
                     + " WHERE id = ?";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getDesc());
            stmt.setDouble(3, entidade.getValor());
            stmt.setInt(4, entidade.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
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
            System.out.println(lembreteSQLExcept);
        }
    }

    @Override
    public TipoQuarto buscar(int id) {
        String sql = "SELECT * FROM tipo_quarto WHERE id = ?";

        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    String nome = rs.getString("nome");
                    String desc = rs.getString("descricao");
                    double valor = rs.getDouble("valor_unit");
                    TipoQuarto entidade = new TipoQuarto(id, nome, desc, valor);

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
    public List<TipoQuarto> listar() {
        List<TipoQuarto> entidades = new ArrayList<TipoQuarto>();
        String sql = "SELECT * FROM tipo_quarto";

        try (PreparedStatement stmt = dbConnect(sql);
                ResultSet rs = stmt.executeQuery()) {
            while(rs.next()) {
                String nome = rs.getString("nome");
                String desc = rs.getString("descricao");
                double valor = rs.getDouble("valor_unit");
                int id = rs.getInt("id");
                TipoQuarto entidade = new TipoQuarto(id, nome, desc, valor);
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