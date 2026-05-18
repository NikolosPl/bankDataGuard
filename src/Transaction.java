import java.util.ArrayList;
import java.util.HashMap;
public class Transaction {
    private final String id, nr_konta, amount, currency, date;
    HashMap<String, ArrayList<String>> transaction = new HashMap<>();
    ArrayList<String> list = new ArrayList<>();

    public Transaction(String id, String nr_konta, String amount, String currency,  String date){
        this.id = id;
        this.nr_konta = nr_konta;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
        this.transaction.putIfAbsent(id, new ArrayList<>());
        this.list.add(nr_konta);
        this.list.add(amount);
        this.list.add(currency);
        this.list.add(date);
        this.transaction.put(id, this.list);
    }
    public String getId(){
        return this.id;
    }
    public String getNr_konta(){
        return this.nr_konta;
    }
    public String getAmount(){
        return this.amount;
    }
    public String getCurrency(){
        return  this.currency;
    }
    public String getDate(){
        return  this.date;
    }
    public String toString(){
        return "Transaction ID: " + this.id + "\nAccount number: " + this.nr_konta + "\nAmount: " + this.amount + "\nCurrency: " + this.currency + "\nDate: " + this.date + "\n";
    }
}
