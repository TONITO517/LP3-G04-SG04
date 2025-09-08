package controller;

import java.util.ArrayList;
import java.util.List;
import model.Room;
public class RoomController {
    private List<Room> rooms;
    
    public RoomController() {
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Added: " + room.getRoomInfo());
    }
    public double calculateTotalCost(Room room, int nights, int guests) {
        if (!room.canAccommodate(guests)) {
            throw new RuntimeException("No puede acomodar " + guests + " huéspedes");
        }    
        return room.calculatePrice(nights);
    }

    public void showAllRooms() {
        System.out.println("\n--- TODAS LAS HABITACIONES ---");
        for (Room room : rooms) {
            System.out.println(room.getRoomInfo() + 
                " - Capacidad: " + room.getCapacity());
        }
    }   
    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }
}