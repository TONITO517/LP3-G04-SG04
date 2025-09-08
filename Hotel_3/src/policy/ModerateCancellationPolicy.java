package policy;

import java.time.LocalDateTime;
import model.Reservation;
public class ModerateCancellationPolicy implements CancellationPolicy {  
    @Override
    public boolean canCancel(Reservation reservation) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime checkInDateTime = reservation.getCheckInDate().atTime(14, 0);
        return now.isBefore(checkInDateTime.minusHours(72));
    }
    
    @Override
    public double calculateCancellationFee(Reservation reservation) {
        if (canCancel(reservation)) {
            return reservation.getTotalPrice() * 0.5; // 50% de penalización
        } else {
            return reservation.getTotalPrice(); // 100% de penalización
        }
    }    
    @Override
    public String getPolicyDescription() {
        return "Cancelación con 50% de cargo hasta 72 horas antes. Sin reembolso después.";
    }
}