package grupo12;

public interface IGestorClientes {
	void registrarCliente(Cliente cliente);
    Cliente buscarClientePorDni(String dni);
    boolean esClienteFrecuente(String dni);
}
