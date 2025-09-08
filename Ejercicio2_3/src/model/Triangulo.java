package model;
public class Triangulo extends Forma {   
    public Triangulo() {
        super("Triángulo");
    }    
    @Override
    public void dibujar() {
        System.out.println("Dibujando un triángulo ");
    }
}