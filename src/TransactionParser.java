import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class TransactionParser {
    private final String filePath;
    private final ArrayList<Transaction> transactions;
    public TransactionParser(){
        this.filePath = "resources/transactions.csv";
        this.transactions = new ArrayList<>();
    }
    public TransactionParser(String filePath) {
        this.filePath = filePath;
        this.transactions = new ArrayList<>();
    }
    public void parseTransaction(){
        this.transactions.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.isBlank()) continue;
                String[] parts = line.trim().split(";", -1);
                if(parts.length < 5){
                    System.out.println("Row has less than 5 columns: " + line);
                    continue;
                }
                if(parts[0].startsWith("ID_TRANSAKCJI")) continue;
                this.transactions.add(new Transaction(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3],
                        parts[4]
                ));
            }
        } catch(IOException e) {
            System.out.println("File empty or non-existent.");
        }
    }
    public ArrayList<Transaction> getTransactions(){
        return this.transactions;
    }
}
