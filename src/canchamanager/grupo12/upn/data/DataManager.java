package canchamanager.grupo12.upn.data;



import canchamanager.grupo12.upn.model.Reserva;
import canchamanager.grupo12.upn.model.Cancha;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
     return canchas;
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
     return reservas;
 }

 /**
  * Agrega una nueva reserva a la lista.
  * @param reserva La instancia de Reserva a agregar.
  */
 public static void addReserva(Reserva reserva) {
     reservas.add(reserva);
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
}
