package controlador_ej2_3;

import modelo_ej2_3.CombateModel;
import vista_ej2_3.CombateVista;
import vista_ej2_3.InventarioVista;

public class CombateController {
    private CombateModel modelo;
    private CombateVista vista;
    private InventarioVista vistaInventario;

    public CombateController(CombateModel modelo, CombateVista vista, InventarioVista vistaInventario) {
        this.modelo = modelo;
        this.vista = vista;
        this.vistaInventario = vistaInventario;
    }

    public void iniciarCombate() {
        vista.mostrarEnemigos(modelo.getEnemigos());
        int indiceEnemigo = vista.solicitarEnemigo();
        
        String resultado = modelo.iniciarCombate(indiceEnemigo);
        vista.mostrarMensajeCombate(resultado);
        
        if (modelo.isCombateActivo()) {
            gestionarCombate();
        }
    }

    private void gestionarCombate() {
        while (modelo.isCombateActivo() && 
               modelo.getJugador().estaVivo() && 
               modelo.getEnemigoActual().estaVivo()) {
            
            vista.mostrarEstadoCombate(modelo.getJugador(), modelo.getEnemigoActual());
            vista.mostrarMenuCombate();
            String opcion = vista.solicitarOpcion();
            
            switch (opcion) {
                case "1": 
                    String resultadoAtaque = modelo.jugadorAtaca();
                    vista.mostrarMensajeCombate(resultadoAtaque);
                    if (modelo.isCombateActivo() && modelo.getEnemigoActual().estaVivo()) {
                        String ataqueEnemigo = modelo.enemigoAtaca();
                        vista.mostrarMensajeCombate(ataqueEnemigo);
                    }
                    break;
                case "2": 
                    vistaInventario.mostrarInventario(modelo.getJugador().getInventario().obtenerItems());
                    String nombreItem = vista.solicitarNombreItem();
                    String resultadoItem = modelo.usarObjeto(nombreItem);
                    vista.mostrarMensajeCombate(resultadoItem);
                    if (modelo.isCombateActivo() && modelo.getEnemigoActual().estaVivo()) {
                        String ataqueEnemigo = modelo.enemigoAtaca();
                        vista.mostrarMensajeCombate(ataqueEnemigo);
                    }
                    break;
                case "3": 
                    vistaInventario.mostrarInventario(modelo.getJugador().getInventario().obtenerItems());
                    String nombreArma = vista.solicitarNombreItem();
                    modelo.getJugador().equiparArma(nombreArma);
                    vista.mostrarMensajeCombate("Arma equipada: " + nombreArma);
                    break;     
                case "4": 
                    vistaInventario.mostrarInventario(modelo.getJugador().getInventario().obtenerItems());
                    break; 
                case "5": 
                    String resultadoHuir = modelo.huir();
                    vista.mostrarMensajeCombate(resultadoHuir);
                    return;
                case "6": 
                    modelo.huir();
                    return;
                    
                default:
                    vista.mostrarMensajeCombate("Opción no válida.");
            }
        }
    }
}