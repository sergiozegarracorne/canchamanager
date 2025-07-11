package canchamanager.grupo12.upn.dao;

import canchamanager.grupo12.upn.model.Cancha;
import java.util.List;

public interface IGestorCanchas {
    void registrarCancha(Cancha cancha);
    void actualizarCancha(Cancha cancha);
    void eliminarCancha(int id);
    Cancha buscarCanchaPorId(int id);
    List<Cancha> listarCanchas();
}
