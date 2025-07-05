package canchamanager.grupo12.upn.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import canchamanager.grupo12.upn.model.Cliente;

public class GestorClientesMySQL implements IGestorClientes {

	// ✅ Crear cliente
	@Override
	public void registrarCliente(Cliente cliente) {
		String sql = "INSERT INTO clientes (nombre, telefono, email, dni, frecuente) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = ConexionDB.getConexion();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getTelefono());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getDni());
			stmt.setBoolean(5, cliente.isFrecuente());
			int affectedRows = stmt.executeUpdate();

			if (affectedRows > 0) {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					cliente.setId(generatedKeys.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ✅ Buscar cliente por DNI
	@Override
	public Cliente buscarClientePorDni(String dni) {
		String sql = "SELECT * FROM clientes WHERE dni = ?";
		try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, dni);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return mapearCliente(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ✅ Actualizar cliente
	@Override
	public void actualizarCliente(Cliente cliente) {
		String sql = "UPDATE clientes SET nombre=?, telefono=?, email=?, dni=?, frecuente=? WHERE id=?";
		try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getTelefono());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getDni());
			stmt.setBoolean(5, cliente.isFrecuente());
			stmt.setInt(6, cliente.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ✅ Eliminar cliente
	@Override
	public void eliminarCliente(int idCliente) {
		String sql = "DELETE FROM clientes WHERE id=?";
		try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idCliente);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ✅ Listar todos los clientes
	@Override
	public List<Cliente> listarClientes() {
		List<Cliente> lista = new ArrayList<>();
		String sql = "SELECT * FROM clientes";
		try (Connection conn = ConexionDB.getConexion();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				lista.add(mapearCliente(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	// ✅ Cambiar estado de cliente frecuente
	@Override
	public void cambiarEstadoFrecuente(int idCliente, boolean frecuente) {
		String sql = "UPDATE clientes SET frecuente=? WHERE id=?";
		try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setBoolean(1, frecuente);
			stmt.setInt(2, idCliente);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Cliente buscarClientePorId(int id) {
		String sql = "SELECT * FROM clientes WHERE id = ?";
		try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return mapearCliente(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Cliente mapearCliente(ResultSet rs) throws SQLException {
		Timestamp timestamp = rs.getTimestamp("fecha_registro");
		LocalDateTime fechaRegistro = (timestamp != null) ? timestamp.toLocalDateTime() : null;

		return new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("telefono"), rs.getString("email"),
				rs.getString("dni"), rs.getBoolean("frecuente"), fechaRegistro);
	}

}
