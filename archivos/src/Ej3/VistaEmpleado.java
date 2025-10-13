package Ej3;

import java.util.List;
import java.util.Scanner;

public class VistaEmpleado {
    private Scanner scanner;
    
    public VistaEmpleado() {
        scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        System.out.println("\n SISTEMA DE GESTIÓN DE EMPLEADOS");
        System.out.println("1. Listar todos los empleados");
        System.out.println("2. Agregar nuevo empleado");
        System.out.println("3. Buscar empleado por número");
        System.out.println("4. Eliminar empleado por número");
        System.out.println("5. Salir del programa");
        System.out.print("Seleccione una opción: ");
    }
    
    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public Empleado leerDatosEmpleado() {
        try {
            System.out.print("Número de empleado: ");
            int numero = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Sueldo: ");
            double sueldo = Double.parseDouble(scanner.nextLine());
            
            return new Empleado(numero, nombre, sueldo);
        } catch (NumberFormatException e) {
            System.out.println(" Error: Datos numéricos inválidos");
            return null;
        }
    }
    
    public int leerNumeroEmpleado() {
        try {
            System.out.print("Número de empleado: ");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" Error: Número inválido");
            return -1;
        }
    }
    
    public void mostrarEmpleados(List<Empleado> empleados) {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados");
            return;
        }
        
        System.out.println("\n LISTA DE EMPLEADOS:");
        for (Empleado emp : empleados) {
            System.out.println(emp);
        }
    }
    
    public void mostrarEmpleado(Empleado empleado) {
        if (empleado != null) {
            System.out.println(" EMPLEADO ENCONTRADO:");
            System.out.println(empleado);
        } else {
            System.out.println(" Empleado no encontrado");
        }
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    public void cerrarScanner() {
        scanner.close();
    }
}