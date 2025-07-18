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


import org.mindrot.jbcrypt.BCrypt;

public class GestorUsuariosMySQL implements IGestorUsuarios {

	@Override
	public Usuario autenticar(String username, String password) {
		String sql = "SELECT * FROM usuarios WHERE username=? AND fecha_baja IS NULL";
		try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String hashAlmacenado = rs.getString("password");
				boolean ok = BCrypt.checkpw(password, hashAlmacenado);

				if (ok) {
					return mapearUsuario(rs);
				} else {
					System.out.println("❌ Contraseña incorrecta");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // usuario no encontrado o contraseña incorrecta
	}

	@Override
	public Usuario verificar(String username) {
		String sql = "SELECT * FROM usuarios WHERE username=? ";
		try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return mapearUsuario(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // usuario no encontrado
	}

	private Usuario mapearUsuario(ResultSet rs) throws SQLException {
		LocalDateTime fechaAlta = rs.getTimestamp("fecha_alta").toLocalDateTime();
		Timestamp baja = rs.getTimestamp("fecha_baja");
		LocalDateTime fechaBaja = (baja != null) ? baja.toLocalDateTime() : null;

		return new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("nombre_completo"),
				rs.getString("password"), rs.getString("rol"), fechaAlta, fechaBaja);
	}

	@Override
	public boolean registrarUsuario(Usuario usuario) {
		String sql = "INSERT INTO usuarios (username, nombre_completo, password, rol, fecha_alta) "
				+ "VALUES (?, ?, ?, ?, NOW())";
		try (Connection conn = ConexionDB.getConexion();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, usuario.getUsername());
			stmt.setString(2, usuario.getNombreCompleto());

			String hash = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
			stmt.setString(3, hash);

			stmt.setString(4, usuario.getRol());
			int affectedRows = stmt.executeUpdate();

			if (affectedRows > 0) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					usuario.setId(keys.getInt(1));
				}
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean actualizarUsuario(Usuario usuario) {
		String sql = "UPDATE usuarios SET username=?, nombre_completo=?, rol=? WHERE id=?";
		try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, usuario.getUsername());
			stmt.setString(2, usuario.getNombreCompleto());
			stmt.setString(3, usuario.getRol());
			stmt.setInt(4, usuario.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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

	public boolean darDeBajaUsuario(int idUsuario) {
		String sql = "UPDATE usuarios SET fecha_baja=NOW() WHERE id=?";
		try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idUsuario);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean reactivarUsuario(int idUsuario) {
		String sql = "UPDATE usuarios SET fecha_baja=NULL WHERE id=?";
		try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idUsuario);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean cambiarPassword(int idUsuario, String nuevaPassword) {

		try {
			nuevaPassword = BCrypt.hashpw(nuevaPassword, BCrypt.gensalt());

			String sql = "UPDATE usuarios SET password=? WHERE id=?";
			try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, nuevaPassword);
				stmt.setInt(2, idUsuario);
				stmt.executeUpdate();
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
