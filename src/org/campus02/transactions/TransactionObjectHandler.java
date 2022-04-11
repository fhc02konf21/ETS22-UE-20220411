package org.campus02.transactions;

import java.io.*;
import java.util.ArrayList;

public class TransactionObjectHandler {

    public static void saveTransaction(ArrayList<Transaction> list, String path) throws TransactionObjectException {

        try (ObjectOutputStream oos = new ObjectOutputStream(
                                        new BufferedOutputStream(
                                            new FileOutputStream(path)))) {

            //oos.writeObject(list); // => ganze liste schreiben

            // manuell schreiben
            int count = 0;
            for(Transaction obj : list) {
                oos.writeObject(obj);

                if (count % 100 == 0) {
                    oos.flush();
                }
            }
            oos.writeObject(null);// um das ende markieren

            oos.flush();

        } catch (FileNotFoundException e) {
            // ALT+ENTER
            throw new TransactionObjectException(e); // Exception Chaining
        } catch (IOException e) {
            throw new TransactionObjectException(e);
        }

    }
}
