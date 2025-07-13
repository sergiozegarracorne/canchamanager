package canchamanager.grupo12.upn.model;

public class Horario {
    private int id;
    private int canchaDeporteId; // FK de la relación cancha-deporte
    private String diaSemana;    // Ej: LUNES, MARTES
    private String horaInicio;   // Ej: "08:00"
    private double porcentaje;   // Ej: +20% o -10%

    public Horario(int id, int canchaDeporteId, String diaSemana, String horaInicio, double porcentaje) {
        this.id = id;
        this.canchaDeporteId = canchaDeporteId;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.porcentaje = porcentaje;
    }

    public Horario(int canchaDeporteId, String diaSemana, String horaInicio, double porcentaje) {
        this(0, canchaDeporteId, diaSemana, horaInicio, porcentaje);
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCanchaDeporteId() { return canchaDeporteId; }
    public void setCanchaDeporteId(int canchaDeporteId) { this.canchaDeporteId = canchaDeporteId; }

    public String getDiaSemana() { return diaSemana; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }

    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
}
