package vista_ej1;

import modelo_ej1.Producto;
import modelo_ej1.Carrito;
import java.util.List;
import java.util.Scanner;

public class TiendaVista {
    private Scanner scanner;

    public TiendaVista() {
        scanner = new Scanner(System.in);
    }
    public void mostrarMenuPrincipal() {
        System.out.println("\nTIENDA ONLINE :");
        System.out.println("1. Listar Productos");
        System.out.println("2. Agregar Producto al Carrito");
        System.out.println("3. Ver Carrito");
        System.out.println("4. Eliminar Producto del Carrito");
        System.out.println("5. Aplicar Descuento");
        System.out.println("6. Realizar Compra");
        System.out.println("7. Ver Historial de Compras");
        System.out.println("8. Salir");
    }
    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }

    public void mostrarProductos(List<Producto> productos) {
        System.out.println("\n=== PRODUCTOS DISPONIBLES ===");
        for (int i = 0; i < productos.size(); i++) {
            System.out.println((i + 1) + ". " + productos.get(i));
        }
    }

    public int solicitarNumeroProducto() {
        System.out.print("Selecciona el número del producto: ");
        try {
            return Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

 
    public void mostrarCarrito(Carrito carrito) {
        System.out.println("\n=== TU CARRITO ===");
        List<Producto> productos = carrito.getProductos();
        
        if (productos.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            for (int i = 0; i < productos.size(); i++) {
                System.out.println((i + 1) + ". " + productos.get(i));
            }
            System.out.println("─────────────────────────");
            System.out.println("Subtotal: $" + carrito.getSubtotal());
            System.out.println("Descuento: -$" + carrito.getDescuento());
            System.out.println("Envío: $" + carrito.getCostoEnvio());
            System.out.println("TOTAL: $" + carrito.getTotal());
        }
    }
    public int solicitarEliminarProducto() {
        System.out.print("Número del producto a eliminar: ");
        try {
            return Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public double solicitarDescuento() {
        System.out.print("Porcentaje de descuento: ");
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public void mostrarHistorial(List<String> compras) {
        System.out.println("\n=== HISTORIAL DE COMPRAS ===");
        if (compras.isEmpty()) {
            System.out.println("No hay compras registradas.");
        } else {
            for (int i = 0; i < compras.size(); i++) {
                System.out.println((i + 1) + ". " + compras.get(i));
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    public void cerrarScanner() {
        scanner.close();
    }
}