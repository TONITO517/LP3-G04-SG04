package Main_ej1;

import vista_ej1.TiendaVista;
import controlador_ej1.TiendaControlador;

public class Main {
    public static void main(String[] args) {
        TiendaVista vista = new TiendaVista();
        TiendaControlador controlador = new TiendaControlador(vista);
        controlador.iniciar();
    }
}