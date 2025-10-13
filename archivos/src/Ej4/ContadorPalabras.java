package Ej4;
import javax.swing.SwingUtilities;
public class ContadorPalabras {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ContadorModel model = new ContadorModel();
                ContadorVista vista = new ContadorVista();
                ContadorControlador controlador = new ContadorControlador(model, vista);
                controlador.iniciar();
            }
        });
    }
}