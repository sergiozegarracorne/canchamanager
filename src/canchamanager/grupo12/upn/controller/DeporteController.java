package canchamanager.grupo12.upn.controller;

import canchamanager.grupo12.upn.dao.GestorDeportesMySQL;
import canchamanager.grupo12.upn.dao.IGestorDeportes;
import java.util.List;

public class DeporteController {

    private IGestorDeportes dao = new GestorDeportesMySQL();

    public List<String> listarNombresDeportes() {
        return dao.listarNombresDeportes();
    }
}
