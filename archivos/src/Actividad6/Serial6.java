package Actividad6;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Serial6 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        
        try {

            fis = new FileInputStream("alumnos.dat");
            entrada = new ObjectInputStream(fis);
            
            System.out.println(" alumnos deseralizados:");
        
            while (true) {
                try {
                    Alumno a = (Alumno) entrada.readObject();
                    System.out.println(a);
                } catch (java.io.EOFException e) {
                    break; 
                }
            }
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("error: " + e.getMessage());
        } finally {
            try {
                if (fis != null) fis.close();
                if (entrada != null) entrada.close();
            } catch (IOException e) {
                System.out.println("error al cerrar: " + e.getMessage());
            }
        }
    }
}