package com.econegigobhoood.HotelManagerPro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.controller.ControlPessoa;
import com.econegigobhoood.HotelManagerPro.controller.Controller;
import com.econegigobhoood.HotelManagerPro.model.dao.*;
import com.econegigobhoood.HotelManagerPro.model.entity.*;
import com.econegigobhoood.HotelManagerPro.view.View;

import java.util.Scanner;

@SpringBootApplication
public class HotelManagerProApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagerProApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Inicializando as dependências
		DAOFuncionario daoFunc = new DAOFuncionario();
		ControlPessoa<Funcionario> conFunc = new ControlPessoa<>(daoFunc);
		
		DAOHospede daoHosp = new DAOHospede();
		ControlPessoa<Hospede> conHosp = new ControlPessoa<>(daoHosp);

		DAOTipoQuarto daoTipoQua = new DAOTipoQuarto();
		Controller<TipoQuarto> conTipoQua = new Controller<>(daoTipoQua);

		DAOPedido daoPedido = new DAOPedido();
		Controller<Pedido> conPedido = new Controller<>(daoPedido);

		// Inicializando o banco de dados
		DBConfig.createTables();

		// Inicialização da CLI
		Scanner scanner = new Scanner(System.in);
		View view = new View(scanner, conFunc, conHosp, conTipoQua, conPedido);

		// Inicialização do sistema
		view.iniciar();
	}
}
