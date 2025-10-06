package modelo_ej2_3;

public class Item {
    private String nombre;
    private int cantidad;
    private String tipo; 
    private String descripcion;

    public Item(String nombre, int cantidad, String tipo, String descripcion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public String getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }

    // Setters
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String usarItem() {
        if (cantidad > 0) {
            cantidad--;
            return "Usaste: " + nombre + " - " + descripcion;
        }
        return "No tienes suficientes " + nombre;
    }

    @Override
    public String toString() {
        return nombre + " x" + cantidad + " [" + tipo + "]";
    }
}