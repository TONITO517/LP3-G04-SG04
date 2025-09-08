package policy;

import model.Reservation;

public class StrictCancellationPolicy implements CancellationPolicy {  
    @Override
    public boolean canCancel(Reservation reservation) {
        return false;
    }
    @Override
    public double calculateCancellationFee(Reservation reservation) {
        return reservation.getTotalPrice(); 
    }
    @Override
    public String getPolicyDescription() {
        return "No se permiten cancelaciones después de realizar la reserva.";
    }
}