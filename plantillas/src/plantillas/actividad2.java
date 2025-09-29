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

public class actividad2<E> {
    private final int tamanio;
    private int superior;
    private E[] elements;
    
    public actividad2() {///pila
        this(10);
    }   
    public actividad2(int s) {//pila
        tamanio = s > 0 ? s : 10;
        superior = -1;
        elements = (E[]) new Object[tamanio];
    }
  
    public void push(E valorAMeter) {
        if (superior == tamanio - 1) {
            throw new ExceptionPilaLlena(String.format("La pila está llena, no se puede meter %s", valorAMeter));
        }
        elements[++superior] = valorAMeter;
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
}