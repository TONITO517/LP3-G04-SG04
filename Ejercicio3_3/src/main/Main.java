package main;
import model.Vehiculo;
import model.Coche;
import model.Bicicleta;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRINCIPIO DE SUSTITUCIÓN DE LISKOV (LSP) ===");
        Vehiculo[] vehiculos = {
            new Coche("Toyota", "Corolla", 10),
            new Bicicleta("Trek", "Mountain", 8),
            new Coche("Honda", "Civic", 2),
            new Bicicleta("Specialized", "Road", 2)
        };
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println("\n--- Probando: " + vehiculo.getMarca() + " " + vehiculo.getModelo() + " ---");
            vehiculo.acelerar(20);
            vehiculo.acelerar(15);
            vehiculo.frenar(10);
            
            System.out.println("Velocidad final: " + vehiculo.getVelocidad() + " km/h");
        }
        System.out.println("\n--- TEST LSP: SUSTITUCIÓN TRANSPARENTE ---");
        Vehiculo miVehiculo = new Bicicleta("BMX", "Freestyle", 7);
        miVehiculo.acelerar(30); 
        miVehiculo.frenar(15);          
        System.out.println("\n✅ ¡LSP CUMPLIDO! Todas las subclases:");
        System.out.println("1. ✅ Pueden sustituir a Vehiculo");
        System.out.println("2. ✅ No alteran el comportamiento esperado");
        System.out.println("3. ✅ No rompen el contrato de la clase base");
    }
}