package Ej4;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ContadorVista {
    private JFrame frame;
    private JTextArea areaResultados;
    private JButton btnSeleccionarArchivo;
    private JLabel lblEstatus;
    
    public ContadorVista() {
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        frame = new JFrame("Contador de Palabras");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel panelSuperior = new JPanel(new FlowLayout());
        btnSeleccionarArchivo = new JButton("Seleccionar Archivo");
        lblEstatus = new JLabel("Seleccione un archivo para analizar");
        panelSuperior.add(btnSeleccionarArchivo);
        panelSuperior.add(lblEstatus);

        areaResultados = new JTextArea(20, 50);
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaResultados);
        
        frame.add(panelSuperior, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    public void mostrarVista() {
        frame.setVisible(true);
    }
    public void actualizarResultados(int lineas, int palabras, int caracteres, 
                                   double promedio, List<Map.Entry<String, Integer>> palabrasFrecuentes) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESULTADOS DEL ANÁLISIS ===\n\n");
        sb.append(String.format("Total de líneas: %d\n", lineas));
        sb.append(String.format("Total de palabras: %d\n", palabras));
        sb.append(String.format("Total de caracteres: %d\n", caracteres));
        sb.append(String.format("Promedio de palabras por línea: %.2f\n\n", promedio));
        
        sb.append("=== PALABRAS MÁS FRECUENTES ===\n");
        for (Map.Entry<String, Integer> entry : palabrasFrecuentes) {
            sb.append(String.format("%-15s : %d\n", entry.getKey(), entry.getValue()));
        }
        areaResultados.setText(sb.toString());
    }
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public void actualizarEstatus(String mensaje) {
        lblEstatus.setText(mensaje);
    }
    public JButton getBtnSeleccionarArchivo() {
        return btnSeleccionarArchivo;
    }
    public JFrame getFrame() {
        return frame;
    }
}