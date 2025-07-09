package canchamanager.grupo12.controller;

import java.util.List;

import canchamanager.grupo12.upn.dao.GestorUsuariosMySQL;
import canchamanager.grupo12.upn.model.Usuario;
import canchamanager.grupo12.upn.dao.IGestorUsuarios;

public class UsuarioController {
    private IGestorUsuarios dao = new GestorUsuariosMySQL();
    
    public Usuario verificar(String userName) {
    	return dao.verificar(userName);		
    	
    }

    public boolean registrarUsuario(Usuario u) {
        if (dao.verificar(u.getUsername()) != null) {
            return false; // Ya existe
        }
        dao.registrarUsuario(u);
        return true;
    }

    public boolean actualizarUsuario(Usuario u) {
        Usuario existente = dao.verificar(u.getUsername());
        if (existente != null && existente.getId() != u.getId()) {
            return false; // Username ya en uso
        }
        dao.actualizarUsuario(u);
        return true;
    }

    public List<Usuario> listarUsuarios() {
        return dao.listarTodosLosUsuarios();
    }

    public void darDeBaja(int id) {
        dao.darDeBajaUsuario(id);
    }

    public void reactivar(int id) {
        dao.reactivarUsuario(id);
    }
}
