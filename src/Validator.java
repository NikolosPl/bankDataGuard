import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Validator {
    private final TransactionParser transactionParser = new TransactionParser();
    private final Currency pln = Currency.PLN;
    private final Currency euro = Currency.EUR;
    private final Currency usd = Currency.USD;

    private final ArrayList<Transaction> data = transactionParser.getTransactions();
    private final ArrayList<Transaction> validatedData = new ArrayList<>();
    private final ArrayList<Transaction> rejectedData = new ArrayList<>();
    public void validateTransaction(){
        Set<String> ids = new HashSet<>();
        for (Transaction transaction: this.data) {
            if(transaction.getCurrency().equals(pln.toString()) || transaction.getCurrency().equals(euro.toString()) || transaction.getCurrency().equals(usd.toString())){
                if(transaction.getDate().matches("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")){
                    try{
                        if(Double.parseDouble(transaction.getAmount()) > 0.0){
                            if(transaction.getNr_konta().length() == 10){
                                if(ids.contains(transaction.getId())){
                                   this.rejectedData.add(transaction);
                                } else{
                                    ids.add(transaction.getId());
                                    this.validatedData.add(transaction);
                                }
                            } else{
                                this.rejectedData.add(transaction);
                            }
                        } else{
                            this.rejectedData.add(transaction);
                        }
                    } catch (NumberFormatException e){
                        continue;
                    }
                }
                else{
                    this.rejectedData.add(transaction);
                }
            } else{
                this.rejectedData.add(transaction);
            }
        }
    }
    public ArrayList<Transaction> getValidatedData(){
        return this.validatedData;
    }
    public ArrayList<Transaction> getRejectedData(){
        return this.rejectedData;
    }
    public void printValidateData(){
        System.out.println("Validated data:\n");
        for (Transaction transaction: this.validatedData) {
            System.out.println(transaction);
        }
    }
    public void printRejectedData(){
        System.out.println("Rejected data:\n");
        for (Transaction transaction: this.rejectedData) {
            System.out.println(transaction);
        }
    }
}
