package miproyecto;

public class TestAgregacion {
    public static void main(String[] args) {

        Motor motor1 = new Motor(12345, 3000);
        Motor motor2 = new Motor(67890, 2500);
        Motor motor3 = new Motor(54321, 3500);

        Automovil auto1 = new Automovil("ABC123", 4, "Toyota", "Corolla");
        Automovil auto2 = new Automovil("XYZ789", 2, "Honda", "Civic");
        Automovil auto3 = new Automovil("DEF456", 5, "Nissan", "Sentra");

        auto1.setMotor(motor1);
        auto2.setMotor(motor2);
        auto3.setMotor(motor3);

        System.out.println("=== AUTOMÓVIL 1 ===");
        System.out.println(auto1.toString());
        System.out.println("\n=== AUTOMÓVIL 2 ===");
        System.out.println(auto2.toString());
        System.out.println("\n=== AUTOMÓVIL 3 ===");
        System.out.println(auto3.toString());

        System.out.println("\n=== INFORMACIÓN ESPECÍFICA DEL MOTOR ===");
        System.out.println("Motor del Auto 1: " + auto1.getMotor().getNumMotor());
        System.out.println("RPM del Auto 2: " + auto2.getMotor().getRevPorMin());

        Motor motorNuevo = new Motor(99999, 4000);
        auto1.setMotor(motorNuevo);
        System.out.println("\n=== DESPUÉS DE CAMBIAR EL MOTOR ===");
        System.out.println("Nuevo motor del Auto 1: " + auto1.getMotor().toString());
    }
}