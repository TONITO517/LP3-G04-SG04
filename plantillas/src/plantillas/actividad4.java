package plantillas;

class ExceptionPilaLlena extends RuntimeException {
    public ExceptionPilaLlena(String message) {
        super(message);
    }
}

class ExceptionPilaVacia extends RuntimeException {
    public ExceptionPilaVacia(String message) {
        super(message);
    }
}
public class actividad4<E> { //clase pila
    private final int tamanio;
    private int superior;
    private E[] elements;
    
    public actividad4() {
        this(10);
    }
    
    public actividad4(int s) { //metodo pila
        tamanio = s > 0 ? s : 10;
        superior = -1;
        elements = (E[]) new Object[tamanio];
    }
    
    public void push(E valorMeter) {
        if (superior == tamanio - 1) {
            throw new ExceptionPilaLlena(String.format("La pila está llena, no se puede meter %s", valorMeter));
        }
        elements[++superior] = valorMeter;
    }
    
    public E pop() {
        if (superior == -1) {
            throw new ExceptionPilaVacia("La pila está vacía, no se puede sacar");
        }
        return elements[superior--];
    }
    
    public boolean contains(E elemento) {
        for (int i = superior; i >= 0; i--) {
            if (elements[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean esIgual(actividad4<E> otraPila) {
        if (this.superior != otraPila.superior) {
            return false;
        }
        actividad4<E> pilaTemporal1 = new actividad4<>(this.superior + 1);
        actividad4<E> pilaTemporal2 = new actividad4<>(otraPila.superior + 1);
        
        for (int i = 0; i <= this.superior; i++) {
            pilaTemporal1.push(this.elements[i]);
            pilaTemporal2.push(otraPila.elements[i]);
        }
        
        while (pilaTemporal1.superior >= 0) {
            if (!pilaTemporal1.pop().equals(pilaTemporal2.pop())) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        actividad4<Integer> pila1 = new actividad4<>();
        actividad4<Integer> pila2 = new actividad4<>();
        
        pila1.push(10);
        pila1.push(20);
        pila1.push(30);
        
        pila2.push(10);
        pila2.push(20);
        pila2.push(30);
        
        System.out.println("¿Las pilas son iguales? " + pila1.esIgual(pila2)); // true
        
        pila2.pop();
        pila2.push(40);
        
        System.out.println("¿Las pilas son iguales? " + pila1.esIgual(pila2)); // false
    }
}