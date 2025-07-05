package canchamanager.grupo12.upn.model;

import java.time.LocalDateTime;

public class Usuario {

    private int id; // ID único del usuario
    private String username; // nombre de usuario para login
    private String nombreCompleto; // nombre real del usuario
    private String password; // contraseña (encriptada o plana)
    private String rol; // ADMIN o USUARIO
    private LocalDateTime fechaAlta; // cuándo se creó
    private LocalDateTime fechaBaja; // si está inactivo, tiene fecha

    // ✅ Constructor completo
    public Usuario(int id, String username, String nombreCompleto, String password, String rol,
                   LocalDateTime fechaAlta, LocalDateTime fechaBaja) {
        this.id = id;
        this.username = username;
        this.nombreCompleto = nombreCompleto;
        this.password = password;
        this.rol = rol;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
    }

    // ✅ Constructor sin ID (para crear nuevos usuarios)
    public Usuario(String username, String nombreCompleto, String password, String rol) {
        this.username = username;
        this.nombreCompleto = nombreCompleto;
        this.password = password;
        this.rol = rol;
        this.fechaAlta = LocalDateTime.now();
        this.fechaBaja = null; // activo por defecto
    }

    // ✅ Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }
    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDateTime getFechaBaja() {
        return fechaBaja;
    }
    public void setFechaBaja(LocalDateTime fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    // ✅ Saber si el usuario está activo
    public boolean isActivo() {
        return fechaBaja == null;
    }

    // ✅ Mostrar bonito en listas o debug
    @Override
    public String toString() {
        return nombreCompleto + " (" + username + ") - " + (isActivo() ? "Activo" : "Inactivo");
    }
}
