package canchamanager.grupo12.upn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorCanchasDeportesMySQL implements IGestorCanchasDeportes {

    @Override
    public boolean asignarDeporte(int canchaId, int deporteId) {
        String sql = "INSERT INTO canchas_deportes (cancha_id, deporte_id) VALUES (?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, canchaId);
            ps.setInt(2, deporteId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminarAsignacion(int canchaId, int deporteId) {
        String sql = "DELETE FROM canchas_deportes WHERE cancha_id=? AND deporte_id=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, canchaId);
            ps.setInt(2, deporteId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Integer> listarDeportesPorCancha(int canchaId) {
        List<Integer> lista = new ArrayList<>();
        String sql = "SELECT deporte_id FROM canchas_deportes WHERE cancha_id=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, canchaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getInt("deporte_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
