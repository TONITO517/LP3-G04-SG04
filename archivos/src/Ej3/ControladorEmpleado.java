package Ej3;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorEmpleado {
    private List<Empleado> empleados;
    private String archivo = "empleados.dat";
    private VistaEmpleado vista;
    
    public ControladorEmpleado(VistaEmpleado vista) {
        this.vista = vista;
        this.empleados = new ArrayList<>();
        leerEmpleados();
    }
    public void leerEmpleados() {
        File file = new File(archivo);
        if (!file.exists()) {
            vista.mostrarMensaje(" No existe archivo de empleados. Se creará uno nuevo.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            empleados.clear();
            
            while (true) {
                try {
                    Empleado emp = (Empleado) ois.readObject();
                    empleados.add(emp);
                } catch (EOFException e) {
                    break; 
                }
            }
            
            vista.mostrarMensaje( empleados.size() + " empleados cargados desde archivo");
            
        } catch (FileNotFoundException e) {
            vista.mostrarMensaje(" Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            vista.mostrarMensaje(" Error de lectura: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            vista.mostrarMensaje(" Error de formato: " + e.getMessage());
        }
    }
    private void guardarEmpleados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            for (Empleado emp : empleados) {
                oos.writeObject(emp);
            }
        } catch (IOException e) {
            vista.mostrarMensaje("❌ Error al guardar: " + e.getMessage());
        }
    }
    
    public void listarEmpleados() {
        vista.mostrarEmpleados(empleados);
    }
    
    public void agregarEmpleado() {
        Empleado nuevo = vista.leerDatosEmpleado();
        if (nuevo == null) return;
        
        for (Empleado emp : empleados) {
            if (emp.getNumero() == nuevo.getNumero()) {
                vista.mostrarMensaje("❌ Ya existe un empleado con el número: " + nuevo.getNumero());
                return;
            }
        }
        
        empleados.add(nuevo);
        guardarEmpleados();
        vista.mostrarMensaje("✅ Empleado agregado exitosamente");
    }
    
    public void buscarEmpleado() {
        int numero = vista.leerNumeroEmpleado();
        if (numero == -1) return;
        
        for (Empleado emp : empleados) {
            if (emp.getNumero() == numero) {
                vista.mostrarEmpleado(emp);
                return;
            }
        }
        vista.mostrarMensaje("❌ No se encontró empleado con número: " + numero);
    }
    public void eliminarEmpleado() {
        int numero = vista.leerNumeroEmpleado();
        if (numero == -1) return;
        
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getNumero() == numero) {
                Empleado eliminado = empleados.remove(i);
                guardarEmpleados();
                vista.mostrarMensaje("✅ Empleado eliminado: " + eliminado.getNombre());
                return;
            }
        }
        vista.mostrarMensaje("❌ No se encontró empleado con número: " + numero);
    }
    public List<Empleado> getEmpleados() {
        return new ArrayList<>(empleados);
    }
}