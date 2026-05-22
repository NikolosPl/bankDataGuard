import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(
        String id,
        String accountNumber,
        BigDecimal amount,
        String currency,
        LocalDate date
) {}