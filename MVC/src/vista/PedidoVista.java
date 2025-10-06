package vista;
import modelo.Pedido;
import modelo.PedidoModelo;
import java.util.List;
import java.util.Scanner;
public class PedidoVista {
    private Scanner scanner;

    public PedidoVista() {
        scanner = new Scanner(System.in);
    }
    public String solicitarNombrePlato() {
        System.out.print("Introduce el nombre del plato: ");
        return scanner.nextLine();
    }

    public String solicitarTipoPlato() {
        System.out.print("Introduce el tipo de plato (ej entrada, principal, postre, bebida): ");
        return scanner.nextLine();
    }
    public void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos en la lista.");
        } else {
            System.out.println("Lista de Pedidos:");
            for (int i = 0; i < pedidos.size(); i++) {
                Pedido pedido = pedidos.get(i);
                System.out.println((i + 1) + ". " + pedido.getNombrePlato() + 
                                 " - Tipo: " + pedido.getTipo() + 
                                 " - Estado: " + pedido.getEstado());
            }
        }
    }

    public void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE PEDIDOS ===");
        System.out.println("1. Agregar Pedido");
        System.out.println("2. Mostrar Todos los Pedidos");
        System.out.println("3. Eliminar Pedido");
        System.out.println("4. Actualizar Pedido");
        System.out.println("5. Buscar Pedido por Nombre");
        System.out.println("6. Buscar Pedido por Tipo");
        System.out.println("7. Estadísticas de Pedidos");
        System.out.println("8. Marcar Pedido como Completo");    
        System.out.println("9. Mostrar Pedidos por Estado");     
        System.out.println("10. Mostrar Pedidos Pendientes");    
        System.out.println("11. Mostrar Historial");             
        System.out.println("12. Salir");
    }

    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public int solicitarIndice() {
        System.out.print("Introduce el número del pedido: ");
        try {
            return Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String solicitarNuevoNombre() {
        System.out.print("Introduce el nuevo nombre del plato: ");
        return scanner.nextLine();
    }

    public String solicitarNombreBusqueda() {
        System.out.print("Introduce el nombre a buscar: ");
        return scanner.nextLine();
    }

    public String solicitarTipoBusqueda() {
        System.out.print("Introduce el tipo a buscar: ");
        return scanner.nextLine();
    }

    public void mostrarEstadisticas(int total, List<String> tipos, PedidoModelo modelo) {
        System.out.println("\n=== ESTADÍSTICAS DE PEDIDOS ===");
        System.out.println("Total de pedidos: " + total);
        System.out.println("Pedidos pendientes: " + modelo.contarPedidosPendientes()); 
        System.out.println("Pedidos por tipo:");
        for (String tipo : tipos) {
            int cantidad = modelo.contarPedidosPorTipo(tipo);
            System.out.println("- " + tipo + ": " + cantidad);
        }
    }

    public String solicitarEstado() {
        System.out.print("Introduce el estado a filtrar (pendiente/completo): ");
        return scanner.nextLine().toLowerCase();
    }

    public void mostrarContadorPendientes(int cantidad) {
        System.out.println("\n=== PEDIDOS PENDIENTES ===");
        System.out.println("Total de pedidos pendientes: " + cantidad);
    }

    public void mostrarHistorial(List<Pedido> historial) {
        if (historial.isEmpty()) {
            System.out.println("No hay pedidos en el historial.");
        } else {
            System.out.println("\n=== HISTORIAL DE PEDIDOS ===");
            for (int i = 0; i < historial.size(); i++) {
                Pedido pedido = historial.get(i);
                System.out.println((i + 1) + ". " + pedido.getNombrePlato() + 
                                 " - Tipo: " + pedido.getTipo() + 
                                 " - Estado: " + pedido.getEstado());
            }
        }
    }

    public void cerrarScanner() {
        scanner.close();
    }
}