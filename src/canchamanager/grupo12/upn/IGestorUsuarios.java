package canchamanager.grupo12.upn;

public interface IGestorUsuarios {
	 void crearUsuario(Usuario usuario);
	    boolean validarUsuario(String usuario, String clave);
	    void cambiarContrasena(String usuario, String nuevaClave);
}
