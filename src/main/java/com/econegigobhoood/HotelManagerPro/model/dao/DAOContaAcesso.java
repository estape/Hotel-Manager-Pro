package com.econegigobhoood.HotelManagerPro.model.dao;


import com.econegigobhoood.HotelManagerPro.config.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DAOContaAcesso {
    private String lembreteSQLExcept = "O tratamento deste erro, em aplicações que"
            + " não sejam CLI (tipo web), deve ser feito em outro lugar, tipo na"
            + " View. Deixando o tratamento aqui e retornando nulo, se não for CLI,"
            + " nunca saberemos que o erro aconteceu.";

    private PreparedStatement dbConnect(String sql) throws SQLException {
        Connection connection = DBConfig.getCon();
        return connection.prepareStatement(sql,
                PreparedStatement.RETURN_GENERATED_KEYS);
    }

    public void reservaLog(String func, LocalDate dia) {
        String sql = "INSERT INTO reserva_diaria_log (nome_func, dt_acesso) " +
                     "VALUES(?, ?)";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setString(1, func);
            stmt.setObject(2, dia);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
    }

    public int contaAcessos(LocalDate dia) {
        String sql = "SELECT COUNT(*) FROM reserva_diaria_log WHERE dt_acesso = ?";
        
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setObject(1, dia);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Indice da coluna 1 (que tem a contagem)
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }

        return 0;
    }
}