package service;

import java.time.LocalDate;
import model.Room;

public class PriceCalculator {
    
    public double calculateFinalPrice(Room room, int nights, LocalDate checkInDate) {
        double basePrice = room.calculateBasePrice(nights);

        if (isHighSeason(checkInDate)) {
            basePrice *= 1.2; 
        }
        
        return basePrice;
    }
    private boolean isHighSeason(LocalDate date) {
        return date.getMonthValue() >= 6 && date.getMonthValue() <= 8;
    }
    public double applyPromotion(double price, double discountPercentage) {
        return price * (1 - discountPercentage / 100);
    }
}