package service;

public interface CanalNotificacion {
    void enviarNotificacion(String destinatario, String mensaje);
    String getTipoCanal();
}