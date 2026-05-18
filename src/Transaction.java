public record Transaction(
        String id,
        String accountNumber,
        String amount,
        String currency,
        String date
) {}