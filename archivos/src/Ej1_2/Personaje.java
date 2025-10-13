package Ej1_2;
public class Personaje {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;
    private int nivel;  //nuevo atributo
    
    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        this(nombre, vida, ataque, defensa, alcance, 1);  // nivel por defecto
    }
    
    // nuevo constructor
    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance, int nivel) {
        if (vida <= 0 || ataque <= 0 || defensa <= 0 || alcance <= 0 || nivel <= 0) {
            throw new IllegalArgumentException(" Todos los atributos deben ser mayores a 0");
        }
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.alcance = alcance;
        this.nivel = nivel;
    }

    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getAlcance() { return alcance; }
    public int getNivel() { return nivel; }  //nuevo getter    

    public void setVida(int vida) {
        if (vida <= 0) throw new IllegalArgumentException(" La vida debe ser mayor a 0");
        this.vida = vida;
    }
    
    public void setAtaque(int ataque) {
        if (ataque <= 0) throw new IllegalArgumentException(" El ataque debe ser mayor a 0");
        this.ataque = ataque;
    }
    
    public void setDefensa(int defensa) {
        if (defensa <= 0) throw new IllegalArgumentException(" La defensa debe ser mayor a 0");
        this.defensa = defensa;
    }
    
    public void setAlcance(int alcance) {
        if (alcance <= 0) throw new IllegalArgumentException(" El alcance debe ser mayor a 0");
        this.alcance = alcance;
    }
    public void setNivel(int nivel) {
        if (nivel <= 0) throw new IllegalArgumentException(" El nivel debe ser mayor a 0");
        this.nivel = nivel; // nuevoi seter
    }
    
    
    @Override
    public String toString() {
        return String.format("%-12s %-6d %-8d %-9d %-8d %-6d", 
                           nombre, vida, ataque, defensa, alcance, nivel);
    }
    public void subirNivel() {//nuevo metodo
        this.nivel++;
        this.vida += 2;     
        this.ataque += 1;    
        this.defensa += 1;  
        System.out.println("🎉 " + nombre + " subió al nivel " + nivel + "!");
    }
    
    public String toFileString() {
        return nombre + "," + vida + "," + ataque + "," + defensa + "," + alcance + "," + nivel;
    }
    public static Personaje fromFileString(String line) {
        String[] parts = line.split(",");
     
        if (parts.length == 5) {
            return new Personaje(
                parts[0].trim(), 
                Integer.parseInt(parts[1].trim()), 
                Integer.parseInt(parts[2].trim()), 
                Integer.parseInt(parts[3].trim()), 
                Integer.parseInt(parts[4].trim())
            );
        }
        else if (parts.length == 6) {
            return new Personaje(
                parts[0].trim(), 
                Integer.parseInt(parts[1].trim()), 
                Integer.parseInt(parts[2].trim()), 
                Integer.parseInt(parts[3].trim()), 
                Integer.parseInt(parts[4].trim()), 
                Integer.parseInt(parts[5].trim())
            );
        }
        else {
            throw new IllegalArgumentException("Formato inválido. Se esperaban 5 o 6 valores, pero se encontraron: " + parts.length);
        }
    }
}