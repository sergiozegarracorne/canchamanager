package canchamanager.grupo12.upn.model;


import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase para representar una Reserva de cancha en el sistema CanchaManager.
 */
public class Reserva {
    private String cliente;
    private Cancha cancha;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private boolean pagada;

    /**
     * Constructor para crear una nueva instancia de Reserva.
     * @param cliente El nombre del cliente que realiza la reserva.
     * @param cancha La cancha que se está reservando.
     * @param fecha La fecha de la reserva.
     * @param horaInicio La hora de inicio de la reserva.
     * @param horaFin La hora de fin de la reserva.
     */
    public Reserva(String cliente, Cancha cancha, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        this.cliente = cliente;
        this.cancha = cancha;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.pagada = false; // Por defecto, una reserva nueva no está pagada
    }

    // Getters
    public String getCliente() {
        return cliente;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public boolean isPagada() {
        return pagada;
    }

    // Setters
    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    // Setter para el estado de pago
    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }
}
