import java.math.BigDecimal;

public enum Currency {
    PLN(new BigDecimal("1.00")),
    EUR(new BigDecimal("4.30")),
    USD(new BigDecimal("4.00"));

    private final BigDecimal value;
    Currency(BigDecimal v) {
        this.value = v;
    }
    public BigDecimal getExchangeRate(){
        return  this.value;
    }
    public static Currency fromString(String currency){
        try{
            return Currency.valueOf(currency);
        } catch (IllegalArgumentException e){
            return null;
        }
    }
}
