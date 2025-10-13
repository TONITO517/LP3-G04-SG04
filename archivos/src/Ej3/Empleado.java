package Ej3;

import java.io.Serializable;

public class Empleado implements Serializable {
    private int numero;
    private String nombre;
    private double sueldo;
    
    public Empleado(int numero, String nombre, double sueldo) {
        this.numero = numero;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }
    public int getNumero() { return numero; }
    public String getNombre() { return nombre; }
    public double getSueldo() { return sueldo; }
    public void setNumero(int numero) { this.numero = numero; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }
    
    @Override
    public String toString() {
        return String.format("N°%d | %-15s | S/.%,.2f", numero, nombre, sueldo);
    }
    public String toFileString() {
        return numero + "," + nombre + "," + sueldo;
    }
    public static Empleado fromFileString(String line) {
        String[] parts = line.split(",");
        return new Empleado(
            Integer.parseInt(parts[0]), 
            parts[1], 
            Double.parseDouble(parts[2])
        );
    }
}