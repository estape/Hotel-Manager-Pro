package com.econegigobhoood.HotelManagerPro.view;

import java.util.Scanner;
import com.econegigobhoood.HotelManagerPro.model.Misc;
import java.util.ArrayList;

public class MainMenu {

    public static ArrayList<String> listOptions = new ArrayList<String>();

    private static void msgDefault(int timeSec) {
        Misc.clearScreen();
        Misc.text("ATENÇÃO -> Opção inválida\n");
        Misc.delay(timeSec);
        Misc.clearScreen();
    }

    @SuppressWarnings("resource")
    private static void helpMenu() {
        Scanner scannerInput = new Scanner(System.in);

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
            scannerInput.nextLine();
        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            System.exit(-1);
        }

        Misc.clearScreen();
        callMainMenu();
    }

    @SuppressWarnings("resource")
    private static String baseMsg(String nameScreen, ArrayList<String> opSelector) {
        String op = "";

        Scanner scannerInput = new Scanner(System.in);
        Misc.text("**** Sistema de Gestão de Hotel ****\n");

        Misc.text("Selecione a opção desejada digitando o número correspondente:\n");

        Misc.text("=========== %s ===========\n", nameScreen);
        Misc.text("Dica: Para receber ajuda escreva AJUDAR.\n");

        for (int i = 0; i < opSelector.size(); i++) {
            Misc.text(opSelector.get(i));
        }

        try {
            Misc.text("Número da opção: ");
            op = scannerInput.nextLine();
            op = op.toUpperCase();

        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            Misc.delay(2);
            Misc.clearScreen();
            scannerInput.nextLine();
            callMainMenu();
        }

        return op;
    }

    protected static void baseMsgFunc(String nameScreen) {
        Misc.text("**** Sistema de Gestão de Hotel ****\n");

        Misc.text("=========== %s ===========\n\n", nameScreen);
    }

    // Menu inicial é este aqui.
    public static void callMainMenu() {
        String op = "";
        listOptions.clear();
        listOptions.add("1 - Reservas");
        listOptions.add("2 - Hóspedes");
        listOptions.add("3 - Gestão de funcionários");
        listOptions.add("4 - Sair do sistema");

        op = baseMsg("Menu principal", listOptions);

        switch (op) {
            case "1":
                Misc.clearScreen();
                reservationsMenu();
                break;

            case "2":
                Misc.clearScreen();
                guestsMenu();
                break;
            case "3":
                Misc.clearScreen();
                userMenu();
                break;
            case "4":
                Misc.colorBackground("black");
                Misc.text("Sistema fechado, até logo!\n\n");
                System.exit(0);
                break;
            case "AJUDAR":
                Misc.clearScreen();
                helpMenu();
                break;

            default:
                msgDefault(3);
                callMainMenu();
                break;
        }
    }

    // Opção 1
    public static void reservationsMenu() {
        String op = "";
        listOptions.clear();
        listOptions.add("1 - Nova reserva");
        listOptions.add("2 - Alterar reserva");
        listOptions.add("3 - Consultar");
        listOptions.add("4 - Voltar ao menu anterior");

        op = baseMsg("Reservas", listOptions);

        switch (op) {
            case "1":

                break;
            case "2":

                break;
            case "3":
                Misc.clearScreen();
                break;
            case "4":
                Misc.clearScreen();
                callMainMenu();
            default:
                msgDefault(3);
                reservationsMenu();
                break;
        }
    }

    // Opção 2
    public static void guestsMenu() {
        String op = "";
        listOptions.clear();
        listOptions.add("1 - Novo hospede");
        listOptions.add("2 - Atualizar cadastro hospede");
        listOptions.add("3 - Consultar");
        listOptions.add("4 - Voltar ao menu anterior");

        op = baseMsg("Hospedes", listOptions);

        switch (op) {
            case "1":
                Misc.clearScreen();

                break;
            case "2":
                Misc.clearScreen();
                break;
            case "3":
                Misc.clearScreen();
                break;
            case "4":
                Misc.clearScreen();
                callMainMenu();
            default:
                msgDefault(3);
                reservationsMenu();
                break;
        }
    }

    // Opção 3
    public static void userMenu() {
        String op = "";
        listOptions.clear();
        listOptions.add("1 - Novo funcionário");
        listOptions.add("2 - Atualilzar funcionário");
        listOptions.add("3 - Consultar funcionários");
        listOptions.add("4 - Voltar ao menu anterior");

        op = baseMsg("Gestão de funcionários", listOptions);

        switch (op) {
            case "1":
                Misc.clearScreen();

                break;
            case "2":
                Misc.clearScreen();
                break;
            case "3":
                Misc.clearScreen();
            case "4":
                Misc.clearScreen();
                callMainMenu();
                break;
            default:
                msgDefault(3);
                reservationsMenu();
                break;
        }
    }
}