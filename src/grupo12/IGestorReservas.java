package grupo12;

public interface IGestorReservas {
	  boolean estaDisponible(String fecha, String hora);
	    void crearReserva(String fecha, String hora, Cliente cliente, Cancha cancha);
	    void cancelarReserva(int idReserva);
	    void editarReserva(int idReserva, String nuevaHora);
}
