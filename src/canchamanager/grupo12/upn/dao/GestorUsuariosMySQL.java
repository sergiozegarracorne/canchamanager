package canchamanager.grupo12.upn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import canchamanager.grupo12.upn.model.Usuario;

public class GestorUsuariosMySQL implements IGestorUsuarios {

	@Override
	public Usuario autenticar(String username, String password) {
		String sql = "SELECT * FROM usuarios WHERE username=? AND password=? AND fecha_baja IS NULL";
		try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return mapearUsuario(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // usuario no encontrado o desactivado
	}

	private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        LocalDateTime fechaAlta = rs.getTimestamp("fecha_alta").toLocalDateTime();
        Timestamp baja = rs.getTimestamp("fecha_baja");
        LocalDateTime fechaBaja = (baja != null) ? baja.toLocalDateTime() : null;

        return new Usuario(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("nombre_completo"),
                rs.getString("password"),
                rs.getString("rol"),
                fechaAlta,
                fechaBaja
        );
    }

	@Override
	public void registrarUsuario(Usuario usuario) {
		String sql = "INSERT INTO usuarios (username, nombre_completo, password, rol, fecha_alta) "
				+ "VALUES (?, ?, ?, ?, NOW())";
		try (Connection conn = ConexionDB.getConexion();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, usuario.getUsername());
			stmt.setString(2, usuario.getNombreCompleto());
			stmt.setString(3, usuario.getPassword());
			stmt.setString(4, usuario.getRol());
			int affectedRows = stmt.executeUpdate();

			if (affectedRows > 0) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					usuario.setId(keys.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		String sql = "UPDATE usuarios SET username=?, nombre_completo=?, rol=? WHERE id=?";
		try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, usuario.getUsername());
			stmt.setString(2, usuario.getNombreCompleto());
			stmt.setString(3, usuario.getRol());
			stmt.setInt(4, usuario.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	 @Override
	    public List<Usuario> listarUsuariosActivos() {
	        List<Usuario> lista = new ArrayList<>();
	        String sql = "SELECT * FROM usuarios WHERE fecha_baja IS NULL";
	        try (Connection conn = ConexionDB.getConexion();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                lista.add(mapearUsuario(rs));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return lista;
	    }

	 @Override
	    public List<Usuario> listarTodosLosUsuarios() {
	        List<Usuario> lista = new ArrayList<>();
	        String sql = "SELECT * FROM usuarios";
	        try (Connection conn = ConexionDB.getConexion();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                lista.add(mapearUsuario(rs));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return lista;
	    }

	public void darDeBajaUsuario(int idUsuario) {
		String sql = "UPDATE usuarios SET fecha_baja=NOW() WHERE id=?";
		try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idUsuario);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
    public void reactivarUsuario(int idUsuario) {
        String sql = "UPDATE usuarios SET fecha_baja=NULL WHERE id=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void cambiarPassword(int idUsuario, String nuevaPassword) {
        String sql = "UPDATE usuarios SET password=? WHERE id=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuevaPassword);
            stmt.setInt(2, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
