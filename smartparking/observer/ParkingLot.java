package smartparking.observer;

import smartparking.factory.ParkingSlot;
import java.time.Duration;
import java.util.*;

public class ParkingLot {
    private List<ParkingObserver> observers = new ArrayList<>();
    private List<ParkingSlot> slots = new ArrayList<>();

    public void addObserver(ParkingObserver o) {
        observers.add(o);
    }

    public void removeObserver(ParkingObserver o) {
        observers.remove(o);
    }

    public void addSlot(ParkingSlot s) {
        slots.add(s);
    }

    public void occupySlot(ParkingSlot s) {
        s.occupy();
    }

    public void freeSlot(ParkingSlot s) {
        s.free();
        notifyAvailable(s);
    }

    public void notifyAvailable(ParkingSlot s) {
        for (ParkingObserver o : observers)
            o.onSlotAvailable(s);
    }

    public void notifyExpiring(ParkingSlot s, Duration remaining) {
        for (ParkingObserver o : observers)
            o.onSlotExpiring(s, remaining);
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }
}
