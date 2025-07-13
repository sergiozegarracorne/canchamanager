package canchamanager.grupo12.upn.dao;

import java.util.List;
import canchamanager.grupo12.upn.model.Horario;

public interface IGestorHorarios {
    boolean registrar(Horario horario);
    boolean actualizar(Horario horario);
    boolean eliminar(int id);
    List<Horario> listarPorCanchaDeporte(int canchaDeporteId);
    boolean eliminarPorCanchaDeporte(int canchaDeporteId);
}
