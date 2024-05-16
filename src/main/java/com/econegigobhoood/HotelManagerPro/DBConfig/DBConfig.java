package com.econegigobhoood.HotelManagerPro.DBConfig;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConfig {
  private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
  private static final String USER = "sa";
  private static final String PASSWORD = "";
  private static Connection conexion;

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
  
    public static boolean hayConection() {
        return (conexion != null);
    }

    
    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
            // Estabelece a conexão com o banco de dados H2 em memória
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "sa", "");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conexion;
    }

    
    public static ResultSet executarSQL(String consultaSQL) throws SQLException {
        Statement sql = conexion.createStatement();
        return sql.executeQuery(consultaSQL);
    }

    
    public static boolean executarAtualizacaoSQL(String comandoSQL) throws SQLException {
        PreparedStatement sql = conexion.prepareStatement(comandoSQL);
        System.out.println(sql);
        return sql.executeUpdate() != 0;
    }

   
    public static void desconectar() {
        try{
            if (conexion != null) {
            conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    


  public static void criarTabelaClientes() {
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement()) {

          String sql = "CREATE TABLE IF NOT EXISTS Clientes ("
          + "IdCliente SERIAL PRIMARY KEY,"
          + "Nome VARCHAR(255),"
          + "RG VARCHAR(20),"
          + "Idade INT,"
          + ");";

      stmt.execute(sql);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  public static void criarTabelaFuncionarios() {
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement()) {

          String sql = "CREATE TABLE IF NOT EXISTS Funcionarios ("
          + "IdFuncionario SERIAL PRIMARY KEY,"
          + "Nome VARCHAR(255),"
          + "RG VARCHAR(20),"
          + "Idade INT,"
          + "Cargo VARCHAR(25)"
          + ");";

      stmt.execute(sql);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  public static void criarTabelaHospedagens() {
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement()) {

          String sql = "CREATE TABLE IF NOT EXISTS Hospedagens ("
          + "IdHospedagem SERIAL PRIMARY KEY,"
          + "DtEntreda DATE"
          + "DtSaida DATE"
          + "NumeroQuarto"
          + "IdCliente"
          + ");";

      stmt.execute(sql);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}

