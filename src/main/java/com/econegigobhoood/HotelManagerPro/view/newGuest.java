package com.econegigobhoood.HotelManagerPro.view;

import java.util.Scanner;
import com.econegigobhoood.HotelManagerPro.model.Misc;

public class newGuest {
    @SuppressWarnings("resource")
    public static void newGuestMenu() {
        String nome = "";
        String cpf = "";
        String tel = "";

        Scanner scannerInput = new Scanner(System.in);

        MainMenu.baseMsgFunc("Novo hospede");

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
            cpf = scannerInput.nextLine();

        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            Misc.delay(2);
            Misc.clearScreen();
            scannerInput.nextInt();
        }

        Misc.text("As informações estão corretas?");
        Misc.text("Nome: %s\nCPF: %d\nTelefone: %s", nome, cpf, tel);

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
                    cpf = "";
                    tel = "";
                    MainMenu.callMainMenu();
                    break;
                case "N":
                    Misc.clearScreen();
                    newGuestMenu();
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
