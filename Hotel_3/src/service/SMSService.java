package service;

public class SMSService implements CanalNotificacion {
    
    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("📱 SMS enviado a: " + destinatario);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("--- SMS enviado exitosamente ---");
    }
    
    @Override
    public String getTipoCanal() {
        return "SMS";
    }
}