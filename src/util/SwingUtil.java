package util;

import javax.swing.*;
import java.awt.*;

public class SwingUtil {

    /**
     * Ejecuta una tarea en segundo plano con mensaje de carga.
     *
     * @param parentPanel Panel donde se mostrará el mensaje
     * @param task        Tarea pesada (se ejecuta en background)
     * @param onFinish    Acción a realizar cuando termina
     * @param loadingText Texto que se muestra mientras carga
     */
    public static void ejecutarConLoading(
            JPanel parentPanel,
            Runnable task,
            Runnable onFinish,
            String loadingText
    ) {
        // Mostrar mensaje de carga
        parentPanel.removeAll();
        JLabel loadingLabel = new JLabel(loadingText, SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        parentPanel.add(loadingLabel, BorderLayout.CENTER);
        parentPanel.revalidate();
        parentPanel.repaint();

        // Ejecutar en segundo plano
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                task.run();
                return null;
            }

            @Override
            protected void done() {
                parentPanel.removeAll();
                onFinish.run();
                parentPanel.revalidate();
                parentPanel.repaint();
            }
        };

        worker.execute();
    }
}
