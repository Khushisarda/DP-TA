package smartparking.decorator;
public class BaseFee implements Fee {
    private double ratePerHour;
    public BaseFee(double ratePerHour){ this.ratePerHour = ratePerHour; }
    @Override public double calculate(FeeContext ctx){
        double hours = Math.ceil(ctx.getDuration().toMinutes() / 60.0);
        return hours * ratePerHour;
    }
}