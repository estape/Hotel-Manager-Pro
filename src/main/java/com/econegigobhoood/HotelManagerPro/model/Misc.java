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

    public void writeText(String name, ArrayList<String> text) {
        try {
            String tempDirectoryPath = System.getenv("LOCALAPPDATA");
            String lineText = "";
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempDirectoryPath +"\\temp\\" + name + ".tmp"));

            for (int i = 0; i < text.size(); i++) {
                lineText = lineText + text.get(i);
            }

            writer.write(lineText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(FILE_NOT_WRITED_ERROR);
            System.exit(-1);
        }
    }

    public ArrayList<String> readText(String name) {
        ArrayList<String> text = new ArrayList<String>();
        try {
            String tempDirectoryPath = System.getenv("LOCALAPPDATA");
            BufferedReader reader = new BufferedReader(new FileReader(tempDirectoryPath + "\\temp\\"+ name +".tmp"));
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

    public void deleteFile(String name) {
        String tempDirectoryPath = System.getenv("LOCALAPPDATA");
        java.io.File refFile = new java.io.File(tempDirectoryPath + "\\temp\\" + name + ".tmp");
    
        try {
            refFile.delete();
            
        } catch (Exception refFilException) {
            System.out.println(FILE_NOT_DELETED_ERROR);
            System.exit(-1);
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void delay(int timeSeconds) {
        timeSeconds = timeSeconds * 1000;
        try {
            Thread.sleep(timeSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void text(String msg, Object... args) {
        if(args == null){
            System.out.println(msg);
        }
        else{
            System.out.printf(msg + "\n", args);
        }
        
    }

    public static Object inputUser() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else if (scanner.hasNextDouble()) {
            return scanner.nextDouble();
        } else if (scanner.hasNextBoolean()) {
            return scanner.nextBoolean();
        } else {
            return scanner.next();
        }
    }
}
