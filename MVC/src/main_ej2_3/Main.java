package main_ej2_3;

import modelo_ej2_3.*;
import vista_ej2_3.*;
import controlador_ej2_3.*;

public class Main {
    public static void main(String[] args) {
        Jugador jugador = new Jugador("Héroe");
        InventarioModel modeloInventario = jugador.getInventario();
        CombateModel modeloCombate = new CombateModel(jugador);
        InventarioVista vistaInventario = new InventarioVista();
        CombateVista vistaCombate = new CombateVista();
        InventarioController controladorInventario = new InventarioController(modeloInventario, vistaInventario);
        CombateController controladorCombate = new CombateController(modeloCombate, vistaCombate, vistaInventario);
        menuPrincipal(controladorInventario, controladorCombate, vistaCombate);
    }
    public static void menuPrincipal(InventarioController controladorInventario, 
                                   CombateController controladorCombate,
                                   CombateVista vista) {
        String opcion;
        do {
            System.out.println("\n=== SISTEMA RPG ===");
            System.out.println("1. Gestión de Inventario");
            System.out.println("2. Sistema de Combate");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");       
            opcion = vista.solicitarOpcion();
            switch (opcion) {
                case "1":
                    controladorInventario.iniciar();
                    break;
                case "2":
                    controladorCombate.iniciarCombate();
                    break;
                case "3":
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (!opcion.equals("3"));
        
        vista.cerrarScanner();
    }
}