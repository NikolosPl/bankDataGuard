import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Validator {
    private double total = 0;
    private final ArrayList<Transaction> data;
    private final ArrayList<Transaction> validatedData = new ArrayList<>();
    private final HashMap<Transaction, String> rejectedData = new HashMap<>();

    public Validator(ArrayList<Transaction> data) {
        this.data = data;
    }

    public void validateTransaction(){
        Set<String> ids = new HashSet<>();
        for (Transaction transaction: this.data) {
            if(!ids.add(transaction.id())){
                this.rejectedData.put(transaction, "Duplicate transaction ID");
                continue;
            }
            double amount;
            try{
                amount = Double.parseDouble(transaction.amount());
                if(amount <= 0){
                    this.rejectedData.put(transaction, "Invalid amount");
                    continue;
                }
            } catch (NumberFormatException _){
                this.rejectedData.put(transaction, "Invalid amount");
                continue;
            }
            if(transaction.accountNumber().length() != 10){
                this.rejectedData.put(transaction, "Invalid account number");
                continue;
            }
            Currency currency = Currency.fromString(transaction.currency());
            if(currency == null){
                this.rejectedData.put(transaction, "Invalid currency");
                continue;
            }
            try{
                if(LocalDate.parse(transaction.date(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isAfter(LocalDate.now())){
                    this.rejectedData.put(transaction, "Invalid date format, expected YYYY-MM-DD");
                    continue;
                }
            } catch (DateTimeParseException e){
                this.rejectedData.put(transaction, "Invalid date");
                continue;
            }

            this.validatedData.add(transaction);
            this.total += amount * currency.getRate();
        }
    }
    public double getTotal(){
        return this.total;
    }
    public ArrayList<Transaction> getValidatedData(){
        return this.validatedData;
    }
    public HashMap<Transaction, String> getRejectedData(){
        return this.rejectedData;
    }
}
