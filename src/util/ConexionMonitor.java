package util;

import javax.swing.*;

import canchamanager.grupo12.upn.dao.ConexionDB;

import java.awt.*;
import java.sql.Connection;

public class ConexionMonitor {
    private static Timer timer;           // 🕒 Timer compartido
    private static boolean estadoPrevio;  // 🟢 Estado anterior

    // 🔥 Componente semáforo reutilizable
    public static JLabel crearSemaforo() {
        JLabel semaforo = new JLabel("●");
        semaforo.setFont(new Font("Arial", Font.BOLD, 30));
        semaforo.setForeground(Color.GRAY); // Color inicial
        actualizarSemaforo(semaforo);       // Primera comprobación

        if (timer == null) {
            timer = new Timer(10000, e -> actualizarSemaforo(semaforo));
            timer.start();
        }

        return semaforo;
    }

    // 🔄 Actualiza el color y lanza alertas si el estado cambia
    private static void actualizarSemaforo(JLabel semaforo) {
        boolean conectado = hayConexion();

        if (conectado) {
            semaforo.setForeground(Color.GREEN); // 🟢 Conexión OK
        } else {
            semaforo.setForeground(Color.RED);   // 🔴 Sin conexión
        }

        // ⚠️ Detectar cambio de estado
        if (conectado != estadoPrevio) {
            if (conectado) {
            	 semaforo.setForeground(Color.GREEN); // 🟢 Conexión OK
            } else {
            	 semaforo.setForeground(Color.RED);   // 🔴 Sin conexión
            }
        }

        // 📝 Actualizar estado previo
        estadoPrevio = conectado;
    }

    // ✅ Método reutilizable para verificar conexión
    public static boolean hayConexion() {
        try (Connection conn = ConexionDB.getConexion()) {
            return conn != null && !conn.isClosed();
        } catch (Exception e) {
            return false;
        }
    }
}