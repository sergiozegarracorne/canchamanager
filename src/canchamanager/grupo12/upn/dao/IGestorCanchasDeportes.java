package canchamanager.grupo12.upn.dao;

import java.util.List;



public interface IGestorCanchasDeportes {
    boolean asignarDeporte(int canchaId, int deporteId);
    boolean eliminarAsignacion(int canchaId, int deporteId);
    java.util.List<Integer> listarDeportesPorCancha(int canchaId);


    int obtenerCanchaDeporteId(int canchaId, int deporteId);
    
    List<String> listarNombresDeportesPorCancha(int canchaId);
}
