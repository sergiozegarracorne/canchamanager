package grupo12;

public class Reserva {
	 private int id;
	    private Cliente cliente;
	    private Cancha cancha;
	    private String fecha;
	    private String horaInicio;
	    private String horaFin;
	    private boolean estado;
	    private boolean pagado;

	    public boolean esValida() {
	        return true;
	    }

	    public boolean interfiereCon(Reserva otraReserva) {
	        return this.fecha.equals(otraReserva.fecha) && this.cancha.equals(otraReserva.cancha) && this.horaInicio.equals(otraReserva.horaInicio);
	    }
	}

