package com.econegigobhoood.HotelManagerPro.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.model.DTOs.DTOQuarto;


public class DAOQuarto {
    private Connection conexion;
     public List<DTOQuarto> listaQuartos() {
        
        List<DTOQuarto> cliente = new ArrayList<DTOQuarto>();
        String query = "SELECT * FROM Quartos";

        try {
            conexion = DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idQuarto    = rs.getInt("idQuarto");
                int qtdCamaSolt = rs.getInt("qtdCamaSolt");
                int qtdCamacas  = rs.getInt("qtdCamacas") ;
                int qtdbanheiro = rs.getInt("qtdbanheiro");
                String infadd   = rs.getString("infadd");     
                int idtpQuarto  = rs.getInt("idtpQuarto");
            

                DTOQuarto DTOQuarto = new DTOQuarto (idQuarto, qtdCamaSolt, qtdCamacas,  qtdbanheiro, infadd,idtpQuarto);

                cliente.add (DTOQuarto);
            }

            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //DBConfig.desconectar();
        }
        return null;
    }


    public void insertarQuarto(DTOQuarto entidade) {
        try {
            conexion = DBConfig.getConnection();
            String consulta = "INSERT INTO Quartos (idQuarto, qtdCamaSolt, qtdCamacas, qtdbanheiro,infadd,idtpQuarto) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1,entidade.getIdQuarto());
            statement.setInt(2,entidade.getQtdCamaSolt());
            statement.setInt(3,entidade.getQtdCamacas()) ;
            statement.setInt(4,entidade.getQtdbanheiro());
            statement.setString(5,entidade.getInfadd());     
            statement.setInt(6,entidade.getIdtpQuarto());

            statement.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void deletarQuarto(int idQuarto){
        String query = "DELETE FROM Quartos WHERE idQuarto = ?";
        
        try {
            conexion = DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, idQuarto);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    

    public void alterarInfoQuarto(String infadd,int idQuarto ){
        String query = "UPDATE Quartos SET infadd = ? WHERE idQuarto = ?";

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

    public DTOQuarto selecionarQuarto(int id){
        String query = "SELECT * FROM Quartos WHERE idQuarto = ?";
        
        try {
            conexion = DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idQuarto    = rs.getInt("idQuarto");
                int qtdCamaSolt = rs.getInt("qtdCamaSolt");
                int qtdCamacas  = rs.getInt("qtdCamacas") ;
                int qtdbanheiro = rs.getInt("qtdbanheiro");
                String infadd   = rs.getString("infadd");     
                int idtpQuarto  = rs.getInt("idtpQuarto");
               

                DTOQuarto DTOQuarto = new DTOQuarto (idQuarto, qtdCamaSolt, qtdCamacas,  qtdbanheiro, infadd,idtpQuarto);

                return DTOQuarto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }
}
