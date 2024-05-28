package com.econegigobhoood.HotelManagerPro.model.dao;

import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.model.entity.Funcionario;
import com.econegigobhoood.HotelManagerPro.model.entity.Hospede;
import com.econegigobhoood.HotelManagerPro.model.entity.Pedido;
import com.econegigobhoood.HotelManagerPro.model.entity.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class DAOPedido implements IDAO<Pedido> {
    private String lembreteSQLExcept = "O tratamento deste erro, em aplicações que " +
            "não sejam CLI (tipo web), deve ser feito em outro lugar, tipo na " +
            "View. Deixando o tratamento aqui e retornando nulo, se não for CLI, " +
            "nunca saberemos que o erro aconteceu. ";
    // Importa as Foreign Keys
    private DAOFuncionario daoFuncionario;
    private DAOHospede daoHospede;
    private DAOReserva daoReserva;
    
    public DAOPedido(DAOFuncionario daoFuncionario, DAOHospede daoHospede) {
        this.daoFuncionario = daoFuncionario;
        this.daoHospede = daoHospede;
    }

    public void setDaoReserva(DAOReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    @Override
    public String getNomeClasse() {
        return Pedido.class.getSimpleName();
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
    public int cadastrar(Pedido entidade) {
        String sql = "INSERT INTO pedido (dt_pedido, id_hosp_fk, id_func_fk) " +
                     "VALUES(?, ?, ?)";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setObject(1, entidade.getDtPedido());
            stmt.setInt(2, entidade.getHospede().getId());
            stmt.setInt(3, entidade.getFuncionario().getId());

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
        String sql = "UPDATE pedido SET id_hosp_fk = ?, id_func_fk = ? WHERE id = ?";
        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, entidade.getHospede().getId());
            stmt.setInt(2, entidade.getFuncionario().getId());
            stmt.setInt(3, entidade.getId());

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
        String sql = "SELECT p.*, vp.valor_total_pedido " +
                     "FROM pedido AS p " +
                     "JOIN valor_total_pedido AS vp ON p.id = vp.pedido_id " +
                     "WHERE p.id = ?";

        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    LocalDate dtPedido = rs.getObject("dt_pedido", LocalDate.class);
                    double vlTotalPedido = rs.getDouble("valor_total_pedido");
                    // Pega e busca Hospede
                    int idHosp = rs.getInt("id_hosp_fk");
                    Hospede hospede = daoHospede.buscar(idHosp);
                    // Pega e busca Funcionario
                    int idFunc = rs.getInt("id_func_fk");
                    Funcionario funcionario = daoFuncionario.buscar(idFunc);

                    Pedido entidade = new Pedido(id, dtPedido, vlTotalPedido,
                            hospede, funcionario);

                    return entidade;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(lembreteSQLExcept);
        }
        return null;
    }

    public List<Reserva> getListReserva(int idPedido) {
        return daoReserva.getFKPedido(idPedido);
    }


    public Pedido buscarInicial(int id) {
        String sql = "SELECT * FROM pedido WHERE id = ?";

        try (PreparedStatement stmt = dbConnect(sql)) {
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    LocalDate dtPedido = rs.getObject("dt_pedido", LocalDate.class);
                    // Pega e busca Hospede
                    int idHosp = rs.getInt("id_hosp_fk");
                    Hospede hospede = daoHospede.buscar(idHosp);
                    // Pega e busca Funcionario
                    int idFunc = rs.getInt("id_func_fk");
                    Funcionario funcionario = daoFuncionario.buscar(idFunc);
                    
                    Pedido entidade = new Pedido(id, dtPedido, hospede, funcionario);

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
    public List<Pedido> listar() {
        List<Pedido> entidades = new ArrayList<Pedido>();
        String sql = "SELECT p.*, vp.valor_total_pedido " +
                      "FROM pedido AS p " +
                      "JOIN valor_total_pedido AS vp ON p.id = vp.pedido_id ";
        
        try (PreparedStatement stmt = dbConnect(sql);
                ResultSet rs = stmt.executeQuery();) {
            while(rs.next()) {
                LocalDate dtPedido = rs.getObject("dt_pedido", LocalDate.class);
                double vlTotalPedido = rs.getDouble("valor_total_pedido");
                // Pega e busca Hospede
                int idHosp = rs.getInt("id_hosp_fk");
                Hospede hospede = daoHospede.buscar(idHosp);
                // Pega e busca Funcionario
                int idFunc = rs.getInt("id_func_fk");
                Funcionario funcionario = daoFuncionario.buscar(idFunc);
                // busca Reservas relacionadas
                int id = rs.getInt("id");
                
                Pedido entidade = new Pedido(id, dtPedido, vlTotalPedido,
                        hospede, funcionario);
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
