package smartparking.decorator;
public class ZoneMultiplierDecorator extends FeeDecorator {
    private double multiplier;
    public ZoneMultiplierDecorator(Fee inner, double multiplier){
        super(inner); this.multiplier = multiplier; }
    @Override public double calculate(FeeContext ctx){ return
            inner.calculate(ctx) * multiplier; }
}