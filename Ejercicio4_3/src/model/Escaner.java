package model;

import service.Escaneable;

public class Escaner implements Escaneable {
    private String modelo;
    private boolean calibrado;
    
    public Escaner(String modelo) {
        this.modelo = modelo;
        this.calibrado = true;
    }
    
    @Override
    public void escanear(String documento) {
        if (calibrado) {
            System.out.println("Escaneando en escáner dedicado: " + documento);
            System.out.println("Modelo: " + modelo);
        } else {
            System.out.println(" Escáner necesita calibración");
        }
    }
    
    @Override
    public String getCalidadEscaneo() {
        return "Resolución profesional - 2400 DPI";
    }
    
    @Override
    public void calibrarEscaner() {
        calibrado = true;
        System.out.println("Escáner profesional calibrado");
    }
    
    public void modoDocumentos() {
        System.out.println(" Modo documentos activado");
    }
    
    public void modoFotos() {
        System.out.println(" Modo fotos activado");
    }
    
    public String getModelo() {
        return modelo;
    }
}