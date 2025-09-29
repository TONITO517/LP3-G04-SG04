package plantillas;

public class actividad3{
    
    // metodo para comparar elementos
    public static <E> boolean esIgualA(E obj1, E obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }
    
    public static void main(String[] args) {
        Integer a = 5;
        Integer b = 5;
        String str1 = "Hola";
        String str2 = "Hola";
        Object obj1 = new Object();
        Object obj2 = obj1;
        
        System.out.println("Integer: " + esIgualA(a, b)); // true
        System.out.println("String: " + esIgualA(str1, str2)); // true
        System.out.println("Object: " + esIgualA(obj1, obj2)); // true
        System.out.println("Null: " + esIgualA(a, null)); // false
        System.out.println("Null: " + esIgualA(null, null)); // true
        System.out.println("Different types: " + esIgualA(a, str1)); // false
    }
}