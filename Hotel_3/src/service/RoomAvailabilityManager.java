package service;  // ✅ IMPORTANTE: package service

import java.time.LocalDate;
import java.util.List;
import model.Room;
import model.Reservation;

public class RoomAvailabilityManager {
    private List<Reservation> reservations;
    
    public RoomAvailabilityManager(List<Reservation> reservations) {
        this.reservations = reservations;
    } 
    public boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        return true;
    }
}