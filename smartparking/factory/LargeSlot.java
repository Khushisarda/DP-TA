 package smartparking.factory;
 import smartparking.model.Vehicle;
 public class LargeSlot extends ParkingSlot {
 public LargeSlot(String id){ super(id); }
 @Override public boolean fits(Vehicle v){ return true; }
 }