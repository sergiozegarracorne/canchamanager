package canchamanager.grupo12.upn.gui;

import javax.swing.*;
import java.awt.*;

import java.util.List;


import canchamanager.grupo12.upn.controller.CanchaDeporteController;
import canchamanager.grupo12.upn.controller.DeporteController;
import canchamanager.grupo12.upn.controller.CanchaController;
import canchamanager.grupo12.upn.model.Cancha;

public class GestionCanchasDeportesFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JComboBox<Cancha> comboCanchas;
    private JPanel panelDeportes;
    private JButton btnGuardar;
    private JCheckBox[] checkDeportes;
    private List<String> deportes;

    private CanchaDeporteController controller = new CanchaDeporteController();
    private CanchaController canchaController = new CanchaController();
    private DeporteController deporteController = new DeporteController();
    


    public GestionCanchasDeportesFrame() {
        setTitle("Asignación de Deportes a Canchas");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.DARK_GRAY);
        
        

        // 🔵 ComboBox para elegir cancha
        JPanel panelSuperior = new JPanel(new FlowLayout());
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Selecciona una cancha"));
        comboCanchas = new JComboBox<>();
        cargarCanchas();
        comboCanchas.addActionListener(e -> cargarDeportesAsignados());
        panelSuperior.add(comboCanchas);

        // 🟢 Panel de checkboxes para deportes
        deportes = deporteController.listarNombresDeportes();
        panelDeportes = new JPanel();
        panelDeportes.setLayout(new GridLayout(deportes.size(), 1, 5, 5));
        panelDeportes.setBorder(BorderFactory.createTitledBorder("Deportes disponibles"));
        checkDeportes = new JCheckBox[deportes.size()];
        
        for (int i = 0; i < deportes.size(); i++) {
            checkDeportes[i] = new JCheckBox(deportes.get(i));
            panelDeportes.add(checkDeportes[i]);
        }

        // 🟣 Botón guardar
        JPanel panelInferior = new JPanel(new FlowLayout());
        btnGuardar = new JButton("💾 GUARDAR");
        btnGuardar.addActionListener(e -> guardarAsignacion());
        panelInferior.add(btnGuardar);

        // Añadir a ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(panelDeportes, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        cargarDeportesAsignados();
    }

    private void cargarCanchas() {
        comboCanchas.removeAllItems();
        List<Cancha> canchas = canchaController.listarCanchas();
        for (Cancha c : canchas) {
            comboCanchas.addItem(c);
        }
    }

    private void cargarDeportesAsignados() {
        if (comboCanchas.getSelectedItem() == null) return;

        Cancha cancha = (Cancha) comboCanchas.getSelectedItem();
        List<Integer> deportesAsignados = controller.listarDeportesPorCancha(cancha.getId());

        for (int i = 0; i < checkDeportes.length; i++) {
            // Si el índice +1 está en la lista de IDs → está marcado
            checkDeportes[i].setSelected(deportesAsignados.contains(i + 1));
        }
    }

    private void guardarAsignacion() {
        Cancha cancha = (Cancha) comboCanchas.getSelectedItem();
        if (cancha == null) return;

        // 🔄 Primero eliminamos todas las asignaciones previas
        for (int i = 0; i < deportes.size(); i++) {
            controller.eliminarAsignacion(cancha.getId(), i + 1);
        }

        // ✅ Luego registramos los deportes seleccionados
        for (int i = 0; i < checkDeportes.length; i++) {
            if (checkDeportes[i].isSelected()) {
                controller.asignarDeporte(cancha.getId(), i + 1);
            }
        }

        JOptionPane.showMessageDialog(this, "✅ Deportes actualizados correctamente.");
    }
}
