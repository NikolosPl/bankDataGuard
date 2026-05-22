import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ReportGenerator {
    private final Path path = Paths.get("report.txt");
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private final Validator validator;
    public ReportGenerator(Validator validator){
        this.validator = validator;
    }

    public void generateReport(){
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        validator.validateTransaction();
        ArrayList<Transaction> validatedData = validator.getValidatedData();
        LinkedHashMap<String, String> rejectedData = validator.getRejectedData();
        StringBuilder txt = new StringBuilder("Łączna liczba przetworzonych transakcji: " + (validatedData.size() + rejectedData.size()) + "\nLiczba zaakceptowanych transakcji: " + validatedData.size() + "\nLiczba odrzuconych transakcji: " + rejectedData.size() + "\n-------------------------------------------------------------------");
        for (var entry : rejectedData.entrySet()) {
            txt.append("\n").append(entry.getKey()).append(" - BŁĄD: ").append(entry.getValue());
        }
        txt.append("\n-------------------------------------------------------------------\nCałkowita suma poprawnych transakcji przeliczona na PLN: ").append(validator.getTotal().setScale(2, RoundingMode.HALF_UP).toPlainString()).append("zł");
        System.out.println(txt);
        try {
            Files.writeString(path, txt.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
