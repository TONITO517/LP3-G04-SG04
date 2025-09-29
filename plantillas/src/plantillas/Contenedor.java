package plantillas;

import java.util.ArrayList;
import java.util.List;

public class Contenedor<F, S> {
    private ArrayList<Par<F, S>> pares;
    
    public Contenedor() {
        this.pares = new ArrayList<>();
    }
    public void agregarPar(F primero, S segundo) {
        Par<F, S> nuevoPar = new Par<>(primero, segundo);
        pares.add(nuevoPar);
    }
    public Par<F, S> obtenerPar(int indice) {
        if (indice >= 0 && indice < pares.size()) {
            return pares.get(indice);
        }
        return null;
    }
    public List<Par<F, S>> obtenerTodosLosPares() { //devuelve una copia
        return new ArrayList<>(pares); 
    }
    public void mostrarPares() {
        System.out.println("=== PARES EN EL CONTENEDOR ===");
        for (int i = 0; i < pares.size(); i++) {
            System.out.println("Par " + i + ": " + pares.get(i));
        }
    }
}