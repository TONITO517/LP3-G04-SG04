package miproyecto;

public class EjemploCoche {

	public static void main(String[] args) {
        Coche cocheDeportivo = new Coche("Ferrari", "488 GTB", 2022, 250000);
        Coche cocheTodoTerreno = new Coche("Toyota", "Hilux", 2008, 35000);

        cocheDeportivo.encender();
        cocheDeportivo.acelerar(50);
        cocheDeportivo.frenar(20);
        cocheDeportivo.apagar();

        cocheTodoTerreno.encender();
        cocheTodoTerreno.acelerar(30);
        cocheTodoTerreno.frenar(10);
        
        boolean descuentoAplicado = cocheTodoTerreno.aplicarDescuento(5000);
        System.out.println("Descuento aplicado al Toyota: " + descuentoAplicado);
        
        cocheTodoTerreno.apagar();
	}

}
