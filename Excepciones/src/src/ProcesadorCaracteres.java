package src;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
class LeerEntrada {
    private Reader stream;
    public LeerEntrada(InputStream fuente) {
        stream = new InputStreamReader(fuente);
    }
    
    public char getChar() throws IOException {
        return (char) this.stream.read();
    }
}
class ExcepcionVocal extends Exception {
    private char vocal;
    public ExcepcionVocal(char vocal) {
        super("Excepción de vocal: '" + vocal + "'");
        this.vocal = vocal;
    }
    public char getVocal() { return vocal; }
}

class ExcepcionNumero extends Exception {
    private char numero;
    public ExcepcionNumero(char numero) {
        super("Excepción de número: '" + numero + "'");
        this.numero = numero;
    }
    public char getNumero() { return numero; }
}

class ExcepcionBlanco extends Exception {
    private char blanco;
    public ExcepcionBlanco(char blanco) {
        super("Excepción de blanco");
        this.blanco = blanco;
    }
    public char getBlanco() { return blanco; }
}

class ExcepcionSalida extends Exception {
    private char salida;
    public ExcepcionSalida(char salida) {
        super("Excepción de salida: '" + salida + "'");
        this.salida = salida;
    }
    public char getSalida() { return salida; }
}
public class ProcesadorCaracteres {
    private LeerEntrada lector;
    
    public ProcesadorCaracteres() {
        this.lector = new LeerEntrada(System.in);
    }
    
    public void procesar() throws IOException, ExcepcionVocal, ExcepcionNumero, ExcepcionBlanco, ExcepcionSalida {
        char caracter = lector.getChar();
        
        if ("aeiouAEIOU".indexOf(caracter) != -1) {
            throw new ExcepcionVocal(caracter);
        }
        else if (Character.isDigit(caracter)) {
            throw new ExcepcionNumero(caracter);
        }
        else if (Character.isWhitespace(caracter)) {
            throw new ExcepcionBlanco(caracter);
        }
        else if (caracter == 'x' || caracter == 'X') {
            throw new ExcepcionSalida(caracter);
        }
    }
    public static void main(String[] args) {
        ProcesadorCaracteres procesador = new ProcesadorCaracteres();
        boolean continuar = true;
        
        System.out.println("=== PROCESADOR DE CARACTERES ===");
        System.out.println("Introduzca caracteres (x o X para salir):");
        System.out.println("Nota: Debes presionar ENTER después de cada carácter");
        
        while (continuar) {
            try {
                System.out.print("Ingrese carácter: ");
                procesador.procesar();
                
            } catch (ExcepcionVocal e) {
                System.out.println("🟢 " + e.getMessage());
                
            } catch (ExcepcionNumero e) {
                System.out.println("🔢 " + e.getMessage());
                
            } catch (ExcepcionBlanco e) {
                System.out.println("⚪ " + e.getMessage());
                
            } catch (ExcepcionSalida e) {
                System.out.println("🚪 " + e.getMessage());
                System.out.println("Saliendo del programa...");
                continuar = false;
                
            } catch (IOException e) {
                System.out.println("❌ Error de entrada/salida: " + e.getMessage());
                continuar = false;
            }
        }
    }
}