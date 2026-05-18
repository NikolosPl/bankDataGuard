import java.text.DecimalFormat;
import java.util.ArrayList;

public class ReportGenerator {
    private final DecimalFormat df = new DecimalFormat("#.##");
    private final Validator validator = new Validator();
    private final ArrayList<Transaction> validatedData = validator.getValidatedData();
    private final ArrayList<Transaction> rejectedData = validator.getRejectedData();
    public void generateReport(){
        validator.validateTransaction();
        System.out.println("Łączna liczba przetworzonych transakcji: " + (validatedData.size() + rejectedData.size()));
        System.out.println("Liczba zaakceptowanych transakcji: " + validatedData.size());
        System.out.println("Liczba odrzuconych transakcji: " + rejectedData.size());
        validator.printRejectedData();
        System.out.println("-------------------------\nCałkowita suma poprawnych transackji przeliczona na PLN: " + df.format(Validator.total) + "zł");
    }
}
