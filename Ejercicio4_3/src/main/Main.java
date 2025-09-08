package main;

import model.Impresora;
import model.ImpresoraMultifuncional;
import model.Escaner;
import controller.OficinaController;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE OFICINA ===");    
        OficinaController controller = new OficinaController();

        Impresora hp = new Impresora("HP LaserJet");
        ImpresoraMultifuncional epson = new ImpresoraMultifuncional("Epson WorkForce");
        Escaner canon = new Escaner("Canon ScanPro");
        controller.mostrarCapacidades(hp);
        controller.mostrarCapacidades(epson);
        controller.mostrarCapacidades(canon);
        System.out.println("\n" + "=".repeat(50));
        controller.realizarImpresion(hp, "Reporte financiero.pdf");
        controller.realizarEscaneo(canon, "Contrato importante.pdf");
        controller.usarMultifuncional(epson, "Presentación.pptx");
        System.out.println("\n" + "=".repeat(50));
        hp.recargarTinta();
        epson.recargarTinta();
        System.out.println("\n" + "=".repeat(50));
        canon.calibrarEscaner();
        epson.calibrarEscaner();      
    }
}