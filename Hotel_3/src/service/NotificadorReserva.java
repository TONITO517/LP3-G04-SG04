package service;

public class NotificadorReserva {
    private CanalNotificacion canalNotificacion;
    public NotificadorReserva(CanalNotificacion canalNotificacion) {
        this.canalNotificacion = canalNotificacion;
        System.out.println("Notificador configurado con canal: " + canalNotificacion.getTipoCanal());
    }

    public void notificarReservaConfirmada(String clienteEmail, String reservaId, String detalles) {
        String mensaje = "Reserva CONFIRMADA\nID: " + reservaId + "\nDetalles: " + detalles;
        canalNotificacion.enviarNotificacion(clienteEmail, mensaje);
    }  
    public void notificarReservaCancelada(String clienteEmail, String reservaId, String motivo) {
        String mensaje = " Reserva CANCELADA\nID: " + reservaId + "\nMotivo: " + motivo;
        canalNotificacion.enviarNotificacion(clienteEmail, mensaje);
    }
    public void notificarCheckIn(String clienteEmail, String reservaId, String horaCheckIn) {
        String mensaje = " Check-IN realizado\nID: " + reservaId + "\nHora: " + horaCheckIn;
        canalNotificacion.enviarNotificacion(clienteEmail, mensaje);
    }
    public void cambiarCanalNotificacion(CanalNotificacion nuevoCanal) {
        this.canalNotificacion = nuevoCanal;
        System.out.println("Canal cambiado a: " + nuevoCanal.getTipoCanal());
    }
}