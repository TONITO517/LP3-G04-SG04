package miproyecto;

public class Motor {
    private int numMotor;
    private int revPorMin;

    public Motor(int numMotor, int revPorMin) {
        this.numMotor = numMotor;
        this.revPorMin = revPorMin;
    }

    public int getNumMotor() {
        return numMotor;
    }
    public void setNumMotor(int numMotor) {
        this.numMotor = numMotor;
    }
    public int getRevPorMin() {
        return revPorMin;
    }
    public void setRevPorMin(int revPorMin) {
        this.revPorMin = revPorMin;
    }
    public String toString() {
        return "Número de Motor: " + numMotor + ", Revoluciones por Minuto: " + revPorMin;
    }
}