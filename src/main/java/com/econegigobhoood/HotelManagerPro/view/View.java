package com.econegigobhoood.HotelManagerPro.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.econegigobhoood.HotelManagerPro.controller.ControlPessoa;
import com.econegigobhoood.HotelManagerPro.controller.Controller;
import com.econegigobhoood.HotelManagerPro.model.entity.*;

public class View {
    private Scanner scanner;
    private Funcionario funcionario;
    private ControlPessoa<Funcionario> conFunc;
    private ControlPessoa<Hospede> conHosp;
    private Controller<TipoQuarto> conTipoQua;
    private Controller<Pedido> conPedido;
    private Controller<Quarto> conQuarto;

    // Construtor
    public View(Scanner scanner, ControlPessoa<Funcionario> conFunc,
            ControlPessoa<Hospede> conHosp, Controller<TipoQuarto> conTipoQua,
            Controller<Pedido> conPedido, Controller<Quarto> conQuarto) {
        this.scanner = scanner;
        this.conFunc = conFunc;
        this.conHosp = conHosp;
        this.conTipoQua = conTipoQua;
        this.conPedido = conPedido;
        this.conQuarto = conQuarto;
    }

    // Métodos
    public void iniciar() {
        funcionario = acesso();
        mainMenu();
    }

    private void mainMenu() {
        cadastrarPedido(funcionario);
    }

    private void cadastrarPedido(Funcionario funcionario) {
        System.out.println("================================");
        System.out.println("         SESSÃO PEDIDO          ");
        System.out.println("================================");
        LocalDate hoje = LocalDate.now();
        Hospede hospede = buscarHospedeCPF();

        if (hospede == null) {
            mainMenu();
            return; // Saia do método cadastrarPedido
        }

        // TODO: Futuro
        /*
         * Caso protótipo aprovado, tratar pedido vazio/incompleto por interrupção:
         * - Possivel resolução: dar rollback caso não finalizar o processo de reserva
         */
        Pedido pedido = new Pedido(hoje, hospede, funcionario);
        int idPedido = conPedido.cadastrar(pedido);
        List<Reserva> reservas = new ArrayList<Reserva>();
        
        String opcao = "s";
        while (opcao.equals("s")) {
            Reserva reserva = cadastrarReserva(idPedido);
            reservas.add(reserva);
            System.out.println("Gostaria de fazer outra reserva? (s/n) ");
            opcao = scanner.nextLine();
        }

        System.out.println("Resumo do pedido: ");
        mostrarDetalhesPedido(pedido);

        System.out.println("Seu pedido está correto? (s/n) ");
        opcao = scanner.nextLine();
        if (opcao.equals("s")) {
            pedido.setReservas(reservas);
            conPedido.atualizar(pedido);
            System.out.println("Cadastro do Pedido finalizado com sucesso!");
            mainMenu();
            return; // Sai do método cadastrarPedido
        } else {
            conPedido.excluir(idPedido);
            System.out.println("Pedido retirado do Banco!");
            System.out.println("Operação cancelada!");
            mainMenu();
            return; // Sai do método cadastrarPedido
        }
    }

    private Hospede buscarHospedeCPF() {
        System.out.println("Digite o CPF do Hospede: ");
        String cpf = scanner.nextLine();
        Hospede hospede = conHosp.buscarCpf(cpf);

        while (hospede == null) {
            System.out.println("Hospede não encontrado!\nGostaria de:");
            System.out.println("1 - Cadastrar Hospede");
            System.out.println("2 - Inserir CPF novamente");
            System.out.println("3 - Cancelar operação");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch(opcao) {
                case 1 -> hospede = cadastraHospede();
                case 2 -> {
                    System.out.println("Digite o CPF do Hospede:");
                    cpf = scanner.nextLine();
                    hospede = conHosp.buscarCpf(cpf);
                }
                case 3 -> { 
                    System.out.println("Operação cancelada!");
                    return null;
                }
                default -> System.out.println("Opção invalida.");
            }
        }

        return hospede;
    }

    private Reserva cadastrarReserva(int idPedido) {
        System.out.println("================================");
        System.out.println("            RESERVA             ");
        System.out.println("================================");
        LocalDate dtEntrada = _getLocalDate("Entrada");
        LocalDate dtSaida = _getLocalDate("Saida");
        Quarto quarto = mostraQuartosDisponiveis();
        Pedido pedido = conPedido.buscar(idPedido);
        Reserva reserva = new Reserva (dtEntrada, dtSaida, pedido, quarto);

        return reserva;
    }

