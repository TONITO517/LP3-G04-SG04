package controller;

import service.Imprimible;
import service.Escaneable;
import service.Multifuncional;

public class OficinaController {

    public void realizarImpresion(Imprimible imprimible, String documento) {
        System.out.println("\n--- INICIANDO IMPRESIÓN ---");
        System.out.println("Estado: " + imprimible.getEstadoCartucho());
        imprimible.imprimir(documento);
    }
    
    public void realizarEscaneo(Escaneable escaneable, String documento) {
        System.out.println("\n--- INICIANDO ESCANEO ---");
        System.out.println("Calidad: " + escaneable.getCalidadEscaneo());
        escaneable.escanear(documento);
    }
    
    public void usarMultifuncional(Multifuncional multifuncional, String documento) {
        System.out.println("\n--- USANDO MULTIFUNCIONAL ---");
        multifuncional.imprimir(documento);
        multifuncional.escanear(documento);
        multifuncional.fotocopiar(documento);
    }
    
    public void mostrarCapacidades(Object dispositivo) {
        System.out.println("\n--- CAPACIDADES DEL DISPOSITIVO ---");
        
        if (dispositivo instanceof Imprimible) {
            System.out.println("✓ Puede imprimir");
        }
        if (dispositivo instanceof Escaneable) {
            System.out.println("✓ Puede escanear");
        }
        if (dispositivo instanceof Multifuncional) {
            System.out.println("✓ Es multifuncional");
        }
        
        System.out.println("Tipo: " + dispositivo.getClass().getSimpleName());
    }
}