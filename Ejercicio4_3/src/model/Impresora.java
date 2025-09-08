package model;

import service.Imprimible;

public class Impresora implements Imprimible {
    private String modelo;
    private int nivelTinta;
    
    public Impresora(String modelo) {
        this.modelo = modelo;
        this.nivelTinta = 100;
    }
    
    @Override
    public void imprimir(String documento) {
        if (nivelTinta > 0) {
            System.out.println("🖨 Imprimiendo: " + documento + " en " + modelo);
            nivelTinta -= 10;
        } else {
            System.out.println(" Sin tinta para imprimir");
        }
    }
    
    @Override
    public String getEstadoCartucho() {
        return "Cartucho estándar - Nivel: " + nivelTinta + "%";
    }
    
    @Override
    public int getNivelTinta() {
        return nivelTinta;
    }
    
    public void recargarTinta() {
        nivelTinta = 100;
        System.out.println(" Tinta recargada al 100%");
    }
    
    public String getModelo() {
        return modelo;
    }
}