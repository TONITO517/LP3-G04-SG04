package service;

public interface Imprimible {
    void imprimir(String documento);
    String getEstadoCartucho();
    int getNivelTinta();
}