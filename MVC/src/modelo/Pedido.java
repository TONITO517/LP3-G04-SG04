package modelo;

public class Pedido {
    private String nombrePlato;
    private String tipo; //nuevas variables
    private String estado;

    public Pedido(String nombrePlato, String tipo) {
        this.nombrePlato = nombrePlato;
        this.tipo = tipo;
        this.estado = "pendiente"; 
    }

    public String getNombrePlato() {
        return nombrePlato;
    }                             //getterss

    public String getTipo() {
        return tipo;
    }
    public String getEstado() { 
        return estado;
    }
    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void marcarCompleto() {
        this.estado = "completo";
    }
    public void marcarEliminado() {
        this.estado = "eliminado";
    }
    public boolean estaPendiente() {
        return "pendiente".equals(estado);
    }
}