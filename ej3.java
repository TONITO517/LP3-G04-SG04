import java.util.Random;
public class LanzarDado {
    public static void main(String[] args) {
        Random random = new Random();
        int[] frecuencias = new int[6]; 
        for (int i = 0; i < 20000; i++) {
            int cara = random.nextInt(6) + 1; 
            frecuencias[cara - 1]++; 
        }


        System.out.println("Frecuencia de cada cara:");
        for (int i = 0; i < frecuencias.length; i++) {
            System.out.println("Cara " + (i + 1) + ": " + frecuencias[i]);
        }
    }
}
