package smartparking.observer;

import smartparking.factory.ParkingSlot;
import java.time.Duration;

public class DisplayBoard implements ParkingObserver {
    @Override
    public void onSlotAvailable(ParkingSlot slot) {
        System.out.println("[DISPLAY] Slot " + slot.getId() + " -> AVAILABLE");
    }

    @Override
    public void onSlotExpiring(ParkingSlot slot, Duration remaining) {
        /* ignore */ }
}
