package modelo_ej2_3;

import java.util.ArrayList;
import java.util.List;

public class InventarioModel {
    private List<Item> items;

    public InventarioModel() {
        items = new ArrayList<>();
        items.add(new Item("Espada de Acero", 1, "Arma", "Una espada afilada de acero"));
        items.add(new Item("Poción de Vida", 3, "Pocion", "Restaura 50 HP"));
        items.add(new Item("Escudo de Madera", 1, "Armadura", "Protección básica"));
    }
    public void agregarItem(Item item) {
        for (Item i : items) {
            if (i.getNombre().equalsIgnoreCase(item.getNombre())) {
                i.setCantidad(i.getCantidad() + item.getCantidad());
                return;
            }
        }
        items.add(item);
    }
    public boolean eliminarItem(String nombre) {
        return items.removeIf(item -> item.getNombre().equalsIgnoreCase(nombre));
    }
    public Item buscarItem(String nombre) {
        for (Item item : items) {
            if (item.getNombre().equalsIgnoreCase(nombre)) {
                return item;
            }
        }
        return null;
    }
    public List<Item> obtenerItems() {
        return new ArrayList<>(items);
    }
    public String usarItem(String nombre) {
        Item item = buscarItem(nombre);
        if (item != null) {
            return item.usarItem();
        }
        return "Item no encontrado: " + nombre;
    }
    public String mostrarDetalles(String nombre) {
        Item item = buscarItem(nombre);
        if (item != null) {
            return "Detalles de " + nombre + ":\n" +
                   "Tipo: " + item.getTipo() + "\n" +
                   "Cantidad: " + item.getCantidad() + "\n" +
                   "Descripción: " + item.getDescripcion();
        }
        return "Item no encontrado: " + nombre;
    }
}