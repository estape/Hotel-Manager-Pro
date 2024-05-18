package com.econegigobhoood.HotelManagerPro.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Misc {

    /**
     * Altera a cor do texto do console de acordo com a definição na entrada
     * 'color', para mais informações acesse:
     * https://github.com/estape/Hotel-Manager-Pro/wiki/ANSI-Code-List
     * 
     * @param frontColor Nome da cor, Ex: white
     */
    public static void colorForeground(String frontColor) {
        int codeColor = 97;

        switch (frontColor) {
            case "black":
                codeColor = 30;
                break;
            case "red":
                codeColor = 31;
                break;
            case "green":
                codeColor = 32;
                break;
            case "yellow":
                codeColor = 33;
                break;
            case "blue":
                codeColor = 34;
                break;
            case "magenta":
                codeColor = 35;
                break;
            case "cyan":
                codeColor = 36;
                break;
            case "white":
                codeColor = 37;
                break;
            case "bright black":
                codeColor = 90;
                break;
            case "bright red":
                codeColor = 91;
                break;
            case "bright green":
                codeColor = 92;
                break;
            case "bright yellow":
                codeColor = 93;
                break;
            case "bright blue":
                codeColor = 94;
                break;
            case "bright magenta":
                codeColor = 95;
                break;
            case "bright cyan":
                codeColor = 96;
                break;
            case "bright white":
                codeColor = 97;
                break;
            default:
                codeColor = 97;
                break;
        }

        // Usar %d para formatar o código de cor corretamente
        System.out.printf("\033[%dm", codeColor);
        clearScreen();
    }

    /**
     * Altera a cor de fundo do console de acordo com a definição na entrada
     * 'color', para mais informações acesse:
     * https://github.com/estape/Hotel-Manager-Pro/wiki/ANSI-Code-List
     * 
     * @param backColor Nome da cor, Ex: white
     */
    public static void colorBackground(String backColor) {
        int codeColor = 40;

        switch (backColor) {
            case "black":
                codeColor = 40;
                break;
            case "red":
                codeColor = 41;
                break;
            case "green":
                codeColor = 42;
                break;
            case "yellow":
                codeColor = 43;
                break;
            case "blue":
                codeColor = 44;
                break;
            case "magenta":
                codeColor = 45;
                break;
            case "cyan":
                codeColor = 46;
                break;
            case "white":
                codeColor = 47;
                break;
            case "bright black":
                codeColor = 100;
                break;
            case "bright red":
                codeColor = 101;
                break;
            case "bright green":
                codeColor = 102;
                break;
            case "bright yellow":
                codeColor = 103;
                break;
            case "bright blue":
                codeColor = 104;
                break;
            case "bright magenta":
                codeColor = 105;
                break;
            case "bright cyan":
                codeColor = 106;
                break;
            case "bright white":
                codeColor = 107;
                break;
            default:
                codeColor = 40;
                break;
        }

        System.out.printf("\033[%dm", codeColor);
        clearScreen();
    }

    /**
     * Altera a cor de fundo do console de acordo com a definição na entrada
     * 'color', para mais informações acesse:
     * https://github.com/estape/Hotel-Manager-Pro/wiki/ANSI-Code-List
     * 
     * @param font      Nome da fonte, Ex: bold
     * @param underline Se o texto será sublinhado ou não.
     */
    public static void fontStyle(String font, Boolean underline) {
        int codeFont = 0;

        switch (font) {
            case "normal":
                codeFont = 22;
                break;
            case "bold":
                codeFont = 1;
                break;
            case "green":
                codeFont = 3;
                break;
            default:
                codeFont = 22;
                break;
        }

        if (underline) {
            System.out.printf("\033[%d;1m", codeFont);
            clearScreen();
        } else {
            System.out.printf("\033[%dm", codeFont);
            clearScreen();
        }
    }

    // Tratativas de erros
    public static final String SCANNER_INPUT_ERROR = "ERRO: Não foi possível definir a entrada do usuário no Scanner.\n\n";
    public static final String SCANNER_INVALID_ERROR = "ERRO: A entrada do usuário no Scanner foi inválida. Tipo de variável incorreto?\n\n";
    public static final String DATABASE_CONNECTION_ERROR = "ERRO: Não foi possível conectar ao banco de dados.\n\n";
    public static final String FILE_NOT_FOUND_ERROR = "ERRO: Arquivo não encontrado.\n\n";
    public static final String FILE_NOT_WRITED_ERROR = "ERRO: Não foi possível salvar o arquivo.\n\n";
    public static final String FILE_NOT_DELETED_ERROR = "ERRO: Não foi possível excluir o arquivo.\n\n";

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
     * Imprime na tela do usuário o valor String definido em 'msg', e se caso houver
     * formatação, quaisquer variáveis devem ser inseridas em 'args'.
     * 
     * @param msg  Texto em String.
     * @param args Variáveis separadas por vírgula caso haja formatação.
     */
    public static void text(String msg, Object... args) {
        if (args == null || args.length == 0) {
            System.out.println(msg);
        } else {
            System.out.printf(msg, args);
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
}
