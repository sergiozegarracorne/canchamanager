package canchamanager.grupo12.upn;

public class Usuario {
	 private String usuario;
	    private String clave;
	    private String rol;

	    public boolean validarAcceso(String claveIngresada) {
	        return this.clave.equals(claveIngresada);
	    }
}
