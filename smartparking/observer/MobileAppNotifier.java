package smartparking.observer;

import smartparking.factory.ParkingSlot;
import java.time.Duration;

public class MobileAppNotifier implements ParkingObserver {
@Override public void onSlotAvailable(ParkingSlot slot){
System.out.println("[MOBILE] Slot available: " + slot.getId()); }

@Override public void onSlotExpiring(ParkingSlot slot, Durationremaining){ System.out.println("[MOBILE] Slot " + slot.getId() + " expiring
in " + remaining.toMinutes() + " mins"); }
}
