package main;

import java.time.LocalDate;
import model.Room;
import model.StandardRoom;
import controller.ReservationController;
import service.EmailService;
import service.SMSService;
import service.SlackService;
import service.CanalNotificacion;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRINCIPIO DE INVERSIÓN DE DEPENDENCIAS (DIP) ===");

        CanalNotificacion emailService = new EmailService();
        CanalNotificacion smsService = new SMSService();
        CanalNotificacion slackService = new SlackService();

        System.out.println("\n--- USANDO EMAIL SERVICE ---");
        ReservationController controllerEmail = new ReservationController(emailService);
        testReservation(controllerEmail, "cliente1@email.com");

        System.out.println("\n--- CAMBIANDO A SMS SERVICE ---");
        controllerEmail.cambiarCanalNotificacion(smsService);
        testReservation(controllerEmail, "cliente2@email.com");

        System.out.println("\n--- USANDO SLACK SERVICE ---");
        ReservationController controllerSlack = new ReservationController(slackService);
        testReservation(controllerSlack, "@cliente3");
        
        System.out.println("\n✅ ¡DIP CUMPLIDO! El controlador depende de abstracciones, no de implementaciones concretas");
    }  
    private static void testReservation(ReservationController controller, String clienteEmail) {
        try {
            Room room = new StandardRoom("101", 50.0, 2);
            controller.createReservation(room, 
                LocalDate.of(2024, 6, 15), 
                LocalDate.of(2024, 6, 20), 
                2, "FLEXIBLE", clienteEmail);

            controller.cancelReservation("RES1", clienteEmail, "Cambio de planes");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}