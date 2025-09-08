package model;

public class Empleado {
    private String nombre;
    private double salario;
    private String departamento;
    private int horasTrabajadas;
    private double tasaHorasExtra;

    public Empleado(String nombre, double salario, String departamento, int horasTrabajadas, double tasaHorasExtra) {
        this.nombre = nombre;
        this.salario = salario;
        this.departamento = departamento;
        this.horasTrabajadas = horasTrabajadas;
        this.tasaHorasExtra = tasaHorasExtra;
    }

    public String getNombre() { return nombre; }
    public double getSalario() { return salario; }
    public String getDepartamento() { return departamento; }
    public int getHorasTrabajadas() { return horasTrabajadas; }
    public double getTasaHorasExtra() { return tasaHorasExtra; }

    @Override
    public String toString() {
        return nombre + " - " + departamento + " - $" + salario;
    }
}