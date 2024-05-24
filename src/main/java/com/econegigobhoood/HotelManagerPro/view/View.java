package com.econegigobhoood.HotelManagerPro.view;

import java.util.List;
import java.util.Scanner;

import com.econegigobhoood.HotelManagerPro.controller.ControlPessoa;
import com.econegigobhoood.HotelManagerPro.controller.Controller;
import com.econegigobhoood.HotelManagerPro.model.entity.*;

public class View {
    private Scanner scanner;
    private ControlPessoa<Funcionario> conFunc;
    private ControlPessoa<Hospede> conHosp;
    private Controller<TipoQuarto> conTipoQua;

    // Construtor
    public View(Scanner scanner, ControlPessoa<Funcionario> conFunc,
                ControlPessoa<Hospede> conHosp, Controller<TipoQuarto> conTipoQua) {
        this.scanner = scanner;
        this.conFunc = conFunc;
        this.conHosp = conHosp;
        this.conTipoQua = conTipoQua;
    }

    // Métodos
    public void iniciar () {
        acesso();
        // >>> MOSTRA MENU

        // SELECIONA > Fazer pedido
        /*
        * data = now()
        * PRINT(Qual cliente? (CPF))
        * cpf = cpf
        * buscaHospede(cpf) existe ? 
        * - SIM> Hospede hospede = buscaHospede(cpf)
        * - NAO> PRINT(hospede n encontrado, cadastra?)
        * -- SIM> pergunta todos os valores, menos o CPF pois já foi lançado
        * --- cadastraHospede(valores, valores, cpf)
        * --- Hospede hospede = buscaHospede(cpf)
        * -- NAO> Volta menu
        * 
        * Pedido pedido = new Pedido(data, hospede, funcionario)
        * Lista<Reserva> reservas = new List<>
        * 
        * PRINT(RESERVA:)
        * private LocalDate getLocalDate(String titulo) {
        *      PRINT(Qual a data de entrada:)
        *      PRINT([titulo] dia: )
        *      dia = dia
        *      PRINT([titulo] mes (numeral): )
        *      mes = mes
        *      PRINT([titulo] ano (4 numerais): )
        *      ano = ano
        *      LocalDate dtEntrada = LocalDate.of(ano, mes, dia)
        * }
        * 
        * LocalDate dtEntrada = getLocalDate("Entrada");
        * LocalDate dtSaida = getLocalDate("Saida");
        * 
        * MOSTRAQUARTOSDISPONIVEIS() consulta data entre reservas feitas
        * 
        * // Buscado idPedido aqui, ao invés de lá em cima e deixar mais enxuto o codigo, para reduzir a chance de criar pedido vazio (sem reserva cadastrada)
        * idPedido = cadastraPedido(pedido)
        * Reserva reserva = new Reserva (dtEntrada, dtSaida, idPedido, quarto)
        * 
        * String resp = "s"
        * while (resp.equals("s")) {
        *      PRINT(NOVA RESERVA:)
        *      LocalDate dtEntrada = getLocalDate("Entrada");
        *      LocalDate dtSaida = getLocalDate("Saida");
        *      Reserva reserva = new Reserva (dtEntrada, dtSaida, idPedido, quarto);
        * 
        *      MOSTRAQUARTOSDISPONIVEIS() consulta data entre reservas feitas
        * 
        *      reservas.add(reserva)
        *      PRINT("Quer fazer outra reserva?")
        * }
        * 
        * ACOMPANHANTE FICA EM PEDIDOS
        * 
        */
    }

    
    private void acesso() {
        System.out.println("================================");
        System.out.println("         TELA DE LOGIN          ");
        System.out.println("================================");
        
        Funcionario funcionario = null;
        
        System.out.println("Digite seu login (CPF): ");
        String cpfFunc = scanner.nextLine();
        funcionario = conFunc.buscarCpf(cpfFunc);
        while (funcionario == null) {
            System.out.println("Usuário não encontrado!");
            System.out.println("Digite seu login (CPF): ");
            funcionario = conFunc.buscarCpf(scanner.nextLine());
        }
        mostrarLogo();
        System.out.println("Bem vindo(a) " + funcionario.getNome() + "!");
    }

    private void mostrarLogo() {
        System.out.println("======================================================================================================");
        System.out.println("|  _   _  ___ _____ _____ _       __  __    _    _   _    _    ____ _____ ____    ____  ____   ___   |\r\n" + //
                           "| | | | |/ _ \\_   _| ____| |     |  \\/  |  / \\  | \\ | |  / \\  / ___| ____|  _ \\  |  _ \\|  _ \\ / _ \\  |\r\n" + //
                           "| | |_| | | | || | |  _| | |     | |\\/| | / _ \\ |  \\| | / _ \\| |  _|  _| | |_) | | |_) | |_) | | | | |\r\n" + //
                           "| |  _  | |_| || | | |___| |___  | |  | |/ ___ \\| |\\  |/ ___ \\ |_| | |___|  _ <  |  __/|  _ <| |_| | |\r\n" + //
                           "| |_| |_|\\___/ |_| |_____|_____| |_|  |_/_/   \\_\\_| \\_/_/   \\_\\____|_____|_| \\_\\ |_|   |_| \\_\\\\___/  |\r\n" + //
                           "|                                                                                                    |");
        System.out.println("======================================================================================================");
        System.out.println("|          Sistema de Reserva de Hotel - Trabalho final do 3º semestre de ADS (POO e BD)             |");
        System.out.println("======================================================================================================\n");
    }
}
