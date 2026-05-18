import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Validator {
    static double total = 0;
    private final TransactionParser transactionParser = new TransactionParser();
    private final ArrayList<Transaction> data = transactionParser.getTransactions();
    private final ArrayList<Transaction> validatedData = new ArrayList<>();
    private final HashMap<Transaction, String> rejectedData = new HashMap<>();
    public void validateTransaction(){
        Set<String> ids = new HashSet<>();
        for (Transaction transaction: this.data) {
            String date = transaction.getDate();
            if(ids.contains(transaction.getId())){
                this.rejectedData.put(transaction, "Duplicate transaction ID");
                continue;
            } else{
                ids.add(transaction.getId());
            }
            try{
                if(Double.parseDouble(transaction.getAmount()) <= 0){
                    this.rejectedData.put(transaction, "Invalid amount");
                    continue;
                }
            } catch (NumberFormatException e){
                this.rejectedData.put(transaction, "Invalid amount");
                continue;
            }
            if(transaction.getNr_konta().length() != 10){
                this.rejectedData.put(transaction, "Invalid account number");
                continue;
            }
            if(!transaction.getCurrency().equals("PLN") && !transaction.getCurrency().equals("EUR") && !transaction.getCurrency().equals("USD")){
                this.rejectedData.put(transaction, "Invalid currency");
                continue;
            }
            try{
                if(!date.matches("^(19[0-9]{2}|[2-9][0-9]{3}|3000)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$") && LocalDate.parse(date).isAfter(LocalDate.now())){
                    this.rejectedData.put(transaction, "Invalid date");
                    continue;
                }
            } catch (Exception e){
                this.rejectedData.put(transaction, "Invalid date");
                continue;
            }
            switch (transaction.getCurrency()) {
                case "PLN" -> total += Double.parseDouble(transaction.getAmount());
                case "EUR" -> total += Double.parseDouble(transaction.getAmount()) * Currency.EUR.getRate();
                case "USD" -> total += Double.parseDouble(transaction.getAmount()) * Currency.USD.getRate();
                default -> {
                    this.rejectedData.put(transaction, "Invalid currency");
                    continue;
                }
            }
            this.validatedData.add(transaction);
        }
    }
    public ArrayList<Transaction> getValidatedData(){
        return this.validatedData;
    }
    public HashMap<Transaction, String> getRejectedData(){
        return this.rejectedData;
    }
}
