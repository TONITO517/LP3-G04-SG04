package Ej3;
public class SistemaEmpleados {
    public static void main(String[] args) {
        VistaEmpleado vista = new VistaEmpleado();
        ControladorEmpleado controlador = new ControladorEmpleado(vista);
        
        System.out.println(" Sistema empleados");
        
        boolean ejecutando = true;
        
        while (ejecutando) {
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();
            
            switch (opcion) {
                case 1:
                    controlador.listarEmpleados();
                    break;
                    
                case 2:
                    controlador.agregarEmpleado();
                    break;
                    
                case 3:
                    controlador.buscarEmpleado();
                    break;
                    
                case 4:
                    controlador.eliminarEmpleado();
                    break;
                    
                case 5:
                    vista.mostrarMensaje("👋 ¡Hasta pronto!");
                    ejecutando = false;
                    break;
                    
                default:
                    vista.mostrarMensaje("❌ Opción no válida. Intente nuevamente.");
            }
        }
        
        vista.cerrarScanner();
    }
}