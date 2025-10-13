package Actividad6;

import java.io.Serializable;

public class Persona implements Serializable {  // ← AGREGAR Serializable
    protected String nif;
    protected String nombre;
    protected int edad;
    
    public Persona() {
    }
    
    public Persona(String nif, String nombre, int edad) {
        this.nif = nif;
        this.nombre = nombre;
        this.edad = edad;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public String getNif() {
        return nif;
    }
    
    public void setNif(String nif) {
        this.nif = nif;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    // Mantener el método de la actividad 5
    public String toString() {
        return this.nombre + "\t" + this.nif + "\t" + this.edad + "\n";
    }
}