import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TransactionParser {
    private String filePath;
    private HashMap<String, ArrayList<String>> data;
    public TransactionParser(){
        this.filePath = "resources/transactions.csv";
        this.data = new HashMap<>();
    }
    public TransactionParser(String filePath) {
        this.filePath = filePath;
        this.data = new HashMap<>();
    }
    public void parseTransaction(){
        try(BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line, transactionID, accountNumber,  amount, currency, transactionDate;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if(parts[0].startsWith("ID_TRANSAKCJI")) continue;
                transactionID = parts[0];
                accountNumber = parts[1];
                amount = parts[2];
                currency = parts[3];
                transactionDate = parts[parts.length-1];
                ArrayList<String> lines = new ArrayList<>();
                lines.add(accountNumber);
                lines.add(amount);
                lines.add(currency);
                lines.add(transactionDate);
                data.put(transactionID, lines);
            }
        } catch(IOException e) {
            System.out.println("File empty or non-existent.");
        }
    }
    public HashMap<String, ArrayList<String>> getData(){
        this.parseTransaction();
        return this.data;
    }
    public void printData(){
        for (String key: this.data.keySet()) {
            System.out.println("Transaction ID: " + key + "\nAccount number: " + this.data.get(key).get(0) + "\nAmount: " + this.data.get(key).get(1) + "\nCurrency: " + this.data.get(key).get(2) + "\nTransaction Date: " +  this.data.get(key).get(3) + "\n");
        }
    }
}
