package plantillas;

public class main {
    public static void main(String[] args) {
        actividad2<Integer> pila = new actividad2<>();
        pila.push(10);
        pila.push(20);
        pila.push(30);
        pila.push(40);
        
        System.out.println("¿La pila contiene el número 20? " + pila.contains(20)); // true
        System.out.println("¿La pila contiene el número 50? " + pila.contains(50)); // false
        
        pila.pop();
        System.out.println("¿La pila contiene el número 40 después de pop? " + pila.contains(40)); // false
    }
}