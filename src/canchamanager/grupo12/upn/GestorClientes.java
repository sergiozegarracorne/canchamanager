package canchamanager.grupo12.upn;

public class GestorClientes implements IGestorClientes {
    @Override
    public void registrarCliente(Cliente cliente) {
        // Guardar nuevo cliente
    }

    @Override
    public Cliente buscarClientePorDni(String dni) {
        // Buscar cliente por DNI
        return null;
    }

    @Override
    public boolean esClienteFrecuente(String dni) {
        // Verificar si cliente es frecuente
        return false;
    }
}