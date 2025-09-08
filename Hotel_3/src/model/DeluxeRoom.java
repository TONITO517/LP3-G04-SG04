package model;
import service.CleaningService;
import service.FoodService;
public class DeluxeRoom extends Room implements CleaningService, FoodService { 
    public DeluxeRoom(String roomNumber, double pricePerNight, int capacity) {
        super(roomNumber, "Deluxe", pricePerNight, capacity);
    }
    @Override
    public void requestCleaning() {
        System.out.println("Limpieza premium solicitada para habitación " + getRoomNumber());
    }
    @Override
    public String getCleaningSchedule() {
        return "Limpieza premium 2 veces al día";
    }
    @Override
    public void requestFoodService() {
        System.out.println("Servicio de comida solicitado para habitación " + getRoomNumber());
    }
    @Override
    public String getMenu() {
        return "Menú Deluxe: Pizza, Hamburguesas, Ensaladas, Bebidas";
    }
    @Override
    public double getFoodPrice(String item) {
        switch (item.toLowerCase()) {
            case "pizza": return 12.99;
            case "hamburguesa": return 8.99;
            case "ensalada": return 6.99;
            default: return 5.99;
        }
    }
}