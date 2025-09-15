package src;
import java.util.Scanner;
import java.util.NoSuchElementException;
class Estudiante {
    private String nombre;
    private int edad;
    private String codigo;  
    public Estudiante(String nombre, int edad, String codigo) {
        setNombre(nombre);
        this.edad = edad;
        this.codigo = codigo;
    }   
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: El nombre no puede ser nulo o vacío");
        }
        this.nombre = nombre.trim();
    }    
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getCodigo() { return codigo; }
    
    @Override
    public String toString() {
        return "Código: " + codigo + " - Nombre: " + nombre + " - Edad: " + edad;
    }
}
class RegistroEstudiantes {
    private Estudiante[] estudiantes;
    private int cantidad;
    private static final int CAPACIDAD_MAXIMA = 100;
    
    public RegistroEstudiantes() {
        estudiantes = new Estudiante[CAPACIDAD_MAXIMA];
        cantidad = 0;
    }
    public void agregarEstudiante(Estudiante estudiante) {
        if (cantidad >= CAPACIDAD_MAXIMA) {
            throw new IllegalStateException("Error: El registro está lleno");
        }    
        if (estudiante == null) {
            throw new IllegalArgumentException("Error: El estudiante no puede ser nulo");
        }
        
        estudiantes[cantidad] = estudiante;
        cantidad++;
    }
    public Estudiante buscarEstudiante(String nombre) throws NoSuchElementException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: El nombre de búsqueda no puede ser nulo o vacío");
        }       
        String nombreBusqueda = nombre.trim().toLowerCase();
        
        for (int i = 0; i < cantidad; i++) {
            if (estudiantes[i].getNombre().toLowerCase().equals(nombreBusqueda)) {
                return estudiantes[i];
            }
        }     
        throw new NoSuchElementException("Error: Estudiante '" + nombre + "' no encontrado");
    }
    public void mostrarEstudiantes() {
        if (cantidad == 0) {
            System.out.println(" No hay estudiantes registrados");
            return;
        }    
        System.out.println("\n LISTA DE ESTUDIANTES:");
        for (int i = 0; i < cantidad; i++) {
            System.out.println((i + 1) + ". " + estudiantes[i]);
        }
    }
    
    public int getCantidad() { return cantidad; }
    public int getCapacidadMaxima() { return CAPACIDAD_MAXIMA; }
}
public class SistemaRegistroEstudiantes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegistroEstudiantes registro = new RegistroEstudiantes();       
        System.out.println("SISTEMA DE REGISTRO DE ESTUDIANTES:");
        boolean continuar = true;
        while (continuar) {
            try {
                System.out.println("\nOpciones:");
                System.out.println("1. Agregar estudiante");
                System.out.println("2. Buscar estudiante por nombre");
                System.out.println("3. Mostrar todos los estudiantes");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");
                
                int opcion = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (opcion) {
                    case 1:
                        agregarEstudiante(scanner, registro);
                        break;
                        
                    case 2:
                        buscarEstudiante(scanner, registro);
                        break;
                        
                    case 3:
                        registro.mostrarEstudiantes();
                        break;
                        
                    case 4:
                        continuar = false;
                        System.out.println("👋 Saliendo del sistema...");
                        break;
                        
                    default:
                        System.out.println("❌ Opción inválida");
                }
                
            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage());
                
            } catch (NoSuchElementException e) {
                System.out.println("❌ " + e.getMessage());
                
            } catch (IllegalStateException e) {
                System.out.println("❌ " + e.getMessage());
                
            } catch (Exception e) {
                System.out.println("❌ Error inesperado: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
        
        scanner.close();
    }
    private static void agregarEstudiante(Scanner scanner, RegistroEstudiantes registro) {
        System.out.println("\n➕ AGREGAR ESTUDIANTE");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();     
        System.out.print("Código: ");
        String codigo = scanner.nextLine();       
        Estudiante estudiante = new Estudiante(nombre, edad, codigo);
        registro.agregarEstudiante(estudiante);        
        System.out.println("Estudiante agregado exitosamente");
        System.out.println("Total de estudiantes: " + registro.getCantidad() + "/" + registro.getCapacidadMaxima());
    }
    private static void buscarEstudiante(Scanner scanner, RegistroEstudiantes registro) {
        System.out.println("\n BUSCAR ESTUDIANTE");
        
        if (registro.getCantidad() == 0) {
            System.out.println(" No hay estudiantes registrados para buscar");
            return;
        }
        
        System.out.print("Ingrese el nombre a buscar: ");
        String nombreBuscar = scanner.nextLine();
        
        try {
            Estudiante estudiante = registro.buscarEstudiante(nombreBuscar);
            System.out.println(" Estudiante encontrado:");
            System.out.println("   " + estudiante);
            
        } catch (NoSuchElementException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}