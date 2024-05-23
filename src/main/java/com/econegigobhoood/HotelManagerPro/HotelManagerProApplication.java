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
		
		DAOHospede daoHosp = new DAOHospede();
		CONPessoa<Hospede> conHosp = new CONPessoa<>(daoHosp);

		// Inicializando o banco de dados
		DBConfig.createTables();

		// Inicializando o menu principal
		// MainMenu.callMainMenu();



		/***********************************************
		 *** AREA DE TESTE (Pode apagar tudo depois) ***
		 ***********************************************/
		// TESTE FUNCIONARIO
		Funcionario func = new Funcionario("Agenor", "43223212312", "Vendedor");
		Funcionario funcBD = new Funcionario(2, "Agenorildson", "43223212312", "Vendedor");

		// CADASTRA
		// System.out.println(conFunc.cadastrar(func)); // Executa e printa
		// ATUALIZA
		// System.out.println(conFunc.atualizar(funcBD)); // Executa e printa
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

		// TESTE HOSPEDE
		Hospede hosp = new Hospede("Agenor", "43223212312", "Vendedor");
		Hospede hospBD = new Hospede(1, "Agenorildson", "43223212312", "Vendedor");

		// CADASTRA
		// System.out.println(conHosp.cadastrar(hosp)); // Executa e printa
		// ATUALIZA
		// System.out.println(conHosp.atualizar(hospBD)); // Executa e printa
		// EXCLUI
		// System.out.println(conHosp.excluir(1)); // Executa e printa
		// BUSCA
		// Hospede testeBusca = conHosp.buscar(1); // Executa
		// System.out.println(testeBusca.getNome()); // Printa
		// LISTA
		// List<Hospede> listaTesteBusca = conHosp.listar(); // Executa
		// System.out.println("Lista de Hospede: ");
        // for (Hospede hospede : listaTesteBusca) {
        //     System.out.println("ID: " + hospede.getId() + ", Nome: " + hospede.getNome());
        // };
	}
}
