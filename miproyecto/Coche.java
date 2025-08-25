package miproyecto;

public class Coche {
	private String marca;
    private String modelo;
    private int añoFabricacion;
    private double precio;
    private boolean encendido;
    private int velocidad;


    public Coche() {
        this.marca = "Desconocida";
        this.modelo = "Desconocido";
        this.añoFabricacion = 2023;
        this.precio = 0.0;
        this.encendido = false;
        this.velocidad = 0;
    }


    public Coche(String marca, String modelo, int añoFabricacion, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.añoFabricacion = añoFabricacion;
        this.precio = precio;
        this.encendido = false;
        this.velocidad = 0;
    }


    public boolean aplicarDescuento(double descuento) {
        if (añoFabricacion < 2010) {
            this.precio -= descuento;
            return true;
        }
        return false;
    }


    public void encender() {
        encendido = true;
        System.out.println(marca + " " + modelo + " encendido");
    }


    public void acelerar(int kmh) {
        if (encendido) {
            velocidad += kmh;
            System.out.println(marca + " " + modelo + " acelerando. Velocidad: " + velocidad + " km/h");
        }
    }


    public void frenar(int kmh) {
        if (encendido && velocidad > 0) {
            velocidad = Math.max(0, velocidad - kmh);
            System.out.println(marca + " " + modelo + " frenando. Velocidad: " + velocidad + " km/h");
        }
    }


    public void apagar() {
        encendido = false;
        velocidad = 0;
        System.out.println(marca + " " + modelo + " apagado");
    }


    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAñoFabricacion() { return añoFabricacion; }
    public double getPrecio() { return precio; }
}



