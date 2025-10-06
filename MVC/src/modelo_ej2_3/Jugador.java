package modelo_ej2_3;

public class Jugador {
    private String nombre;
    private int salud;
    private int saludMaxima;
    private int nivel;
    private InventarioModel inventario;
    private Item armaEquipada;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.salud = 100;
        this.saludMaxima = 100;
        this.nivel = 1;
        this.inventario = new InventarioModel();
        this.armaEquipada = null;

        inventario.agregarItem(new Item("Espada Básica", 1, "Arma", "Daño: 10-15"));
        inventario.agregarItem(new Item("Poción de Vida", 2, "Pocion", "Cura 30 HP"));
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public int getSaludMaxima() { return saludMaxima; }
    public int getNivel() { return nivel; }
    public InventarioModel getInventario() { return inventario; }
    public Item getArmaEquipada() { return armaEquipada; }

    public int atacar() {
        int dañoBase = 5; 
        if (armaEquipada != null) {
            if (armaEquipada.getNombre().contains("Espada Básica")) dañoBase = 10 + (int)(Math.random() * 6);
            else if (armaEquipada.getNombre().contains("Hacha")) dañoBase = 15 + (int)(Math.random() * 6);
            else if (armaEquipada.getNombre().contains("Arco")) dañoBase = 12 + (int)(Math.random() * 4);
        }
        return dañoBase;
    }

    public void recibirDaño(int daño) {
        salud -= daño;
        if (salud < 0) salud = 0;
    }

    public String usarObjeto(String nombreItem) {
        Item item = inventario.buscarItem(nombreItem);
        if (item != null) {
            if (item.getTipo().equals("Pocion")) {
                int curacion = 30;
                salud += curacion;
                if (salud > saludMaxima) salud = saludMaxima;
                inventario.usarItem(nombreItem);
                return nombre + " usó " + nombreItem + " y recuperó " + curacion + " HP!";
            }
        }
        return "No se pudo usar el objeto: " + nombreItem;
    }

    public void equiparArma(String nombreArma) {
        Item arma = inventario.buscarItem(nombreArma);
        if (arma != null && arma.getTipo().equals("Arma")) {
            armaEquipada = arma;
        }
    }

    public boolean estaVivo() {
        return salud > 0;
    }

    public void curarCompletamente() {
        salud = saludMaxima;
    }
}