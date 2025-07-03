package canchamanager.grupo12.upn;

public interface IGestorClientes {
	void registrarCliente(Cliente cliente);
    Cliente buscarClientePorDni(String dni);
    boolean esClienteFrecuente(String dni);
}
