package miproyecto;

public class Contador {
    static int acumulador = 0;
    static int nContadores = 0;
    static int ultimoContador = 0;
    private int valor;
    public static final int VALOR_INICIAL = 10;
    public Contador() {
        this(VALOR_INICIAL);
    }
    public Contador(int valor) {
        this.valor = valor;
        acumulador += valor;
        nContadores++;
        ultimoContador = valor;
    }
    public void inc() {
        valor++;
        acumulador++;
    }

    public int getValor() {
        return valor;
    }
    public static int getAcumulador() {
        return acumulador;
    }
    public static int getNContadores() {
        return nContadores;
    }
    public static int getUltimoContador() {
        return ultimoContador;
    }
}