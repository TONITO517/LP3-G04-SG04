package src;
import java.util.Scanner;

class DivisionPorCeroException extends Exception {
    public DivisionPorCeroException(String mensaje) {
        super(mensaje);
    }
}
class Calculadora {
    public double sumar(double a, double b) {
        return a + b;
    }
    public double restar(double a, double b) {
        return a - b;
    }
    public double multiplicar(double a, double b) {
        return a * b;
    }
    public double dividir(double a, double b) throws DivisionPorCeroException {
        if (b == 0) {
            throw new DivisionPorCeroException("Error: No se puede dividir por cero");
        }
        return a / b;
    }
}
public class CalculadoraBasica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculadora calc = new Calculadora();
        
        System.out.println("=== CALCULADORA BÁSICA ===");
        
        boolean continuar = true;
        while (continuar) {
            try {
                System.out.println("\nOperaciones disponibles:");
                System.out.println("1. Suma");
                System.out.println("2. Resta");
                System.out.println("3. Multiplicación");
                System.out.println("4. División");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                
                int opcion = scanner.nextInt();
                
                if (opcion == 5) {
                    continuar = false;
                    System.out.println("Saliendo de la calculadora...");
                    continue;
                }               
                if (opcion < 1 || opcion > 5) {
                    throw new IllegalArgumentException("Error: Opción inválida");
                }                
                System.out.print("Ingrese el primer número: ");
                double num1 = scanner.nextDouble();
                
                System.out.print("Ingrese el segundo número: ");
                double num2 = scanner.nextDouble();
                
                double resultado;
                
                switch (opcion) {
                    case 1:
                        resultado = calc.sumar(num1, num2);
                        System.out.println(" Resultado: " + num1 + " + " + num2 + " = " + resultado);
                        break;                       
                    case 2:
                        resultado = calc.restar(num1, num2);
                        System.out.println(" Resultado: " + num1 + " - " + num2 + " = " + resultado);
                        break;                     
                    case 3:
                        resultado = calc.multiplicar(num1, num2);
                        System.out.println("Resultado: " + num1 + " * " + num2 + " = " + resultado);
                        break;                        
                    case 4:
                        resultado = calc.dividir(num1, num2);
                        System.out.println(" Resultado: " + num1 + " / " + num2 + " = " + resultado);
                        break;
                }
                
            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage());
                scanner.nextLine();              
            } catch (DivisionPorCeroException e) {
                System.out.println("❌ " + e.getMessage());
                
            } catch (ArithmeticException e) {
                System.out.println("Error aritmético: " + e.getMessage());
                
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                scanner.nextLine(); 
            }
        }     
        scanner.close();
    }
}