package service;
import java.util.List;
import model.Forma;
public class DibujadorFormas {
    public void dibujarForma(Forma forma) {
        forma.dibujar();
    }
    public void dibujarTodasLasFormas(List<Forma> formas) {
        System.out.println("\n--- DIBUJANDO TODAS LAS FORMAS ---");
        for (Forma forma : formas) {
            System.out.print(forma.getNombre() + ": ");
            forma.dibujar();
        }
    }
    
    public void mostrarInfoForma(Forma forma) {
        System.out.println("Forma: " + forma.getNombre());
    }
}