 package smartparking.factory;
 import smartparking.model.Vehicle;
 public abstract class ParkingSlot {
 protected String id;
 protected boolean occupied = false;
 public ParkingSlot(String id) { this.id = id; }
 public abstract boolean fits(Vehicle v);
 public boolean isOccupied() { return occupied; }
 public void occupy() { occupied = true; }
 public void free() { occupied = false; }
 public String getId() { return id; }
 @Override public String toString(){ return getClass().getSimpleName() + "["+id+"]"; }
 }