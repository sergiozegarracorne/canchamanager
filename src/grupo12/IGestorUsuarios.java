package grupo12;

public interface IGestorUsuarios {
	 void crearUsuario(Usuario usuario);
	    boolean validarUsuario(String usuario, String clave);
	    void cambiarContrasena(String usuario, String nuevaClave);
}
