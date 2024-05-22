package com.econegigobhoood.HotelManagerPro.model.dao;

import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.model.entity.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DAOFuncionario implements IDAO<Funcionario> {
    private Connection conexion;
     public List<Funcionario> listaFuncionario() {
        
        List<Funcionario> cliente = new ArrayList<Funcionario>();
        String query = "SELECT * FROM Funcionario";

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
            

                Funcionario Funcionario = new Funcionario (idFuncionario, nome, cargo, login, senha);

                cliente.add (Funcionario);
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
            String consulta = "INSERT INTO Funcionario (idFuncionario, nome, cargo, login, senha) VALUES (?, ?, ?, ?)";
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
    
    public void deletarPessoa(int idFuncionario){
        String query = "DELETE FROM Funcionario WHERE idFuncionario = ?";
        
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
        String query = "UPDATE Funcionario SET login = ?, senha  = ? WHERE idFuncionario = ?";

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
    
     public Funcionario selecionarFuncionario(int id){
        String query = "SELECT * FROM Funcionario WHERE idHospede = ?";
        
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
               

                Funcionario Funcionario = new Funcionario (idFuncionario, nome, cargo, login, senha);

                return Funcionario;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }


    /*
     * !!! ATENÇÃO !!!
     * Métodos estabelecidos pela interface DAO (IDAO)
     */
    @Override
    public void cadastrar(Funcionario func) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrar'");
    }

    @Override
    public void atualizar(Funcionario func) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void excluir(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }

    @Override
    public Funcionario buscar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscar'");
    }

    @Override
    public List<Funcionario> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public String getNomeClasse() {
        return Funcionario.class.getSimpleName();
    }
}
