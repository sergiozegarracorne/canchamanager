package canchamanager.grupo12.upn.dao;

import canchamanager.grupo12.upn.model.Cancha;
import canchamanager.grupo12.upn.dao.ConexionDB;  // tu clase existente

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorCanchasMySQL implements IGestorCanchas {

    @Override
    public List<Cancha> listarTodas() {
        List<Cancha> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, direccion, gps, estado FROM canchas";
        try (Connection conn = ConexionDB.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Cancha(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("gps"),
                    rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Cancha buscarPorId(int id) {
        String sql = "SELECT id, nombre, direccion, gps, estado FROM canchas WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cancha(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("gps"),
                    rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean registrar(Cancha c) {
        String sql = "INSERT INTO canchas (nombre, direccion, gps, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDireccion());
            ps.setString(3, c.getGps());
            ps.setString(4, c.getEstado());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) c.setId(keys.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizar(Cancha c) {
        String sql = "UPDATE canchas SET nombre=?, direccion=?, gps=?, estado=? WHERE id=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDireccion());
            ps.setString(3, c.getGps());
            ps.setString(4, c.getEstado());
            ps.setInt(5, c.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM canchas WHERE id=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
