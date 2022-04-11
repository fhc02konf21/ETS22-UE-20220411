package org.campus02.transactions;

import java.util.ArrayList;
import java.util.Collections;

public class DemoApp {
    public static void main(String[] args) throws TransactionLoadException, TransactionObjectException {

        ArrayList<Transaction> list =  TransactionLoader.loadTransactions("D:\\Temp\\transactions.csv");
        //System.out.println("list = " + list);
        System.out.println("list.size() = " + list.size());

        System.out.println("--------------------------");
        System.out.println("sort with default-sorting");
        Collections.sort(list); // es wird die List selbst sortiert
        System.out.println(list);

        System.out.println("--------------------------");
        System.out.println("sort with PriceProduct-sorting");
        Collections.sort(list, new PriceProductComparator()); // es wird die List selbst sortiert
        System.out.println(list);


        TransactionObjectHandler.saveTransaction(list, "D:\\temp\\transactions-objects.dat");
        
        
        TransactionManager tm = new TransactionManager(list);

        System.out.println("tm.getTransactionCountyByCity() = " + tm.getTransactionCountyByCity());

        System.out.println("tm.getCountOfTransaction(\"United States\") = " + tm.getCountOfTransaction("United States"));

        System.out.println("tm.getAverageTransactionAmountByPaymentType() = " + tm.getAverageTransactionAmountByPaymentType());

    }
}
