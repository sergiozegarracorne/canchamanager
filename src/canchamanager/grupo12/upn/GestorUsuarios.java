package canchamanager.grupo12.upn;

public class GestorUsuarios implements IGestorUsuarios {
    @Override
    public void crearUsuario(Usuario usuario) {
        // Crear nuevo usuario
    }

    @Override
    public boolean validarUsuario(String usuario, String clave) {
        // Validar credenciales de usuario
        return false;
    }

    @Override
    public void cambiarContrasena(String usuario, String nuevaClave) {
        // Cambiar clave del usuario
    }
}