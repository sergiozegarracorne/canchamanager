package canchamanager.grupo12.upn.data;



import canchamanager.grupo12.upn.model.Reserva;
import canchamanager.grupo12.upn.model.Cancha;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
* Clase para gestionar los datos de la aplicación (canchas y reservas).
* Actualmente simula una base de datos en memoria.
* Esta clase podría ser extendida para interactuar con una base de datos real (ej. SQLite, MySQL).
*/
public class DataManager {
 // Listas estáticas para almacenar canchas y reservas en memoria
 private static List<Cancha> canchas = new ArrayList<>();
 private static List<Reserva> reservas = new ArrayList<>();

 // Bloque estático para inicializar con algunos datos de prueba
 static {
     // Añadir canchas de prueba
     canchas.add(new Cancha("Cancha Principal A", "Av. Siempre Viva 123", "08:00-22:00"));
     canchas.add(new Cancha("Cancha Secundaria B", "Calle Falsa 456", "09:00-21:00"));

     // Añadir reservas de prueba para el día actual
     reservas.add(new Reserva("Juan Pérez", canchas.get(0), LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(11, 0)));
     reservas.add(new Reserva("María García", canchas.get(1), LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(15, 0)));
 }

 /**
  * Obtiene la lista de todas las canchas registradas.
  * @return Una lista de objetos Cancha.
  */
 public static List<Cancha> getCanchas() {
     return Collections.unmodifiableList(canchas);
 }

 /**
  * Agrega una nueva cancha a la lista.
  * @param cancha La instancia de Cancha a agregar.
  */
 public static void addCancha(Cancha cancha) {
     canchas.add(cancha);
 }

 /**
  * Obtiene la lista de todas las reservas registradas.
  * @return Una lista de objetos Reserva.
  */
 public static List<Reserva> getReservas() {
     return Collections.unmodifiableList(reservas);
 }

    /**
     * Agrega una nueva reserva si no existe un cruce de horarios y la cancha
     * está disponible en ese intervalo (RF05, RF06, RF18).
     *
     * @param reserva La instancia de Reserva a agregar.
     * @return {@code true} si la reserva fue agregada correctamente,
     *         {@code false} si hubo un conflicto de horarios o fuera del
     *         horario permitido.
     */
    public static boolean addReserva(Reserva reserva) {
        // Verificar que la reserva esté dentro del horario permitido de la cancha
        if (!dentroDeHorario(reserva.getCancha(), reserva.getHoraInicio(), reserva.getHoraFin())) {
            return false;
        }

        // Comprobar que no exista cruce de horarios con otras reservas
        if (hayCruceDeHorario(reserva.getCancha(), reserva.getFecha(),
                reserva.getHoraInicio(), reserva.getHoraFin(), null)) {
            return false;
        }

        reservas.add(reserva);
        return true;
    }

 /**
  * Obtiene todas las reservas para una fecha específica.
  * @param fecha La fecha para la cual se desean obtener las reservas.
  * @return Una lista de objetos Reserva correspondientes a la fecha dada.
  */
    public static List<Reserva> getReservasPorDia(LocalDate fecha) {
        List<Reserva> reservasDelDia = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getFecha().isEqual(fecha)) {
                reservasDelDia.add(r);
            }
        }
        return reservasDelDia;
    }

    /**
     * Cancela una reserva existente. (RF08)
     * @param reserva La reserva a eliminar.
     */
    public static void cancelarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    /**
     * Marca una reserva como pagada. (RF11)
     * @param reserva Reserva a actualizar.
     */
    public static void marcarReservaPagada(Reserva reserva) {
        if (reservas.contains(reserva)) {
            reserva.setPagada(true);
        }
    }

    /**
     * Edita una reserva existente cambiando cancha u horario si está disponible.
     * (RF07)
     *
     * @param reserva        Reserva a modificar.
     * @param nuevaCancha    Nueva cancha para la reserva.
     * @param nuevaFecha     Nueva fecha.
     * @param nuevaHoraInicio Nueva hora de inicio.
     * @param nuevaHoraFin   Nueva hora de fin.
     * @return {@code true} si la reserva se actualizó correctamente,
     *         {@code false} si hubo conflicto de horarios o el horario está fuera
     *         del rango permitido.
     */
    public static boolean editarReserva(Reserva reserva, Cancha nuevaCancha,
            LocalDate nuevaFecha, LocalTime nuevaHoraInicio, LocalTime nuevaHoraFin) {

        if (!reservas.contains(reserva)) {
            return false;
        }

        // Validar horario permitido
        if (!dentroDeHorario(nuevaCancha, nuevaHoraInicio, nuevaHoraFin)) {
            return false;
        }

        // Verificar cruce excluyendo la propia reserva
        if (hayCruceDeHorario(nuevaCancha, nuevaFecha, nuevaHoraInicio, nuevaHoraFin, reserva)) {
            return false;
        }

        reserva.setCancha(nuevaCancha);
        reserva.setFecha(nuevaFecha);
        reserva.setHoraInicio(nuevaHoraInicio);
        reserva.setHoraFin(nuevaHoraFin);

        return true;
    }

 /**
  * Verifica si hay un cruce de horarios para una cancha y fecha dadas.
  * (RF06, RF19 - Recibir alertas si hay cruce de horarios en las reservas)
  * @param cancha La cancha para la cual se verifica el cruce.
  * @param fecha La fecha de la posible reserva.
  * @param nuevaHoraInicio La hora de inicio de la nueva reserva.
  * @param nuevaHoraFin La hora de fin de la nueva reserva.
  * @param reservaAExcluir Una reserva existente que debe ser excluida de la verificación (útil para edición).
  * Si es una nueva reserva, este parámetro debe ser `null`.
  * @return `true` si hay un cruce de horario, `false` en caso contrario.
  */
    public static boolean hayCruceDeHorario(Cancha cancha, LocalDate fecha, LocalTime nuevaHoraInicio, LocalTime nuevaHoraFin, Reserva reservaAExcluir) {
        for (Reserva r : reservas) {
            // Excluir la propia reserva si estamos editando (para que no se detecte un cruce consigo misma)
            if (r == reservaAExcluir) {
                continue;
         }
         // Comprobar si la reserva actual es para la misma cancha y la misma fecha
         if (r.getCancha().equals(cancha) && r.getFecha().isEqual(fecha)) {
             // Comprobar si hay superposición de horarios usando la lógica:
             // [inicio1, fin1) y [inicio2, fin2) se superponen si:
             // (inicio1 < fin2) AND (fin1 > inicio2)
             if (nuevaHoraInicio.isBefore(r.getHoraFin()) && nuevaHoraFin.isAfter(r.getHoraInicio())) {
                 return true; // Hay cruce
             }
        }
    }
    return false; // No hay cruce
}

    // Verifica que un intervalo de horas esté dentro del horario disponible de la cancha
    private static boolean dentroDeHorario(Cancha cancha, LocalTime inicio, LocalTime fin) {
        String horario = cancha.getHorarioDisponible();
        String[] partes = horario.split("-");
        if (partes.length != 2) {
            return false; // formato incorrecto
        }
        LocalTime inicioCancha = LocalTime.parse(partes[0]);
        LocalTime finCancha = LocalTime.parse(partes[1]);
        return !inicio.isBefore(inicioCancha) && !fin.isAfter(finCancha);
    }
}
