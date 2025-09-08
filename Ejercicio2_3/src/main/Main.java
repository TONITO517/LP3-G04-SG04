package main;
import java.util.Arrays;
import java.util.List;
import model.Circulo;
import model.Rectangulo;
import model.Triangulo;
import model.Forma;
import service.DibujadorFormas;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PRINCIPIO ABIERTO/CERRADO (OCP) ===");   
        DibujadorFormas dibujador = new DibujadorFormas();
        Forma circulo = new Circulo();
        Forma rectangulo = new Rectangulo();
        Forma triangulo = new Triangulo();
        System.out.println("\n--- DIBUJANDO FORMAS INDIVIDUALES ---");
        dibujador.dibujarForma(circulo);
        dibujador.dibujarForma(rectangulo);
        dibujador.dibujarForma(triangulo);
        List<Forma> todasLasFormas = Arrays.asList(circulo, rectangulo, triangulo);
        dibujador.dibujarTodasLasFormas(todasLasFormas);
        System.out.println("\n--- AÑADIENDO MÁS FORMAS (EXTENSIBILIDAD) ---");
        Forma pentagono = new Pentagono();
        dibujador.dibujarForma(pentagono);
        System.out.println("\n El sistema está:");
        System.out.println("🔒 CERRADO para modificación");
        System.out.println("🚀 ABIERTO para extensión");
    }
}
class Pentagono extends Forma {
    public Pentagono() {
        super("Pentágono");
    }    
    @Override
    public void dibujar() {
        System.out.println("Dibujando un pentágono ⬟");
    }
}
class Estrella extends Forma {
    public Estrella() {
        super("Estrella");
    }    
    @Override
    public void dibujar() {
        System.out.println("Dibujando una estrella ✰");
    }
}