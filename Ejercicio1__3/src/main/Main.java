package main;

import model.Empleado;
import service.CalculadoraPago;
import service.ReporteGenerator;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== REFACTORIZACIÓN SRP - SISTEMA DE PAGOS ===");
        Empleado emp1 = new Empleado("Juan Pérez", 2000, "Ventas", 175, 25);
        Empleado emp2 = new Empleado("María López", 2500, "IT", 160, 30);
        CalculadoraPago calculadora = new CalculadoraPago();
        ReporteGenerator reporteador = new ReporteGenerator();
        System.out.println("\n--- CÁLCULO DE PAGOS ---");
        System.out.println(emp1.getNombre() + ": $" + calculadora.calcularPagoMensual(emp1));
        System.out.println(emp2.getNombre() + ": $" + calculadora.calcularPagoMensual(emp2));
        System.out.println("\n--- REPORTES DE PAGO ---");
        System.out.println(reporteador.generarReportePago(emp1, calculadora));
        System.out.println("\n" + reporteador.generarReportePago(emp2, calculadora));
        System.out.println("\n--- INFORMACIÓN DE EMPLEADOS ---");
        System.out.println(emp1.toString());
        System.out.println(emp2.toString());
     
    }
}