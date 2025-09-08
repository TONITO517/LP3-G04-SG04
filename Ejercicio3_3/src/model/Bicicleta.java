package model;
public class Bicicleta extends Vehiculo {
    private int fuerzaPedaleo;
    
    public Bicicleta(String marca, String modelo, int fuerzaPedaleo) {
        super(marca, modelo);
        this.fuerzaPedaleo = fuerzaPedaleo;
    }
    @Override
    protected boolean puedeAcelerar(int incremento) {
        return fuerzaPedaleo > 3; 
    }
    
    @Override
    protected int calcularIncrementoReal(int incremento) {
        return incremento * fuerzaPedaleo / 10;
    }    
    @Override
    protected void ejecutarAccionAceleracion() {
        System.out.println("Pedaleando . Fuerza: " + fuerzaPedaleo + "/10");
    }
    public void aumentarFuerzaPedaleo(int incremento) {
        fuerzaPedaleo = Math.min(10, fuerzaPedaleo + incremento);
        System.out.println("Fuerza de pedaleo aumentada: " + fuerzaPedaleo + "/10");
    }
}