import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
                BigDecimal amount = null;
                LocalDate date = null;
                try{
                    amount = new BigDecimal(parts[2]);
                } catch (NumberFormatException _){
                    continue;
                }
                try{
                    date = LocalDate.parse(parts[4]);
                } catch (DateTimeParseException _){
                    continue;
                }
                if(parts[0].startsWith("ID_TRANSAKCJI")) continue;
                this.transactions.add(new Transaction(
                        parts[0],
                        parts[1],
                        amount,
                        parts[3],
                        date
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
