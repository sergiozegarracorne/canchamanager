package canchamanager.grupo12.upn.dao;

import canchamanager.grupo12.upn.model.Cancha;
import java.util.List;

public interface IGestorCanchas {
    List<Cancha> listarTodas();
    Cancha buscarPorId(int id);
    boolean registrar(Cancha c);
    boolean actualizar(Cancha c);
    boolean eliminar(int id);
}
