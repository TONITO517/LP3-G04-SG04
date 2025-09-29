package plantillas;

public class Par<F, S> {
    private F primero;
    private S segundo;
    
    public Par(F primero, S segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }
    
    public F getPrimero() { return primero; }
    public void setPrimero(F primero) { this.primero = primero; }
    
    public S getSegundo() { return segundo; }
    public void setSegundo(S segundo) { this.segundo = segundo; }
    
    public boolean esIgual(Par<F, S> otroPar) {
        if (otroPar == null) return false;
        return this.primero.equals(otroPar.primero) && 
               this.segundo.equals(otroPar.segundo);
    }
    
    @Override
    public String toString() {
        return "(" + primero + ", " + segundo + ")";
    }
}