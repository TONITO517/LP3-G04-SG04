package miproyecto;

public class ContadorTest {
    public static void main(String[] args) {
        System.out.println("=== INICIO DEL TEST ===");

        System.out.println("Contadores creados: " + Contador.getNContadores());
        System.out.println("Último contador: " + Contador.getUltimoContador());
        System.out.println("Acumulador: " + Contador.getAcumulador());
        

        Contador c1 = new Contador();
        System.out.println("\nDespués de crear c1 (constructor por defecto):");
        System.out.println("Valor de c1: " + c1.getValor());
        System.out.println("Contadores creados: " + Contador.getNContadores());
        System.out.println("Último contador: " + Contador.getUltimoContador());
        System.out.println("Acumulador: " + Contador.getAcumulador());
        
        Contador c2 = new Contador(5);
        System.out.println("\nDespués de crear c2 (valor 5):");
        System.out.println("Valor de c2: " + c2.getValor());
        System.out.println("Contadores creados: " + Contador.getNContadores());
        System.out.println("Último contador: " + Contador.getUltimoContador());
        System.out.println("Acumulador: " + Contador.getAcumulador());
        
        c1.inc();
        c2.inc();
        System.out.println("\nDespués de incrementar ambos contadores:");
        System.out.println("Valor de c1: " + c1.getValor());
        System.out.println("Valor de c2: " + c2.getValor());
        System.out.println("Acumulador: " + Contador.getAcumulador());

        Contador c3 = new Contador(8);
        System.out.println("\nDespués de crear c3 (valor 8):");
        System.out.println("Valor de c3: " + c3.getValor());
        System.out.println("Contadores creados: " + Contador.getNContadores());
        System.out.println("Último contador: " + Contador.getUltimoContador());
        System.out.println("Acumulador: " + Contador.getAcumulador());
        
        System.out.println("\n=== FIN DEL TEST ===");
    }
}