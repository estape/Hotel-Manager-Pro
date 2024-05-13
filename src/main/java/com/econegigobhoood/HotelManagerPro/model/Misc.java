package com.econegigobhoood.HotelManagerPro.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Misc {

    // Tratativas de erros
    public static final String SCANNER_INPUT_ERROR = "ERROR: Unable to set user input on Scanner.";
    public static final String SCANNER_INVALID_ERROR = "ERROR: user input was invalid on Scanner. Wrong variable type?";
    public static final String DATABASE_CONNECTION_ERROR = "ERROR: Unable to connect to the database.";
    public static final String FILE_NOT_FOUND_ERROR = "ERROR: File not found.";
    public static final String FILE_NOT_WRITED_ERROR = "ERROR: File can't be saved.";
    public static final String FILE_NOT_DELETED_ERROR = "ERROR: File can't be deleted.";

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Escreve um arquivo de texto e salva na pasta temporária do sistema
     * operacional com a extensão '.tmp'.
     * Retorna FILE_NOT_WRITED_ERROR caso não for possível salvar o arquivo.
     * 
     * @param name Nome do arquivo.
     * @param text Uma List do tipo string, cada índice equivale a uma linha de
     *             texto.
     */
    public void writeText(String name, ArrayList<String> text) {
        try {
            String tempDirectoryPath = System.getenv("LOCALAPPDATA");
            String lineText = "";
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempDirectoryPath + "\\temp\\" + name + ".tmp"));

            for (int i = 0; i < text.size(); i++) {
                lineText = lineText + text.get(i);
            }

            writer.write(lineText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(FILE_NOT_WRITED_ERROR);
        }
    }

    /**
     * Carrega um arquivo de texto na pasta temporária do sistema operacional com a
     * extensão '.tmp' e faz a leitura,
     * o valor do texto retornará como String.
     * Retorna FILE_NOT_FOUND_ERROR caso não for possível carregar o arquivo.
     * 
     * @param name Nome do arquivo a ser carregado.
     * @return Texto em String.
     */
    public ArrayList<String> readText(String name) {
        ArrayList<String> text = new ArrayList<String>();
        try {
            String tempDirectoryPath = System.getenv("LOCALAPPDATA");
            BufferedReader reader = new BufferedReader(new FileReader(tempDirectoryPath + "\\temp\\" + name + ".tmp"));
            String line;

            while ((line = reader.readLine()) != null) {
                text.add(line);
            }

            reader.close();
        } catch (IOException BufferedReader) {
            System.out.println(FILE_NOT_FOUND_ERROR);
        }
        return text;
    }

    /**
     * Excluí o arquivo definido por nome na pasta tempóraria do sistema operacional
     * com a extensão '.tmp',
     * caso não for possível excluír o arquivo FILE_NOT_DELETED_ERROR retornará na
     * tela do usuário.
     * 
     * @param name Nome do arquivo a ser excluido.
     */
    public void deleteFile(String name) {
        String tempDirectoryPath = System.getenv("LOCALAPPDATA");
        java.io.File refFile = new java.io.File(tempDirectoryPath + "\\temp\\" + name + ".tmp");

        try {
            refFile.delete();

        } catch (Exception refFilException) {
            System.out.println(FILE_NOT_DELETED_ERROR);
        }
    }

    /**
     * Limpa toda tela do console, excluindo todo texto na tela.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Aplica um delay, um atraso para a próxima execução de instrução, o valor de
     * tempo pode ser definido em Inteiro
     * e no intervalo de segundos.
     * 
     * @param timeSeconds Esperar o tempo definido em segundos.
     */
    public static void delay(int timeSeconds) {
        timeSeconds = timeSeconds * 1000;
        try {
            Thread.sleep(timeSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Imprimi na tela do usuário o valor String definido em 'msg', e se caso houver
     * formatação,
     * quaisquer variavéis devem ser inseridos em 'args'
     * 
     * @param msg  Texto em String.
     * @param args Variavéis separados por virgula caso haja formatação.
     */
    public static void text(String msg, Object... args) {
        if (args == null) {
            System.out.println(msg);
        } else {
            System.out.printf(msg + "\n", args);
        }

    }

    /**
     * Este método lê a entrada do usuário e retorna um objeto correspondente ao
     * tipo de dado inserido.
     * Se a entrada for um int, double, float, String ou boolean, ele retornará o
     * valor
     * correspondente.
     * 
     * @return Um objeto que pode ser um int, double, float, String ou boolean,
     *         dependendo da entrada do usuário.
     */
    public static Object inputUser() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else if (scanner.hasNextDouble()) {
            return scanner.nextDouble();
        } else if (scanner.hasNextFloat()) {
            return scanner.nextFloat();
        } else if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else if (scanner.hasNextBoolean()) {
            return scanner.nextBoolean();
        } else {
            return scanner.next();
        }
    }

    /**
     * Altera a cor dtexto do console de acordo com a definição na entrada
     * 'color', para mais informações acesse:
     * https://github.com/estape/Hotel-Manager-Pro/wiki/ANSI-Codes
     * 
     * @param frontColor Nome da cor, Ex: bold
     */
    public static void colorForeground(String frontColor) {
        int codeColor = 0;

        switch (frontColor) {
            case "black":
                codeColor = 30;
            case "red":
                codeColor = 31;
            case "green":
                codeColor = 32;
            case "yellow":
                codeColor = 33;
            case "blue":
                codeColor = 34;
            case "magenta":
                codeColor = 35;
            case "cyan":
                codeColor = 36;
            case "white":
                codeColor = 37;
            case "bright black":
                codeColor = 90;
            case "bright red":
                codeColor = 91;
            case "bright green":
                codeColor = 92;
            case "bright yellow":
                codeColor = 93;
            case "bright blue":
                codeColor = 94;
            case "bright mangeta":
                codeColor = 95;
            case "bright cyan":
                codeColor = 96;
            case "bright white":
                codeColor = 97;
            default:
                codeColor = 97;
                break;
        }

        System.out.printf("\033[{0}m", codeColor);
        clearScreen();
    }

    /**
     * Altera a cor de fundo do console de acordo com a definição na entrada
     * 'color', para mais informações acesse:
     * https://github.com/estape/Hotel-Manager-Pro/wiki/ANSI-Codes
     * 
     * @param backColor Nome da cor, Ex: white
     */
    public static void colorBackground(String backColor) {
        int codeColor = 0;

        switch (backColor) {
            case "black":
                codeColor = 40;
            case "red":
                codeColor = 41;
            case "green":
                codeColor = 42;
            case "yellow":
                codeColor = 43;
            case "blue":
                codeColor = 44;
            case "magenta":
                codeColor = 45;
            case "cyan":
                codeColor = 46;
            case "white":
                codeColor = 47;
            case "bright black":
                codeColor = 100;
            case "bright red":
                codeColor = 101;
            case "bright green":
                codeColor = 102;
            case "bright yellow":
                codeColor = 103;
            case "bright blue":
                codeColor = 104;
            case "bright mangeta":
                codeColor = 105;
            case "bright cyan":
                codeColor = 106;
            case "bright white":
                codeColor = 107;
            default:
                codeColor = 40;
                break;
        }

        System.out.printf("\033[{0}m", codeColor);
        clearScreen();
    }

    /**
     * Altera a cor de fundo do console de acordo com a definição na entrada
     * 'color', para mais informações acesse:
     * https://github.com/estape/Hotel-Manager-Pro/wiki/ANSI-Codes
     * 
     * @param font Nome da fonte, Ex: bold
     * @param underline Se o texto será sublinhado ou não.
     */
    public static void fontStyle(String font, Boolean underline) {
        int codeFont = 0;

        switch (font) {
            case "normal":
                codeFont = 22;
            case "bold":
                codeFont = 1;
            case "green":
                codeFont = 3;
            default:
                codeFont = 22;
                break;
        }

        if (underline) {
            System.out.printf("\033[{0};1m", codeFont);
            clearScreen();
        }
        else{
            System.out.printf("\033[{0}m", codeFont);
        clearScreen();
        }
    }
}
