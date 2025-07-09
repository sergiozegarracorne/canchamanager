package canchamanager.grupo12.upn.controller;

import canchamanager.grupo12.upn.dao.GestorUsuariosMySQL;
import canchamanager.grupo12.upn.dao.IGestorUsuarios;
import canchamanager.grupo12.upn.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController {

    private IGestorUsuarios dao = new GestorUsuariosMySQL();

    /**
     * Autentica un usuario con username y password
     * @param username nombre de usuario
     * @param password contraseña en texto plano
     * @return Usuario si las credenciales son correctas, null si fallan
     */
    public Usuario autenticar(String username, String password) {
        Usuario user = dao.verificar(username); // Verifica si existe
        if (user != null && user.isActivo()) {
            // Validar el hash de la contraseña
            if (BCrypt.checkpw(password, user.getPassword())) {
                return user; // usuario autenticado
            }
        }
        return null; // datos incorrectas o usuario inactivo
    }
}
