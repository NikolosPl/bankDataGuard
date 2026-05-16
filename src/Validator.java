import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Validator {
    private  TransactionParser transactionParser = new TransactionParser();
    private final ArrayList<Transaction> data = transactionParser.getTransactions();
    private ArrayList<Transaction> validatedData = new ArrayList<>();
    public Validator(String filePath) {
        this.transactionParser = new TransactionParser(filePath);
    }
    public Validator(){
        this.transactionParser = new TransactionParser();
    }
    public void validateTransaction(){
        for (Transaction transaction: this.data) {

        }
    }
}
