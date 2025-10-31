package smartparking.decorator;
import java.time.*;
import smartparking.model.Vehicle;
import smartparking.factory.ParkingSlot;
public class FeeContext {
    private Vehicle vehicle;
    private ParkingSlot slot;
    private Duration duration;
    private LocalDateTime startTime;
    public FeeContext(Vehicle vehicle, ParkingSlot slot, Duration duration,
                      LocalDateTime startTime){
        this.vehicle = vehicle; this.slot = slot; this.duration = duration;
        this.startTime = startTime;
    }
    public Vehicle getVehicle(){ return vehicle; }
    public ParkingSlot getSlot(){ return slot; }
    public Duration getDuration(){ return duration; }
    public LocalDateTime getStartTime(){ return startTime; }
}