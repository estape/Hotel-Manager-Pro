package com.econegigobhoood.HotelManagerPro.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.swing.table.DefaultTableModel;


import com.econegigobhoood.HotelManagerPro.DBConfig.DBConfig;
import com.econegigobhoood.HotelManagerPro.Model.DTOCliente;

public class DAOCliente {
     public List<DAOCliente> listaLivros() {
        List<DAOCliente> livros = new ArrayList<DAOCliente>();
        String query = "SELECT * FROM Livros";

        try {
            DBConfig.conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("IdLivro");
                String nome = rs.getString("Nome");
                String autor = rs.getString("Autor");
                int paginas = rs.getInt("Paginas");
                String status = rs.getString("Status");

                DAOCliente DAOCliente = new DAOCliente (id, nome, autor, paginas, status);

                DAOCliente.add (DAOCliente);
            }

            return livros;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConfig.desconectar();
        }
        return null;
    }
}
