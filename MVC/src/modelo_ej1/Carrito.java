package modelo_ej1;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Producto> productos;
    private double descuento;
    private double costoEnvio;

    public Carrito() {
        productos = new ArrayList<>();
        descuento = 0;
        costoEnvio = 5.0; 
    }
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
    public void eliminarProducto(int indice) {
        if (indice >= 0 && indice < productos.size()) {
            productos.remove(indice);
        }
    }
    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }
    public void vaciarCarrito() {
        productos.clear();
        descuento = 0;
    }
    public double getSubtotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }
    public void aplicarDescuento(double porcentaje) {
        this.descuento = porcentaje;
    }
    public double getDescuento() {
        return (getSubtotal() * descuento) / 100;
    }
    public double getTotal() {
        return getSubtotal() - getDescuento() + costoEnvio;
    }
    public double getCostoEnvio() {
        return costoEnvio;
    }
    public int getCantidadProductos() {
        return productos.size();
    }
}