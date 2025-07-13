package canchamanager.grupo12.upn.dao;

import canchamanager.grupo12.upn.model.Horario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorHorariosMySQL implements IGestorHorarios {

    @Override
    public boolean registrar(Horario horario) {
        String sql = "INSERT INTO horarios_canchas_deportes (cancha_deporte_id, dia_semana, hora_inicio, porcentaje) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, horario.getCanchaDeporteId());
            ps.setString(2, horario.getDiaSemana());
            ps.setString(3, horario.getHoraInicio());
            ps.setDouble(4, horario.getPorcentaje());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizar(Horario horario) {
        String sql = "UPDATE horarios_canchas_deportes SET porcentaje=? WHERE id=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, horario.getPorcentaje());
            ps.setInt(2, horario.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM horarios_canchas_deportes WHERE id=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Horario> listarPorCanchaDeporte(int canchaDeporteId) {
        List<Horario> lista = new ArrayList<>();
        String sql = "SELECT * FROM horarios_canchas_deportes WHERE cancha_deporte_id=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, canchaDeporteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Horario h = new Horario(
                    rs.getInt("id"),
                    rs.getInt("cancha_deporte_id"),
                    rs.getString("dia_semana"),
                    rs.getString("hora_inicio"),
                    rs.getDouble("porcentaje")
                );
                lista.add(h);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean eliminarPorCanchaDeporte(int canchaDeporteId) {
        String sql = "DELETE FROM horarios_canchas_deportes WHERE cancha_deporte_id=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, canchaDeporteId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
