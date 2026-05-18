void main() {
    TransactionParser parser = new TransactionParser();
    ArrayList<Transaction> rawData = parser.getTransactions();

    Validator validator = new Validator(rawData);

    ReportGenerator reportGenerator = new ReportGenerator(validator);
    reportGenerator.generateReport();
}
