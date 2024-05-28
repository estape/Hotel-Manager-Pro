package com.econegigobhoood.HotelManagerPro.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.econegigobhoood.HotelManagerPro.controller.*;
import com.econegigobhoood.HotelManagerPro.model.Misc;
import com.econegigobhoood.HotelManagerPro.model.abs.Tipo;
import com.econegigobhoood.HotelManagerPro.model.dao.DAOContaAcesso;
import com.econegigobhoood.HotelManagerPro.model.entity.*;

public class View {
    private Scanner scanner;
    private LocalDate hoje;
    private Funcionario systemFuncionario;
    private ControlPessoa<Funcionario> conFunc;
    private ControlPessoa<Hospede> conHosp;
    private Controller<TipoQuarto> conTipoQua;
    private ControlPedido<Pedido> conPedido;
    private ControlQuarto<Quarto> conQuarto;
    private Controller<Reserva> conRes;
    private DAOContaAcesso daoContaAcesso;

    // Construtor
    public View(Scanner scanner, LocalDate hoje, ControlPessoa<Funcionario> conFunc,
            ControlPessoa<Hospede> conHosp, Controller<TipoQuarto> conTipoQua,
            ControlPedido<Pedido> conPedido, ControlQuarto<Quarto> conQuarto,
            Controller<Reserva> conRes, DAOContaAcesso daoContaAcesso) {
        this.scanner = scanner;
        this.hoje = hoje;
        this.conFunc = conFunc;
        this.conHosp = conHosp;
        this.conTipoQua = conTipoQua;
        this.conPedido = conPedido;
        this.conQuarto = conQuarto;
        this.conRes = conRes;
        this.daoContaAcesso = daoContaAcesso;
    }

    // TODO ********************* METODO INICIALIZADOR ********************
    public void iniciar() {
        acesso();
        avisoAcesso();
        callMainMenu();
    }

    public ArrayList<String> listOptions = new ArrayList<String>();

    // TODO******************** MÉTODOS DE MENU *******************
    public void callMainMenu() {
        String op = "";
        listOptions.clear();
        listOptions.add("1 - Pedidos e reservas");
        listOptions.add("2 - Registrar Check-in");
        listOptions.add("3 - Gestão de Pessoas");
        listOptions.add("4 - Gestão de Quartos");
        listOptions.add("! - Sair do sistema");
        op = baseMsg("MENU PRINCIPAL", listOptions);

        switch (op) {
            case "1"-> {
                Misc.clearScreen();
                pedidosMenu();
                return;
            }
            case "2"-> {
                Misc.clearScreen();
                atualizarReserva();
                callMainMenu();
                return;
            }
            case "3"-> {
                Misc.clearScreen();
                pessoaMenu();
                return;
            }
            case "4"-> {
                Misc.clearScreen();
                quartoMenu();
                return;
            }
            case "!"-> {
                Misc.colorBackground("black");
                Misc.text("Sistema fechado, até logo!\n\n");
                System.exit(0);
            }
            case "AJUDAR"-> {
                Misc.clearScreen();
                helpMenu();
                return;
            }
            default-> {
                msgDefault(3);
                callMainMenu();
                return;
            }
        }
    }

    public void pedidosMenu() {
        String op = "";
        listOptions.clear();
        listOptions.add("========= PEDIDOS ========");
        listOptions.add("1 - Realizar pedido");
        listOptions.add("2 - Consultar pedidos");
        listOptions.add("3 - Atualizar pedidos");
        listOptions.add("4 - Listar pedidos");
        listOptions.add("5 - Excluir pedidos");
        listOptions.add("========= RESERVAS ========");
        listOptions.add("6 - Consultar reserva");
        listOptions.add("7 - Registrar Check-in");
        listOptions.add("8 - Listar reservas");
        listOptions.add("9 - Excluir reservas");
        listOptions.add("0 - Voltar ao menu principal");
        op = baseMsg("MENU PEDIDOS/RESERVAS", listOptions);

        switch (op) {
            case "1" -> {
                cadastrarPedido();
                pedidosMenu();
                return;
            }
            case "2" -> {
                buscarPedido();
                pedidosMenu();
                return;
            }
            case "3" -> {
                atualizarPedido();
                pedidosMenu();
                return;
            }
            case "4" -> {
                listarPedido();
                pedidosMenu();
                return;
            }
            case "5" -> {
                excluirPedido();
                pedidosMenu();
                return;
            }
            case "6" -> {
                buscarReserva();
                pedidosMenu();
                return;
            }
            case "7" -> {
                atualizarReserva();
                pedidosMenu();
                return;
            }
            case "8" -> {
                listarReserva();
                pedidosMenu();
                return;
            }
            case "9" -> {
                excluirReserva();
                pedidosMenu();
                return;
            }
            case "0" -> {
                Misc.clearScreen();
                callMainMenu();
            }
            default -> {
                msgDefault(3);
                pedidosMenu();
                return;
            }
        }
    }

