package canchamanager.grupo12.upn.controller;

import canchamanager.grupo12.upn.dao.GestorCanchasMySQL;
import canchamanager.grupo12.upn.dao.IGestorCanchas;
import canchamanager.grupo12.upn.model.Cancha;
import java.util.List;

public class CanchaController {

    private IGestorCanchas dao = new GestorCanchasMySQL();

    public void registrarCancha(Cancha cancha) {
        dao.registrarCancha(cancha);
    }

    public void actualizarCancha(Cancha cancha) {
        dao.actualizarCancha(cancha);
    }

    public void eliminarCancha(int id) {
        dao.eliminarCancha(id);
    }

    public Cancha buscarCanchaPorId(int id) {
        return dao.buscarCanchaPorId(id);
    }

    public List<Cancha> listarCanchas() {
        return dao.listarCanchas();
    }
}
