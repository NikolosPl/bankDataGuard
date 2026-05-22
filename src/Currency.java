public enum Currency {
    PLN(1.00),
    EUR(4.30),
    USD(4.00);

    private final double value;
    Currency(double v) {
        this.value = v;
    }
    public double getRate(){
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