    public void pessoaMenu() {
        String op = "";
        listOptions.clear();
        listOptions.add("============ HOSPEDES =============");
        listOptions.add("1 - Listar hospedes");
        listOptions.add("2 - Cadastrar hospede");
        listOptions.add("3 - Atualizar hospede");
        listOptions.add("4 - Excluir hospede");
        listOptions.add("=========== FUNCIONARIOS =============");
        listOptions.add("5 - Listar funcionarios");
        listOptions.add("6 - Cadastrar funcionario");
        listOptions.add("7 - Atualizar funcionario");
        listOptions.add("8 - Excluir funcionario");
        listOptions.add("0 - Voltar ao menu principal");
        op = baseMsg("MENU PESSOAS", listOptions);

        switch (op) {
            case "1" -> {
                listarHospede();
                pessoaMenu();
                return;
            }
            case "2" -> {
                cadastrarHospede();
                pessoaMenu();
                return;
            }
            case "3" -> {
                atualizarHospede();
                pessoaMenu();
                return;
            }
            case "4" -> {
                excluirHospede();
                pessoaMenu();
                return;
            }
            case "5" -> {
                listarFuncionario();
                pessoaMenu();
                return;
            }
            case "6" -> {
                cadastrarFuncionario();
                pessoaMenu();
                return;
            }
            case "7" -> {
                atualizarFuncionario();
                pessoaMenu();
                return;
            }
            case "8" -> {
                excluirFuncionario();
                pessoaMenu();
                return;
            }
            case "0" -> {
                Misc.clearScreen();
                callMainMenu();
            }
            default -> {
                msgDefault(3);
                pessoaMenu();
                return;
            }
        }
    }

    public void quartoMenu() {
        String op = "";
        listOptions.clear();
        listOptions.add("============== TIPO =============");
        listOptions.add("1 - Listar tipo de quarto");
        listOptions.add("2 - Cadastrar tipo de quarto");
        listOptions.add("3 - Atualizar tipo de quarto");
        listOptions.add("4 - Excluir tipo de quarto");
        listOptions.add("============ QUARTOS ============");
        listOptions.add("5 - Listar todos os quartos");
        listOptions.add("6 - Cadastrar quarto");
        listOptions.add("7 - Atualizar quarto");
        listOptions.add("8 - Excluir quarto");
        listOptions.add("0 - Voltar ao menu principal");
        op = baseMsg("MENU QUARTO", listOptions);

        switch (op) {
            case "1" -> {
                listarTipo();
                quartoMenu();
                return;
            }
            case "2" -> {
                cadastrarTipo();
                quartoMenu();
                return;
            }
            case "3" -> {
                atualizarTipo();
                quartoMenu();
                return;
            }
            case "4" -> {
                excluirTipo();
                quartoMenu();
                return;
            }
            case "5" -> {
                listarQuarto();
                quartoMenu();
                return;
            }
            case "6" -> {
                cadastrarQuarto();
                quartoMenu();
                return;
            }
            case "7" -> {
                atualizarQuarto();
                quartoMenu();
                return;
            }
            case "8" -> {
                excluirQuarto();
                quartoMenu();
                return;
            }
            case "0" -> { 
                Misc.clearScreen();
                callMainMenu();
            }
            default -> {
                msgDefault(3);
                quartoMenu();
                return;
            }
        }
    }

    // TODO******************** MÉTODOS DE HOSPEDE *******************

