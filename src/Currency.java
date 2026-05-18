public enum Currency {
    PLN(1),
    EUR(4.24),
    USD(3.63);

    private double value;
    Currency(double v) {
        this.value = v;
    }
    public double getRate(){
        return  this.value;
    }
}
