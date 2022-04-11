package org.campus02.transactions;

import java.io.*;
import java.util.ArrayList;

public class TransactionLoader {

    public static ArrayList<Transaction> loadTransactions(String path) throws TransactionLoadException {

        File file = new File(path);
        if (!file.exists()) {
            throw new TransactionLoadException("File in path does not exist: " + path); // ALT+ENTER
        }

        // try-with-ressources, java7
        // weitere Ressourcen mit Strichpunkt
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            ArrayList<Transaction> list = new ArrayList<>();
            String line = bufferedReader.readLine(); // ignore this head line

            int count = 1;
            while ((line = bufferedReader.readLine()) != null) {
               count++;
               String[] columns = line.split(";");

               if (columns.length != 8) {
                   throw new TransactionLoadException("Wrong format in line: " + count + " Content: " + line);
               }

               Transaction transaction = new Transaction(
                       columns[0],
                       columns[1],
                       Double.parseDouble(columns[2]), // Wrapper-Klasse
                       columns[3],
                       columns[4],
                       columns[5],
                       columns[6],
                       columns[7]
               );
               list.add(transaction);
            }
            return list;

        } catch (FileNotFoundException e) {
            throw new TransactionLoadException(e); // Exception Chaining
        } catch (IOException e) {
            throw new TransactionLoadException(e);
        }
    }
}
