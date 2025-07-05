package canchamanager.grupo12.upn.model;

import java.time.LocalDateTime;


public class Cliente {
    private int id;
    private String nombre;
    private String telefono;
    private String email;
    private String dni;
    private boolean frecuente;
    private LocalDateTime fechaRegistro; // ✅ nuevo atributo

    // Constructor
    public Cliente(String nombre, String telefono, String email, String dni, boolean frecuente) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.dni = dni;
        this.frecuente = frecuente;
        this.fechaRegistro = LocalDateTime.now(); // Asigna la fecha actual
    }
    
    public Cliente(int id, String nombre, String telefono, String email, String dni, boolean frecuente, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.dni = dni;
        this.frecuente = frecuente;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public boolean isFrecuente() { return frecuente; }
    public void setFrecuente(boolean frecuente) { this.frecuente = frecuente; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
