package model;
public class Coche extends Vehiculo {
    private int combustible;
    
    public Coche(String marca, String modelo, int combustible) {
        super(marca, modelo);
        this.combustible = combustible;
    }    
    @Override
    protected boolean puedeAcelerar(int incremento) {
        return combustible > 0;
    } 
    @Override
    protected int calcularIncrementoReal(int incremento) {
        if (combustible < 5) {
            return incremento / 2;
        }
        return incremento;
    }
    @Override
    protected void ejecutarAccionAceleracion() {
        combustible -= 1;
        System.out.println("Acelerando con motor. Combustible: " + combustible + "L");
    }  
    public void repostar(int litros) {
        combustible += litros;
        System.out.println("Repostando. Combustible: " + combustible + "L");
    }
}