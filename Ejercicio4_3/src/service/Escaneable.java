package service;

public interface Escaneable {
    void escanear(String documento);
    String getCalidadEscaneo();
    void calibrarEscaner();
}