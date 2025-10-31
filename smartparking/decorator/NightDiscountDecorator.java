package smartparking.decorator;
import java.time.*;
public class NightDiscountDecorator extends FeeDecorator {
    public NightDiscountDecorator(Fee inner){ super(inner); }
    @Override public double calculate(FeeContext ctx){
        double fee = inner.calculate(ctx);
        LocalTime t = ctx.getStartTime().toLocalTime();
        if (t.isAfter(LocalTime.of(22,0)) || t.isBefore(LocalTime.of(6,0)))
            return fee * 0.8;
        return fee;
    }
}