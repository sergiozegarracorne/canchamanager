package grupo12;

import java.util.ArrayList;

public class SistemaReservas implements IGestorReservas {
    private ArrayList<Cancha> canchas = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();

    @Override
    public boolean estaDisponible(String fecha, String hora) {
        return true;
    }

    @Override
    public void crearReserva(String fecha, String hora, Cliente cliente, Cancha cancha) {
        // Lógica para crear reserva
    }

    @Override
    public void cancelarReserva(int idReserva) {
        // Lógica para cancelar
    }

    @Override
    public void editarReserva(int idReserva, String nuevaHora) {
        // Lógica para editar
    }

    public void generarPDF(Reserva r) {
        // Genera comprobante
    }
}