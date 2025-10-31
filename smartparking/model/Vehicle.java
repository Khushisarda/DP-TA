package smartparking.model;
public class Vehicle {
    private String regNo;
    private VehicleSize size;
    private boolean electric;
    public Vehicle(String regNo, VehicleSize size, boolean electric) {
        this.regNo = regNo;
        this.size = size;
        this.electric = electric;
    }
    public String getRegNo() { return regNo; }
    public VehicleSize getSize() { return size; }
    public boolean isElectric() { return electric; }
    @Override public String toString(){
        return regNo + " (" + size + (electric?", EV":"") + ")";
    }
}