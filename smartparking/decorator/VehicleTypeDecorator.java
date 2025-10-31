package smartparking.decorator;
import smartparking.model.VehicleSize;
public class VehicleTypeDecorator extends FeeDecorator {
    public VehicleTypeDecorator(Fee inner){ super(inner); }
    @Override public double calculate(FeeContext ctx){
        double fee = inner.calculate(ctx);
        switch(ctx.getVehicle().getSize()){
            case LARGE: return fee * 1.5;
            case COMPACT: return fee;
            default: return fee;
        }
    }
}