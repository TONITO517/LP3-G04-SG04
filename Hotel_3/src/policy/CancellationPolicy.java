package policy;

import model.Reservation;

public interface CancellationPolicy {
    boolean canCancel(Reservation reservation);
    double calculateCancellationFee(Reservation reservation);
    String getPolicyDescription();
}