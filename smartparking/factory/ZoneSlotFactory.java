 package smartparking.factory;
 public class ZoneSlotFactory extends SlotFactory {
 @Override
 public ParkingSlot createSlot(String id, String type){
 if (type == null) return new LargeSlot(id);
 switch(type.toUpperCase()){
 case "COMPACT": return new CompactSlot(id);
 case "EV": return new EVSlot(id);
 case "LARGE":
 default: return new LargeSlot(id);
 }
 }
 }