package canchamanager.grupo12.upn.controller;

import canchamanager.grupo12.upn.dao.GestorClientesMySQL;
import canchamanager.grupo12.upn.dao.IGestorClientes;
import canchamanager.grupo12.upn.model.Cliente;

import java.util.List;

public class ClienteController {

    private IGestorClientes dao = new GestorClientesMySQL();

    // ✅ Registrar cliente con validación
    public boolean registrarCliente(Cliente cliente) {
        if (dao.buscarClientePorDni(cliente.getDni()) != null) {
            return false; // ❌ Ya existe un cliente con ese DNI
        }
        dao.registrarCliente(cliente);
        return true;
    }

    // ✅ Actualizar cliente con validación de DNI
    public boolean actualizarCliente(Cliente cliente) {
        Cliente existente = dao.buscarClientePorDni(cliente.getDni());
        if (existente != null && existente.getId() != cliente.getId()) {
            return false; // ❌ El DNI pertenece a otro cliente
        }
        dao.actualizarCliente(cliente);
        return true;
    }

    // ✅ Buscar cliente por ID
    public Cliente buscarClientePorId(int id) {
        return dao.buscarClientePorId(id);
    }

    // ✅ Buscar cliente por DNI
    public Cliente buscarClientePorDni(String dni) {
        return dao.buscarClientePorDni(dni);
    }

    // ✅ Listar todos los clientes
    public List<Cliente> listarClientes() {
        return dao.listarClientes();
    }
}
