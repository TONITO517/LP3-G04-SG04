package service;

public class SlackService implements CanalNotificacion {
    
    @Override
    public void enviarNotificacion(String destinatario, String mensaje) {
        System.out.println("💬 Slack message to: " + destinatario);
        System.out.println("Message: " + mensaje);
        System.out.println("--- Slack message sent successfully ---");
    }
    
    @Override
    public String getTipoCanal() {
        return "SLACK";
    }
}