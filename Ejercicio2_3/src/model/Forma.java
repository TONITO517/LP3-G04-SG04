package model;

public abstract class Forma {
    private String nombre;    
    public Forma(String nombre) {
        this.nombre = nombre;
    }
    public abstract void dibujar();
    public String getNombre() {
        return nombre;
    }
}