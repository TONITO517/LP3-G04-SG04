package miproyecto;

public class Cuenta {
    private int numero;
    private double saldo;
    public Cuenta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }
    public Cuenta(int numero) {
        this(numero, 0);
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public String toString() {
        return "Número de Cuenta: " + numero + ", Saldo: $" + saldo;
    }
}
