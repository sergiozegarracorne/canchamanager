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
	public boolean registrarUsuario(Usuario usuario) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean actualizarUsuario(Usuario usuario) {
		return false;
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
	public boolean darDeBajaUsuario(int idUsuario) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean reactivarUsuario(int idUsuario) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean cambiarPassword(int idUsuario, String nuevaPassword) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario verificar(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}