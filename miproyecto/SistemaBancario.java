package miproyecto;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Cuenta {
    protected int numeroCuenta;
    protected double saldo;
    
    public Cuenta(int numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }
    
    // Métodos comunes
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.println("Depósito exitoso. Nuevo saldo: S/." + saldo);
        } else {
            System.out.println("Monto inválido para depósito");
        }
    }
    
    public boolean retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            System.out.println("Retiro exitoso. Nuevo saldo: S/." + saldo);
            return true;
        } else {
            System.out.println("Fondos insuficientes o monto inválido");
            return false;
        }
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public int getNumeroCuenta() {
        return numeroCuenta;
    }
    
    // Método abstracto para ser implementado por subclases
    public abstract void consultar();
    
    @Override
    public String toString() {
        return "Cuenta #" + numeroCuenta + " - Saldo: S/." + saldo;
    }
}

// Clase Cuenta de Ahorros
class CuentaAhorro extends Cuenta {
    private double tasaInteres;
    private double saldoMinimoMensual;
    
    public CuentaAhorro(int numeroCuenta, double saldoInicial, double tasaInteres) {
        super(numeroCuenta, saldoInicial);
        this.tasaInteres = tasaInteres;
        this.saldoMinimoMensual = saldoInicial;
    }
    
    @Override
    public boolean retirar(double monto) {
        boolean retiroExitoso = super.retirar(monto);
        if (retiroExitoso) {
            // Actualizar saldo mínimo
            if (saldo < saldoMinimoMensual) {
                saldoMinimoMensual = saldo;
            }
        }
        return retiroExitoso;
    }
    
    @Override
    public void consultar() {
        // Calcular y depositar intereses
        double intereses = saldoMinimoMensual * (tasaInteres / 100);
        depositar(intereses);
        System.out.println("Intereses calculados: S/." + intereses);
        
        // Reiniciar saldo mínimo para el próximo mes
        saldoMinimoMensual = saldo;
    }
    
    @Override
    public String toString() {
        return super.toString() + " (Ahorro - Tasa: " + tasaInteres + "%)";
    }
}

// Clase Cuenta Corriente
class CuentaCorriente extends Cuenta {
    private static final double TARIFA_RETIRO = 3.0;
    private static final int RETIROS_GRATIS = 3;
    private int retirosEsteMes;
    
    public CuentaCorriente(int numeroCuenta, double saldoInicial) {
        super(numeroCuenta, saldoInicial);
        this.retirosEsteMes = 0;
    }
    
    @Override
    public boolean retirar(double monto) {
        double montoTotal = monto;
        
        // Verificar si aplica tarifa
        if (retirosEsteMes >= RETIROS_GRATIS) {
            montoTotal += TARIFA_RETIRO;
            System.out.println("Tarifa por retiro adicional: S/." + TARIFA_RETIRO);
        }
        
        if (montoTotal <= saldo) {
            saldo -= montoTotal;
            retirosEsteMes++;
            System.out.println("Retiro exitoso. Nuevo saldo: S/." + saldo);
            System.out.println("Retiros este mes: " + retirosEsteMes + "/" + RETIROS_GRATIS + " gratis");
            return true;
        } else {
            System.out.println("Fondos insuficientes para retiro + tarifa");
            return false;
        }
    }
    
    @Override
    public void consultar() {
        // Reiniciar contador de retiros
        retirosEsteMes = 0;
        System.out.println("Contador de retiros reiniciado para el nuevo mes");
    }
    
    @Override
    public String toString() {
        return super.toString() + " (Corriente - Retiros: " + retirosEsteMes + "/" + RETIROS_GRATIS + ")";
    }
}

// Clase principal con menú
public class SistemaBancario {
    private static ArrayList<Cuenta> cuentas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        inicializarCuentas();
        mostrarMenu();
    }
    
    private static void inicializarCuentas() {
        // Crear 5 cuentas de cada tipo
        for (int i = 1; i <= 5; i++) {
            cuentas.add(new CuentaAhorro(1000 + i, 1000 * i, 2.5));
            cuentas.add(new CuentaCorriente(2000 + i, 1500 * i));
        }
    }
    
    private static void mostrarMenu() {
        char opcion;
        do {
            System.out.println("\n=== SISTEMA BANCARIO ===");
            System.out.println("D)epositar");
            System.out.println("R)etirar");
            System.out.println("C)onsultar");
            System.out.println("S)alir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 'D':
                    realizarDeposito();
                    break;
                case 'R':
                    realizarRetiro();
                    break;
                case 'C':
                    realizarConsulta();
                    break;
                case 'S':
                    System.out.println("Gracias por usar el sistema bancario");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 'S');
    }
    
    private static void realizarDeposito() {
        System.out.print("Ingrese número de cuenta: ");
        int numero = scanner.nextInt();
        System.out.print("Ingrese monto a depositar: ");
        double monto = scanner.nextDouble();
        
        Cuenta cuenta = buscarCuenta(numero);
        if (cuenta != null) {
            cuenta.depositar(monto);
        } else {
            System.out.println("Cuenta no encontrada");
        }
    }
    
    private static void realizarRetiro() {
        System.out.print("Ingrese número de cuenta: ");
        int numero = scanner.nextInt();
        System.out.print("Ingrese monto a retirar: ");
        double monto = scanner.nextDouble();
        
        Cuenta cuenta = buscarCuenta(numero);
        if (cuenta != null) {
            cuenta.retirar(monto);
        } else {
            System.out.println("Cuenta no encontrada");
        }
    }
    
    private static void realizarConsulta() {
        System.out.println("\n=== CONSULTA GENERAL ===");
        for (Cuenta cuenta : cuentas) {
            cuenta.consultar();
            System.out.println(cuenta);
        }
    }
    
    private static Cuenta buscarCuenta(int numero) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta() == numero) {
                return cuenta;
            }
        }
        return null;
    }
}