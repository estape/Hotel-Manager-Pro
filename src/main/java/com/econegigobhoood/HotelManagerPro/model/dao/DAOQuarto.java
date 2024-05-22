package com.econegigobhoood.HotelManagerPro.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.model.entity.Quarto;


public class DAOQuarto {
    private Connection conexion;
     public List<Quarto> listaQuartos() {
        
        List<Quarto> cliente = new ArrayList<Quarto>();
        String query = "SELECT * FROM Quartos";

        try {
            DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idQuarto    = rs.getInt("idQuarto");
                int qtdCamaSolt = rs.getInt("qtdCamaSolt");
                int qtdCamacas  = rs.getInt("qtdCamacas") ;
                int qtdbanheiro = rs.getInt("qtdbanheiro");
                String infadd   = rs.getString("infadd");     
                int idtpQuarto  = rs.getInt("idtpQuarto");
            

                Quarto Quarto = new Quarto (idQuarto, qtdCamaSolt, qtdCamacas,  qtdbanheiro, infadd,idtpQuarto);

                cliente.add (Quarto);
            }

            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //DBConfig.desconectar();
        }
        return null;
    }


    public void insertarQuarto(int idQuarto,int qtdCamaSolt, int qtdCamacas,int qtdbanheiro,String infadd,int idtpQuarto) {
        try {
            DBConfig.getConnection();
            String consulta = "INSERT INTO Quartos (idQuarto, qtdCamaSolt, qtdCamacas, qtdbanheiro,infadd,idtpQuarto) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1,idQuarto);
            statement.setInt(2,qtdCamaSolt);
            statement.setInt(3,qtdCamacas) ;
            statement.setInt(4,qtdbanheiro);
            statement.setString(5,infadd);     
            statement.setInt(6,idtpQuarto);

            statement.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public void deletarQuarto(int idQuarto){
        String query = "DELETE FROM Quartos WHERE idQuarto = ?";
        
        try {
            DBConfig.getConnection();
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
            DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setString(1, infadd);
            stmt.setInt(2, idQuarto);


            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public Quarto selecionarQuarto(int id){
        String query = "SELECT * FROM Quartos WHERE idQuarto = ?";
        
        try {
            DBConfig.getConnection();
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
               

                Quarto Quarto = new Quarto (idQuarto, qtdCamaSolt, qtdCamacas,  qtdbanheiro, infadd,idtpQuarto);

                return Quarto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }
}