    private Hospede cadastrarHospede() {
        Misc.clearScreen();
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

    private Hospede buscarHospedeCPF() {
        System.out.println("Digite o CPF do Hospede: ");
        String cpf = scanner.nextLine();
        Hospede hospede = conHosp.buscarCpf(cpf);

        while (hospede == null) {
            Misc.clearScreen();
            System.out.println("\nHospede não encontrado!\nGostaria de:");
            System.out.println("1 - Cadastrar Hospede");
            System.out.println("2 - Inserir CPF novamente");
            System.out.println("3 - Cancelar operação");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch(opcao) {
                case 1 -> hospede = cadastrarHospede();
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
        System.out.println("Hospede selecionado: " + hospede.getNome());
        return hospede;
    }

    private void atualizarHospede() {
        Misc.clearScreen();
        System.out.println("=================================");
        System.out.println("        ATUALIZAR HOSPEDE        ");
        System.out.println("=================================");
        System.out.println("Digite o ID do Hospede a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        Hospede hospede = conHosp.buscar(id);

        if (hospede != null) {
            System.out.println("Digite o novo nome do Hospede: ");
            String nome = scanner.nextLine();
            hospede.setNome(nome);
            System.out.println("Digite o CPF do Hospede: ");
            String cpf = scanner.nextLine();
            hospede.setCpf(cpf);
            System.out.println("Digite o Cargo do Hospede");
            String telefone = scanner.nextLine();
            hospede.setTelefone(telefone);
            String retorno = conHosp.atualizar(hospede);
            System.out.println(retorno);
        } else {
            System.out.println("Id não encontrado!");
        }
    }

    private void listarHospede() {
        System.out.println("\n=+=+=+=+= Lista de Hospede =+=+=+=+=");
        List<Hospede> hospedes = conHosp.listar();
        if (!hospedes.isEmpty()) {
            for (Hospede hospede : hospedes) {
                System.out.println(">>> Hospede ID: " + hospede.getId() + " <<<");
                System.out.println("Nome: " + hospede.getNome());
                System.out.println("CPF: " + hospede.getCpf());
                System.out.println("Cargo: " + hospede.getTelefone());
                System.out.println("--------------------------------------");
            }
        } else {
            System.out.println("Lista está vazia!");
        }
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=\n");
    }

    private void excluirHospede() {
        Misc.clearScreen();
        System.out.println("===============================");
        System.out.println("        EXCLUIR HOSPEDE        ");
        System.out.println("===============================");
        System.out.println("Digite o ID do Hospede a ser excluido: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        String retorno = conHosp.excluir(id);
        System.out.println(retorno);
    }

    // TODO******************** MÉTODOS DE RESERVA *******************

    private void cadastrarReserva(int idPedido) {
        System.out.println("================================");
        System.out.println("            RESERVA             ");
        System.out.println("================================");
        LocalDate dtEntrada = _getLocalDate("Entrada");
        LocalDate dtSaida = _getLocalDate("Saida");
        List<Integer> quartosDisp = listaQuartosDisponiveis(dtEntrada, dtSaida);
        if (quartosDisp.isEmpty()) {
            return;
        }
        System.out.print("Escolha o número do quarto desejado: ");
        int numQuarto = scanner.nextInt();
        scanner.nextLine();
        Quarto quarto = conQuarto.buscarNum(numQuarto);
        if (quartosDisp.contains(numQuarto)) {
            System.out.println("Você selecionou o quarto número " + numQuarto);
        } else {
            System.out.println("O quarto número " + numQuarto + " está ocupado.");
            pedidosMenu();
            return;
        }
        Pedido pedido = conPedido.buscarInicial(idPedido);
        Reserva reserva = new Reserva (dtEntrada, dtSaida, pedido, quarto);
        conRes.cadastrar(reserva);
        daoContaAcesso.reservaLog(this.systemFuncionario.getNome(), this.hoje);
    }

    private void buscarReserva() {
        Misc.clearScreen();
        System.out.println("Digite o ID da Reserva a ser buscada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // coleta buffer
        Reserva reserva = conRes.buscar(id);
        if (reserva != null) {
            Misc.clearScreen();
            mostraDetalhesReserva(reserva);
            pedidosMenu();
            return;
        } else {
            System.out.println("Pedido não encontrado!");
            pedidosMenu();
            return;
        }
    }

    public void listarReserva() {
        Misc.clearScreen();
        System.out.println("\n=+=+=+= Lista de Reservas =+=+=+=");
        List<Reserva> reservas = conRes.listar();
        for (Reserva reserva : reservas) {
            mostraDetalhesReserva(reserva);
            System.out.println("--------------------------------------");
        }
        System.out.println("\n=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
    }

    private void atualizarReserva() {
        Misc.clearScreen();;
        System.out.println("Digite o ID da reserva a ser atualizado (Check-in): ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        Reserva reserva = conRes.buscar(id);

        if (reserva != null) {
            System.out.println("- Reserva # " + reserva.getId());
            System.out.println("- Quarto # " + reserva.getQuarto().getNumQuarto());
            System.out.println("Check-in realizado com sucesso!");
            String retorno = conRes.atualizar(reserva);
            System.out.println(retorno);
        } else {
            System.out.println("Id não encontrado!");
        }
    }

    private void excluirReserva() {
        Misc.clearScreen();
        System.out.println("Digite o ID da reserva a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // limpa buffer
        Reserva reserva = conRes.buscar(id);

        if (reserva == null) {
            System.out.println("Pedido não encontrado!");
            pedidosMenu();
            return;
        }

        String retorno = conRes.excluir(id);
        System.out.println(retorno);
    }

    private void mostraDetalhesReserva(Reserva reserva) {
        System.out.println(">>> Reserva do ID # " + reserva.getId() +
                           " do pedido # " + reserva.getPedido().getId() + " <<<");
        System.out.println("Quarto reservado: " + reserva.getQuarto().getNumQuarto());
        System.out.println("Status: " + reserva.getStatus().getNome());
        System.out.println("Data de entrada: " + reserva.getDtEntrada());
        System.out.println("Data de saída: " + reserva.getDtSaida());
        System.out.println("Valor da reserva: R$" + reserva.getVlReserva());
        System.out.println("----------------------------------------");
    }

    // TODO******************** MÉTODOS DE PEDIDO *******************

    private void cadastrarPedido() {
        Misc.clearScreen();
        avisoAcesso();
        Hospede hospede = buscarHospedeCPF();

        if (hospede == null) {
            pedidosMenu();
            return; // Saia do método cadastrarPedido
        }
        System.out.println("================================");
        System.out.println("        REALIZAR PEDIDO         ");
        System.out.println("================================");
        // TODO: Futuro
        /*
         * Caso protótipo aprovado, tratar pedido vazio/incompleto por interrupção:
         * - Possivel resolução: dar rollback caso não finalizar o processo de reserva
         */
        Pedido pedido = new Pedido(this.hoje, hospede, this.systemFuncionario);
        int idPedido = conPedido.cadastrar(pedido);
        String opcao = "s";

        while (opcao.equals("s")) {
            cadastrarReserva(idPedido);
            System.out.println("Gostaria de fazer outra reserva? (s/n) ");
            opcao = scanner.nextLine();
        }

        System.out.println("Resumo do pedido: ");
        pedido = conPedido.buscarCompleto(idPedido);
        mostrarDetalhesPedido(pedido);

        System.out.println("Seu pedido está correto? (s/n) ");
        opcao = scanner.nextLine();
        if (opcao.equals("s")) {
            System.out.println("Cadastro do Pedido finalizado com sucesso!");
            pedidosMenu();
            return; // Sai do método cadastrarPedido
        } else {
            conPedido.excluir(idPedido);
            System.out.println("Pedido retirado do Banco!");
            System.out.println("Operação cancelada!");
            pedidosMenu();
            return; // Sai do método cadastrarPedido
        }
    }

    private void buscarPedido () {
        Misc.clearScreen();
        System.out.println("Digite o ID do pedido a ser buscado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // coleta buffer
        Pedido pedido = conPedido.buscarCompleto(id);
        if (pedido != null) {
            Misc.clearScreen();
            mostrarDetalhesPedido(pedido);
            pedidosMenu();
            return;
        } else {
            System.out.println("Pedido não encontrado!");
            pedidosMenu();
            return;
        }
    }

    private void atualizarPedido() {
        System.out.println("Atualização de Hospede e Funcionario. ");
        System.out.println("Digite o ID do pedido a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // coleta buffer
        Pedido pedido = conPedido.buscar(id);

        if (pedido != null) {
            Misc.clearScreen();
            System.out.println("Corrigindo Hospede");
            Hospede hospede = buscarHospedeCPF();
            pedido.setHospede(hospede);
            System.out.println("\nCorrigindo Funcionario");
            Funcionario funcionario = buscarFuncionarioCPF();
            System.out.println("Funcionario selecionado: " + funcionario.getNome());
            pedido.setFuncionario(funcionario);
            if (funcionario == null || hospede == null) {
                pedidosMenu();
                return; // Saia do método cadastrarPedido
            }
            String retorno = conPedido.atualizar(pedido);
            System.out.println(retorno);
            pedidosMenu();
            return;
        } else {
            System.out.println("Pedido não encontrado!");
            pedidosMenu();
            return;
        }
    }

    private void excluirPedido() {
        Misc.clearScreen();
        System.out.println("Digite o ID do pedido a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // limpa buffer
        Pedido pedido = conPedido.buscar(id);

        if (pedido == null) {
            System.out.println("Pedido não encontrado!");
            pedidosMenu();
            return;
        }

        String retorno = conPedido.excluir(id);
        System.out.println(retorno);
        pedidosMenu();
    }

    public void listarPedido() {
        Misc.clearScreen();
        System.out.println("\n=+=+=+= Lista de Pedidos =+=+=+=");
        List<Pedido> pedidos = conPedido.listar();
        for (Pedido pedido : pedidos) {
            System.out.println(">>> Pedido ID: " + pedido.getId() + " <<<");
            System.out.println("Data pedido: " + pedido.getDtPedido());
            System.out.println("Hospede: " + pedido.getHospede().getNome());
            System.out.println("Valor total: R$" + pedido.getVlTotalPedido());
            System.out.println("--------------------------------------");
        }
        System.out.println("\n=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
    }

    private void mostrarDetalhesPedido(Pedido pedido) {
        System.out.println("=+=+=+= Detalhes do Pedido ID: " + pedido.getId() + " =+=+=+=");
        System.out.println("Hospede: " + pedido.getHospede().getNome());
        System.out.println("Data do pedido: " + pedido.getDtPedido());
        System.out.println("Valor total do pedido: " + pedido.getVlTotalPedido());
        System.out.println("Funcionario que realizou: " + pedido.getFuncionario().getNome());
        
        System.out.println("\n=== Lista de reservas do pedido ===");
        List<Reserva> reservas = pedido.getReservas();
        for (Reserva reserva : reservas) {
            mostraDetalhesReserva(reserva);
        }
    }

    // TODO******************** MÉTODOS DE QUARTO *******************

    private void cadastrarQuarto() {
        Misc.clearScreen();
        System.out.println("==================================");
        System.out.println("         CADASTRA QUARTO          ");
        System.out.println("==================================");
        System.out.println("Digite o numero do Quarto: ");
        int numero = scanner.nextInt();
        System.out.println("Quantidade camas de solteiro: ");
        int qtdCamaSolt = scanner.nextInt();
        System.out.println("Quantidade camas de casado: ");
        int qtdCamaCas = scanner.nextInt();
        System.out.println("Quantidade banheiro: ");
        int qtdBanho = scanner.nextInt();
        scanner.nextLine(); // limpa buffer
        System.out.println("Informações adicionais (opcional): ");
        String infoAdc = scanner.nextLine();
        // Mostra tipos
        listarTipo();
        // Pede tipo
        TipoQuarto tipo = buscarTipo();

        Quarto quarto = new Quarto(numero, qtdCamaSolt, qtdCamaCas, qtdBanho,
                 infoAdc, tipo);
        conQuarto.cadastrar(quarto);
    }

    private void listarQuarto() {
        System.out.println("\n=+=+=+=+= Lista de Quartos =+=+=+=+=");
        List<Quarto> quartos = conQuarto.listar();
        if (!quartos.isEmpty()) {
            for (Quarto quarto : quartos) {
                System.out.println(">>> Quarto numero: " + quarto.getNumQuarto() + " <<<");
                System.out.println("Cama solteiro: " + quarto.getQtdCamaSolt());
                System.out.println("Cama casal: " + quarto.getQtdCamaCas());
                System.out.println("Banheiro: " + quarto.getQtdBanho());
                System.out.println("Informação adicional: " + quarto.getInfoAdc());
                System.out.println("Tipo do quarto: " + quarto.getTipo().getNome());
                System.out.println("Valor do quarto: R$" + quarto.getVlQuarto());
                System.out.println("--------------------------------------");
            }
        } else {
            System.out.println("Lista está vazia!");
        }
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=\n");
    }

    private void atualizarQuarto() {
        Misc.clearScreen();
        System.out.println("=================================");
        System.out.println("        ATUALIZAR QUARTO         ");
        System.out.println("=================================");
        System.out.println("Digite o NUMERO do quarto a ser atualizado: ");
        int num = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        Quarto quarto = conQuarto.buscarNum(num);

        if (quarto != null) {
            System.out.println("Quantidade de cama de solteiro: ");
            int camaSolt = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            quarto.setQtdCamaSolt(camaSolt);
            System.out.println("Quantidade de cama de casal: ");
            int camaCas = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            quarto.setQtdCamaCas(camaCas);
            System.out.println("Quantidade de banheiros: ");
            int banheiro = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer
            quarto.setQtdBanho(banheiro);
            System.out.println("Novas informações adicionais: ");
            String infoAdc = scanner.nextLine();
            quarto.setInfoAdc(infoAdc);
            // Busca tipo
            listarTipo();
            // Pede tipo
            TipoQuarto tipo = buscarTipo();
            // Set tipo
            quarto.setTipo(tipo);

            String retorno = conQuarto.atualizar(quarto);
            System.out.println(retorno);
        } else {
            System.out.println("Numero não encontrado!");
        }
    }

    private void excluirQuarto() {
        System.out.println("Digite o NUMERO do quarto a ser excluído: ");
        int num = scanner.nextInt();
        scanner.nextLine();
        Quarto quarto = conQuarto.buscarNum(num);
        if (quarto == null) {
            System.out.println("Quarto não encontrado!");
            return;
        }

        String retorno = conQuarto.excluir(num);
        System.out.println(retorno);
    }

    private List<Integer> listaQuartosDisponiveis(LocalDate dtEntrada, 
            LocalDate dtSaida) {
        System.out.println("\n=+=+=+=+= Quartos Disponiveis =+=+=+=+=");
        List<Quarto> quartos = conQuarto.listarQuartoDisp(dtEntrada, dtSaida);
        List<Integer> numQuartosDisp = new ArrayList<>();
        if (!quartos.isEmpty()) {
            for (Quarto quarto : quartos) {
                System.out.println(">>> Quarto numero: " + quarto.getNumQuarto() + " <<<");
                numQuartosDisp.add(quarto.getNumQuarto());
                System.out.println("Cama solteiro: " + quarto.getQtdCamaSolt());
                System.out.println("Cama casal: " + quarto.getQtdCamaCas());
                System.out.println("Banheiro: " + quarto.getQtdBanho());
                System.out.println("Informação adicional: " + quarto.getInfoAdc());
                System.out.println("Tipo do quarto: " + quarto.getTipo().getNome());
                System.out.println("Valor do quarto: R$" + quarto.getVlQuarto());
                System.out.println("--------------------------------------");
            }
        } else {
            System.out.println("Nenhum quarto disponivel para a data!");
            return null;
        }
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=\n");
        return numQuartosDisp;
    }

    // TODO******************** MÉTODOS DE TIPO *******************
    private TipoQuarto buscarTipo() {
        System.out.println("==============================");
        System.out.println("         BUSCAR TIPO          ");
        System.out.println("==============================");
        System.out.println("Digite o ID do Tipo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // limpa buffer
        TipoQuarto tipo = conTipoQua.buscar(id);

        while (tipo == null) {
            Misc.clearScreen();
            System.out.println("\nTipo não encontrado!\nGostaria de:");
            System.out.println("1 - Cadastrar Tipo");
            System.out.println("2 - Inserir ID novamente");
            System.out.println("3 - Cancelar operação");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch(opcao) {
                case 1 -> tipo = cadastrarTipo();
                case 2 -> {
                    System.out.println("Digite o ID do Tipo: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // limpa buffer
                    tipo = conTipoQua.buscar(id);
                }
                case 3 -> { 
                    System.out.println("Operação cancelada!");
                    return null;
                }
                default -> System.out.println("Opção invalida.");
            }
        }
        return tipo;
    }

    private void listarTipo() {
        System.out.println("\n=+=+=+=+= Lista de Tipos =+=+=+=+=");
        List<TipoQuarto> tipos = conTipoQua.listar();
        if (!tipos.isEmpty()) {
            for (TipoQuarto tipo : tipos) {
                System.out.println(">>> Tipo ID: " + tipo.getId() + " <<<");
                System.out.println("Nome: " + tipo.getNome());
                System.out.println("Descricao: " + tipo.getDesc());
                System.out.println("Valor do tipo: R$" + tipo.getValor());
                System.out.println("--------------------------------------");
            }
        } else {
            System.out.println("Lista está vazia!");
        }
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=\n");
    }

    private TipoQuarto cadastrarTipo() {
        Misc.clearScreen();
        System.out.println("================================");
        System.out.println("         CADASTRA TIPO          ");
        System.out.println("================================");
        System.out.println("Digite o nome do Tipo: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o descricao do Tipo: ");
        String descricao = scanner.nextLine();
        System.out.println("Digite o valor do Tipo: R$");
        Double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer

        TipoQuarto tipo = new TipoQuarto(nome, descricao, valor);
        int idTipo = conTipoQua.cadastrar(tipo);
        tipo = conTipoQua.buscar(idTipo);

        return tipo;
    }

    private void atualizarTipo() {
        Misc.clearScreen();
        System.out.println("=================================");
        System.out.println("         ATUALIZAR TIPO          ");
        System.out.println("=================================");
        System.out.println("Digite o ID do Tipo a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        TipoQuarto tipo = conTipoQua.buscar(id);
        mostrarDetalhesTipo(tipo);

        if (tipo != null) {
            System.out.println("Digite o novo nome do tipo: ");
            String nome = scanner.nextLine();
            tipo.setNome(nome);
            System.out.println("Digite a nova descricao do tipo: ");
            String descricao = scanner.nextLine();
            tipo.setDesc(descricao);
            System.out.println("Digite o novo valor unitário do tipo: R$");
            double valor = scanner.nextDouble();
            scanner.nextLine();
            tipo.setValor(valor);
            String retorno = conTipoQua.atualizar(tipo);
            System.out.println(retorno);
        } else {
            System.out.println("Id não encontrado!");
        }
    }

    private void excluirTipo() {
        Misc.clearScreen();
        System.out.println("===============================");
        System.out.println("         EXCLUIR TIPO          ");
        System.out.println("===============================");
        System.out.println("Digite o nome do ID do Tipo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        String retorno = conTipoQua.excluir(id);
        System.out.println(retorno);
    }

    private void mostrarDetalhesTipo(Tipo tipo) {
        System.out.println(">>> Detalhes do tipo <<<");
        System.out.println("ID " + tipo.getId());
        System.out.println("Nome: " + tipo.getNome());
        System.out.println("Descrição " + tipo.getDesc());
        System.out.println("Valor unitário: R$" + tipo.getValor());
    }

    // TODO******************** MÉTODOS DE FUNCIONARIO *******************

    private void cadastrarFuncionario() {
        Misc.clearScreen();
        System.out.println("=================================");
        System.out.println("      CADASTRA FUNCIONARIO       ");
        System.out.println("=================================");
        System.out.println("Digite o nome do Funcionario: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o cpf do Funcionario: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite o cargo do Funcionario: ");
        String cargo = scanner.nextLine();

        Funcionario funcionario = new Funcionario(nome, cpf, cargo);
        conFunc.cadastrar(funcionario);
    }

    private void listarFuncionario() {
        System.out.println("\n=+=+=+=+= Lista de Funcionarios =+=+=+=+=");
        List<Funcionario> funcionarios = conFunc.listar();
        if (!funcionarios.isEmpty()) {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(">>> Funcionario ID: " + funcionario.getId() + " <<<");
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("CPF: " + funcionario.getCpf());
                System.out.println("Cargo: " + funcionario.getCargo());
                System.out.println("--------------------------------------");
            }
        } else {
            System.out.println("Lista está vazia!");
        }
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=\n");
    }

    private Funcionario buscarFuncionarioCPF() {
        System.out.println("CPF do Funcionario: ");
        String cpfFunc = scanner.nextLine();
        Funcionario funcionario = conFunc.buscarCpf(cpfFunc);
        
        while (funcionario == null) {
            System.out.println("\nUsuário não encontrado!");
            System.out.println("CPF do Funcionario: ");
            cpfFunc = scanner.nextLine();
            funcionario = conFunc.buscarCpf(cpfFunc);
        }
        
        return funcionario;
    }

    private void excluirFuncionario() {
        Misc.clearScreen();
        System.out.println("===============================");
        System.out.println("      EXCLUIR FUNCIONARIO      ");
        System.out.println("===============================");
        System.out.println("Digite o ID do Funcionario a ser excluido: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        String retorno = conFunc.excluir(id);
        System.out.println(retorno);
    }

    private void atualizarFuncionario() {
        Misc.clearScreen();
        System.out.println("=================================");
        System.out.println("      ATUALIZAR FUNCIONARIO      ");
        System.out.println("=================================");
        System.out.println("Digite o ID do Funcionario a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        Funcionario funcionario = conFunc.buscar(id);

        if (funcionario != null) {
            System.out.println("Digite o novo nome do Funcionario: ");
            String nome = scanner.nextLine();
            funcionario.setNome(nome);
            System.out.println("Digite o CPF do Funcionario: ");
            String cpf = scanner.nextLine();
            funcionario.setCpf(cpf);
            System.out.println("Digite o Cargo do Funcionario");
            String cargo = scanner.nextLine();
            funcionario.setCargo(cargo);
            String retorno = conFunc.atualizar(funcionario);
            System.out.println(retorno);
        } else {
            System.out.println("Id não encontrado!");
        }
    }

    // TODO******************** MÉTODOS DE SISTEMA *******************

    private void mostrarLogo() {
        Misc.clearScreen();
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

    private void acesso() {
        Misc.clearScreen();
        System.out.println("================================");
        System.out.println("         TELA DE LOGIN          ");
        System.out.println("================================");
                
        System.out.println("Digite seu login");
        this.systemFuncionario = buscarFuncionarioCPF();
        mostrarLogo();
        System.out.println("Bem vindo(a) " + this.systemFuncionario.getNome() + "!");
    }

    private LocalDate _getLocalDate(String titulo) {
        System.out.println("Qual a data de " + titulo + "?");
        System.out.print("[" + titulo + "]" + " dia: ");
        int dia = scanner.nextInt();
        System.out.print("[" + titulo + "]" + " mes (numeral): ");
        int mes = scanner.nextInt();
        System.out.print("[" + titulo + "]" + " ano (4 numerais): ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Limpa buffer
        LocalDate data = LocalDate.of(ano, mes, dia);
        return data;
    }

    private void avisoAcesso() {
        int consumo = daoContaAcesso.contaAcessos(this.hoje);
        int limite = 100;
        int limiteAlerta = (int) (limite - (limite * 0.15));

        System.out.println("*** AVISO DE NUMERO DE OPERAÇÕES ***");
        System.out.println("- Este sistema está limitado a " + limite +
                           " reservas diarias");
        System.out.println("- Quantidade de reservas feitas: " + consumo);

        if (consumo > limite) {
            System.out.println(">> AVISO!!! <<");
            System.out.println(">> Limite de " + limite + " reservas atingida <<");
            System.out.println(">> Avise a administração <<");
            callMainMenu();
            return;
        } else if (consumo >= limiteAlerta) {
            System.out.println(">> CUIDADO!!! <<");
            System.out.println(">> Avise a administração <<");
            System.out.println("- Saldo atual: " + (limite - consumo));
        } else if (consumo < limite) {
            System.out.println("- Saldo atual: " + (limite - consumo));
        }
    }


    // TODO******************** MÉTODOS DE SUPORTE AO MENU *******************
    private void msgDefault(int timeSec) {
        Misc.clearScreen();
        Misc.text("ATENÇÃO -> Opção inválida\n");
        Misc.delay(timeSec);
        Misc.clearScreen();
    }

    @SuppressWarnings("resource")
    private void helpMenu() {

        Misc.text("**** Sistema de Gestão de Hotel ****\n");
        Misc.text("=========== Tela de ajuda ===========\n\n");
        Misc.text(
                "Para utilizar o Sistema de Gestão de Livros será apresentado opções de cada tela presente ao usuário, cada opção tem um número correspondente");
        Misc.text(
                "sendo assim para selecionar a opção desejada basta digitar o número desta opção, por exemplo:\n");

        Misc.text("1 - Carregar arquivo");
        Misc.text("O número 1 representa o número da opção e Carregar arquivo é a descrição da opção.\n");

        Misc.text(
                "Após digitar a opção desejada basta pressionar a tecla ENTER que será carregado a tela correspondente da função escolhida.\n");
        Misc.text("Pressione qualquer tecla para retornar ao menu principal...");

        try {
            scanner.nextLine();
        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            System.exit(-1);
        }

        Misc.clearScreen();
        callMainMenu();
    }

    @SuppressWarnings("resource")
    private String baseMsg(String nameScreen, ArrayList<String> opSelector) {
        String op = "";

        Misc.text("Selecione a opção desejada digitando o número correspondente:\n");

        Misc.text("=========== %s ===========\n", nameScreen);
        Misc.text("Dica: Para receber ajuda escreva AJUDAR.\n");

        for (int i = 0; i < opSelector.size(); i++) {
            Misc.text(opSelector.get(i));
        }

        try {
            Misc.text("Número da opção: ");
            op = scanner.nextLine();
            op = op.toUpperCase();

        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            Misc.delay(2);
            Misc.clearScreen();
            scanner.nextLine();
            callMainMenu();
        }

        return op;
    }

    protected void baseMsgFunc(String nameScreen) {
        mostrarLogo();

        Misc.text("=========== %s ===========\n\n", nameScreen);
    }
}