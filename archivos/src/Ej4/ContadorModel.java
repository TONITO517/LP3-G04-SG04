package Ej4;
import java.util.*;
import java.io.*;

public class ContadorModel {
    private int totalLineas;
    private int totalPalabras;
    private int totalCaracteres;
    private double promedioPalabrasPorLinea;
    private Map<String, Integer> palabrasFrecuentes;
    
    public ContadorModel() {
        palabrasFrecuentes = new HashMap<>();
        resetearContadores();
    }
    
    public void resetearContadores() {
        totalLineas = 0;
        totalPalabras = 0;
        totalCaracteres = 0;
        promedioPalabrasPorLinea = 0.0;
        palabrasFrecuentes.clear();
    }
    public void procesarArchivo(File archivo) throws IOException {
        resetearContadores();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            
            while ((linea = reader.readLine()) != null) {
                totalLineas++;
                procesarLinea(linea);
            }
            if (totalLineas > 0) {
                promedioPalabrasPorLinea = (double) totalPalabras / totalLineas;
            }
        }
    }
    
    private void procesarLinea(String linea) {
        totalCaracteres += linea.length();
        StringBuilder palabraActual = new StringBuilder();
        
        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);
            
            if (Character.isLetterOrDigit(c)) {
                palabraActual.append(c);
            } else {
                if (palabraActual.length() > 0) {
                    agregarPalabra(palabraActual.toString());
                    palabraActual.setLength(0); 
                }
            }
        }
        if (palabraActual.length() > 0) {
            agregarPalabra(palabraActual.toString());
        }
    }
    
    private void agregarPalabra(String palabra) {
        totalPalabras++;
        String palabraLower = palabra.toLowerCase();
        palabrasFrecuentes.put(palabraLower, palabrasFrecuentes.getOrDefault(palabraLower, 0) + 1);
    }
    
    // Getters
    public int getTotalLineas() { return totalLineas; }
    public int getTotalPalabras() { return totalPalabras; }
    public int getTotalCaracteres() { return totalCaracteres; }
    public double getPromedioPalabrasPorLinea() { return promedioPalabrasPorLinea; }
    
    public List<Map.Entry<String, Integer>> getPalabrasMasFrecuentes(int cantidad) {
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(palabrasFrecuentes.entrySet());
        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));       
        return lista.subList(0, Math.min(cantidad, lista.size()));
    }
}