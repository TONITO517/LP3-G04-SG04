package model;
public class Rectangulo extends Forma {   
    public Rectangulo() {
        super("Rectángulo");
    }    
    @Override
    public void dibujar() {
        System.out.println("Dibujando un rectángulo ");
    }
}