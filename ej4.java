public class NumeroMenor {
    public static double menor(double a, double b, double c) {
        double min = a;
        if (b < min) {
            min = b;
        }
        if (c < min) {
            min = c;
        }
        return min;
    }
    public static void main(String[] args) {
        double x = 5.7, y = 3.2, z = 8.9;
        System.out.println("El menor es: " + menor(x, y, z));
    }
}
