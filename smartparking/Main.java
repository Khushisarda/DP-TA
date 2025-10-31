package smartparking;

import smartparking.factory.*;
import smartparking.model.*;
import smartparking.decorator.*;
import smartparking.observer.*;
import smartparking.manager.*;

import java.time.*;
import java.util.*;

public class Main {
 private static final Scanner sc = new Scanner(System.in);

 public static void main(String[] args) {
  ParkingLot lot = new ParkingLot();
  SlotFactory factory = new ZoneSlotFactory();
  ParkingManager manager = new ParkingManager(factory, lot);

  // observers
  lot.addObserver(new MobileAppNotifier());
  lot.addObserver(new DisplayBoard());

  // default fee structure
  Fee feeEngine = new NightDiscountDecorator(
          new ZoneMultiplierDecorator(
                  new VehicleTypeDecorator(new BaseFee(50)), 1.2)
  );

  Map<String, LocalDateTime> parkTimes = new HashMap<>();
  System.out.println("🚗 Smart Parking System — Interactive Mode\n");

  while (true) {
   System.out.println("\n1) Add Slot  2) List  3) Park  4) Unpark  5) Expiry  6) Exit");
   System.out.print("> ");
   switch (sc.nextLine().trim()) {
    case "1": addSlot(manager); break;
    case "2": showSlots(lot); break;
    case "3": parkVehicle(manager, lot, parkTimes); break;
    case "4": unparkVehicle(lot, parkTimes, feeEngine); break;
    case "5": expiry(lot); break;
    case "6": System.out.println("Bye!"); return;
    default: System.out.println("Invalid choice.");
   }
  }
 }

 private static void addSlot(ParkingManager m) {
  System.out.print("Slot ID: "); String id = sc.nextLine();
  System.out.print("Type (COMPACT/EV/LARGE): "); String type = sc.nextLine();
  m.provisionSlot(id, type);
  System.out.println("✅ Slot added: " + id);
 }

 private static void showSlots(ParkingLot lot) {
  System.out.println("📋 Slots:");
  lot.getSlots().forEach(s -> System.out.printf("  %s (%s)\n", s.getId(), s.isOccupied() ? "occupied" : "free"));
 }

 private static void parkVehicle(ParkingManager m, ParkingLot lot, Map<String, LocalDateTime> parkTimes) {
  System.out.print("Vehicle No: "); String reg = sc.nextLine();
  System.out.print("Size (COMPACT/MEDIUM/LARGE): "); VehicleSize size = VehicleSize.valueOf(sc.nextLine().toUpperCase());
  System.out.print("EV? (y/n): "); boolean ev = sc.nextLine().equalsIgnoreCase("y");

  Vehicle v = new Vehicle(reg, size, ev);
  Optional<ParkingSlot> slot = m.allocate(v);
  if (slot.isEmpty()) { System.out.println("❌ No slot available."); return; }

  slot.get().occupy();
  parkTimes.put(slot.get().getId(), LocalDateTime.now());
  System.out.println("✅ Parked " + reg + " in " + slot.get().getId());
 }

 private static void unparkVehicle(ParkingLot lot, Map<String, LocalDateTime> parkTimes, Fee feeEngine) {
  System.out.print("Slot ID to unpark: "); String id = sc.nextLine();
  ParkingSlot slot = lot.getSlots().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
  if (slot == null || !slot.isOccupied()) { System.out.println("❌ Invalid or already free."); return; }

  LocalDateTime start = parkTimes.getOrDefault(id, LocalDateTime.now().minusHours(2));
  Duration dur = Duration.between(start, LocalDateTime.now());
  Vehicle v = new Vehicle("TEMP", VehicleSize.COMPACT, false);
  FeeContext ctx = new FeeContext(v, slot, dur, start);
  System.out.printf("💰 Fee: Rs %.2f (%d mins)\n", feeEngine.calculate(ctx), dur.toMinutes());

  lot.freeSlot(slot);
  parkTimes.remove(id);
 }

 private static void expiry(ParkingLot lot) {
  System.out.print("Slot ID: "); String id = sc.nextLine();
  lot.getSlots().stream().filter(s -> s.getId().equals(id)).findFirst()
          .ifPresent(s -> lot.notifyExpiring(s, Duration.ofMinutes(10)));
 }
}
