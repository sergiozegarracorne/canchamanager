package canchamanager.grupo12.upn.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import canchamanager.grupo12.upn.controller.CanchaController;
import canchamanager.grupo12.upn.controller.CanchaDeporteController;
import canchamanager.grupo12.upn.controller.HorarioController;
import canchamanager.grupo12.upn.model.Cancha;
import canchamanager.grupo12.upn.model.Horario;
import util.TemaUtil;

public class GestionHorariosFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JComboBox<Cancha> comboCanchas;
    private JComboBox<String> comboDeportes;
    private JTable tablaHorarios;
    private DefaultTableModel modeloTabla;

    private CanchaController canchaController = new CanchaController();
    private CanchaDeporteController canchaDeporteController = new CanchaDeporteController();
    private HorarioController horarioController = new HorarioController();

    private static final String[] DIAS_SEMANA = {
        "LUNES", "MARTES", "MIERCOLES", "JUEVES",
        "VIERNES", "SABADO", "DOMINGO"
    };

    public static void main(String[] args) {
        TemaUtil.aplicarTemaGuardado(); // aplica claro u oscuro según config

        EventQueue.invokeLater(() -> {
            GestionHorariosFrame frame = new GestionHorariosFrame();
            frame.setVisible(true);
        });
    }

    public GestionHorariosFrame() {
        setTitle("Gestión de Horarios y Precios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 🟢 Panel superior: selección de cancha y deporte
        JPanel panelSeleccion = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        comboCanchas = new JComboBox<>();
        comboDeportes = new JComboBox<>();

        comboCanchas.addActionListener(e -> cargarDeportes());
        comboDeportes.addActionListener(e -> cargarHorarios());

        panelSeleccion.add(new JLabel("Cancha:"));
        panelSeleccion.add(comboCanchas);
        panelSeleccion.add(new JLabel("Deporte:"));
        panelSeleccion.add(comboDeportes);

        panelPrincipal.add(panelSeleccion, BorderLayout.NORTH);

        // 🟣 Tabla horarios
        modeloTabla = new DefaultTableModel(new String[]{
            "Día", "Hora Inicio", "Disponible", "Variación (%)"
        }, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 2) return Boolean.class; // Disponible
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // No editable el nombre del día
            }
        };
        tablaHorarios = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaHorarios);

        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // 🟡 Panel inferior: botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnGuardar = new JButton("💾 Guardar Cambios");
        JButton btnCerrar = new JButton("❌ Cerrar");

        btnGuardar.addActionListener(e -> guardarHorarios());
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCerrar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);

        // 🔥 Cargar canchas directamente
        cargarCanchas();
    }

    private void cargarCanchas() {
        List<Cancha> canchas = canchaController.listarCanchas();
        comboCanchas.removeAllItems();
        for (Cancha c : canchas) {
            comboCanchas.addItem(c);
        }
    }

    private void cargarDeportes() {
        Cancha canchaSeleccionada = (Cancha) comboCanchas.getSelectedItem();
        if (canchaSeleccionada != null) {
            List<String> deportes = canchaDeporteController.listarNombresDeportesPorCancha(canchaSeleccionada.getId());
            comboDeportes.removeAllItems();
            for (String d : deportes) {
                comboDeportes.addItem(d);
            }
        }
    }

    private void cargarHorarios() {
        modeloTabla.setRowCount(0); // Limpiar tabla

        Cancha canchaSeleccionada = (Cancha) comboCanchas.getSelectedItem();
        String deporteSeleccionado = (String) comboDeportes.getSelectedItem();

        if (canchaSeleccionada != null && deporteSeleccionado != null) {
            int canchaDeporteId = canchaDeporteController.obtenerCanchaDeporteIdPorNombre(
                canchaSeleccionada.getId(), deporteSeleccionado
            );

            List<Horario> horarios = horarioController.listarPorCanchaDeporte(canchaDeporteId);

            for (String dia : DIAS_SEMANA) {
            	List<Horario> horariosDelDia = horarios.stream()
            		    .filter(h -> h.getDiaSemana().equalsIgnoreCase(dia))
            		    .toList();

            		if (!horariosDelDia.isEmpty()) {
            		    // Agregar cada horario encontrado
            		    for (Horario h : horariosDelDia) {
            		        modeloTabla.addRow(new Object[]{
            		            dia,
            		            h.getHoraInicio(),
            		            true,
            		            h.getPorcentaje()
            		        });
            		    }
            		} else {
            		    // Agregar un registro vacío si no hay horarios en ese día
            		    modeloTabla.addRow(new Object[]{
            		        dia,
            		        "08:00",
            		        false,
            		        0.0
            		    });
            		}

            }
        }
    }

    private void guardarHorarios() {
        Cancha canchaSeleccionada = (Cancha) comboCanchas.getSelectedItem();
        String deporteSeleccionado = (String) comboDeportes.getSelectedItem();

        if (canchaSeleccionada != null && deporteSeleccionado != null) {
            int canchaDeporteId = canchaDeporteController.obtenerCanchaDeporteIdPorNombre(
                canchaSeleccionada.getId(), deporteSeleccionado
            );

            // Eliminar horarios existentes antes de registrar nuevos
            horarioController.eliminarPorCanchaDeporte(canchaDeporteId);

            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                String dia = modeloTabla.getValueAt(i, 0).toString();
                String hora = modeloTabla.getValueAt(i, 1).toString();
                boolean disponible = (boolean) modeloTabla.getValueAt(i, 2);
                double porcentaje = Double.parseDouble(modeloTabla.getValueAt(i, 3).toString());

                if (disponible) {
                    Horario h = new Horario(canchaDeporteId, dia, hora, porcentaje);
                    horarioController.registrar(h);
                }
            }

            JOptionPane.showMessageDialog(this, "✅ Horarios guardados correctamente.");
        }
    }
}
