package canchamanager.grupo12.upn.model;


public class Cancha {
    private String nombre;
    private String direccion;
    private String horarioDisponible; // Formato "HH:MM-HH:MM"

    /**
     * Constructor para crear una nueva instancia de Cancha.
     * @param nombre El nombre de la cancha.
     * @param direccion La dirección física de la cancha.
     * @param horarioDisponible El horario en que la cancha está disponible (ej. "08:00-22:00").
     */
    public Cancha(String nombre, String direccion, String horarioDisponible) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.horarioDisponible = horarioDisponible;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getHorarioDisponible() {
        return horarioDisponible;
    }

    /**
     * Sobreescribe el método toString() para mostrar el nombre de la cancha
     * en componentes como JComboBox.
     * @return El nombre de la cancha.
     */
    @Override
    public String toString() {
        return nombre;
    }
}