package smartparking.factory;
 import smartparking.model.Vehicle;
 import smartparking.model.VehicleSize;
 public class CompactSlot extends ParkingSlot {
 public CompactSlot(String id){ super(id); }
 @Override public boolean fits(Vehicle v){ return v.getSize() ==
 VehicleSize.COMPACT; }
 }