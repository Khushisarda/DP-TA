package smartparking.observer;

import smartparking.factory.ParkingSlot;
import java.time.Duration;

public interface ParkingObserver {
    void onSlotAvailable(ParkingSlot slot);

    void onSlotExpiring(ParkingSlot slot, Duration remaining);
}