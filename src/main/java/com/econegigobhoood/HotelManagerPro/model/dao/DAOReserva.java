package com.econegigobhoood.HotelManagerPro.model.dao;

import com.econegigobhoood.HotelManagerPro.model.entity.Reserva;
import com.econegigobhoood.HotelManagerPro.model.entity.Pedido;
import com.econegigobhoood.HotelManagerPro.model.entity.Quarto;
import com.econegigobhoood.HotelManagerPro.model.entity.Status;
import com.econegigobhoood.HotelManagerPro.config.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOReserva implements IDAO<Reserva> {
    private String lembreteSQLExcept = "O tratamento deste erro, em aplicações que " +
            "não sejam CLI (tipo web), deve ser feito em outro lugar, tipo na " +
            "View. Deixando o tratamento aqui e retornando nulo, se não for CLI, " +
            "nunca saberemos que o erro aconteceu. ";
    // Importa as Foreign Keys
    private DAOPedido daoPedido;
    private DAOQuarto daoQuarto;
    private DAOStatus daoStatus;

    public DAOReserva(DAOPedido daoPedido, DAOQuarto daoQuarto,
                      DAOStatus daoStatus) {
        this.daoPedido = daoPedido;
        this.daoQuarto = daoQuarto;
        this.daoStatus = daoStatus;
    }

    @Override
    public String getNomeClasse() {
        return Reserva.class.getSimpleName();
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
    public int cadastrar(Reserva entidade) {
        String sql = "INSERT INTO reserva (dt_entrada, dt_saida, " +
                     "id_res_status_fk, id_pedido_fk, id_quarto_fk) " +
                     "VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setObject(1, entidade.getDtEntrada());
            stmt.setObject(2, entidade.getDtSaida());
            stmt.setInt(3, 1); // Reservado
            stmt.setInt(4, entidade.getPedido().getId());
            stmt.setInt(5, entidade.getQuarto().getId());

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
    public void atualizar(Reserva entidade) {
        String sql = "UPDATE reserva SET id_res_status_fk = ? WHERE id = ?";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, 2); // Check-in feito
            stmt.setInt(2, entidade.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM reserva WHERE id = ?";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
    }

    @Override
    public Reserva buscar(int id) {
        String selectSQL = "SELECT r.*, vr.valor_reserva " +
                           "FROM reserva AS r " +
                           "JOIN valor_reserva AS vr ON r.id = vr.reserva_id " +
                           "WHERE r.id = ?";

        String updateSQL = "UPDATE reserva " +
                           "SET id_res_status_fk = 3 " +
                           "WHERE dt_entrada < CURRENT_DATE AND id_res_status_fk = 1";

        // Execute update
        try (PreparedStatement updateStmt = dbConnect(updateSQL)) {
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
        // Execute select
        try (PreparedStatement stmt = dbConnect(selectSQL)) {
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    double vlReserva = rs.getDouble("valor_reserva");
                    LocalDate dtEntrada = rs.getObject("dt_entrada", LocalDate.class);
                    LocalDate dtSaida = rs.getObject("dt_saida", LocalDate.class);
                    // pega e busca status
                    int idStat = rs.getInt("id_res_status_fk");
                    Status status = daoStatus.buscar(idStat);
                    // pega e busca pedido
                    int idPedido = rs.getInt("id_pedido_fk");
                    Pedido pedido = daoPedido.buscar(idPedido);
                    // pega e busca quarto
                    int idQuarto = rs.getInt("id_quarto_fk");
                    Quarto quarto = daoQuarto.buscar(idQuarto);

                    Reserva entidade = new Reserva(id, vlReserva, dtEntrada,
                    dtSaida, status, pedido, quarto);

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
    public List<Reserva> listar() {
        List<Reserva> entidades = new ArrayList<Reserva>();
        String selectSQL = "SELECT r.*, vr.valor_reserva " +
                           "FROM reserva AS r " +
                           "JOIN valor_reserva AS vr ON r.id = vr.reserva_id";
       
        String updateSQL = "UPDATE reserva " +
                           "SET id_res_status_fk = 3 " +
                           "WHERE dt_entrada < CURRENT_DATE AND id_res_status_fk = 1";
        // Execute update
        try (PreparedStatement updateStmt = dbConnect(updateSQL)) {
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
        // Execute select
        try (PreparedStatement stmt = dbConnect(selectSQL);
                ResultSet rs = stmt.executeQuery()) {
            while(rs.next()) {
                int id = rs.getInt("id");
                double vlReserva = rs.getDouble("valor_reserva");
                LocalDate dtEntrada = rs.getObject("dt_entrada", LocalDate.class);
                LocalDate dtSaida = rs.getObject("dt_saida", LocalDate.class);
                // pega e busca status
                int idStat = rs.getInt("id_res_status_fk");
                Status status = daoStatus.buscar(idStat);
                // pega e busca pedido
                int idPedido = rs.getInt("id_pedido_fk");
                Pedido pedido = daoPedido.buscar(idPedido);
                // pega e busca quarto
                int idQuarto = rs.getInt("id_quarto_fk");
                Quarto quarto = daoQuarto.buscar(idQuarto);

                Reserva entidade = new Reserva(id, vlReserva, dtEntrada,
                dtSaida, status, pedido, quarto);

                entidades.add(entidade);
            }
            return entidades;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
        return null;
    }

    public List<Reserva> getFKPedido(int idPK) {
        List<Reserva> entidades = new ArrayList<>();
        String selectSQL = "SELECT r.*, vr.valor_reserva " +
                           "FROM reserva AS r " +
                           "JOIN valor_reserva AS vr ON r.id = vr.reserva_id " +
                           "WHERE r.id_pedido_fk = ?";

        String updateSQL = "UPDATE reserva " +
                           "SET id_res_status_fk = 3 " +
                           "WHERE dt_entrada < CURRENT_DATE AND id_res_status_fk = 1";
    
        // Obtém a conexão
        Connection connection = null;
        try {
            connection = DBConfig.getCon();
            connection.setAutoCommit(false); // Desativa o autocommit
    
            // Execute update
            try (PreparedStatement updateStmt = connection.prepareStatement(updateSQL)) {
                updateStmt.executeUpdate();
            }
    
            // Execute select
            try (PreparedStatement stmt = connection.prepareStatement(selectSQL)) {
                stmt.setInt(1, idPK);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        double vlReserva = rs.getDouble("valor_reserva");
                        LocalDate dtEntrada = rs.getObject("dt_entrada", LocalDate.class);
                        LocalDate dtSaida = rs.getObject("dt_saida", LocalDate.class);
                        // pega e busca status
                        int idStat = rs.getInt("id_res_status_fk");
                        Status status = daoStatus.buscar(idStat);
                        // pega e busca pedido
                        int idPedido = rs.getInt("id_pedido_fk");
                        Pedido pedido = daoPedido.buscar(idPedido);
                        // pega e busca quarto
                        int idQuarto = rs.getInt("id_quarto_fk");
                        Quarto quarto = daoQuarto.buscar(idQuarto);
    
                        Reserva entidade = new Reserva(id, vlReserva, dtEntrada,
                        dtSaida, status, pedido, quarto);
    
                        entidades.add(entidade);
                    }
                }
            }
    
            connection.commit(); // Comita as alterações no banco de dados
    
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
            try {
                if (connection != null) {
                    connection.rollback(); // Desfaz as alterações no banco de dados
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println(lembreteSQLExcept);
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true); // Restaura o autocommit
                    connection.close(); // Fecha a conexão
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println(lembreteSQLExcept);
            }
        }
    
        return entidades;
    }
}
