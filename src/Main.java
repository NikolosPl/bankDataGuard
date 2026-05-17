void main() {
    Validator validator = new Validator();
    validator.validateTransaction();
    validator.printValidateData();
    System.out.println("--------------------------------------------------");
    validator.printRejectedData();
}
