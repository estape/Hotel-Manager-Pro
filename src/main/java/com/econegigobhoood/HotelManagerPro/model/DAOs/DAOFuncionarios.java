package com.econegigobhoood.HotelManagerPro.model.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.model.DTOs.DTOFuncionarios;
import com.econegigobhoood.HotelManagerPro.model.Abstract.Pessoa;

public class DAOFuncionarios extends Pessoa {
    private Connection conexion;
     public List<DTOFuncionarios> listaFuncionarios() {
        
        List<DTOFuncionarios> cliente = new ArrayList<DTOFuncionarios>();
        String query = "SELECT * FROM Funcionarios";

        try {
            DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idFuncionario  = rs.getInt("idFuncionario");
                String nome       = rs.getString("nome");
                String cargo      = rs.getString("cargo");
                String login     = rs.getString("login");
                String senha     = rs.getString("senha"); 
            

                DTOFuncionarios DTOFuncionarios = new DTOFuncionarios (idFuncionario, nome, cargo, login, senha);

                cliente.add (DTOFuncionarios);
            }

            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }
    
    public void insertarPessoa(int idFuncionario, String nome, String cargo, String login, String senha) {
        try {
            DBConfig.getConnection();
            String consulta = "INSERT INTO Funcionarios (idFuncionario, nome, cargo, login, senha) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1,idFuncionario);
            statement.setString(2,nome);
            statement.setString(3,cargo) ;
            statement.setString(4,login);
            statement.setString(5,senha);

            statement.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    @Override
    public void deletarPessoa(int idFuncionario){
        String query = "DELETE FROM Funcionarios WHERE idFuncionarios = ?";
        
        try {
            DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, idFuncionario);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    

    /*public void alterarPessoa(String login, String senha, int idFuncionario){
        String query = "UPDATE Funcionarios SET login = ?, senha  = ? WHERE idFuncionario = ?";

        try {
            DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setString(1, login);
            stmt.setString(2,senha);
            stmt.setInt(3,idFuncionario);


            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    } */
     //Falar com Rodrigo e Ronan se de fato isso deve existir
    
     public DTOFuncionarios selecionarFuncionarios(int id){
        String query = "SELECT * FROM Funcionarios WHERE idHospede = ?";
        
        try {
            DBConfig.getConnection();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idFuncionario  = rs.getInt("idFuncionario");
                String nome       = rs.getString("nome");
                String cargo      = rs.getString("cargo");
                String login     = rs.getString("login");
                String senha     = rs.getString("senha"); 
               

                DTOFuncionarios DTOFuncionarios = new DTOFuncionarios (idFuncionario, nome, cargo, login, senha);

                return DTOFuncionarios;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }
}
