package Ej4;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;
public class ContadorControlador {
    private ContadorModel model;
    private ContadorVista vista;
    public ContadorControlador(ContadorModel model, ContadorVista vista) {
        this.model = model;
        this.vista = vista;
        configurarEventos();
    }
    private void configurarEventos() {
        vista.getBtnSeleccionarArchivo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarYProcesarArchivo();
            }
        });
    }
    private void seleccionarYProcesarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo para analizar");
        
        int resultado = fileChooser.showOpenDialog(vista.getFrame());
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            
            try {
                vista.actualizarEstatus("Procesando archivo: " + archivo.getName());
                model.procesarArchivo(archivo);
                List<Map.Entry<String, Integer>> palabrasFrecuentes = model.getPalabrasMasFrecuentes(10);
                vista.actualizarResultados(
                    model.getTotalLineas(),
                    model.getTotalPalabras(),
                    model.getTotalCaracteres(),
                    model.getPromedioPalabrasPorLinea(),
                    palabrasFrecuentes
                );
                vista.actualizarEstatus("Archivo procesado exitosamente");
            } catch (Exception ex) {
                vista.mostrarError("Error al procesar el archivo: " + ex.getMessage());
                vista.actualizarEstatus("Error al procesar archivo");
            }
        }
    }
    public void iniciar() {
        vista.mostrarVista();
    }
}