package canchamanager.grupo12.upn.controller;

import java.util.List;

import canchamanager.grupo12.upn.dao.GestorCanchasDeportesMySQL;
import canchamanager.grupo12.upn.dao.GestorDeportesMySQL;
import canchamanager.grupo12.upn.dao.IGestorCanchasDeportes;

public class CanchaDeporteController {
    private IGestorCanchasDeportes dao = new GestorCanchasDeportesMySQL();

    public boolean asignarDeporte(int canchaId, int deporteId) {
        return dao.asignarDeporte(canchaId, deporteId);
    }

    public boolean eliminarAsignacion(int canchaId, int deporteId) {
        return dao.eliminarAsignacion(canchaId, deporteId);
    }

    public List<Integer> listarDeportesPorCancha(int canchaId) {
        return dao.listarDeportesPorCancha(canchaId);
    }

    // ✅ NUEVO método
    public int obtenerCanchaDeporteId(int canchaId, int deporteId) {
        return dao.obtenerCanchaDeporteId(canchaId, deporteId);
    }
    
    public int obtenerCanchaDeporteIdPorNombre(int canchaId, String deporteNombre) {
        // Buscar el ID del deporte por su nombre
        int deporteId = new GestorDeportesMySQL().obtenerIdPorNombre(deporteNombre);
        if (deporteId != -1) {
            return dao.obtenerCanchaDeporteId(canchaId, deporteId);
        }
        return -1; // ❌ No encontrado
    }
    
    public List<String> listarNombresDeportesPorCancha(int canchaId) {
        return dao.listarNombresDeportesPorCancha(canchaId);
    }
}
