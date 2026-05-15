public class Transaction {
    private String transactionID;
    private String accountNumber;
    private String amount;
    private String currency;
    private String date;
    public Transaction(String transactionID, String accountNumber, String amount, String currency, String date){
        this.transactionID = transactionID;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
    }
}
