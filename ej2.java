import java.util.Scanner;
public class ArregloAscendente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numeros = new int[10];
        System.out.println("Ingrese 10 numeros en orden ascendente:");
        for (int i = 0; i < numeros.length; i++) {
            int num;
            while (true) {
                System.out.print("Número " + (i + 1) + ": ");
                num = sc.nextInt();
                if (i == 0 || num > numeros[i - 1]) {
                    numeros[i] = num;
                    break;
                } else {
                    System.out.println("El nnumeroebe ser mayor que el anterior (" + numeros[i - 1] + "). Intente nuevamente.");
                }
            }
        }
        System.out.println("\n Numeros ingresados correctamente:");
        for (int n : numeros) {
            System.out.print(n + " ");
        }
        sc.close();
    }
}
