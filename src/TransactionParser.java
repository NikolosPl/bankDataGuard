import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class TransactionParser {
    private String filePath;
    private ArrayList<Transaction> transactions;
    public TransactionParser(){
        this.filePath = "resources/transactions.csv";
        this.transactions = new ArrayList<>();
    }
    public TransactionParser(String filePath) {
        this.filePath = filePath;
        this.transactions = new ArrayList<>();
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
                this.transactions.add(new Transaction(transactionID, accountNumber, amount, currency, transactionDate));
            }
        } catch(IOException e) {
            System.out.println("File empty or non-existent.");
        }
    }
    public ArrayList<Transaction> getTransactions(){
        this.parseTransaction();
        return this.transactions;
    }
    public void printData(){
        for (Transaction transaction : this.transactions) {
            System.out.println("Transaction id: " + transaction.getId() + "\nAccount number: " + transaction.getNr_konta() + "\nAmount: " + transaction.getAmount() + "\nCurrency: " + transaction.getCurrency() + "\nDate: " + transaction.getDate() + "\n");
        }
    }
}
