package model;

public class Circulo extends Forma {
    
    public Circulo() {
        super("Círculo");
    }  
    @Override
    public void dibujar() {
        System.out.println("Dibujando un círculo");
    }
}