package basedatos;

import java.sql.*;
import java.util.*;

public class MainSQLite {

    private static final String DB_URL = "jdbc:sqlite:personas.db";
    private static final String CLAVE_CORRECTA = "admin123";

    public static void main(String[] args) {
        crearTablaSiNoExiste();
        mostrarMenu();
    }

    private static void crearTablaSiNoExiste() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS personas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT NOT NULL," +
                    "edad INTEGER," +
                    "correo TEXT)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Ingresar persona");
            System.out.println("2. Mostrar personas");
            System.out.println("3. Actualizar persona");
            System.out.println("4. Borrar persona");
            System.out.println("5. Consultar registros desde arreglo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> ingresarPersona();
                case 2 -> mostrarPersonas();
                case 3 -> actualizarPersona();
                case 4 -> borrarPersona();
                case 5 -> consultarDesdeArreglo();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private static void ingresarPersona() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();

        if (verificarClave()) {
            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                conn.setAutoCommit(false);
                String sql = "INSERT INTO personas (nombre, edad, correo) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, nombre);
                    pstmt.setInt(2, edad);
                    pstmt.setString(3, correo);
                    pstmt.executeUpdate();
                    conn.commit();
                    System.out.println("Persona agregada correctamente.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    private static void mostrarPersonas() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM personas")) {

            System.out.println("\n--- LISTA DE PERSONAS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Nombre: " + rs.getString("nombre") +
                        " | Edad: " + rs.getInt("edad") +
                        " | Correo: " + rs.getString("correo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void actualizarPersona() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese ID de la persona a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nueva edad: ");
        int edad = sc.nextInt();
        sc.nextLine();
        System.out.print("Nuevo correo: ");
        String correo = sc.nextLine();

        if (verificarClave()) {
            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                conn.setAutoCommit(false);
                String sql = "UPDATE personas SET nombre=?, edad=?, correo=? WHERE id=?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, nombre);
                    pstmt.setInt(2, edad);
                    pstmt.setString(3, correo);
                    pstmt.setInt(4, id);
                    int filas = pstmt.executeUpdate();

                    if (filas > 0) {
                        conn.commit();
                        System.out.println("Persona actualizada correctamente.");
                    } else {
                        conn.rollback();
                        System.out.println("No se encontró el ID especificado.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Clave incorrecta. Operación revertida.");
        }
    }

    private static void borrarPersona() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese ID de la persona a eliminar: ");
        int id = sc.nextInt();

        if (verificarClave()) {
            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                conn.setAutoCommit(false);
                String sql = "DELETE FROM personas WHERE id=?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, id);
                    int filas = pstmt.executeUpdate();

                    if (filas > 0) {
                        conn.commit();
                        System.out.println("Persona eliminada correctamente.");
                    } else {
                        conn.rollback();
                        System.out.println("No se encontró el ID especificado.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Clave incorrecta. Operación cancelada.");
        }
    }

    private static void consultarDesdeArreglo() {
        GestorPersonas gestor = new GestorPersonas();
        gestor.cargarDatos();

        Scanner sc = new Scanner(System.in);
        System.out.print("Campos a mostrar (ejemplo: nombre,edad,correo): ");
        String campos = sc.nextLine();

        System.out.print("Condición (ejemplo: edad>20 o nombre=Juan, dejar vacío si no): ");
        String condicion = sc.nextLine();

        System.out.print("Campo para ordenar (id, nombre, edad, correo, dejar vacío si no): ");
        String ordenar = sc.nextLine();

        System.out.print("Orden ascendente? (true/false): ");
        boolean asc = sc.nextBoolean();

        System.out.print("Límite de registros (0 = sin límite): ");
        int limite = sc.nextInt();

        gestor.consultar(
                Arrays.asList(campos.split(",")),   
                condicion.isEmpty() ? null : condicion,
                ordenar.isEmpty() ? null : ordenar,
                asc,
                limite
        );
    }

    private static boolean verificarClave() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la clave para confirmar: ");
        String clave = sc.nextLine();
        return clave.equals(CLAVE_CORRECTA);
    }
}
