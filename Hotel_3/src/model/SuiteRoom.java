package model;
import service.CleaningService;
import service.FoodService;
import service.LaundryService;
public class SuiteRoom extends Room implements CleaningService, FoodService, LaundryService {    
    public SuiteRoom(String roomNumber, double pricePerNight, int capacity) {
        super(roomNumber, "Suite", pricePerNight, capacity);
    }
    @Override
    public void requestCleaning() {
        System.out.println("Limpieza VIP solicitada para suite " + getRoomNumber());
    }
    @Override
    public String getCleaningSchedule() {
        return "Limpieza VIP 3 veces al día";
    }
    @Override
    public void requestFoodService() {
        System.out.println("Servicio gourmet solicitado para suite " + getRoomNumber());
    }
    @Override
    public String getMenu() {
        return "Menú Gourmet: Filete, Pasta, Mariscos, Vinos";
    }
    @Override
    public double getFoodPrice(String item) {
        switch (item.toLowerCase()) {
            case "filete": return 25.99;
            case "pasta": return 15.99;
            case "mariscos": return 30.99;
            default: return 20.99;
        }
    }
    @Override
    public void requestLaundryService() {
        System.out.println("Servicio de lavandería solicitado para suite " + getRoomNumber());
    }
    @Override
    public double getLaundryPrice(String serviceType) {
        switch (serviceType.toLowerCase()) {
            case "normal": return 10.99;
            case "express": return 19.99;
            case "premium": return 29.99;
            default: return 15.99;
        }
    }
}