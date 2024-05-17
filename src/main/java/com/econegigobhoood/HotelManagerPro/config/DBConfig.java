package com.econegigobhoood.HotelManagerPro.config;

// java.sql.imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
// java.diversos.imports
  // Curiosidade: BufferedReader e FileReader são extendidas por Reader
import java.io.BufferedReader; // Para realizar leitura em bloco e não caracter por caracter. Torna a leitura mais otimizada em caso de alta carga de leitura ou rede lenta
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class DBConfig {
  private static final String URL = "jdbc:postgresql://localhost:5432/db_hotel_mgmt_sys";
  private static final String USER = "postgres";
  private static final String PASSWORD = "admin";

  // Getter de conexão
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  public static void createTables() {
    // Use esse snipet mais para criar as tabelas, pois, ele considera ";" como separador para atribuir nas instrucoes.
    // Em SQL real, um ponto e vírgula pode aparecer dentro de uma string literal e não deve ser tratado como o final de uma instrução. Para um tratamento correto de scripts SQL, podemos estudar, para outros projetos, bibliotecas como o jOOQ ou o JSR 356.
    
    // estrutura Try-with-resources, ou seja, try(resources){ } catch{ }, automaticamente fecha o recurso declarado no final do bloco.
    try (
      // InputStream is = DBConfig.class.getResourceAsStream("/model_f_sgh.sql");
      BufferedReader reader = new BufferedReader(new FileReader("src\\main\\java\\com\\econegigobhoood\\HotelManagerPro\\config\\model_f_sgh.sql"));
      Connection conexao = getConnection();
      Statement stmt = conexao.createStatement()
    ) {
      String conteudo = reader.lines().collect(Collectors.joining("\n"));
      String[] instrucoes = conteudo.split(";");

      for (String instrucao : instrucoes) {
          stmt.execute(instrucao);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}