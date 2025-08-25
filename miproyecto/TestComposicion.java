package miproyecto;

public class TestComposicion {

	public static void main(String[] args) {
        Persona persona1 = new Persona(1, "Juan", "Pérez");
        Persona persona2 = new Persona(2, "María", "Gómez");
        Persona persona3 = new Persona(3, "Carlos", "López");
        System.out.println("=== PERSONA 1 ===");
        System.out.println(persona1.toString());
        
        System.out.println("\n=== PERSONA 2 ===");
        System.out.println(persona2.toString());
        
        System.out.println("\n=== PERSONA 3 ===");
        System.out.println(persona3.toString());
        System.out.println("\n=== OPERACIONES CON CUENTAS ===");
                System.out.println("Saldo inicial de " + persona1.getNombre() + ": $" + 
                          persona1.getCuenta().getSaldo());
                persona1.getCuenta().setSaldo(1000.50);
        System.out.println("Saldo después de depósito: $" + persona1.getCuenta().getSaldo());
                System.out.println("\nNúmero de cuenta de " + persona1.getNombre() + ": " + 
                          persona1.getCuenta().getNumero());
        System.out.println("Número de cuenta de " + persona2.getNombre() + ": " + 
                          persona2.getCuenta().getNumero());
    }
}
