package src;
import java.util.Scanner;
class Numero {
    private double valor;   
    public Numero(double valor) {
        setValor(valor);
    }   
    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("Error: El valor no puede ser negativo");
        }
        this.valor = valor;
    }    
    public double getValor() {
        return valor;
    }  
    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
public class GestionNumeros {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Numero numero = null;
        
        System.out.println("=== GESTIÓN DE NÚMEROS NO NEGATIVOS ===");
        
        boolean continuar = true;
        while (continuar) {
            try {
                System.out.println("\n1. Crear número");
                System.out.println("2. Cambiar valor");
                System.out.println("3. Ver valor");
                System.out.println("4. Salir");
                System.out.print("Opción: ");
                
                int opcion = scanner.nextInt();
                
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese valor (≥ 0): ");
                        double valor = scanner.nextDouble();
                        numero = new Numero(valor);
                        System.out.println(" Número creado: " + numero.getValor());
                        break;
                        
                    case 2:
                        if (numero == null) {
                            System.out.println(" Primero cree un número");
                            break;
                        }
                        System.out.print("Nuevo valor (≥ 0): ");
                        double nuevoValor = scanner.nextDouble();
                        numero.setValor(nuevoValor);
                        System.out.println(" Valor actualizado: " + numero.getValor());
                        break;
                        
                    case 3:
                        if (numero == null) {
                            System.out.println(" No hay número creado");
                        } else {
                            System.out.println(" Valor actual: " + numero.getValor());
                        }
                        break;
                        
                    case 4:
                        continuar = false;
                        System.out.println(" Adiós!");
                        break;
                        
                    default:
                        System.out.println(" Opción inválida");
                }
                
            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage());
                
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
        scanner.close();
    }
}