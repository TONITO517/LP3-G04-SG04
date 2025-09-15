package src;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SistemaBancario {
    private static HashMap<String, CuentaBancaria> cuentas = new HashMap<>();
    private static List<Transaccion> historialTransacciones = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        cuentas.put("001", new CuentaBancaria("001", "Juan Perez", 1000));
        cuentas.put("002", new CuentaCredito("002", "Maria Lopez", 500, 1000));
        cuentas.put("003", new CuentaBancaria("003", "Carlos Ruiz", 300));
        
        System.out.println("=== SISTEMA BANCARIO AVANZADO ===");

        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Transferir");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Cerrar cuenta");
            System.out.println("7. Listar cuentas");
            System.out.println("8. Generar reporte de transacciones");
            System.out.println("9. Leer transacciones desde archivo");
            System.out.println("10. Salir");
            System.out.print("Elija opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearCuenta(scanner);
                    break;
                case 2:
                    depositar(scanner);
                    break;
                case 3:
                    retirar(scanner);
                    break;
                case 4:
                    transferir(scanner);
                    break;
                case 5:
                    consultarSaldo(scanner);
                    break;
                case 6:
                    cerrarCuenta(scanner);
                    break;
                case 7:
                    listarCuentas();
                    break;
                case 8:
                    generarReporteTransacciones(scanner);
                    break;
                case 9:
                    leerTransaccionesDesdeArchivo(scanner);
                    break;
                case 10:
                    System.out.println("👋 Saliendo del sistema...");
                    break;
                default:
                    System.out.println("❌ Opción inválida");
            }
        } while (opcion != 10);

        scanner.close();
    }
    private static void generarReporteTransacciones(Scanner scanner) {
        System.out.println("\n--- GENERAR REPORTE DE TRANSACCIONES ---");
        System.out.print("Número de cuenta: ");
        String num = scanner.nextLine();
        
        try {
            CuentaBancaria cuenta = obtenerCuenta(num);
            
            if (historialTransacciones.isEmpty()) {
                throw new HistorialVacioException("Error: No hay transacciones para generar reporte");
            }
            
            System.out.print("Nombre del archivo para el reporte: ");
            String nombreArchivo = scanner.nextLine();
            try (FileWriter writer = new FileWriter(nombreArchivo + ".txt")) {
                writer.write("=== REPORTE DE TRANSACCIONES ===\n");
                writer.write("Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");
                writer.write("Cuenta: " + cuenta.getNumeroCuenta() + "\n");
                writer.write("Titular: " + cuenta.getTitular() + "\n");
                writer.write("Saldo actual: S/" + cuenta.getSaldo() + "\n\n");
                writer.write("--- HISTORIAL DE TRANSACCIONES ---\n");
                
                for (Transaccion transaccion : historialTransacciones) {
                    if (transaccion.getNumeroCuenta().equals(num)) {
                        writer.write(transaccion + "\n");
                    }
                }
                
                System.out.println("✅ Reporte generado exitosamente: " + nombreArchivo + ".txt");
            } catch (IOException e) {
                System.out.println("❌ Error al escribir el archivo: " + e.getMessage());
            }
            
        } catch (CuentaNoEncontradaException | HistorialVacioException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }  
    private static void leerTransaccionesDesdeArchivo(Scanner scanner) {
        System.out.println("\n--- LEER TRANSACCIONES DESDE ARCHIVO ---");
        System.out.print("Nombre del archivo a leer: ");
        String nombreArchivo = scanner.nextLine();
        try (Scanner fileScanner = new Scanner(new File(nombreArchivo))) {
            System.out.println("📖 Contenido del archivo:");
            System.out.println("========================");
            
            while (fileScanner.hasNextLine()) {
                String linea = fileScanner.nextLine();
                System.out.println(linea);
            }
            
            System.out.println("========================\n✅ Archivo leído exitosamente");
            
        } catch (FileNotFoundException e) {
            System.out.println("❌ Error: Archivo no encontrado - " + e.getMessage());
        } catch (IOException e) {
            System.out.println("❌ Error al leer el archivo: " + e.getMessage());
        }
    }

    private static void crearCuenta(Scanner scanner) {
        System.out.println("\n--- CREAR CUENTA ---");
        System.out.print("Número de cuenta: ");
        String num = scanner.nextLine();
        System.out.print("Titular: ");
        String titular = scanner.nextLine();
        System.out.print("Saldo inicial: ");
        double saldoInicial = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("¿Es cuenta de crédito? (s/n): ");
        String esCredito = scanner.nextLine();

        try {
            CuentaBancaria nuevaCuenta;
            if (esCredito.equalsIgnoreCase("s")) {
                System.out.print("Límite de crédito: ");
                double limite = scanner.nextDouble();
                scanner.nextLine();
                nuevaCuenta = new CuentaCredito(num, titular, saldoInicial, limite);
            } else {
                nuevaCuenta = new CuentaBancaria(num, titular, saldoInicial);
            }
            
            cuentas.put(num, nuevaCuenta);
            System.out.println("Cuenta creada: " + nuevaCuenta);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
    private static void depositar(Scanner scanner) {
        System.out.println("\n--- DEPOSITAR ---");
        System.out.print("Número de cuenta: ");
        String num = scanner.nextLine();        
        try {
            CuentaBancaria cuenta = obtenerCuenta(num);
            System.out.print("Monto a depositar: ");
            double monto = scanner.nextDouble();
            scanner.nextLine();            
            cuenta.depositar(monto);
            System.out.println("Depósito exitoso. Nuevo saldo: S/" + cuenta.getSaldo());
        } catch (CuentaNoEncontradaException | IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void retirar(Scanner scanner) {
        System.out.println("\n--- RETIRAR ---");
        System.out.print("Número de cuenta: ");
        String num = scanner.nextLine();
        
        try {
            CuentaBancaria cuenta = obtenerCuenta(num);
            System.out.print("Monto a retirar: ");
            double monto = scanner.nextDouble();
            scanner.nextLine();
            if (cuenta instanceof CuentaCredito) {
                ((CuentaCredito) cuenta).retirar(monto);
            } else {
                cuenta.retirar(monto);
            }
            System.out.println("Retiro exitoso. Nuevo saldo: S/" + cuenta.getSaldo());
            if (cuenta instanceof CuentaCredito) {
                CuentaCredito cc = (CuentaCredito) cuenta;
                System.out.println("Crédito utilizado: S/" + cc.getCreditoUtilizado() + 
                                 " / Disponible: S/" + cc.getCreditoDisponible());
            }
        } catch (CuentaNoEncontradaException | IllegalArgumentException | 
                 SaldoInsuficienteException | LimiteCreditoExcedidoException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void transferir(Scanner scanner) {
        System.out.println("\n--- TRANSFERIR ---");
        System.out.print("Cuenta origen: ");
        String origenNum = scanner.nextLine();
        System.out.print("Cuenta destino: ");
        String destinoNum = scanner.nextLine();
        System.out.print("Monto a transferir: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();
        try {
            CuentaBancaria origen = obtenerCuenta(origenNum);
            CuentaBancaria destino = obtenerCuenta(destinoNum);
            if (origen instanceof CuentaCredito) {
                ((CuentaCredito) origen).transferir(destino, monto);
            } else {
                origen.transferir(destino, monto);
            }
            
            System.out.println("Transferencia exitosa");
            System.out.println("Saldo origen: S/" + origen.getSaldo());
            System.out.println("Saldo destino: S/" + destino.getSaldo());
            if (origen instanceof CuentaCredito) {
                CuentaCredito cc = (CuentaCredito) origen;
                System.out.println("Crédito utilizado: S/" + cc.getCreditoUtilizado() + 
                                 " / Disponible: S/" + cc.getCreditoDisponible());
            }
        } catch (CuentaNoEncontradaException | IllegalArgumentException | 
                 SaldoInsuficienteException | LimiteCreditoExcedidoException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void consultarSaldo(Scanner scanner) {
        System.out.println("\n--- CONSULTAR SALDO ---");
        System.out.print("Número de cuenta: ");
        String num = scanner.nextLine();
        
        try {
            CuentaBancaria cuenta = obtenerCuenta(num);
            System.out.println("Saldo actual: S/" + cuenta.getSaldo());
            if (cuenta instanceof CuentaCredito) {
                CuentaCredito cc = (CuentaCredito) cuenta;
                System.out.println("Límite de crédito: S/" + cc.getLimiteCredito());
                System.out.println("Crédito utilizado: S/" + cc.getCreditoUtilizado());
                System.out.println("Crédito disponible: S/" + cc.getCreditoDisponible());
                System.out.println("Total disponible: S/" + (cc.getSaldo() + cc.getCreditoDisponible()));
            }
        } catch (CuentaNoEncontradaException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void cerrarCuenta(Scanner scanner) {
        System.out.println("\n--- CERRAR CUENTA ---");
        System.out.print("Número de cuenta: ");
        String num = scanner.nextLine();
        
        try {
            CuentaBancaria cuenta = obtenerCuenta(num);
            cuenta.cerrarCuenta();
            cuentas.remove(num);
            System.out.println("Cuenta cerrada exitosamente");
        } catch (CuentaNoEncontradaException | SaldoNoCeroException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void listarCuentas() {
        System.out.println("\n--- LISTA DE CUENTAS ---");
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas registradas");
            return;
        }
        for (CuentaBancaria cuenta : cuentas.values()) {
            System.out.println(cuenta);
        }
    }

    private static CuentaBancaria obtenerCuenta(String numero) throws CuentaNoEncontradaException {
        CuentaBancaria cuenta = cuentas.get(numero);
        if (cuenta == null) {
            throw new CuentaNoEncontradaException("Error: Cuenta " + numero + " no encontrada");
        }
        return cuenta;
    }
    private static void registrarTransaccion(String numeroCuenta, String tipo, double monto, double saldoResultante) {
        Transaccion transaccion = new Transaccion(numeroCuenta, tipo, monto, saldoResultante);
        historialTransacciones.add(transaccion);
    }
}
class SaldoInsuficienteException extends Exception {
    private static final long serialVersionUID = 1L;
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}

class CuentaNoEncontradaException extends Exception {
    private static final long serialVersionUID = 1L;
    public CuentaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}

class SaldoNoCeroException extends Exception {
    private static final long serialVersionUID = 1L;
    public SaldoNoCeroException(String mensaje) {
        super(mensaje);
    }
}

class LimiteCreditoExcedidoException extends Exception {
    private static final long serialVersionUID = 1L;
    public LimiteCreditoExcedidoException(String mensaje) {
        super(mensaje);
    }
}
class Transaccion {
    private String numeroCuenta;
    private String tipo;
    private double monto;
    private double saldoResultante;
    private LocalDateTime fecha;

    public Transaccion(String numeroCuenta, String tipo, double monto, double saldoResultante) {
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.monto = monto;
        this.saldoResultante = saldoResultante;
        this.fecha = LocalDateTime.now();
    }

    public String getNumeroCuenta() { return numeroCuenta; }
    public String getTipo() { return tipo; }
    public double getMonto() { return monto; }
    public double getSaldoResultante() { return saldoResultante; }
    public LocalDateTime getFecha() { return fecha; }

    @Override
    public String toString() {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + 
               " - " + tipo + " - Monto: S/" + monto + 
               " - Saldo: S/" + saldoResultante;
    }
}
class HistorialVacioException extends Exception {
    private static final long serialVersionUID = 1L;
    public HistorialVacioException(String mensaje) {
        super(mensaje);
    }
}
class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private boolean activa;

    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Error: Saldo inicial no puede ser negativo");
        }
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.activa = true;
    }

    public void depositar(double monto) {
        if (!activa) {
            throw new IllegalArgumentException("Error: Cuenta inactiva");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("Error: El monto a depositar debe ser positivo");
        }
        saldo += monto;
    }

    public void retirar(double monto) throws SaldoInsuficienteException, LimiteCreditoExcedidoException {
        if (!activa) {
            throw new IllegalArgumentException("Error: Cuenta inactiva");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("Error: El monto a retirar debe ser positivo");
        }
        if (monto > saldo) {
            throw new SaldoInsuficienteException("Error: Saldo insuficiente. Saldo actual: S/" + saldo);
        }
        saldo -= monto;
    }
    public void transferir(CuentaBancaria destino, double monto) 
            throws SaldoInsuficienteException, LimiteCreditoExcedidoException {
        if (!activa) {
            throw new IllegalArgumentException("Error: Cuenta origen inactiva");
        }
        if (!destino.activa) {
            throw new IllegalArgumentException("Error: Cuenta destino inactiva");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("Error: El monto a transferir debe ser positivo");
        }
        if (monto > saldo) {
            throw new SaldoInsuficienteException("Error: Saldo insuficiente para transferencia. Saldo actual: S/" + saldo);
        }      
        saldo -= monto;
        destino.saldo += monto;
    }

    public void cerrarCuenta() throws SaldoNoCeroException {
        if (saldo > 0) {
            throw new SaldoNoCeroException("Error: No se puede cerrar cuenta con saldo positivo. Saldo actual: S/" + saldo);
        }
        if (saldo < 0) {
            throw new SaldoNoCeroException("Error: No se puede cerrar cuenta con saldo negativo. Saldo actual: S/" + saldo);
        }
        activa = false;
    }

    public double getSaldo() { return saldo; }
    public String getNumeroCuenta() { return numeroCuenta; }
    public String getTitular() { return titular; }
    public boolean isActiva() { return activa; }

    @Override
    public String toString() {
        String estado = activa ? "ACTIVA" : "CERRADA";
        return "Cuenta: " + numeroCuenta + " - Titular: " + titular + 
               " - Saldo: S/" + saldo + " - Estado: " + estado;
    }
}
class CuentaCredito extends CuentaBancaria {
    private double limiteCredito;
    private double creditoUtilizado;

    public CuentaCredito(String numeroCuenta, String titular, double saldoInicial, double limiteCredito) {
        super(numeroCuenta, titular, saldoInicial);
        this.limiteCredito = limiteCredito;
        this.creditoUtilizado = 0;
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException, LimiteCreditoExcedidoException {
        if (!isActiva()) {
            throw new IllegalArgumentException("Error: Cuenta inactiva");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("Error: El monto a retirar debe ser positivo");
        }

        double saldoDisponible = getSaldo() + (limiteCredito - creditoUtilizado);
        
        if (monto > saldoDisponible) {
            throw new LimiteCreditoExcedidoException(
                "Error: Límite de crédito excedido. " +
                "Saldo: S/" + getSaldo() + 
                ", Crédito disponible: S/" + (limiteCredito - creditoUtilizado) +
                ", Total disponible: S/" + saldoDisponible
            );
        }
        if (monto > getSaldo()) {
            double excedente = monto - getSaldo();
            creditoUtilizado += excedente;
            super.retirar(getSaldo());
        } else {
            super.retirar(monto); 
        }
    }

    public void transferir(CuentaBancaria destino, double monto) 
            throws SaldoInsuficienteException, LimiteCreditoExcedidoException {
        retirar(monto);
        destino.depositar(monto);
    }

    public double getLimiteCredito() { return limiteCredito; }
    public double getCreditoUtilizado() { return creditoUtilizado; }
    public double getCreditoDisponible() { return limiteCredito - creditoUtilizado; }

    @Override
    public String toString() {
        String estado = isActiva() ? "ACTIVA" : "CERRADA";
        return "Cuenta Crédito: " + getNumeroCuenta() + " - Titular: " + getTitular() + 
               " - Saldo: S/" + getSaldo() + 
               " - Límite: S/" + limiteCredito +
               " - Crédito usado: S/" + creditoUtilizado +
               " - Estado: " + estado;
    }
}
