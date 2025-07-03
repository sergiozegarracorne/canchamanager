package canchamanager.grupo12.upn;

import canchamanager.grupo12.upn.model.Reserva;

public interface IGeneradorReportes {
	void generarReporteSemanal();
    void exportarReservaPDF(Reserva reserva);
}
