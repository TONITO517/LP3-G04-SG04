package Ej1_2;
import java.io.*;
import java.util.*;
import java.util.Scanner;
public class Gestor {
    private List<Personaje> personajes;
    private String archivo = "personajes.txt";
    
    public Gestor() {
        personajes = new ArrayList<>();
        cargarPersonajes();
    }
    private void cargarPersonajes() {
        try {
            File file = new File(archivo);
            if (!file.exists()) {
                System.out.println(" Creando archivo de personajes...");
                crearPersonajesIniciales();
                return;
            }
            
            Scanner scanner = new Scanner(file);
            personajes.clear(); 
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    try {
                        Personaje p = Personaje.fromFileString(line);
                        personajes.add(p);
                        System.out.println(" Cargado: " + p.getNombre()); 
                    } catch (Exception e) {
                        System.out.println(" Error cargando personaje: " + line);
                    }
                }
            }
            scanner.close();
            System.out.println(personajes.size() + " personajes cargados");
            
        } catch (FileNotFoundException e) {
            System.out.println(" Error al cargar personajes: " + e.getMessage());
        }
    }
    private void crearPersonajesIniciales() {
        agregarPersonaje(new Personaje("Caballero", 4, 2, 4, 2));
        agregarPersonaje(new Personaje("Guerrero", 2, 4, 2, 4));
        agregarPersonaje(new Personaje("Arquero", 2, 4, 1, 8));
        guardarCambios();
    }
    
    public void guardarCambios() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Personaje p : personajes) {
                writer.println(p.toFileString());
            }
            System.out.println("Cambios guardados en " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
    
    public boolean agregarPersonaje(Personaje nuevo) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nuevo.getNombre())) {
                System.out.println("El personaje '" + nuevo.getNombre() + "' ya existe");
                return false;
            }
        }
        personajes.add(nuevo);
        guardarCambios();
        System.out.println(" Personaje '" + nuevo.getNombre() + "' agregado");
        return true;
    }
    
    public void mostrarPersonajes() {
        System.out.println("\nLISTA DE PERSONAJES:");
        System.out.printf("%-12s %-6s %-8s %-9s %-8s %-6s%n", 
                         "NOMBRE", "VIDA", "ATAQUE", "DEFENSA", "ALCANCE", "NIVEL");
        for (Personaje p : personajes) {
            System.out.println(p);
        }
    }
    
    public Personaje buscarPersonaje(String nombre) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }
    
    public boolean modificarPersonaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        Personaje p = buscarPersonaje(nombre);
        if (p != null) {
            try {
                p.setVida(vida);
                p.setAtaque(ataque);
                p.setDefensa(defensa);
                p.setAlcance(alcance);
                guardarCambios();
                System.out.println(" Personaje '" + nombre + "' modificado");
                return true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        System.out.println(" Personaje '" + nombre + "' no encontrado");
        return false;
    }
    
    public boolean eliminarPersonaje(String nombre) {
        Personaje p = buscarPersonaje(nombre);
        if (p != null) {
            personajes.remove(p);
            guardarCambios();
            System.out.println(" Personaje '" + nombre + "' eliminado");
            return true;
        }
        System.out.println(" Personaje '" + nombre + "' no encontrado");
        return false;
    }
    
    public List<Personaje> getPersonajes() {
        return new ArrayList<>(personajes);
    }
    public void filtrarPorAtributo(String atributo, boolean ascendente) {
        List<Personaje> copia = new ArrayList<>(personajes);
        
        switch (atributo.toLowerCase()) {
            case "vida":
                copia.sort((p1, p2) -> ascendente ? Integer.compare(p1.getVida(), p2.getVida()) 
                                                 : Integer.compare(p2.getVida(), p1.getVida()));
                break;
            case "ataque":
                copia.sort((p1, p2) -> ascendente ? Integer.compare(p1.getAtaque(), p2.getAtaque()) 
                                                 : Integer.compare(p2.getAtaque(), p1.getAtaque()));
                break;
            case "defensa":
                copia.sort((p1, p2) -> ascendente ? Integer.compare(p1.getDefensa(), p2.getDefensa()) 
                                                 : Integer.compare(p2.getDefensa(), p1.getDefensa()));
                break;
            case "alcance":
                copia.sort((p1, p2) -> ascendente ? Integer.compare(p1.getAlcance(), p2.getAlcance()) 
                                                 : Integer.compare(p2.getAlcance(), p1.getAlcance()));
                break;
            case "nivel":
                copia.sort((p1, p2) -> ascendente ? Integer.compare(p1.getNivel(), p2.getNivel()) 
                                                 : Integer.compare(p2.getNivel(), p1.getNivel()));
                break;            default:
                System.out.println(" Atributo no válido");
                return;
        }
        
        System.out.println("\n PERSONAJES ORDENADOS POR " + atributo.toUpperCase() + 
                          " (" + (ascendente ? "ASCENDENTE" : "DESCENDENTE") + "):");
        System.out.printf("%-12s %-6s %-8s %-9s %-8s %-6s%n", 
                         "NOMBRE", "VIDA", "ATAQUE", "DEFENSA", "ALCANCE", "NIVEL");
        for (Personaje p : copia) {
            System.out.println(p);
        }
    }
    public void cargarPersonajesAleatorios(int cantidad) {
        String[] nombres = {"Mago", "Asesino", "Tanque", "Sanador", "Ninja", "Barbaro", "Paladin", "Pícaro"};
        Random rand = new Random();
        
        for (int i = 0; i < cantidad; i++) {
            String nombre = nombres[rand.nextInt(nombres.length)] + (i + 1);
            int vida = rand.nextInt(10) + 1;
            int ataque = rand.nextInt(8) + 1;
            int defensa = rand.nextInt(6) + 1;
            int alcance = rand.nextInt(10) + 1;
            int nivel = rand.nextInt(5) + 1;
            
            Personaje aleatorio = new Personaje(nombre, vida, ataque, defensa, alcance, nivel);
            agregarPersonaje(aleatorio);
        }
        System.out.println( cantidad + " personajes aleatorios agregados");
    }
    public boolean actualizarAtributoIndividual(String nombre, String atributo, int nuevoValor) {
        Personaje p = buscarPersonaje(nombre);
        if (p == null) {
            System.out.println(" Personaje '" + nombre + "' no encontrado");
            return false;
        }
        
        try {
            switch (atributo.toLowerCase()) {
                case "vida": p.setVida(nuevoValor); break;
                case "ataque": p.setAtaque(nuevoValor); break;
                case "defensa": p.setDefensa(nuevoValor); break;
                case "alcance": p.setAlcance(nuevoValor); break;
                case "nivel": p.setNivel(nuevoValor); break;
                default: 
                    System.out.println("❌ Atributo no válido");
                    return false;
            }
            guardarCambios();
            System.out.println(atributo + " de '" + nombre + "' actualizado a " + nuevoValor);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("❌ No hay personajes para mostrar estadísticas");
            return;
        }
        int totalVida = 0, totalAtaque = 0, totalDefensa = 0, totalAlcance = 0, totalNivel = 0;
        
        for (Personaje p : personajes) {
            totalVida += p.getVida();
            totalAtaque += p.getAtaque();
            totalDefensa += p.getDefensa();
            totalAlcance += p.getAlcance();
            totalNivel += p.getNivel();
        }
        
        int total = personajes.size();
        
        System.out.println("\n ESTADÍSTICAS GENERALES:");
        System.out.println("Total de personajes: " + total);
        System.out.printf("Vida promedio: %.2f%n", (double) totalVida / total);
        System.out.printf("Ataque promedio: %.2f%n", (double) totalAtaque / total);
        System.out.printf("Defensa promedio: %.2f%n", (double) totalDefensa / total);
        System.out.printf("Alcance promedio: %.2f%n", (double) totalAlcance / total);
        System.out.printf("Nivel promedio: %.2f%n", (double) totalNivel / total);
    }
    public void importarDesdeArchivo(String rutaArchivo) {
        try (Scanner scanner = new Scanner(new File(rutaArchivo))) {
            int importados = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    try {
                        Personaje p = Personaje.fromFileString(line);
                        if (agregarPersonaje(p)) {
                            importados++;
                        }
                    } catch (Exception e) {
                        System.out.println(" Error importando línea: " + line);
                    }
                }
            }
            System.out.println(importados + " personajes importados desde " + rutaArchivo);
        } catch (FileNotFoundException e) {
            System.out.println(" Archivo no encontrado: " + rutaArchivo);
        }
    }
    public boolean subirNivel(String nombre) {
        Personaje p = buscarPersonaje(nombre);
        if (p != null) {
            p.subirNivel();
            guardarCambios();
            return true;
        }
        System.out.println("❌ Personaje '" + nombre + "' no encontrado");
        return false;
    }
}