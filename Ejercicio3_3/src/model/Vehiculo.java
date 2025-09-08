package model;

public class Vehiculo {
    private String marca;
    private String modelo;
    protected int velocidad; 
    public Vehiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidad = 0;
    }
    public final void acelerar(int incremento) {
        if (puedeAcelerar(incremento)) {
            int incrementoReal = calcularIncrementoReal(incremento);
            velocidad += incrementoReal;
            System.out.println("Acelerando... Velocidad: " + velocidad + " km/h");
            ejecutarAccionAceleracion();
        } else {
            System.out.println("❌ No se puede acelerar en este momento");
        }
    }
    protected boolean puedeAcelerar(int incremento) {
        return true; 
    }    
    protected int calcularIncrementoReal(int incremento) {
        return incremento; 
    } 
    protected void ejecutarAccionAceleracion() {
    }  
    public void frenar(int decremento) {
        this.velocidad = Math.max(0, velocidad - decremento);
        System.out.println("Frenando... Velocidad: " + velocidad + " km/h");
    }

    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getVelocidad() { return velocidad; }
}