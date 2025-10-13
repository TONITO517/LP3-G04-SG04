package Ej1_2;
import java.util.Scanner;
public class MainVideojuego {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gestor gestor = new Gestor();
        System.out.println(" GESTOR DE PERSONAJES DE VIDEOJUEGO - V2");
        
        while (true) {
            System.out.println("\n MENÚ PRINCIPAL:");
            System.out.println("1. Mostrar personajes");
            System.out.println("2. Agregar personaje");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Eliminar personaje");
            System.out.println("5. Buscar personaje");
            System.out.println("6. ⭐ NUEVAS FUNCIONALIDADES");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    gestor.mostrarPersonajes();
                    break;
                    
                case "2":
                    agregarPersonajeMenu(sc, gestor);
                    break;
                    
                case "3":
                    modificarPersonajeMenu(sc, gestor);
                    break;
                case "4":
                    System.out.print("Nombre del personaje a eliminar: ");
                    String nombreEliminar = sc.nextLine();
                    gestor.eliminarPersonaje(nombreEliminar);
                    break;
                case "5":
                    System.out.print("Nombre del personaje a buscar: ");
                    String nombreBuscar = sc.nextLine();
                    Personaje encontrado = gestor.buscarPersonaje(nombreBuscar);
                    if (encontrado != null) {
                        System.out.println(" Personaje encontrado:");
                        System.out.println(encontrado);
                    } else {
                        System.out.println(" Personaje no encontrado");
                    }
                    break;
                    
                case "6":
                    menuNuevasFuncionalidades(sc, gestor);
                    break;
                    
                case "7":
                    System.out.println(" ¡Hasta pronto!");
                    sc.close();
                    return;
                    
                default:
                    System.out.println(" Opción no válida");
            }
        }
    }
    private static void menuNuevasFuncionalidades(Scanner sc, Gestor gestor) {
        while (true) {
            System.out.println("\n⭐ NUEVAS FUNCIONALIDADES:");
            System.out.println("1. Filtrar personajes por atributo");
            System.out.println("2. Cargar personajes aleatorios");
            System.out.println("3. Actualizar atributo individual");
            System.out.println("4. Mostrar estadísticas");
            System.out.println("5. Importar desde archivo");
            System.out.println("6. Subir de nivel");
            System.out.println("7. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            String opcion = sc.nextLine();
            
            switch (opcion) {
                case "1":
                    System.out.print("Atributo (vida/ataque/defensa/alcance/nivel): ");
                    String atributo = sc.nextLine();
                    System.out.print("Orden (asc/desc): ");
                    String orden = sc.nextLine();
                    gestor.filtrarPorAtributo(atributo, orden.equalsIgnoreCase("asc"));
                    break;
                  
                case "2":
                    System.out.print("Cantidad de personajes aleatorios: ");
                    try {
                        int cantidad = Integer.parseInt(sc.nextLine());
                        gestor.cargarPersonajesAleatorios(cantidad);
                    } catch (NumberFormatException e) {
                        System.out.println(" Cantidad debe ser un número");
                    }
                    break;
                    
                case "3":
                    System.out.print("Nombre del personaje: ");
                    String nombre = sc.nextLine();
                    System.out.print("Atributo a modificar (vida/ataque/defensa/alcance/nivel): ");
                    String attr = sc.nextLine();
                    System.out.print("Nuevo valor: ");
                    try {
                        int valor = Integer.parseInt(sc.nextLine());
                        gestor.actualizarAtributoIndividual(nombre, attr, valor);
                    } catch (NumberFormatException e) {
                        System.out.println(" El valor debe ser un número");
                    }
                    break;
                    
                case "4":
                    gestor.mostrarEstadisticas();
                    break;
                    
                case "5":
                    System.out.print("Ruta del archivo a importar: ");
                    String ruta = sc.nextLine();
                    gestor.importarDesdeArchivo(ruta);
                    break;
                    
                case "6":
                    System.out.print("Nombre del personaje a subir de nivel: ");
                    String nombreNivel = sc.nextLine();
                    gestor.subirNivel(nombreNivel);
                    break;
                    
                case "7":
                    return;
                    
                default:
                    System.out.println(" Opción no válida");
            }
        }
    }
    private static void agregarPersonajeMenu(Scanner sc, Gestor gestor) {
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Vida: ");
            int vida = Integer.parseInt(sc.nextLine());
            System.out.print("Ataque: ");
            int ataque = Integer.parseInt(sc.nextLine());
            System.out.print("Defensa: ");
            int defensa = Integer.parseInt(sc.nextLine());
            System.out.print("Alcance: ");
            int alcance = Integer.parseInt(sc.nextLine());
            System.out.print("Nivel: ");
            int nivel = Integer.parseInt(sc.nextLine());
            
            Personaje nuevo = new Personaje(nombre, vida, ataque, defensa, alcance, nivel);
            gestor.agregarPersonaje(nuevo);
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Los atributos deben ser números enteros");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void modificarPersonajeMenu(Scanner sc, Gestor gestor) {
        try {
            System.out.print("Nombre del personaje a modificar: ");
            String nombre = sc.nextLine();
            System.out.print("Nueva vida: ");
            int vida = Integer.parseInt(sc.nextLine());
            System.out.print("Nuevo ataque: ");
            int ataque = Integer.parseInt(sc.nextLine());
            System.out.print("Nueva defensa: ");
            int defensa = Integer.parseInt(sc.nextLine());
            System.out.print("Nuevo alcance: ");
            int alcance = Integer.parseInt(sc.nextLine());
            System.out.print("Nuevo nivel: ");
            int nivel = Integer.parseInt(sc.nextLine());
            Personaje p = gestor.buscarPersonaje(nombre);
            if (p != null) {
                p.setVida(vida);
                p.setAtaque(ataque);
                p.setDefensa(defensa);
                p.setAlcance(alcance);
                p.setNivel(nivel);
                gestor.guardarCambios();
                System.out.println("✅ Personaje '" + nombre + "' modificado completamente");
            } else {
                System.out.println("❌ Personaje no encontrado");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Los atributos deben ser números enteros");
        }
    }
}