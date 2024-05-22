package com.econegigobhoood.HotelManagerPro.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.model.abs.Pessoa;
import com.econegigobhoood.HotelManagerPro.model.entity.Hospede;


public class DAOHospede extends Pessoa {
    private Connection conexion;
     public List<Hospede> listaHospedes() {
        
        List<Hospede> cliente = new ArrayList<Hospede>();
        String query = "SELECT * FROM Hospedes";

        try {
            DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String telefone  = rs.getString("telefone");
                String cpf       = rs.getString("cpf");
                String nome      = rs.getString("nome");
                int    idHospede = rs.getInt("idHospede"); 
            

                Hospede Hospede = new Hospede (idHospede, nome, cpf,  telefone);

                cliente.add (Hospede);
            }

            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }
    
    public void insertarPessoa(int idHospede, String nome, String cpf, String telefone) {
        try {
            DBConfig.getConnection();
            String consulta = "INSERT INTO Hospedes (idHospede, nome, cpf, telefone) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1,idHospede);
            statement.setString(2,nome);
            statement.setString(3,cpf) ;
            statement.setString(4,telefone);

            statement.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    @Override
    public void deletarPessoa(int idHospede){
        String query = "DELETE FROM Hospedes WHERE idHospede = ?";
        
        try {
            DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, idHospede);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    

    /*public void alterarInfoHospede(String infadd ){
        String query = "UPDATE Hospd SET infadd = ? WHERE idQuarto = ?";

        try {
            DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setString(1, infadd);


            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }*/ //Falar com Rodrigo e Ronan se de fato isso deve existir

    public Hospede selecionarHospede(int id){
        String query = "SELECT * FROM Hospedes WHERE idHospede = ?";
        
        try {
            DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String telefone  = rs.getString("telefone");
                String cpf       = rs.getString("cpf");
                String nome      = rs.getString("nome");
                int    idHospede = rs.getInt("idHospede"); 
               

                Hospede Hospede = new Hospede (idHospede, nome, cpf,  telefone);

                return Hospede;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }
}
