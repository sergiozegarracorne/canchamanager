package util;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class TemaUtil {

    private static boolean temaOscuro = false; // Por defecto claro

    // ✅ Aplicar el tema guardado
    public static void aplicarTemaGuardado() {
        temaOscuro = cargarTemaGuardado();
        aplicarTema(temaOscuro);
    }

    // ✅ Aplicar tema (claro u oscuro) en la app
    public static void aplicarTema(boolean oscuro) {
        try {
            if (oscuro) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } else {
                UIManager.setLookAndFeel(new FlatLightLaf());
            }
            temaOscuro = oscuro;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ✅ Alternar tema en tiempo real para una ventana
    public static void cambiarTema(JFrame frame) {
        aplicarTema(!temaOscuro); // Alternar
        actualizarUI(frame);
        guardarTema(temaOscuro);  // Guardar preferencia
    }

    // ✅ Forzar actualización de todos los componentes
    public static void actualizarUI(JFrame frame) {
        SwingUtilities.updateComponentTreeUI(frame);
    }

    // ✅ Guardar preferencia usando ConfigUtil
    private static void guardarTema(boolean oscuro) {
        ConfigUtil.set("temaOscuro", String.valueOf(oscuro));
    }

    // ✅ Cargar preferencia usando ConfigUtil
    private static boolean cargarTemaGuardado() {
    	String themaActual = ConfigUtil.get("temaOscuro");
        return Boolean.parseBoolean(themaActual);
    }

    // ✅ Obtener estado actual del tema
    public static boolean isTemaOscuro() {
        return temaOscuro;
    }
}
