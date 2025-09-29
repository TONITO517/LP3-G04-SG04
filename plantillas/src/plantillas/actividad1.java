package plantillas;

class InvalidSubscriptException extends Exception {
    public InvalidSubscriptException(String message) {
        super(message);
    }
}
public class actividad1 {
    
    public static <E> void imprimirArreglo(E[] arregloEntrada) {
        for (E elemento : arregloEntrada) {
            System.out.printf("%s ", elemento);
        }
        System.out.println();
    }
    
    public static <E> int imprimirArreglo(E[] arregloEntrada, int subIndiceInferior, int subIndiceSuperior) throws InvalidSubscriptException {
        // Validación de los Índices
        if (subIndiceInferior < 0 || subIndiceSuperior >= arregloEntrada.length || subIndiceSuperior <= subIndiceInferior) {
            throw new InvalidSubscriptException("Índices fuera de rango o incorrectos.");
        }
        
        // Muestra los elementos del arreglo entre los índices especificados
        int elementosImpresos = 0;
        for (int i = subIndiceInferior; i <= subIndiceSuperior; i++) {
            System.out.printf("%s ", arregloEntrada[i]);
            elementosImpresos++;
        }
        System.out.println();
        return elementosImpresos;
    }
    
    public static void main(String[] args) {
        // Crea los objetos
        Integer[] arregloInteger = { 1, 2, 3, 4, 5, 6 };
        Double[] arregloDouble = { 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7 };
        Character[] arregloCharacter = { 'H', 'O', 'L', 'A' };
        
        // Imprimiendo los arreglos
        System.out.println("El arreglo arregloInteger contiene:");
        imprimirArreglo(arregloInteger); // pasa un arreglo Integer
        
        System.out.println("\nEl arreglo arregloDouble contiene:");
        imprimirArreglo(arregloDouble); // pasa un arreglo Double
        
        System.out.println("\nEl arreglo arregloCharacter contiene:");
        imprimirArreglo(arregloCharacter); // pasa un arreglo Character

        try {
            System.out.println("\nEl arreglo arregloInteger con subíndices 1 y 4 contiene:");
            imprimirArreglo(arregloInteger, 1, 4); // pasa subíndices válidos
            
            System.out.println("\nEl arreglo arregloDouble con subíndices 2 y 5 contiene:");
            imprimirArreglo(arregloDouble, 2, 5); // pasa subíndices válidos
            
            // Intentar con un caso de error
            System.out.println("\nProbando un caso de índices inválidos:");
            imprimirArreglo(arregloCharacter, 3, 1); // índices inválidos
        } catch (InvalidSubscriptException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}