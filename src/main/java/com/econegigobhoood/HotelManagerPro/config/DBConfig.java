package com.econegigobhoood.HotelManagerPro.config;

import com.econegigobhoood.HotelManagerPro.model.Misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * Sobre BufferedReader e FileReader
 *  - BufferedReader e FileReader são extendidas por Reader
 *  - BufferedReader: para realizar leitura em bloco e não caracter
 *  por caracter. Torna a leitura mais otimizada em caso de alta 
 *  carga de leitura ou rede lenta
 *  - FileReader: Lê um arquivo, buscando seus bytes e decodificando
 *  em letras através do decodificador do sistema/aplicativo
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class DBConfig {
  private static final String URL = "jdbc:postgresql://localhost:5432/db_hotel_mgmt_sys";
  private static final String USER = "postgres";
  private static final String PASSWORD = "admin";

  // Getter de conexão
  public static Connection getCon() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  public static void createTables() {
    /* estrutura Try-with-resources, ou seja, try(resources){ } catch{ },
    automaticamente fecha o recurso declarado no final do bloco. */ 
    try (
      // >>>>>> Lendo arquivos SQL na pasta config <<<<<<
      BufferedReader reader = new BufferedReader (new FileReader
              ("src\\main\\java\\com\\econegigobhoood\\HotelManagerPro\\config\\model_f_sgh.sql"));
      Connection conexao = getCon();
      Statement stmt = conexao.createStatement()
      ) {
      // >>>>>> Preparando elementos buscados no SQL para execução <<<<<<
      /*
      * [!] .lines() retorena um Stream<string> que corresponde a uma linha de 
      * texto do BufferedReader, não incluindo EOF
      *
      * [!] .collect() é uma operação terminal da API Stream, usado para transformar
      * elementos de um stream em uma estrutura de dados diferente ou valor agregado,
      * recebendo um Collectors. Ou seja, Collectors.toList(ponto_juncao) transforma
      * Stream em Lista, Collectors.joining() junta um Stream<String> em uma única
      * String no ponto de junção colocado em seu parâmetro. Por exemplo, "\n" como ponto de
      * junção juntará todas as linhas que receberem um caracter de pula-linha
      * transformando-o tudo em uma string só.
      */
      String conteudo = reader.lines().collect(Collectors.joining("\n"));
      
      /*
      * [!] Use esse snipet mais para criar as tabelas, pois, ele está considerando
      * ";" como separador para atribuir nas instrucoes.
      * [!] .split separará a String em substrings partindo-as a cada ";". Como no
      * nosso caso o ";" indica o final de cada tabela, String[] instrução passa a
      * virar uma array de tabelas a criar
      *
      * [!] Em SQL real, um ponto e vírgula pode aparecer dentro de uma string
      * literal e não deve ser tratado como o final de uma instrução. Para um
      * tratamento correto de scripts SQL, podemos estudar, para outros projetos,
      * bibliotecas como o jOOQ ou o JSR 356.
      */
      String[] instrucoes = conteudo.split(";");

      for (String instrucao : instrucoes) {
          stmt.execute(instrucao);
      }
    } catch (SQLException e) {
<<<<<<< HEAD
      // Troca lista de texto de erro de exepção por final String.
      Misc.text(Misc.DATABASE_CONNECTION_ERROR);
    } catch (IOException e) {
      // Troca lista de texto de erro de exepção por final String.
      Misc.text(Misc.DATABASE_CONNECTION_ERROR);
=======
      Misc.text(Misc.DATABASE_CONNECTION_ERROR); // Troca lista de texto de erro de exceção por final String.
    } catch (IOException e) {
      Misc.text(Misc.DATABASE_CONNECTION_ERROR); // Troca lista de texto de erro de exceção por final String.
>>>>>>> 7fce2d865b68a8e09d0f0bf4f4b1dbbf3272d543
    }
  }
}