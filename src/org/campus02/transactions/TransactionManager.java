package org.campus02.transactions;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionManager {
    private ArrayList<Transaction> transactions;

    public TransactionManager(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    // key => City
    // value => Anzahl
    public HashMap<String, Integer> getTransactionCountyByCity() {

        HashMap<String, Integer> result = new HashMap<>();

        for (Transaction transaction : transactions) {

            if (result.containsKey(transaction.getCity())) {
                // key ist schon enthalten
/*                Integer value = result.get(transaction.getCity());
                value++;
                result.put(transaction.getCity(), value); // put überschreibt den aktuellen eintrag*/

                result.put(transaction.getCity(), result.get(transaction.getCity()) + 1);
            }
            else {
                // key ist noch nicht in hashmap
                result.put(transaction.getCity(), 1);
            }
        }

        return result;
    }

    public int getCountOfTransaction(String country) {
        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getCountry().equals(country) ){
                count++;
            }
        }
        return count;
    }

    public HashMap<String, Double> getAverageTransactionAmountByPaymentType() {

        HashMap<String, ArrayList<Transaction>> baseMap =  getTransactionsByPaymentType();
        HashMap<String, Double> result = new HashMap<>();

        for (String paymentType : baseMap.keySet()) {
            ArrayList<Transaction> transactions = baseMap.get(paymentType); // alle Transaktionen zu einem PaymentType

            double sum = 0;
            for (Transaction transaction : transactions) {
                sum += transaction.getPrice();
            }
            result.put(paymentType, sum / transactions.size());
        }

        return result;

    }

    private HashMap<String, ArrayList<Transaction>> getTransactionsByPaymentType() {
        HashMap<String, ArrayList<Transaction>> result = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (result.containsKey(transaction.getPaymentType())) {
                ArrayList<Transaction> list = result.get(transaction.getPaymentType());
                list.add(transaction);
                //result.put(transaction.getPaymentType(), list); // nicht notwendig
            }
            else {
                ArrayList<Transaction> list = new ArrayList<>();
                list.add(transaction);
                result.put(transaction.getPaymentType(), list);
            }
        }

        return result;
    }


}
