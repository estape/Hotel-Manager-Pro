package com.econegigobhoood.HotelManagerPro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//import javax.management.modelmbean.ModelMBeanOperationInfo;
//import javax.swing.table.DefaultTableModel;


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
                int IdCliente = rs.getInt("IdCliente");
                String Nome = rs.getString("Nome");
                String RG = rs.getString("RG");
                int Idade = rs.getInt("Idade");
            

                DTOCliente DTOCliente = new DTOCliente (IdCliente,Nome,RG,Idade);

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



}
