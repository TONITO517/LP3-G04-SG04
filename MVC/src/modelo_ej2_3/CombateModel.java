package modelo_ej2_3;

import java.util.ArrayList;
import java.util.List;

public class CombateModel {
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private Enemigo enemigoActual;
    private boolean combateActivo;

    public CombateModel(Jugador jugador) {
        this.jugador = jugador;
        this.enemigos = new ArrayList<>();
        this.combateActivo = false;
        enemigos.add(new Enemigo("Orco Salvaje", 50, 2, "Orco"));
        enemigos.add(new Enemigo("Esqueleto Guerrero", 40, 3, "Esqueleto"));
        enemigos.add(new Enemigo("Mago Oscuro", 35, 4, "Mago"));
    }
    public Jugador getJugador() { return jugador; }
    public List<Enemigo> getEnemigos() { return enemigos; }
    public Enemigo getEnemigoActual() { return enemigoActual; }
    public boolean isCombateActivo() { return combateActivo; }
    public String iniciarCombate(int indiceEnemigo) {
        if (indiceEnemigo >= 0 && indiceEnemigo < enemigos.size()) {
            enemigoActual = enemigos.get(indiceEnemigo);
            combateActivo = true;
            jugador.curarCompletamente(); 
            return "¡Combate iniciado contra " + enemigoActual.getNombre() + "!";
        }
        return "Enemigo no válido.";
    }
    public String jugadorAtaca() {
        if (!combateActivo || enemigoActual == null) return "No hay combate activo.";
        
        int daño = jugador.atacar();
        enemigoActual.recibirDaño(daño);
        
        String mensaje = jugador.getNombre() + " ataca con " + 
                        (jugador.getArmaEquipada() != null ? jugador.getArmaEquipada().getNombre() : "puños") +
                        " y causa " + daño + " de daño!";
        if (!enemigoActual.estaVivo()) {
            mensaje += "\n¡" + enemigoActual.getNombre() + " ha sido derrotado!";
            combateActivo = false;
        }
        return mensaje;
    }
    public String enemigoAtaca() {
        if (!combateActivo || enemigoActual == null || !enemigoActual.estaVivo()) 
            return "";
        
        int daño = enemigoActual.atacar();
        jugador.recibirDaño(daño);
        
        String mensaje = enemigoActual.getNombre() + " ataca y causa " + daño + " de daño!";
        
        if (!jugador.estaVivo()) {
            mensaje += "\n¡" + jugador.getNombre() + " ha sido derrotado!";
            combateActivo = false;
        }
        return mensaje;
    }
    public String usarObjeto(String nombreItem) {
        if (!combateActivo) return "No hay combate activo.";
        
        String resultado = jugador.usarObjeto(nombreItem);
        return resultado;
    }
    public String huir() {
        if (combateActivo) {
            combateActivo = false;
            return jugador.getNombre() + " huye del combate.";
        }
        return "No hay combate activo.";
    }
}