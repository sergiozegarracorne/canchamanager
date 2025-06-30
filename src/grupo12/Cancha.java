package grupo12;

public class Cancha {
    private int id;
    private String nombre;
    private String direccion;
    private String horarioInicio;
    private String horarioFin;
    private boolean estado;

    public boolean disponibleEn(String hora) {
        return true;
    }

    public String toString() {
        return nombre + " - " + direccion;
    }
}