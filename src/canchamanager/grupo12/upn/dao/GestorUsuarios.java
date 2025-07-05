package canchamanager.grupo12.upn.dao;

import java.util.List;

import canchamanager.grupo12.upn.model.Usuario;

public class GestorUsuarios implements IGestorUsuarios {   

	@Override
	public Usuario autenticar(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> listarUsuariosActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listarTodosLosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void darDeBajaUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reactivarUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cambiarPassword(int idUsuario, String nuevaPassword) {
		// TODO Auto-generated method stub
		
	}
}