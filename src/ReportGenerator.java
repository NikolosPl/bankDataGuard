import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportGenerator {
    Path path = Paths.get("raport.txt");
    {
        try {
            if(Files.exists(path)){
                Files.delete(path);
            }
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private final DecimalFormat df = new DecimalFormat("#.##");
    private final Validator validator = new Validator();
    private final ArrayList<Transaction> validatedData = validator.getValidatedData();
    private final HashMap<Transaction, String> rejectedData = validator.getRejectedData();

    public void generateReport(){
        validator.validateTransaction();
        StringBuilder txt = new StringBuilder("Łączna liczba przetworzonych transakcji: " + (validatedData.size() + rejectedData.size()) + "\nLiczba zaakceptowanych transakcji: " + validatedData.size() + "\nLiczba odrzuconych transakcji: " + rejectedData.size() + "\n-------------------------------------------------------------------");
        for (Transaction transaction : this.rejectedData.keySet()) {
            txt.append("\n").append(transaction.getId()).append(" - BŁĄD: ").append(this.rejectedData.get(transaction));
        }
        txt.append("\n-------------------------------------------------------------------\nCałkowita suma poprawnych transakcji przeliczona na PLN: ").append(df.format(Validator.total)).append("zł");
        System.out.println(txt);
        try {
            Files.writeString(path, txt.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
