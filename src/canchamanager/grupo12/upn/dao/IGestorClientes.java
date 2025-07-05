package canchamanager.grupo12.upn.dao;

import java.util.List;

import canchamanager.grupo12.upn.model.Cliente;


public interface IGestorClientes {
    void registrarCliente(Cliente cliente);
    Cliente buscarClientePorDni(String dni);
    void actualizarCliente(Cliente cliente);
    void eliminarCliente(int idCliente);
    List<Cliente> listarClientes();
    void cambiarEstadoFrecuente(int idCliente, boolean frecuente);
}
