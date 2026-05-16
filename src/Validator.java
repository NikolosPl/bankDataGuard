import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Validator {
    private  TransactionParser transactionParser = new TransactionParser();
    private final HashMap<String, ArrayList<String>> data = transactionParser.getData();
    private HashMap<String, ArrayList<String>> validatedData = new HashMap<>();
    public Validator(String filePath) {
        this.transactionParser = new TransactionParser(filePath);
    }
    public Validator(){
        this.transactionParser = new TransactionParser();
    }
    public void validateTransaction(){
        data.forEach((k,v)->{
//            String id = v.get(0);
//            long nr_konta = Long.parseLong(v.get(1));
//            BigDecimal amount = new BigDecimal(v.get(2));
//            String currency = v.get(3);
//            LocalDateTime date = LocalDateTime.parse(v.get(4));
//            System.out.println(date);
            if(data.get(k)==null){
                return;
            }
            if(!validatedData.containsKey(k)){
                validatedData.put(k,new ArrayList<>());
            }
        });
    }
}
