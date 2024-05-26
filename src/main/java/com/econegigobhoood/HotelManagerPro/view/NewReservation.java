package com.econegigobhoood.HotelManagerPro.view;

import com.econegigobhoood.HotelManagerPro.model.Misc;
import java.util.Scanner;
import java.time.LocalDate;

public class NewReservation {

    @SuppressWarnings("resource")
    public static void NewReservationMenu(){
        LocalDate dtEntrada;
        LocalDate dtSaida;
        float vlQuarto = 0;
        String cpf = "";
        Scanner scannerInput = new Scanner(System.in);

        MainMenu.baseMsgFunc("Nova reserva");

        Misc.text("Pesquisa de cliente:");
        try {
            Misc.text("CPF (Somente números): ");
            cpf = scannerInput.nextLine();

        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            Misc.delay(2);
            Misc.clearScreen();
            scannerInput.nextInt();
        }
        /* consulta o cliente no banco de dados, se houver algo puxa as informações e segue com a reserva,
        se não, não continua com a reserva e sugere de cadastrar o novo cliente na classe newUser */

        try {
            Misc.text("Data da reserva: ");
            dtEntrada = LocalDate.parse(scannerInput.nextLine());

        } catch (Exception scannerException) {
            Misc.text(Misc.SCANNER_INPUT_ERROR);
            Misc.delay(2);
            Misc.clearScreen();
            scannerInput.nextInt();
        }

        /* Consulta no banco de dados se tem quartos disponiveis na data inserida e retorna um List com objeto Quartos */


    }
}
