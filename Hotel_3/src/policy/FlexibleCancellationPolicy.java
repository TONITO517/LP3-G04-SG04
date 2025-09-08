package policy;

import java.time.LocalDateTime;
import model.Reservation;

public class FlexibleCancellationPolicy implements CancellationPolicy {   
    @Override
    public boolean canCancel(Reservation reservation) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime checkInDateTime = reservation.getCheckInDate().atTime(14, 0); // Check-in a las 2 PM
        return now.isBefore(checkInDateTime.minusHours(24));
    }  
    @Override
    public double calculateCancellationFee(Reservation reservation) {
        if (canCancel(reservation)) {
            return 0; 
        } else {
            return reservation.getTotalPrice() * 0.2; 
        }
    }   
    @Override
    public String getPolicyDescription() {
        return "Cancelación gratuita hasta 24 horas antes del check-in. 20% de cargo después.";
    }
}