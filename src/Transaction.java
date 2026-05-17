import java.util.ArrayList;
import java.util.HashMap;

public class Transaction {
    private  String id, nr_konta, amount, currency, date;
    private HashMap<String, ArrayList<String>> transaction;
    public Transaction(String id, String nr_konta, String amount, String currency,  String date){
        this.id = id;
        this.nr_konta = nr_konta;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
        this.transaction = new HashMap<>();
        this.transaction.putIfAbsent(id, new ArrayList<>());
        ArrayList<String> list = new ArrayList<>();
        list.add(nr_konta);
        list.add(amount);
        list.add(currency);
        list.add(date);
        this.transaction.put(id, list);
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
