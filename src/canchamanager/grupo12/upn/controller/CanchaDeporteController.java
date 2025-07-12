package canchamanager.grupo12.upn.controller;

import canchamanager.grupo12.upn.dao.GestorCanchasDeportesMySQL;
import canchamanager.grupo12.upn.dao.IGestorCanchasDeportes;

import java.util.List;

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
}
