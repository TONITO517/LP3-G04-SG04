package model;
import service.Multifuncional;
public class ImpresoraMultifuncional implements Multifuncional {
    private String modelo;
    private int nivelTinta;
    private boolean escanerCalibrado;
    
    public ImpresoraMultifuncional(String modelo) {
        this.modelo = modelo;
        this.nivelTinta = 100;
        this.escanerCalibrado = true;
    }
    @Override
    public void imprimir(String documento) {
        if (nivelTinta > 0) {
            System.out.println(" Imprimiendo: " + documento + " en " + modelo);
            nivelTinta -= 5; 
        } else {
            System.out.println(" Sin tinta para imprimir");
        }
    }   
    @Override
    public String getEstadoCartucho() {
        return "Cartucho multifunción - Nivel: " + nivelTinta + "%";
    }
    
    @Override
    public int getNivelTinta() {
        return nivelTinta;
    }
    @Override
    public void escanear(String documento) {
        if (escanerCalibrado) {
            System.out.println(" Escaneando: " + documento + " en " + modelo);
        } else {
            System.out.println(" Escáner necesita calibración");
        }
    }    
    @Override
    public String getCalidadEscaneo() {
        return "Alta resolución - 1200 DPI";
    }
    @Override
    public void calibrarEscaner() {
        escanerCalibrado = true;
        System.out.println(" Escáner calibrado correctamente");
    }
    @Override
    public void fotocopiar(String documento) {
        System.out.println(" Fotocopiando: " + documento + " en " + modelo);
        imprimir("Copia de " + documento);
    }    
    @Override
    public void enviarFax(String numero, String documento) {
        System.out.println("Enviando fax a " + numero + ": " + documento);
    }
    public void recargarTinta() {
        nivelTinta = 100;
        System.out.println(" Tinta recargada al 100%");
    }  
    public String getModelo() {
        return modelo;
    }
}