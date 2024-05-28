package com.econegigobhoood.HotelManagerPro.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.model.DTOs.DTOReserva;

public class DAOReserva {
    private Connection conexion;

    public List<DTOReserva> listaReserva() {

        List<DTOReserva> cliente = new ArrayList<DTOReserva>();
        String query = "SELECT * FROM Reservas";

        try {
            conexion = DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idReserva = rs.getInt("idReserva");
                float valorTotal = rs.getFloat("valorTotal");
                Date dtReserva = rs.getDate("dtReserva");
                int idFuncionarios = rs.getInt("idFuncionarios");
                String Status = rs.getString("Status");
                int idHospede = rs.getInt("idHospede");

                DTOReserva DTOReserva = new DTOReserva(idReserva, valorTotal, dtReserva, idFuncionarios, Status,
                        idHospede);

                cliente.add(DTOReserva);
            }

            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // DBConfig.desconectar();
        }
        return null;
    }

    public void insertarReserva(DTOReserva entidade) {
        try {
            conexion = DBConfig.getConnection();
            String consulta = "INSERT INTO Reservas (idReserva, valorTotal, dtReserva, idFuncionarios,Status,idHospede) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, entidade.getIdReserva());
            statement.setFloat(2, entidade.getValorTotal());
            statement.setDate(3, entidade.getDtReserva());
            statement.setInt(4, entidade.getIdFuncionarios());
            statement.setString(5, entidade.getStatus());
            statement.setInt(6, entidade.getIdHospede());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletarReserva(int idReserva) {
        String query = "DELETE FROM Reservas WHERE idReserva = ?";

        try {
            conexion = DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, idReserva);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarInfoReserva(String infadd, int idQuarto) {
        String query = "UPDATE Reservas SET infadd = ? WHERE idReserva = ?";

        try {
            conexion = DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setString(1, infadd);
            stmt.setInt(2, idQuarto);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DTOReserva selecionarQuarto(int id) {
        String query = "SELECT * FROM Reservas WHERE idReserva = ?";

        try {
            conexion = DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idReserva = rs.getInt("idReserva");
                float valorTotal = rs.getFloat("valorTotal");
                Date dtReserva = rs.getDate("dtReserva");
                int idFuncionarios = rs.getInt("idFuncionarios");
                String Status = rs.getString("Status");
                int idHospede = rs.getInt("idHospede");

                DTOReserva DTOReserva = new DTOReserva(idReserva, valorTotal, dtReserva, idFuncionarios, Status,
                        idHospede);
                ;

                return DTOReserva;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
