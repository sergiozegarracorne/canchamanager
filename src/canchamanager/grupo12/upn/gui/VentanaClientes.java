package canchamanager.grupo12.upn.gui;

import javax.swing.*;
import java.awt.*;

public class VentanaClientes extends JFrame {

    public VentanaClientes() {
        setTitle("Gestión de Clientes");
        setSize(700, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Listado de Clientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(titulo, BorderLayout.NORTH);

        // 
        String[] columnas = {"CODIGO", "Nombre", "Teléfono", "Frecuente"};
        String[][] datos = {
        		 {"N003990380", "SERGIO ZEGARRA CORNE", "987654321", "Si"},
            {"12345678", "Juan Pérez", "987654321", "No"},
            {"87654321", "Ana Torres", "945678321", "No"}
        };

        JTable tabla = new JTable(datos, columnas);
        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);

        add(panel);
    }
}
