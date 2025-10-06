package controlador;

import modelo.Pedido;
import modelo.PedidoModelo;
import vista.PedidoVista;
import java.util.List;

public class PedidoControlador {
    private PedidoModelo modelo;
    private PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    public void agregarPedido(String nombrePlato, String tipo) {
        if (!nombrePlato.isEmpty() && !tipo.isEmpty()) {
            modelo.agregarPedido(new Pedido(nombrePlato, tipo));
            vista.mostrarMensaje("Pedido agregado: " + nombrePlato + " - Tipo: " + tipo);
        } else {
            vista.mostrarMensaje("El nombre del plato y tipo no pueden estar vacíos.");
        }
    }

    public void mostrarPedidos() {
        vista.mostrarPedidos(modelo.obtenerPedidosActivos()); 
    }

    public void eliminarPedido() {
        vista.mostrarPedidos(modelo.obtenerPedidosActivos());
        int indice = vista.solicitarIndice();
        if (modelo.eliminarPedido(indice)) {
            vista.mostrarMensaje("Pedido eliminado correctamente.");
        } else {
            vista.mostrarMensaje("Índice inválido.");
        }
    }

    public void actualizarPedido() {
        vista.mostrarPedidos(modelo.obtenerPedidosActivos());
        int indice = vista.solicitarIndice();
        if (indice >= 0 && indice < modelo.obtenerPedidosActivos().size()) {
            String nuevoNombre = vista.solicitarNuevoNombre();
            if (modelo.actualizarPedido(indice, nuevoNombre)) {
                vista.mostrarMensaje("Pedido actualizado correctamente.");
            } else {
                vista.mostrarMensaje("Error al actualizar el pedido.");
            }
        } else {
            vista.mostrarMensaje("Índice inválido.");
        }
    }

    public void buscarPorNombre() {
        String nombre = vista.solicitarNombreBusqueda();
        List<Pedido> resultados = modelo.buscarPorNombre(nombre);
        if (resultados.isEmpty()) {
            vista.mostrarMensaje("No se encontraron pedidos con ese nombre.");
        } else {
            vista.mostrarMensaje("Resultados de búsqueda:");
            vista.mostrarPedidos(resultados);
        }
    }

    public void buscarPorTipo() {
        String tipo = vista.solicitarTipoBusqueda();
        List<Pedido> resultados = modelo.buscarPorTipo(tipo);
        if (resultados.isEmpty()) {
            vista.mostrarMensaje("No se encontraron pedidos de ese tipo.");
        } else {
            vista.mostrarMensaje("Pedidos de tipo '" + tipo + "':");
            vista.mostrarPedidos(resultados);
        }
    }

    public void mostrarEstadisticas() {
        int total = modelo.contarTotalPedidos();
        List<String> tipos = modelo.obtenerTiposUnicos();
        vista.mostrarEstadisticas(total, tipos, modelo);
    }

    public void marcarPedidoCompleto() {
        vista.mostrarPedidos(modelo.obtenerPedidosPorEstado("pendiente"));
        int indice = vista.solicitarIndice();
        if (modelo.marcarPedidoCompleto(indice)) {
            vista.mostrarMensaje("Pedido marcado como completo.");
        } else {
            vista.mostrarMensaje("Índice inválido.");
        }
    }

    public void mostrarPedidosPorEstado() {
        String estado = vista.solicitarEstado();
        if ("pendiente".equals(estado) || "completo".equals(estado)) {
            List<Pedido> pedidos = modelo.obtenerPedidosPorEstado(estado);
            vista.mostrarMensaje("Pedidos con estado '" + estado + "':");
            vista.mostrarPedidos(pedidos);
        } else {
            vista.mostrarMensaje("Estado no válido. Use 'pendiente' o 'completo'.");
        }
    }

    public void mostrarPedidosPendientes() {
        int pendientes = modelo.contarPedidosPendientes();
        vista.mostrarContadorPendientes(pendientes);
        
        List<Pedido> pedidosPendientes = modelo.obtenerPedidosPorEstado("pendiente");
        vista.mostrarPedidos(pedidosPendientes);
    }

    public void mostrarHistorial() {
        List<Pedido> historial = modelo.obtenerHistorial();
        vista.mostrarHistorial(historial);
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();
            switch (opcion) {
                case "1": 
                    String nombrePlato = vista.solicitarNombrePlato();
                    String tipo = vista.solicitarTipoPlato();
                    agregarPedido(nombrePlato, tipo);
                    break;
                case "2": 
                    mostrarPedidos();
                    break;
                case "3": 
                    eliminarPedido();
                    break;
                case "4": 
                    actualizarPedido();
                    break;
                case "5": 
                    buscarPorNombre();
                    break;
                case "6": 
                    buscarPorTipo();
                    break;
                case "7": 
                    mostrarEstadisticas();
                    break;
                case "8": 
                    marcarPedidoCompleto();
                    break;
                case "9": 
                    mostrarPedidosPorEstado();
                    break;
                case "10": 
                    mostrarPedidosPendientes();
                    break;
                case "11": 
                    mostrarHistorial();
                    break;
                case "12": 
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
            }
        } while (!opcion.equals("12"));
        vista.cerrarScanner();
    }
}