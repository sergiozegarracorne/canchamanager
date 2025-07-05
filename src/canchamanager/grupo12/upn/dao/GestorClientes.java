package canchamanager.grupo12.upn.dao;

import java.util.List;

import canchamanager.grupo12.upn.model.Cliente;

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
	public void actualizarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCliente(int idCliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cliente> listarClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cambiarEstadoFrecuente(int idCliente, boolean frecuente) {
		// TODO Auto-generated method stub
		
	}
}