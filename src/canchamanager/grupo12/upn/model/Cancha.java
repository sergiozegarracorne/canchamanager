package canchamanager.grupo12.upn.model;

import java.time.LocalDateTime;

public class Cancha {
	private int id;
	private String nombre;
	private String direccion;
	private String gps;
	private String deporte;


	private String estado; // DISPONIBLE, OCUPADA, MANTENIMIENTO
	private byte[] imagen1, imagen2, imagen3, imagen4;
	private LocalDateTime fechaRegistro;

	// Constructor para nuevo registro
	public Cancha(String nombre, String direccion, String gps, String deporte,  String estado, byte[] imagen1, byte[] imagen2,byte[] imagen3, byte[] imagen4) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.gps = gps;
		this.deporte = deporte;
		this.estado = estado;
		this.imagen1 = imagen1;
		this.imagen2 = imagen2;
		this.imagen3 = imagen3;
		this.imagen4 = imagen4;
		this.fechaRegistro = LocalDateTime.now();
	}

	// Constructor completo
	public Cancha(int id, String nombre, String direccion, String gps, String deporte, String estado, byte[] imagen1, byte[] imagen2,byte[] imagen3, byte[] imagen4, LocalDateTime fechaRegistro) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.gps = gps;
		this.deporte = deporte;
		this.estado = estado;
		this.imagen1 = imagen1;
		this.imagen2 = imagen2;
		this.imagen3 = imagen3;
		this.imagen4 = imagen4;
		this.fechaRegistro = fechaRegistro;
	}

	// Getters y setters...
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}
	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public byte[] getImagen1() {
		return imagen1;
	}

	public void setImagen1(byte[] imagen1) {
		this.imagen1 = imagen1;
	}

	public byte[] getImagen2() {
		return imagen2;
	}

	public void setImagen2(byte[] imagen2) {
		this.imagen2 = imagen2;
	}

	public byte[] getImagen3() {
		return imagen3;
	}

	public void setImagen3(byte[] imagen3) {
		this.imagen3 = imagen3;
	}

	public byte[] getImagen4() {
		return imagen4;
	}

	public void setImagen4(byte[] imagen4) {
		this.imagen4 = imagen4;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

}
