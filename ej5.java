import java.util.Scanner;
public class CalculadoraEstacionamiento {
    public static double calcularCosto(int horas) {
        final double PRIMERA_HORA = 3.00;
        final double HORAS_SIGUIENTES = 0.50;
        final double MAXIMO_DIARIO = 12.00;        
        if (horas <= 0) {
            return 0.00;
        }        
        double costo = PRIMERA_HORA;        
        if (horas > 1) {
            costo += (horas - 1) * HORAS_SIGUIENTES;
        }        
        if (costo > MAXIMO_DIARIO) {
            costo = MAXIMO_DIARIO;
        }        
        return costo;
    }   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);       
        System.out.print("Ingrese el número de horas estacionado: ");
        int horas = scanner.nextInt();     
        double cargo = calcularCosto(horas);   
        System.out.printf("El cargo por %d horas es: S/%.2f%n", horas, cargo);
        scanner.close();
    }
}
