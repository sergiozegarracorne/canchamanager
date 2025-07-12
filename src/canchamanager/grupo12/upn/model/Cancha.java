package canchamanager.grupo12.upn.model;

public class Cancha {
    private int id;              // Identificador único
    private String nombre;       // Nombre de la cancha
    private String direccion;    // Dirección física
    private String gps;          // Coordenadas GPS
    private String estado;       // DISPONIBLE, OCUPADA, MANTENIMIENTO

    // 🔵 Constructor con ID (para registros existentes)
    public Cancha(int id, String nombre, String direccion, String gps, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.gps = gps;
        this.estado = estado;
    }

    // 🟢 Constructor sin ID (para nuevos registros)
    public Cancha(String nombre, String direccion, String gps, String estado) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.gps = gps;
        this.estado = estado;
    }

    // 🔄 Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getGps() { return gps; }
    public void setGps(String gps) { this.gps = gps; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return nombre; // Para mostrar en JComboBox o listas
    }
}
