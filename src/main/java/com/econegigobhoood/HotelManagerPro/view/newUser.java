package com.econegigobhoood.HotelManagerPro.view;

import java.util.Scanner;

import com.econegigobhoood.HotelManagerPro.model.Misc;

public class newUser {

    @SuppressWarnings("resource")
    public static void newUserMenu() {
        String nome = "";
        int cpf = 0;
        String cargo = "";
        int opCargo = 0;
        Scanner scannerInput = new Scanner(System.in);

        MainMenu.baseMsgFunc("Novo funcionário");

        try {
            Misc.text("Nome completo: ");
            nome = scannerInput.nextLine();

        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            Misc.delay(2);
            Misc.clearScreen();
            scannerInput.nextLine();
        }

        try {
            Misc.text("CPF (Somente números): ");
            cpf = scannerInput.nextInt();

        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            Misc.delay(2);
            Misc.clearScreen();
            scannerInput.nextInt();
        }

        Misc.text("Escolha o cargo designado:");
        Misc.text("1 - Gerente");
        Misc.text("2 - Recepcionista");
        Misc.text("3 - Camareira");
        Misc.text("4 - Cozinheiro");
        Misc.text("5 - Concierge");

        try {
            Misc.text("Cargo (Digite o número correspondente): ");
            opCargo = scannerInput.nextInt();

            switch (opCargo) {
                case 1:
                    cargo = "Gerente";
                    break;
                case 2:
                    cargo = "Recepcionista";
                    break;
                case 3:
                    cargo = "Camareira";
                    break;
                case 4:
                    cargo = "Cozinheiro";
                    break;
                case 5:
                    cargo = "Concierge";
                    break;
                default:
                    cargo = "Opção inválida. Tente novamente.";
                    break;
            }
        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            scannerInput.nextLine();
        }

        Misc.text("As informações estão corretas?");
        Misc.text("Nome: %s\nCPF: %d\nCargo: %s", nome, cpf, cargo);

        try {
            Misc.text("Sim = S / Não = N");
            String confirm = scannerInput.nextLine();
            confirm.toUpperCase();

            switch (confirm) {
                case "S":
                    // Conectar ao banco de dados, após concluir será dado a seguencia de voltar ao
                    // menu inicial
                    Misc.clearScreen();
                    nome = "";
                    cpf = 0;
                    cargo = "";
                    opCargo = 0;
                    MainMenu.callMainMenu();
                    break;
                case "N":
                    Misc.clearScreen();
                    newUserMenu();
                    break;

                default:
                    Misc.text("Opção inválida. Tente novamente.");
                    break;
            }
        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
        }
    }
}
