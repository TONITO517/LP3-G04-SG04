package basedatos;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class GestorPersonas {
    private static final String DB_URL = "jdbc:sqlite:personas.db";
    private List<Persona> listaPersonas = new ArrayList<>();

    public void cargarDatos() {
        listaPersonas.clear();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM personas")) {

            while (rs.next()) {
                Persona p = new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("correo")
                );
                listaPersonas.add(p);
            }
            System.out.println("Datos cargados desde la base de datos correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     *
     * @param campos     
     * @param condicion   
     * @param ordenarPor  
     * @param ascendente  
     * @param limite      
     */
    public void consultar(List<String> campos, String condicion, String ordenarPor, boolean ascendente, int limite) {
        List<Persona> resultado = new ArrayList<>(listaPersonas);

        if (condicion != null && !condicion.isEmpty()) {
            resultado = aplicarCondicion(resultado, condicion);
        }

        if (ordenarPor != null && !ordenarPor.isEmpty()) {
            Comparator<Persona> comparador = switch (ordenarPor.toLowerCase()) {
                case "id" -> Comparator.comparing(Persona::getId);
                case "nombre" -> Comparator.comparing(Persona::getNombre);
                case "edad" -> Comparator.comparing(Persona::getEdad);
                case "correo" -> Comparator.comparing(Persona::getCorreo);
                default -> null;
            };
            if (comparador != null) {
                if (ascendente) resultado.sort(comparador);
                else resultado.sort(comparador.reversed());
            }
        }

        if (limite > 0 && limite < resultado.size()) {
            resultado = resultado.subList(0, limite);
        }

        mostrarResultados(resultado, campos);
    }

    private List<Persona> aplicarCondicion(List<Persona> lista, String condicion) {
        String[] partes = condicion.split("(?<=[=<>])|(?=[=<>])");
        if (partes.length < 3) return lista;

        String campo = partes[0].trim();
        String operador = partes[1].trim();
        String valor = partes[2].trim();

        return lista.stream().filter(p -> {
            try {
                return switch (campo.toLowerCase()) {
                    case "id" -> compararNumerico(p.getId(), operador, Integer.parseInt(valor));
                    case "edad" -> compararNumerico(p.getEdad(), operador, Integer.parseInt(valor));
                    case "nombre" -> compararTexto(p.getNombre(), operador, valor);
                    case "correo" -> compararTexto(p.getCorreo(), operador, valor);
                    default -> true;
                };
            } catch (Exception e) {
                return true;
            }
        }).collect(Collectors.toList());
    }

    private boolean compararNumerico(int dato, String op, int valor) {
        return switch (op) {
            case "=" -> dato == valor;
            case ">" -> dato > valor;
            case "<" -> dato < valor;
            default -> false;
        };
    }

    private boolean compararTexto(String dato, String op, String valor) {
        return switch (op) {
            case "=" -> dato.equalsIgnoreCase(valor);
            default -> false;
        };
    }

    private void mostrarResultados(List<Persona> lista, List<String> campos) {
        System.out.println("\n--- RESULTADOS ---");
        for (Persona p : lista) {
            List<String> salida = new ArrayList<>();
            for (String campo : campos) {
                switch (campo.toLowerCase()) {
                    case "id" -> salida.add("ID: " + p.getId());
                    case "nombre" -> salida.add("Nombre: " + p.getNombre());
                    case "edad" -> salida.add("Edad: " + p.getEdad());
                    case "correo" -> salida.add("Correo: " + p.getCorreo());
                }
            }
            System.out.println(String.join(" | ", salida));
        }
        System.out.println("-------------------\n");
    }
}
