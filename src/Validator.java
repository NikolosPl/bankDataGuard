import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Validator {
    static double total = 0;
    private final TransactionParser transactionParser = new TransactionParser();
    private final Currency pln = Currency.PLN;
    private final Currency euro = Currency.EUR;
    private final Currency usd = Currency.USD;

    private final ArrayList<Transaction> data = transactionParser.getTransactions();
    private final ArrayList<Transaction> validatedData = new ArrayList<>();
    private final HashMap<Transaction, String> rejectedData = new HashMap<>();
    public void validateTransaction(){
        Set<String> ids = new HashSet<>();
        for (Transaction transaction: this.data) {
            if(transaction.getCurrency().equals(pln.toString()) || transaction.getCurrency().equals(euro.toString()) || transaction.getCurrency().equals(usd.toString())){
                if(transaction.getDate().matches("^(19[0-9]{2}|[2-9][0-9]{3}|3000)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")){
                    try{
                        if(Double.parseDouble(transaction.getAmount()) > 0.0){
                            if(transaction.getNr_konta().length() == 10){
                                if(ids.contains(transaction.getId())){
                                   this.rejectedData.put(transaction, "Duplicate ID");
                                } else{
                                    ids.add(transaction.getId());
                                    this.validatedData.add(transaction);
                                    if(transaction.getCurrency().equals(pln.toString())){
                                        total += Double.parseDouble(transaction.getAmount());
                                    } else if(transaction.getCurrency().equals(euro.toString())){
                                        total += Double.parseDouble(transaction.getAmount()) * Currency.EUR.getRate();
                                    } else if(transaction.getCurrency().equals(usd.toString())){
                                        total += Double.parseDouble(transaction.getAmount()) * Currency.USD.getRate();
                                    }
                                }
                            } else{
                                this.rejectedData.put(transaction, "Invalid account number");
                            }
                        } else{
                            this.rejectedData.put(transaction, "Invalid amount");
                        }
                    } catch (NumberFormatException _){
                    }
                }
                else{
                    this.rejectedData.put(transaction, "Invalid date");
                }
            } else{
                this.rejectedData.put(transaction, "Invalid currency");
            }
        }
    }
    public ArrayList<Transaction> getValidatedData(){
        return this.validatedData;
    }
    public HashMap<Transaction, String> getRejectedData(){
        return this.rejectedData;
    }
}
