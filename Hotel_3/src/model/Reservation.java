package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import policy.CancellationPolicy;
public class Reservation {
    private String reservationId;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfGuests;
    private double totalPrice;
    private CancellationPolicy cancellationPolicy;
    private boolean cancelled;
    public Reservation(String reservationId, Room room, LocalDate checkInDate, 
                      LocalDate checkOutDate, int numberOfGuests, CancellationPolicy cancellationPolicy) {
        this.reservationId = reservationId;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.cancellationPolicy = cancellationPolicy;
        this.cancelled = false;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        int nights = (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        return room.getPricePerNight() * nights;
    }

    public CancellationResult cancel() {
        if (cancelled) {
            return new CancellationResult(false, "La reserva ya está cancelada", 0);
        }
        
        if (!cancellationPolicy.canCancel(this)) {
            double fee = cancellationPolicy.calculateCancellationFee(this);
            return new CancellationResult(false, "No se puede cancelar según la política", fee);
        }
        
        double fee = cancellationPolicy.calculateCancellationFee(this);
        this.cancelled = true;
        return new CancellationResult(true, "Cancelación exitosa", fee);
    }

    public static class CancellationResult {
        private final boolean success;
        private final String message;
        private final double fee;
        
        public CancellationResult(boolean success, String message, double fee) {
            this.success = success;
            this.message = message;
            this.fee = fee;
        }
        
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public double getFee() { return fee; }
    }

    public String getReservationId() { return reservationId; }
    public Room getRoom() { return room; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public int getNumberOfGuests() { return numberOfGuests; }
    public double getTotalPrice() { return totalPrice; }
    public CancellationPolicy getCancellationPolicy() { return cancellationPolicy; }
    public boolean isCancelled() { return cancelled; }
}