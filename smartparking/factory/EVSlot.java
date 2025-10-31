package smartparking.factory;
 import smartparking.model.Vehicle;
 public class EVSlot extends ParkingSlot {
 public EVSlot(String id){ super(id); }
 @Override public boolean fits(Vehicle v){ return v.isElectric(); }
 }