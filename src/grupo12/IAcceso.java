package grupo12;

public interface IAcceso {
	boolean iniciarSesion(String usuario, String clave);
    void cerrarSesion();
}

