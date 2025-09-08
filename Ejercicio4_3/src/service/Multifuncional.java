package service;

public interface Multifuncional extends Imprimible, Escaneable {
    void fotocopiar(String documento);
    void enviarFax(String numero, String documento);
}