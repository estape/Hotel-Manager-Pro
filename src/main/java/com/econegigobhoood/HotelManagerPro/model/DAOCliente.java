package com.econegigobhoood.HotelManagerPro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.econegigobhoood.HotelManagerPro.DBConfig.DBConfig;


public class DAOCliente {
    private Connection conexion;
     public List<DTOCliente> listaClientes() {
        
        List<DTOCliente> cliente = new ArrayList<DTOCliente>();
        String query = "SELECT * FROM Clientes";

        try {
            DBConfig.conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("IdCliente");
                String nome = rs.getString("Nome");
                String rg = rs.getString("RG");
                int idade = rs.getInt("Idade");
            

                DTOCliente DTOCliente = new DTOCliente (idCliente,nome,rg,idade);

                cliente.add (DTOCliente);
            }

            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConfig.desconectar();
        }
        return null;
    }


    public void insertarCliente(String nome, String rg,int idade, int idCliente) {
        try {
            DBConfig.conectar();
            String consulta = "INSERT INTO Clientes (Nome, RG, Idade, IdCliente) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, (nome));
            statement.setString(2, (rg));
            statement.setInt(3, (idade));
            statement.setInt(4, (idCliente));

            statement.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConfig.desconectar(); 
        }
    }

    public void deletarCliente(int id){
        String query = "DELETE FROM Cliente WHERE idCliente = ?";
        
        try {
            DBConfig.conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConfig.desconectar(); 
        }
    }
    

    public void alterarCliente(String nome,String rg,int idade, int idCliente ){
        String query = "UPDATE Clientes SET nome = ?, rg = ?, idade = ? WHERE idCliente = ?";

        try {
            DBConfig.conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setString(1, nome);
            stmt.setString(2, rg);
            stmt.setInt(3, idade);
            stmt.setInt(4, idCliente);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConfig.desconectar();
        }
    }

    public DTOCliente selecionarcliente(int idCliente){
        String query = "SELECT * FROM Livros WHERE IdLivro = ?";
        
        try {
            DBConfig.conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, idCliente);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String rg = rs.getString("rg");
                int idade = rs.getInt("idade");
               

                DTOCliente cliente = new DTOCliente(nome, rg, idade);
                
                return cliente;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConfig.desconectar(); 
        }
        return null;
    }


}
