package canchamanager.grupo12.upn;

public interface IAcceso {
	boolean iniciarSesion(String usuario, String clave);
    void cerrarSesion();
}

