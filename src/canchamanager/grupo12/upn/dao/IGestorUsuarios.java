package canchamanager.grupo12.upn.dao;

import java.util.List;

import canchamanager.grupo12.upn.model.Usuario;

public interface IGestorUsuarios {

    /**
     * Autentica un usuario usando username y password.
     * Solo permite el acceso si el usuario está activo (fecha_baja = NULL).
     *
     * @param username Nombre de usuario
     * @param password Contraseña (plana o encriptada según implementación)
     * @return Usuario autenticado o null si no existe o está inactivo
     */
    Usuario autenticar(String username, String password);

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param usuario Objeto Usuario a registrar
     */
    void registrarUsuario(Usuario usuario);

    /**
     * Actualiza los datos de un usuario existente.
     * No modifica la contraseña si no se especifica una nueva.
     *
     * @param usuario Objeto Usuario con datos actualizados
     */
    void actualizarUsuario(Usuario usuario);

    /**
     * Lista todos los usuarios activos.
     *
     * @return Lista de usuarios activos
     */
    List<Usuario> listarUsuariosActivos();

    /**
     * Lista todos los usuarios, activos e inactivos.
     *
     * @return Lista completa de usuarios
     */
    List<Usuario> listarTodosLosUsuarios();

    /**
     * Desactiva (da de baja) un usuario estableciendo fecha_baja.
     *
     * @param idUsuario ID del usuario a desactivar
     */
    void darDeBajaUsuario(int idUsuario);

    /**
     * Reactiva un usuario (elimina fecha_baja).
     *
     * @param idUsuario ID del usuario a reactivar
     */
    void reactivarUsuario(int idUsuario);

    /**
     * Cambia la contraseña de un usuario.
     *
     * @param idUsuario ID del usuario
     * @param nuevaPassword Nueva contraseña
     */
    void cambiarPassword(int idUsuario, String nuevaPassword);
}
