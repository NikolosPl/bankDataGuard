import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Validator {
    private BigDecimal total = BigDecimal.ZERO;
    private final ArrayList<Transaction> data;
    private final ArrayList<Transaction> validatedData = new ArrayList<>();
    private final HashMap<Transaction, String> rejectedData = new HashMap<>();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
            BigDecimal amount;
            try{
                amount = new BigDecimal(transaction.amount());
                if(amount.compareTo(BigDecimal.ZERO) <= 0){
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
                LocalDate parsedDate = LocalDate.parse(transaction.date(), dateFormatter);
                if(parsedDate.isAfter(LocalDate.now())){
                    this.rejectedData.put(transaction, "Invalid date, cannot be in the future");
                    continue;
                }
            } catch (DateTimeParseException e){
                this.rejectedData.put(transaction, "Invalid date format, should be YYYY-MM-DD");
                continue;
            }

            BigDecimal rate = BigDecimal.valueOf(currency.getRate());
            this.total = this.total.add(amount.multiply(rate));
            this.validatedData.add(transaction);
        }
    }
    public BigDecimal getTotal(){
        return this.total;
    }
    public ArrayList<Transaction> getValidatedData(){
        return this.validatedData;
    }
    public HashMap<Transaction, String> getRejectedData(){
        return this.rejectedData;
    }
}
