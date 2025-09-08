package model;
import java.util.ArrayList;
import java.util.List;
public class Room {
    private String roomNumber;
    private String type;
    private double pricePerNight;
    private int capacity;
    private List<String> amenities;
    public Room(String roomNumber, String type, double pricePerNight, int capacity) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.amenities = new ArrayList<>();
    }
    public double calculatePrice(int nights) {
        return pricePerNight * nights;
    }
    public boolean canAccommodate(int guests) {
        return guests <= capacity;
    }
    public String getRoomInfo() {
        return "Habitación " + roomNumber + " - " + type + " - $" + pricePerNight + "/noche";
    }
    public String getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public double getPricePerNight() { return pricePerNight; }
    public int getCapacity() { return capacity; }
    public List<String> getAmenities() { return amenities; }
    public void addAmenity(String amenity) { amenities.add(amenity); }
}