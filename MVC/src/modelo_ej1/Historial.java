package modelo_ej1;

import java.util.ArrayList;
import java.util.List;

public class Historial {
    private List<String> compras;

    public Historial() {
        compras = new ArrayList<>();
    }

    public void agregarCompra(String compra) {
        compras.add(compra);
    }

    public List<String> getCompras() {
        return new ArrayList<>(compras);
    }
}