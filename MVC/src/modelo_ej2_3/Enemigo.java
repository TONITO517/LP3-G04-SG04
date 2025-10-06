package modelo_ej2_3;

public class Enemigo {
    private String nombre;
    private int salud;
    private int nivel;
    private String tipo;

    public Enemigo(String nombre, int salud, int nivel, String tipo) {
        this.nombre = nombre;
        this.salud = salud;
        this.nivel = nivel;
        this.tipo = tipo;
    }

    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public int getNivel() { return nivel; }
    public String getTipo() { return tipo; }

    public int atacar() {
        int dañoBase = 5 + nivel;
        switch (tipo) {
            case "Orco": dañoBase += 5; break;
            case "Esqueleto": dañoBase += 3; break;
            case "Mago": dañoBase += 2; break;
        }
        return dañoBase + (int)(Math.random() * 5);
    }
    public void recibirDaño(int daño) {
        salud -= daño;
        if (salud < 0) salud = 0;
    }
    public boolean estaVivo() {
        return salud > 0;
    }
    @Override
    public String toString() {
        return nombre + " (Nivel " + nivel + ") - HP: " + salud + " - Tipo: " + tipo;
    }
}