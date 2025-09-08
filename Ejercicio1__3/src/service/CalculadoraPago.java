package service;
import model.Empleado;
public class CalculadoraPago {

    public double calcularPagoMensual(Empleado empleado) {
        double pagoBase = empleado.getSalario();
        double pagoHorasExtra = 0;
        
        if (empleado.getHorasTrabajadas() > 160) {
            pagoHorasExtra = (empleado.getHorasTrabajadas() - 160) * empleado.getTasaHorasExtra();
        }
        
        return pagoBase + pagoHorasExtra;
    }
    
    public double calcularPagoAnual(Empleado empleado) {
        return calcularPagoMensual(empleado) * 12;
    }
    
    public double calcularHorasExtra(Empleado empleado) {
        if (empleado.getHorasTrabajadas() > 160) {
            return (empleado.getHorasTrabajadas() - 160) * empleado.getTasaHorasExtra();
        }
        return 0;
    }
}