package com.econegigobhoood.HotelManagerPro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.econegigobhoood.HotelManagerPro.config.DBConfig;
import com.econegigobhoood.HotelManagerPro.controller.*;
import com.econegigobhoood.HotelManagerPro.model.dao.*;
import com.econegigobhoood.HotelManagerPro.model.entity.*;
import com.econegigobhoood.HotelManagerPro.view.View;

import java.time.LocalDate;
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
		
		DAOQuarto daoQuarto = new DAOQuarto(daoTipoQua);
		ControlQuarto<Quarto> conQuarto = new ControlQuarto<>(daoQuarto);
		
		DAOStatus daoStatus = new DAOStatus();
		
		DAOPedido daoPedido = new DAOPedido(daoFunc, daoHosp);
		DAOReserva daoReserva = new DAOReserva(daoPedido, daoQuarto, daoStatus);
		Controller<Reserva> conRes = new Controller<>(daoReserva);
		daoPedido.setDaoReserva(daoReserva);
		ControlPedido<Pedido> conPedido = new ControlPedido<>(daoPedido);

		DAOContaAcesso daoContaAcesso = new DAOContaAcesso();
		
		// Inicializando o banco de dados
		DBConfig.createTables();

		// Inicialização da CLI
		Scanner scanner = new Scanner(System.in);
		LocalDate hoje = LocalDate.now();
		View view = new View(scanner, hoje, conFunc, conHosp, conTipoQua, conPedido,
							conQuarto, conRes, daoContaAcesso);

		// Inicialização do sistema
		view.iniciar();
	}
}
