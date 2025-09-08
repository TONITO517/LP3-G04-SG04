package model;

import service.CleaningService;

public class StandardRoom extends Room implements CleaningService {
    
    public StandardRoom(String roomNumber, double pricePerNight, int capacity) {
        super(roomNumber, "Standard", pricePerNight, capacity);
    }
    @Override
    public void requestCleaning() {
        System.out.println("Limpieza solicitada para habitación " + getRoomNumber());
    }
    @Override
    public String getCleaningSchedule() {
        return "Limpieza diaria de 9:00 AM a 2:00 PM";
    }
}