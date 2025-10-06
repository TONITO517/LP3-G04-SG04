package modelo;

import java.util.ArrayList;
import java.util.List;

public class PedidoModelo {
    private List<Pedido> pedidos;
    private List<Pedido> historial; 

    public PedidoModelo() {
        pedidos = new ArrayList<>();
        historial = new ArrayList<>();  //arreglo para historial
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public boolean eliminarPedido(int indice) {
        if (indice >= 0 && indice < pedidos.size()) {
            Pedido pedido = pedidos.get(indice);
            pedido.marcarEliminado();
            historial.add(pedido);
            return true;
        }
        return false;
    }

    public boolean actualizarPedido(int indice, String nuevoNombre) {
        if (indice >= 0 && indice < pedidos.size()) {
            pedidos.get(indice).setNombrePlato(nuevoNombre);
            return true;
        }
        return false;
    }

    public List<Pedido> buscarPorNombre(String nombre) {
        List<Pedido> resultados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getNombrePlato().toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(pedido);
            }
        }
        return resultados;
    }
    public List<Pedido> buscarPorTipo(String tipo) {
        List<Pedido> resultados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getTipo().equalsIgnoreCase(tipo)) {
                resultados.add(pedido);
            }
        }
        return resultados;
    }
    public int contarTotalPedidos() {
        return pedidos.size();
    }
    public int contarPedidosPorTipo(String tipo) {
        int count = 0;
        for (Pedido pedido : pedidos) {
            if (pedido.getTipo().equalsIgnoreCase(tipo)) {
                count++;
            }
        }
        return count;
    }
    public List<String> obtenerTiposUnicos() {
        List<String> tipos = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (!tipos.contains(pedido.getTipo())) {
                tipos.add(pedido.getTipo());
            }
        }
        return tipos;
    }

    public boolean marcarPedidoCompleto(int indice) { //marca perdido como completo
        if (indice >= 0 && indice < pedidos.size()) {
            Pedido pedido = pedidos.get(indice);
            pedido.marcarCompleto();
            historial.add(pedido); 
            return true;
        }
        return false;
    }

    public List<Pedido> obtenerPedidosPorEstado(String estado) {
        List<Pedido> resultados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getEstado().equals(estado)) {
                resultados.add(pedido);
            }
        }
        return resultados;
    }
    public int contarPedidosPendientes() {
        int count = 0;
        for (Pedido pedido : pedidos) {
            if (pedido.estaPendiente()) {
                count++;
            }
        }
        return count;
    }
    public List<Pedido> obtenerHistorial() {
        return historial;
    }
  
    public List<Pedido> obtenerPedidosActivos() {
        List<Pedido> activos = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (!"eliminado".equals(pedido.getEstado())) {
                activos.add(pedido);
            }
        }
        return activos;
    }
}