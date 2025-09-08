package controller;

import model.Room;
import service.CleaningService;
import service.FoodService;
import service.LaundryService;

public class RoomServiceController {
    public void requestCleaningService(Room room) {
        if (room instanceof CleaningService) {
            CleaningService service = (CleaningService) room;
            service.requestCleaning();
            System.out.println("Horario: " + service.getCleaningSchedule());
        } else {
            System.out.println("Servicio de limpieza no disponible para " + room.getRoomNumber());
        }
    }    
    public void requestFoodService(Room room, String foodItem) {
        if (room instanceof FoodService) {
            FoodService service = (FoodService) room;
            service.requestFoodService();
            double price = service.getFoodPrice(foodItem);
            System.out.println("Precio de " + foodItem + ": $" + price);
        } else {
            System.out.println("Servicio de comida no disponible para " + room.getRoomNumber());
        }
    }    
    public void requestLaundryService(Room room, String serviceType) {
        if (room instanceof LaundryService) {
            LaundryService service = (LaundryService) room;
            service.requestLaundryService();
            double price = service.getLaundryPrice(serviceType);
            System.out.println("Precio lavandería " + serviceType + ": $" + price);
        } else {
            System.out.println("Servicio de lavandería no disponible para " + room.getRoomNumber());
        }
    }

    public void showAvailableServices(Room room) {
        System.out.println("\nServicios disponibles para " + room.getRoomNumber() + ":");        
        if (room instanceof CleaningService) {
            System.out.println("✓ Servicio de limpieza");
        }
        if (room instanceof FoodService) {
            System.out.println("✓ Servicio de comida");
        }
        if (room instanceof LaundryService) {
            System.out.println("✓ Servicio de lavandería");
        }       
        if (!(room instanceof CleaningService) && 
            !(room instanceof FoodService) && 
            !(room instanceof LaundryService)) {
            System.out.println("✗ No tiene servicios adicionales");
        }
    }
}