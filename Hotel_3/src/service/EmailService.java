package service;

public class EmailService implements CanalNotificacion {
    
    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("📧 Email enviado a: " + destinatario);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("--- Email enviado exitosamente ---");
    }
    
    @Override
    public String getTipoCanal() {
        return "EMAIL";
    }
}