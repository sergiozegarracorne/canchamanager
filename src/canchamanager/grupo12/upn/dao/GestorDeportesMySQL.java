package canchamanager.grupo12.upn.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorDeportesMySQL implements IGestorDeportes {

    @Override
    public List<String> listarNombresDeportes() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre FROM deportes";
        try (Connection conn = ConexionDB.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public int obtenerIdPorNombre(String nombre) {
        String sql = "SELECT id FROM deportes WHERE nombre=?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // ❌ No encontrado
    }
    
    
}
