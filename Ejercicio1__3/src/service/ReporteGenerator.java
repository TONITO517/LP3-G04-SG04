package service;

import model.Empleado;

public class ReporteGenerator {

    public String generarReportePago(Empleado empleado, CalculadoraPago calculadora) {
        double pago = calculadora.calcularPagoMensual(empleado);
        double horasExtra = calculadora.calcularHorasExtra(empleado);
        
        return "Reporte de pago para " + empleado.getNombre() + 
               "\nDepartamento: " + empleado.getDepartamento() +
               "\nSalario base: $" + empleado.getSalario() +
               "\nHoras extra: $" + horasExtra +
               "\nPago total: $" + pago +
               "\nHoras trabajadas: " + empleado.getHorasTrabajadas();
    }
    
    public String generarReporteSimple(Empleado empleado) {
        return "Empleado: " + empleado.getNombre() + 
               " - Depto: " + empleado.getDepartamento() +
               " - Salario base: $" + empleado.getSalario();
    }
}