package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Room;
import model.Reservation;
import policy.CancellationPolicy;
import policy.FlexibleCancellationPolicy;
import policy.ModerateCancellationPolicy;
import policy.StrictCancellationPolicy;
import service.CanalNotificacion;
import service.NotificadorReserva;
import service.RoomAvailabilityManager;

public class ReservationController {
    private List<Reservation> reservations;
    private RoomAvailabilityManager availabilityManager;
    private NotificadorReserva notificador;
    
    public ReservationController(CanalNotificacion canalNotificacion) {
        this.reservations = new ArrayList<>();
        this.availabilityManager = new RoomAvailabilityManager(reservations);
        this.notificador = new NotificadorReserva(canalNotificacion);
    }    
    public Reservation createReservation(Room room, LocalDate checkIn, LocalDate checkOut, 
                                       int guests, String policyType, String clienteEmail) {     
        if (!availabilityManager.isRoomAvailable(room, checkIn, checkOut)) {
            throw new RuntimeException("Habitación no disponible");
        }     
        String reservationId = "RES" + (reservations.size() + 1);
        CancellationPolicy policy = createCancellationPolicy(policyType);
        
        Reservation reservation = new Reservation(reservationId, room, checkIn, checkOut, guests, policy);
        reservations.add(reservation);
        String detalles = room.getRoomNumber() + " - " + checkIn + " al " + checkOut;
        notificador.notificarReservaConfirmada(clienteEmail, reservationId, detalles);
        
        return reservation;
    }
    
    private CancellationPolicy createCancellationPolicy(String policyType) {
        if (policyType.equalsIgnoreCase("FLEXIBLE")) {
            return new FlexibleCancellationPolicy();
        } else if (policyType.equalsIgnoreCase("MODERATE")) {
            return new ModerateCancellationPolicy();
        } else if (policyType.equalsIgnoreCase("STRICT")) {
            return new StrictCancellationPolicy();
        } else {
            throw new RuntimeException("Política no válida: " + policyType);
        }
    }
    
    public String cancelReservation(String reservationId, String clienteEmail, String motivo) {
        for (Reservation res : reservations) {
            if (res.getReservationId().equals(reservationId)) {
                if (res.getCancellationPolicy().canCancel(res)) {
                    double fee = res.getCancellationPolicy().calculateCancellationFee(res);  // ✅ calculate
                    notificador.notificarReservaCancelada(clienteEmail, reservationId, motivo);
                    return "Cancelación exitosa. Tarifa: $" + fee;
                } else {
                    double fee = res.getCancellationPolicy().calculateCancellationFee(res);  // ✅ calculate
                    return "No se puede cancelar. Tarifa completa: $" + fee;
                }
            }
        }
        return "Reserva no encontrada";
    }
    
    public void cambiarCanalNotificacion(CanalNotificacion nuevoCanal) {
        notificador.cambiarCanalNotificacion(nuevoCanal);
    }
    
    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }
}