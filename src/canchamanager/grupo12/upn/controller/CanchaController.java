package canchamanager.grupo12.upn.controller;

import java.util.List;

import canchamanager.grupo12.upn.dao.GestorCanchasMySQL;
import canchamanager.grupo12.upn.model.Cancha;
import canchamanager.grupo12.upn.dao.IGestorCanchas;

public class CanchaController {
    private IGestorCanchas dao = new GestorCanchasMySQL();

    public List<Cancha> listarCanchas() {
        return dao.listarTodas();
    }

    public Cancha buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public boolean registrar(Cancha c) {
        return dao.registrar(c);
    }

    public boolean actualizar(Cancha c) {
        return dao.actualizar(c);
    }

    public boolean eliminar(int id) {
        return dao.eliminar(id);
    }
}
