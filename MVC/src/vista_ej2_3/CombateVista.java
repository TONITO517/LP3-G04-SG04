package vista_ej2_3;

import modelo_ej2_3.Jugador;
import modelo_ej2_3.Enemigo;
import java.util.List;
import java.util.Scanner;

public class CombateVista {
    private Scanner scanner;
    public CombateVista() {
        scanner = new Scanner(System.in);
    }
    public void mostrarMenuCombate() {
        System.out.println("\n=== MENÚ DE COMBATE ===");
        System.out.println("1. Atacar");
        System.out.println("2. Usar Objeto");
        System.out.println("3. Equipar Arma");
        System.out.println("4. Ver Inventario");
        System.out.println("5. Huir");
        System.out.println("6. Volver al Menú Principal");
    }

    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine();
    }
    public void mostrarEstadoCombate(Jugador jugador, Enemigo enemigo) {
        System.out.println("\n=== ESTADO DEL COMBATE ===");
        System.out.println(jugador.getNombre() + ": " + jugador.getSalud() + "/" + jugador.getSaludMaxima() + " HP");
        if (enemigo != null) {
            System.out.println(enemigo.getNombre() + ": " + enemigo.getSalud() + " HP");
        }
        if (jugador.getArmaEquipada() != null) {
            System.out.println("Arma equipada: " + jugador.getArmaEquipada().getNombre());
        }
    }
    public void mostrarEnemigos(List<Enemigo> enemigos) {
        System.out.println("\n=== ENEMIGOS DISPONIBLES ===");
        for (int i = 0; i < enemigos.size(); i++) {
            System.out.println((i + 1) + ". " + enemigos.get(i));
        }
    }
    public int solicitarEnemigo() {
        System.out.print("Selecciona un enemigo: ");
        try {
            return Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public String solicitarNombreItem() {
        System.out.print("Nombre del item: ");
        return scanner.nextLine();
    }
    public void mostrarMensajeCombate(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}