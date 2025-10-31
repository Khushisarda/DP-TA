package smartparking.manager;
 import smartparking.factory.*;
 import smartparking.model.Vehicle;
 import smartparking.observer.ParkingLot;
 import java.util.*;
 public class ParkingManager {
 private SlotFactory factory;
 private ParkingLot lot;
 public ParkingManager(SlotFactory factory, ParkingLot lot){ this.factory
 = factory; this.lot = lot; }
 public ParkingSlot provisionSlot(String id, String type){
 ParkingSlot s = factory.createSlot(id, type);
 lot.addSlot(s);
 return s;
 }
 // very small allocation example: first fit
 public Optional<ParkingSlot> allocate(Vehicle v){
 for(ParkingSlot s : lot.getSlots()){
 if (!s.isOccupied() && s.fits(v)) return Optional.of(s);
 }
 return Optional.empty();
 }
 }
 