    private void buscarPedido () {
        System.out.println("Digite o ID do pedido a ser buscado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // coleta buffer
        Pedido pedido = conPedido.buscar(id);
        if (pedido != null) {
            mostrarDetalhesPedido(pedido);
        } else {
            System.out.println("Pedido não encontrado!");
        }
    }

    private void atualizarPedido() {
        System.out.println("Atualização de Hospede e Funcionario. ");
        System.out.println("Digite o ID do pedido a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // coleta buffer
        Pedido pedido = conPedido.buscar(id);

        if (pedido != null) {
            System.out.println("Corrigindo Hospede");
            Hospede hospede = buscarHospedeCPF();
            pedido.setHospede(hospede);
            System.out.println("Corrigindo Funcionario");
            Funcionario funcionario = buscarFuncionarioCPF();
            pedido.setFuncionario(funcionario);
            if (funcionario == null || hospede == null) {
                mainMenu();
                return; // Saia do método cadastrarPedido
            }
            String retorno = conPedido.atualizar(pedido);
            System.out.println(retorno);
        } else {
            System.out.println("Pedido não encontrado!");
        }
    }

    private void excluirPedido() {
        System.out.println("Digite o ID do pedido a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // limpa buffer
        Pedido pedido = conPedido.buscar(id);

        if (pedido == null) {
            System.out.println("Pedido não encontrado!");
            return;
        }

        String retorno = conPedido.excluir(id);
        System.out.println(retorno);
    }

    public void listarPedido() {
        // System.out.println("Como gostaria de listar?");
        // System.out.println("1 - Todos os pedidos");
        // System.out.println("2 - Todos os pedidos do Hospede");
        // System.out.println("3 - Todos os pedidos do Funcionario");
        // int opcao = scanner.nextInt();
        // scanner.nextLine(); // limpa buffer

        // switch(opcao) {
        //     case 1 -> {
        //         conPedido.listar();
        //     }
        //     case 2 -> {
        //         System.out.println("Corrigindo Hospede");
        //         Hospede hospede = buscarHospedeCPF();
        //         pedido.setHospede(hospede);
        //         conPedido.listar("porHospede", 1);
        //     }
        //     case 3 -> { 
        //         System.out.println("Corrigindo Hospede");
        //         Hospede hospede = buscarHospedeCPF();
        //         pedido.setHospede(hospede);
        //     }
        //     default -> System.out.println("Opção invalida.");
        // }

        System.out.println("=+=+=+= Lista de Pedidos =+=+=+=");
        List<Pedido> pedidos = conPedido.listar();
        for (Pedido pedido : pedidos) {
            System.out.println(">>> Pedido ID" + pedido.getId() + " <<<");
            System.out.println("Data pedido: " + pedido.getDtPedido());
            System.out.println("Hospede: " + pedido.getHospede().getNome());
            System.out.println("Valor total: R$" + pedido.getVlTotalPedido());
            System.out.println("--------------------");
        }
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
    }

    private Quarto mostraQuartosDisponiveis() {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    private LocalDate _getLocalDate(String titulo) {
        System.out.println("Qual a data de " + titulo + "?");
        System.out.println("[" + titulo + "]" + " dia: ");
        int dia = scanner.nextInt();
        System.out.println("[" + titulo + "]" + " mes (numeral): ");
        int mes = scanner.nextInt();
        System.out.println("[" + titulo + "]" + " ano (4 numerais): ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Limpa buffer
        LocalDate data = LocalDate.of(ano, mes, dia);
        return data;
    }

    private Hospede cadastraHospede() {
        System.out.println("===================================");
        System.out.println("         CADASTRA HOSPEDE          ");
        System.out.println("===================================");
        System.out.println("Digite o nome do Hospede: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o cpf do Hospede: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite o número de telefone do Hospede: ");
        String telefone = scanner.nextLine();

        Hospede hospede = new Hospede(nome, cpf, telefone);
        int idHospede = conHosp.cadastrar(hospede);
        hospede = conHosp.buscar(idHospede);

        return hospede;
    }

    private Funcionario acesso() {
        System.out.println("================================");
        System.out.println("         TELA DE LOGIN          ");
        System.out.println("================================");
                
        System.out.println("Digite seu login");
        Funcionario funcionario = buscarFuncionarioCPF();
        mostrarLogo();
        System.out.println("Bem vindo(a) " + funcionario.getNome() + "!");
       
        return funcionario;
    }

    private Funcionario buscarFuncionarioCPF() {
        System.out.println("CPF do Funcionario: ");
        String cpfFunc = scanner.nextLine();
        Funcionario funcionario = conFunc.buscarCpf(cpfFunc);

        while (funcionario == null) {
            System.out.println("Usuário não encontrado!");
            System.out.println("CPF do Funcionario: ");
            cpfFunc = scanner.nextLine();
            funcionario = conFunc.buscarCpf(cpfFunc);
        }

        return funcionario;
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

    private void mostrarDetalhesPedido(Pedido pedido) {
        System.out.println(">>> Detalhes do pedido <<<");
        System.out.println("ID " + pedido.getId());
        System.out.println("Hospede: " + pedido.getHospede());
        System.out.println("Data do pedido: " + pedido.getDtPedido());
        System.out.println("Valor total do pedido: " + pedido.getVlTotalPedido());
        System.out.println("Funcionario que realizou: " + pedido.getFuncionario());

        System.out.println("=== Lista de reservas do pedido ===");
        List<Reserva> reservas = pedido.getReservas();
        for (Reserva reserva : reservas) {
            mostraDetalhesReserva(reserva);
        }
    }

    private void mostraDetalhesReserva(Reserva reserva) {
        // TODO
    }
}