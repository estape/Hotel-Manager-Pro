package com.econegigobhoood.HotelManagerPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.controller.CONPessoa;
import com.econegigobhoood.HotelManagerPro.model.Misc;
import com.econegigobhoood.HotelManagerPro.view.MainMenu;
import com.econegigobhoood.HotelManagerPro.model.dao.*;
import com.econegigobhoood.HotelManagerPro.model.entity.*;

import java.util.List;

@SpringBootApplication
public class HotelManagerProApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagerProApplication.class, args);
		
		// teste pra deixar um pouco diferente do padrão :D
		// Misc.colorBackground("blue"); 

		// Inicializando as dependências
		// PessoaView se necessario
		DAOFuncionario daoFunc = new DAOFuncionario();
		CONPessoa<Funcionario> conFunc = new CONPessoa<>(daoFunc);

		// Inicializando o banco de dados
		DBConfig.createTables();

		// Inicializando o menu principal
		// MainMenu.callMainMenu();



		/***********************************************
		 *** AREA DE TESTE (Pode apagar tudo depois) ***
		 ***********************************************/
		Funcionario funcTeste = new Funcionario("Agenor", "43223212312", "Vendedor");
		Funcionario funcTesteNoBanco = new Funcionario(2, "Agenorildson", "43223212312", "Vendedor");

		// CADASTRA
		// System.out.println(conFunc.cadastrar(funcTeste)); // Executa e printa
		// ATUALIZA
		// System.out.println(conFunc.atualizar(funcTesteNoBanco)); // Executa e printa
		// EXCLUI
		// System.out.println(conFunc.excluir(2)); // Executa e printa
		// BUSCA
		// Funcionario testeBusca = conFunc.buscar(1); // Executa
		// System.out.println(testeBusca.getNome()); // Printa
		// LISTA
		// List<Funcionario> listaTesteBusca = conFunc.listar(); // Executa
		// System.out.println("Lista de Funcionarios: ");
        // for (Funcionario func : listaTesteBusca) {
        //     System.out.println("ID: " + func.getId() + ", Nome: " + func.getNome());
        // }; 
	}
}
