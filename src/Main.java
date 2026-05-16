void main() {
//    Validator validator = new Validator();
//    validator.validateTransaction();
    TransactionParser transactionParser = new TransactionParser();
//    ArrayList<Transaction> s = transactionParser.getTransactions();
    transactionParser.parseTransaction();
    transactionParser.printData();
}
