package smartparking;
 import smartparking.factory.*;
 import smartparking.model.*;
 import smartparking.decorator.*;
 import smartparking.observer.*;
 import smartparking.manager.*;
 import java.time.*;
 import java.util.*;
 public class Main {
 public static void main(String[] args) {
 System.out.println("SmartParking multi-file demo\n");
 // setup
 8
ParkingLot lot = new ParkingLot();
 SlotFactory factory = new ZoneSlotFactory();
 ParkingManager manager = new ParkingManager(factory, lot);
 // create slots (factory method)
 manager.provisionSlot("A1", "COMPACT");
 manager.provisionSlot("A2", "EV");
 manager.provisionSlot("A3", "LARGE");
 // register observers
 lot.addObserver(new MobileAppNotifier());
 lot.addObserver(new DisplayBoard());
 // simulate parking
 Vehicle v = new Vehicle("MH12AB1234", VehicleSize.COMPACT, false);
 Optional<ParkingSlot> maybe = manager.allocate(v);
 if (maybe.isPresent()){
 ParkingSlot slot = maybe.get();
 System.out.println("Parking " + v + " into " + slot.getId());
 lot.occupySlot(slot);
 // simulate leaving after 125 minutes
 Duration dur = Duration.ofMinutes(125);
 LocalDateTime start = LocalDateTime.of(2025,10,29,23,0);
 // build fee engine (decorator)
 Fee feeEngine = new NightDiscountDecorator(
 new ZoneMultiplierDecorator(
 new VehicleTypeDecorator(
 new BaseFee(50)
 ), 1.2)
 );
 FeeContext ctx = new FeeContext(v, slot, dur, start);
 double amount = feeEngine.calculate(ctx);
 System.out.println("Parking fee = Rs " + amount);
 // free slot -> observers notified
 lot.freeSlot(slot);
 } else {
 System.out.println("No slot available for " + v);
 }
 // expiry example
 // find EV slot and notify expiry
 for (ParkingSlot s : lot.getSlots()){
 if (s.getId().equals("A2")){
 lot.occupySlot(s);
 lot.notifyExpiring(s, Duration.ofMinutes(10));
 }
 }
 9
System.out.println("\nDemo finished.");
 }
 }