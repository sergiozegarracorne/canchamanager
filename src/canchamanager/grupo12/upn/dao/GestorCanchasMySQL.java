package canchamanager.grupo12.upn.dao;

import canchamanager.grupo12.upn.model.Cancha;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorCanchasMySQL implements IGestorCanchas {

    @Override
    public void registrarCancha(Cancha cancha) {
        String sql = "INSERT INTO canchas (nombre, direccion, gps,  deporte, estado, imagen1, imagen2, imagen3, imagen4) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cancha.getNombre());
            stmt.setString(2, cancha.getDireccion());
            stmt.setString(3, cancha.getGps());
            stmt.setString(4, cancha.getDeporte());
            stmt.setString(5, cancha.getEstado());
            stmt.setBytes(6, cancha.getImagen1());
            stmt.setBytes(7, cancha.getImagen2());
            stmt.setBytes(8, cancha.getImagen3());
            stmt.setBytes(9, cancha.getImagen4());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarCancha(Cancha cancha) {
        String sql = "UPDATE canchas SET nombre=?, direccion=?, gps=?, deporte=?, estado=?, imagen1=?, imagen2=?, imagen3=?, imagen4=? WHERE id=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cancha.getNombre());
            stmt.setString(2, cancha.getDireccion());
            stmt.setString(3, cancha.getGps());
            stmt.setString(4, cancha.getDeporte());
            stmt.setString(5, cancha.getEstado());
            stmt.setBytes(6, cancha.getImagen1());
            stmt.setBytes(7, cancha.getImagen2());
            stmt.setBytes(8, cancha.getImagen3());
            stmt.setBytes(9, cancha.getImagen4());
            stmt.setInt(10, cancha.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarCancha(int id) {
        String sql = "DELETE FROM canchas WHERE id=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cancha buscarCanchaPorId(int id) {
        String sql = "SELECT * FROM canchas WHERE id=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cancha(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("gps"),
                        rs.getString("deporte"),
                        rs.getString("estado"),                        
                        rs.getBytes("imagen1"),
                        rs.getBytes("imagen2"),
                        rs.getBytes("imagen3"),
                        rs.getBytes("imagen4"),
                        rs.getTimestamp("fecha_registro").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cancha> listarCanchas() {
        List<Cancha> lista = new ArrayList<>();
        String sql = "SELECT * FROM canchas";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Cancha(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("gps"),
                        rs.getString("deporte"),
                        rs.getString("estado"),
                        rs.getBytes("imagen1"),
                        rs.getBytes("imagen2"),
                        rs.getBytes("imagen3"),
                        rs.getBytes("imagen4"),
                        rs.getTimestamp("fecha_registro").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
