package vista_ej2_3;


import java.util.List;
import java.util.Scanner;

import modelo_ej2_3.Item;

public class InventarioVista {
    private Scanner scanner;

    public InventarioVista() {
        scanner = new Scanner(System.in);
    }
    public void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE INVENTARIO ===");
        System.out.println("1. Mostrar Inventario");
        System.out.println("2. Agregar Item");
        System.out.println("3. Eliminar Item");
        System.out.println("4. Usar Item");
        System.out.println("5. Buscar Item");
        System.out.println("6. Mostrar Detalles de Item");
        System.out.println("7. Salir");
    }

    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }
    public void mostrarInventario(List<Item> items) {
        System.out.println("\n=== INVENTARIO ===");
        if (items.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
        }
    }
    public String solicitarNombre() {
        System.out.print("Nombre del item: ");
        return scanner.nextLine();
    }

    public int solicitarCantidad() {
        System.out.print("Cantidad: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 1;
        }
    }
    public String solicitarTipo() {
        System.out.print("Tipo (Arma, Pocion, Armadura, etc.): ");
        return scanner.nextLine();
    }
    public String solicitarDescripcion() {
        System.out.print("Descripción: ");
        return scanner.nextLine();
    }
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    public void mostrarDetallesItem(String detalles) {
        System.out.println("\n" + detalles);
    }
}
