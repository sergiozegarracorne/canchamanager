package canchamanager.grupo12.upn.controller;

import java.util.List;
import canchamanager.grupo12.upn.dao.GestorHorariosMySQL;
import canchamanager.grupo12.upn.dao.IGestorHorarios;
import canchamanager.grupo12.upn.model.Horario;

public class HorarioController {
    private IGestorHorarios dao = new GestorHorariosMySQL();

    public boolean registrar(Horario horario) {
        return dao.registrar(horario);
    }

    public boolean actualizar(Horario horario) {
        return dao.actualizar(horario);
    }

    public boolean eliminar(int id) {
        return dao.eliminar(id);
    }

    public List<Horario> listarPorCanchaDeporte(int canchaDeporteId) {
        return dao.listarPorCanchaDeporte(canchaDeporteId);
    }

    public boolean eliminarPorCanchaDeporte(int canchaDeporteId) {
        return dao.eliminarPorCanchaDeporte(canchaDeporteId);
    }
}
