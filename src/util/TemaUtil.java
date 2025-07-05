package util;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

public class TemaUtil {
    private static final String CONFIG_FILE = "config.properties";
    private static boolean temaOscuro = false; // Por defecto claro

    // Aplicar el tema guardado
    public static void aplicarTemaGuardado() {
        temaOscuro = cargarTemaGuardado();
        aplicarTema(temaOscuro);
    }

    // Aplicar tema (claro u oscuro) en la app
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

    // Alternar tema en tiempo real para una ventana
    public static void cambiarTema(JFrame frame) {
        aplicarTema(!temaOscuro); // alternar
        actualizarUI(frame);
        guardarTema(temaOscuro);
    }

    // Forzar actualización de todos los componentes
    public static void actualizarUI(JFrame frame) {
        SwingUtilities.updateComponentTreeUI(frame);
    }

    // Guardar preferencia
    private static void guardarTema(boolean oscuro) {
        Properties props = new Properties();
        props.setProperty("temaOscuro", String.valueOf(oscuro));
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            props.store(fos, "Configuración de la app");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Cargar preferencia
    private static boolean cargarTemaGuardado() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);
            return Boolean.parseBoolean(props.getProperty("temaOscuro", "false"));
        } catch (IOException e) {
            return false; // Por defecto claro
        }
    }

    public static boolean isTemaOscuro() {
        return temaOscuro;
    }
}