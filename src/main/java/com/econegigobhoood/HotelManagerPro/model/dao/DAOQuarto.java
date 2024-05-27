package com.econegigobhoood.HotelManagerPro.model.dao;

import com.econegigobhoood.HotelManagerPro.model.entity.Quarto;
import com.econegigobhoood.HotelManagerPro.model.entity.Reserva;
import com.econegigobhoood.HotelManagerPro.model.entity.TipoQuarto;
import com.econegigobhoood.HotelManagerPro.config.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DAOQuarto implements IDAO<Quarto> {
    private String lembreteSQLExcept = "O tratamento deste erro, em aplicações que " +
            "não sejam CLI (tipo web), deve ser feito em outro lugar, tipo na " +
            "View. Deixando o tratamento aqui e retornando nulo, se não for CLI, " +
            "nunca saberemos que o erro aconteceu. ";
    // Importa as Foreign Keys
    private DAOTipoQuarto daoTipo;
    private DAOReserva daoReserva;

    @Override
    public String getNomeClasse() {
        return Quarto.class.getSimpleName();
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
    public int cadastrar(Quarto entidade) {
        String sql = "INSERT INTO quarto (num_quarto, qtd_cama_solt, " +
                     "qtd_cama_cas, qtd_banh, info_adc, id_tipoquar_fk) " +
                     "VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, entidade.getNumQuarto());
            stmt.setInt(2, entidade.getQtdCamaSolt());
            stmt.setInt(3, entidade.getQtdCamaCas());
            stmt.setInt(4, entidade.getQtdBanho());
            stmt.setString(5, entidade.getInfoAdc());
            stmt.setInt(6, entidade.getTipo().getId());

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
    public void atualizar(Quarto entidade) {
        // BUSCA DEITA PELO NUMERO DO QUARTO, NÃO ID
        /*
        * Não atualizamos as Reservas aqui pois elas tem-de ser
        * atualizadas unitariamente
        */ 
        String sql = "UPDATE quarto " +
                     "SET qtd_cama_solt = ?, qtd_cama_cas = ?, qtd_banh = ? " +
                     "info_adc = ?, id_tipoquar_fk = ? " +
                     "WHERE num_quarto = ?";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, entidade.getQtdCamaSolt());
            stmt.setInt(2, entidade.getQtdCamaCas());
            stmt.setInt(3, entidade.getQtdBanho());
            stmt.setString(4, entidade.getInfoAdc());
            stmt.setInt(5, entidade.getTipo().getId());
            stmt.setInt(6, entidade.getNumQuarto());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
    }

    @Override
    public void excluir(int numQuarto) {
        String sql = "DELETE FROM quarto WHERE num_quarto = ?";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, numQuarto);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
    }

    @Override
    public Quarto buscar(int id) {
        String sql = "SELECT q.*, t.valor_unit " +
                     "FROM quarto AS q " +
                     "JOIN tipo_quarto AS t ON q.id_tipoquar_fk = t.id " +
                     "WHERE q.id = ?";

        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                int numQuarto = rs.getInt("num_quarto");
                int qtdCamaSolt = rs.getInt("qtd_cama_solt");
                int qtdCamaCas = rs.getInt("qtd_cama_cas");
                int qtdBanho = rs.getInt("qtd_banh");
                String infoAdc = rs.getString("infoAdc");
                double vlQuarto = rs.getDouble("valor_unit");
                // pega e busca Tipo
                int idTipo = rs.getInt("id_tipoquar_fk");
                TipoQuarto tipo = daoTipo.buscar(idTipo);
                // busca Reservas relacionadas
                List<Reserva> reservas = daoReserva.getFKList(id, "quarto");

                Quarto entidade = new Quarto(id, numQuarto, qtdCamaSolt, qtdCamaCas,
                        qtdBanho, infoAdc, vlQuarto, tipo, reservas);

                return entidade;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
        return null;
    }

    public Quarto buscarNum(int numQuarto) {
        String sql = "SELECT q.*, t.valor_unit " +
                     "FROM quarto AS q " +
                     "JOIN tipo_quarto AS t ON q.id_tipoquar_fk = t.id " +
                     "WHERE q.num_quarto = ?";

        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, numQuarto);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                int qtdCamaSolt = rs.getInt("qtd_cama_solt");
                int qtdCamaCas = rs.getInt("qtd_cama_cas");
                int qtdBanho = rs.getInt("qtd_banh");
                String infoAdc = rs.getString("infoAdc");
                double vlQuarto = rs.getDouble("valor_unit");
                // pega e busca Tipo
                int idTipo = rs.getInt("id_tipoquar_fk");
                TipoQuarto tipo = daoTipo.buscar(idTipo);
                // busca Reservas relacionadas
                List<Reserva> reservas = daoReserva.getFKList(id, "quarto");

                Quarto entidade = new Quarto(id, numQuarto, qtdCamaSolt, qtdCamaCas,
                        qtdBanho, infoAdc, vlQuarto, tipo, reservas);

                return entidade;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
        return null;
    }

    @Override
    public List<Quarto> listar() {
        List<Quarto> entidades = new ArrayList<Quarto>();
        String sql = "SELECT * FROM quarto";
        
        try (PreparedStatement stmt = dbConnect(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int numQuarto = rs.getInt("num_quarto");
                int qtdCamaSolt = rs.getInt("qtd_cama_solt");
                int qtdCamaCas = rs.getInt("qtd_cama_cas");
                int qtdBanho = rs.getInt("qtd_banh");
                String infoAdc = rs.getString("infoAdc");
                double vlQuarto = rs.getDouble("valor_unit");
                // pega e busca Tipo
                int idTipo = rs.getInt("id_tipoquar_fk");
                TipoQuarto tipo = daoTipo.buscar(idTipo);
                // busca Reservas relacionadas
                List<Reserva> reservas = daoReserva.getFKList(id, "quarto");

                Quarto entidade = new Quarto(id, numQuarto, qtdCamaSolt,
                        qtdCamaCas, qtdBanho, infoAdc, vlQuarto, tipo, reservas);
                        
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
