package plantillas;
public class PruebaPar {
    
    public static <F, S> void imprimirPar(Par<F, S> par) {
        System.out.println("Par: " + par);
    }
    public static void main(String[] args) {
        System.out.println("PROBANDO IMPRIMIR PAR ");
        Par<String, Integer> par1 = new Par<>("Juan", 25);
        Par<Double, Boolean> par2 = new Par<>(3.14, true);    
        imprimirPar(par1);
        imprimirPar(par2);
        System.out.println("\nPROBANDO CONTENEDOR ");
        Contenedor<String, Integer> contenedorPersonas = new Contenedor<>();
        contenedorPersonas.agregarPar("Ana", 20);
        contenedorPersonas.agregarPar("Luis", 30);
        contenedorPersonas.agregarPar("Marta", 25);
        contenedorPersonas.mostrarPares();
        System.out.println("\n OBTENER PAR ESPECÍFICO ");
        Par<String, Integer> parObtenido = contenedorPersonas.obtenerPar(1);
        System.out.println("Par en índice 1: " + parObtenido);
        System.out.println("\n TODOS LOS PARES ");
        for (Par<String, Integer> par : contenedorPersonas.obtenerTodosLosPares()) {
            System.out.println(par);
        }
        System.out.println("\n CONTENEDOR DE NÚMEROS ");
        Contenedor<Double, Boolean> contenedorNumeros = new Contenedor<>();
        contenedorNumeros.agregarPar(10.5, true);
        contenedorNumeros.agregarPar(20.3, false);
        contenedorNumeros.mostrarPares();
    }
}