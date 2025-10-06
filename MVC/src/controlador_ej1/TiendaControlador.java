package controlador_ej1;

import modelo_ej1.*;
import vista_ej1.TiendaVista;
import java.util.Arrays;
import java.util.List;

public class TiendaControlador {
    private List<Producto> productos;
    private Carrito carrito;
    private Historial historial;
    private TiendaVista vista;

    public TiendaControlador(TiendaVista vista) {
        this.vista = vista;
        this.carrito = new Carrito();
        this.historial = new Historial();

        this.productos = Arrays.asList(
            new Producto("Laptop Gamer", 1200.0, "Tecnología"),
            new Producto("Mouse Inalámbrico", 25.0, "Tecnología"),
            new Producto("Teclado Mecánico", 80.0, "Tecnología"),
            new Producto("Camiseta ", 15.0, "Ropa"),
            new Producto("Jean", 45.0, "Ropa"),
            new Producto("Zapatillas ", 60.0, "Calzado")
        );
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenuPrincipal();
            opcion = vista.solicitarOpcion();
            
            switch (opcion) {
                case "1": 
                    vista.mostrarProductos(productos);
                    break;
                    
                case "2": 
                    vista.mostrarProductos(productos);
                    int idxProducto = vista.solicitarNumeroProducto();
                    if (idxProducto >= 0 && idxProducto < productos.size()) {
                        carrito.agregarProducto(productos.get(idxProducto));
                        vista.mostrarMensaje("Producto agregado al carrito.");
                    } else {
                        vista.mostrarMensaje("Número de producto inválido.");
                    }
                    break;
                      case "3": 
                    vista.mostrarCarrito(carrito);
                    break;               
                case "4": 
                    vista.mostrarCarrito(carrito);
                    int idxEliminar = vista.solicitarEliminarProducto();
                    if (idxEliminar >= 0 && idxEliminar < carrito.getProductos().size()) {
                        carrito.eliminarProducto(idxEliminar);
                        vista.mostrarMensaje("Producto eliminado del carrito.");
                    } else {
                        vista.mostrarMensaje("Número de producto inválido.");
                    }
                    break;   
                case "5": 
                    double descuento = vista.solicitarDescuento();
                    carrito.aplicarDescuento(descuento);
                    vista.mostrarMensaje("Descuento aplicado: " + descuento + "%");
                    vista.mostrarCarrito(carrito);
                    break;  
                case "6":
                    if (carrito.getCantidadProductos() > 0) {
                        String compra = "Compra - Total: $" + carrito.getTotal() + 
                                      " - Productos: " + carrito.getCantidadProductos();
                        historial.agregarCompra(compra);
                        vista.mostrarMensaje("¡Compra realizada con éxito!");
                        vista.mostrarMensaje("Total pagado: $" + carrito.getTotal());
                        carrito.vaciarCarrito();
                    } else {
                        vista.mostrarMensaje("El carrito está vacío.");
                    }
                    break;
                case "7": 
                    vista.mostrarHistorial(historial.getCompras());
                    break;
                case "8": 
                    vista.mostrarMensaje("Gracias por visitar nuestra tienda.");
                    break;
                    
                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        } while (!opcion.equals("8"));
        vista.cerrarScanner();
    }
}