package controlador_ej2_3;

import modelo_ej2_3.InventarioModel;
import modelo_ej2_3.Item;
import vista_ej2_3.InventarioVista;
public class InventarioController {
    private InventarioModel modelo;
    private InventarioVista vista;

    public InventarioController(InventarioModel modelo, InventarioVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();
            
            switch (opcion) {
                case "1": 
                    vista.mostrarInventario(modelo.obtenerItems());
                    break;
                case "2": 
                    agregarItem();
                    break;
                case "3": 
                    eliminarItem();
                    break;
                case "4": 
                    usarItem();
                    break;
                case "5": 
                    buscarItem();
                    break;
                case "6": 
                    mostrarDetalles();
                    break;
                case "7": 
                    vista.mostrarMensaje("Saliendo del sistema de inventario...");
                    break;
                    
                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        } while (!opcion.equals("7"));
    }

    private void agregarItem() {
        String nombre = vista.solicitarNombre();
        int cantidad = vista.solicitarCantidad();
        String tipo = vista.solicitarTipo();
        String descripcion = vista.solicitarDescripcion();
        
        Item nuevoItem = new Item(nombre, cantidad, tipo, descripcion);
        modelo.agregarItem(nuevoItem);
        vista.mostrarMensaje("Item agregado: " + nombre);
    }

    private void eliminarItem() {
        vista.mostrarInventario(modelo.obtenerItems());
        String nombre = vista.solicitarNombre();
        
        if (modelo.eliminarItem(nombre)) {
            vista.mostrarMensaje("Item eliminado: " + nombre);
        } else {
            vista.mostrarMensaje("No se pudo eliminar el item: " + nombre);
        }
    }

    private void usarItem() {
        vista.mostrarInventario(modelo.obtenerItems());
        String nombre = vista.solicitarNombre();
        
        String resultado = modelo.usarItem(nombre);
        vista.mostrarMensaje(resultado);
    }

    private void buscarItem() {
        String nombre = vista.solicitarNombre();
        Item item = modelo.buscarItem(nombre);
        
        if (item != null) {
            vista.mostrarMensaje("Item encontrado: " + item);
        } else {
            vista.mostrarMensaje("Item no encontrado: " + nombre);
        }
    }

    private void mostrarDetalles() {
        vista.mostrarInventario(modelo.obtenerItems());
        String nombre = vista.solicitarNombre();
        
        String detalles = modelo.mostrarDetalles(nombre);
        vista.mostrarDetallesItem(detalles);
    }
}