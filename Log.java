package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private File destinationFile;

    public Log() {
        String path = "log.txt";
        destinationFile = new File(path);
        try (PrintWriter writer =
                     new PrintWriter(new FileOutputStream(destinationFile, false))) {
            writer.append("");
        } catch(IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void write(String transactionType, String amountDeposited, String change) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

       String stringOfLog = String.format("%-24s %-22s %-14s %-14s \n" + " ", now, transactionType, amountDeposited, change);
        try (PrintWriter writer =
                     new PrintWriter(new FileOutputStream(destinationFile, true))) {
            writer.append(stringOfLog);
        } catch(IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }


}
