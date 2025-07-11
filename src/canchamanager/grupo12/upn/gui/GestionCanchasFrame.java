package canchamanager.grupo12.upn.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.io.File;
import java.util.List;

import canchamanager.grupo12.upn.controller.CanchaController;
import canchamanager.grupo12.upn.model.Cancha;

public class GestionCanchasFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtId,  txtGps;
    private JTextArea txtNombre, txtDireccion;
    private JComboBox<String> comboEstado, comboDeporte;
    private JLabel[] lblImagenes = new JLabel[4];
    private byte[][] imagenes = new byte[4][];
    private JTable tablaCanchas;
    private DefaultTableModel modeloTabla;

    private CanchaController canchaController = new CanchaController();

    public GestionCanchasFrame() {
        setTitle("Gestión de Canchas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // 🟢 Tab 1: Formulario
        JPanel panelFormulario = crearPanelFormulario();
        tabbedPane.addTab("Registrar / Editar Cancha", panelFormulario);

        // 🟣 Tab 2: Lista de canchas
        JPanel panelLista = crearPanelLista();
        tabbedPane.addTab("Lista de Canchas", panelLista);

        add(tabbedPane);
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 🟢 Panel central con dos columnas
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 10, 10));

        // 🔴 Columna izquierda: campos
        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));
        panelCampos.setBorder(BorderFactory.createTitledBorder("Datos de la cancha"));

        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setPreferredSize(new Dimension(280, 25));

     // ✅ Multilinea: nombre
        txtNombre = new JTextArea(2, 20);
        txtNombre.setLineWrap(true);
        txtNombre.setWrapStyleWord(true);
        txtNombre.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // ✅ Multilinea: dirección
        txtDireccion = new JTextArea(3, 20);
        txtDireccion.setLineWrap(true);
        txtDireccion.setWrapStyleWord(true);
        txtDireccion.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        txtGps = new JTextField();
        txtGps.setPreferredSize(new Dimension(280, 25));

        comboEstado = new JComboBox<>(new String[]{"DISPONIBLE", "OCUPADA", "MANTENIMIENTO"});
        comboEstado.setPreferredSize(new Dimension(280, 25));
        
        comboDeporte = new JComboBox<>(new String[]{"FUTBOL 6", "FUTBOL 8", "FUTBOL 12","VOLLEY","BASQUET"});
        comboDeporte.setPreferredSize(new Dimension(280, 25));

        panelCampos.add(crearFila("ID:", txtId));
        panelCampos.add(crearFilaConScroll("Nombre:*", txtNombre));
        panelCampos.add(crearFilaConScroll("Dirección:*", txtDireccion));
        panelCampos.add(crearFila("GPS:*", txtGps));        
        panelCampos.add(crearFilaConCombo("Deporte:", comboDeporte));
        panelCampos.add(crearFilaConCombo("Estado:", comboEstado));

        // 🔴 Columna derecha: galería imágenes
        JPanel panelImagenes = new JPanel(new GridLayout(2, 2, 5, 5));
        panelImagenes.setBorder(BorderFactory.createTitledBorder("Imágenes (máx. 4)"));

        for (int i = 0; i < 4; i++) {
            lblImagenes[i] = crearLabelImagen("Imagen " + (i + 1), i);
            panelImagenes.add(lblImagenes[i]);
        }

        panelCentral.add(panelCampos);
        panelCentral.add(panelImagenes);

        // 🟣 Panel inferior: botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnGuardar = new JButton("💾 GUARDAR");
        btnGuardar.setPreferredSize(new Dimension(150, 40));
        JButton btnCancelar = new JButton("❌ CANCELAR");
        btnCancelar.setPreferredSize(new Dimension(150, 40));

        btnGuardar.addActionListener(e -> guardarCancha());
        btnCancelar.addActionListener(e -> limpiarFormulario());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        panel.add(panelCentral, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }
    
    private JPanel crearFilaConScroll(String etiqueta, JTextArea area) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(etiqueta);
        label.setPreferredSize(new Dimension(100, 25));

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setPreferredSize(new Dimension(250, 50)); // 👈 Altura ajustada

        fila.add(label);
        fila.add(scrollPane);
        return fila;
    }


    private JPanel crearFila(String etiqueta, JTextField campo) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(etiqueta);
        label.setPreferredSize(new Dimension(100, 25));
        campo.setPreferredSize(new Dimension(250, 25));
        fila.add(label);
        fila.add(campo);
        return fila;
    }

    private JPanel crearFilaConCombo(String etiqueta, JComboBox<String> combo) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(etiqueta);
        label.setPreferredSize(new Dimension(100, 25));
        combo.setPreferredSize(new Dimension(250, 25));
        fila.add(label);
        fila.add(combo);
        return fila;
    }

    private JLabel crearLabelImagen(String texto, int index) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(150, 100));
        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);

        label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(GestionCanchasFrame.this) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        byte[] imageBytes = Files.readAllBytes(file.toPath());
                        ImageIcon icon = new ImageIcon(new ImageIcon(imageBytes).getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH));
                        label.setIcon(icon);
                        label.setText("");
                        imagenes[index] = imageBytes;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(GestionCanchasFrame.this, "Error al cargar imagen", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        return label;
    }

    private JPanel crearPanelLista() {
        JPanel panel = new JPanel(new BorderLayout());

        modeloTabla = new DefaultTableModel(new String[]{
                "ID", "Nombre", "Dirección", "GPS", "Estado"
        }, 0) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaCanchas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaCanchas);

        tablaCanchas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int fila = tablaCanchas.rowAtPoint(e.getPoint());
                    tablaCanchas.setRowSelectionInterval(fila, fila);

                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem editar = new JMenuItem("✏️ Editar");
                    JMenuItem eliminar = new JMenuItem("🗑 Eliminar");

                    editar.addActionListener(a -> {
                        cargarFormularioDesdeTabla(fila);
                        ((JTabbedPane) getContentPane().getComponent(0)).setSelectedIndex(0);
                    });
                    eliminar.addActionListener(a -> eliminarCancha(fila));

                    menu.add(editar);
                    menu.add(eliminar);

                    menu.show(tablaCanchas, e.getX(), e.getY());
                }
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        cargarCanchas();
        return panel;
    }

    private void guardarCancha() {
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String gps = txtGps.getText().trim();
        String estado = (String) comboEstado.getSelectedItem();
        String deporte = (String) comboDeporte.getSelectedItem();

        if (nombre.isEmpty() || direccion.isEmpty() || gps.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (txtId.getText().isEmpty()) {
            Cancha cancha = new Cancha(nombre, direccion, gps,deporte, estado, imagenes[0], imagenes[1], imagenes[2], imagenes[3]);
            canchaController.registrarCancha(cancha);
            JOptionPane.showMessageDialog(this, "✅ Cancha registrada correctamente");
        } else {
            int id = Integer.parseInt(txtId.getText());
            Cancha cancha = new Cancha(id, nombre, direccion, gps, deporte, estado,  imagenes[0], imagenes[1], imagenes[2], imagenes[3], null);
            canchaController.actualizarCancha(cancha);
            JOptionPane.showMessageDialog(this, "✅ Cancha actualizada correctamente");
        }

        limpiarFormulario();
        cargarCanchas();
        ((JTabbedPane) getContentPane().getComponent(0)).setSelectedIndex(1);
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtGps.setText("");
        comboEstado.setSelectedIndex(0);
        for (int i = 0; i < lblImagenes.length; i++) {
            lblImagenes[i].setIcon(null);
            lblImagenes[i].setText("Imagen " + (i + 1));
            imagenes[i] = null;
        }
    }

    private void cargarCanchas() {
        modeloTabla.setRowCount(0);
        List<Cancha> lista = canchaController.listarCanchas();
        for (Cancha c : lista) {
            modeloTabla.addRow(new Object[]{
                    c.getId(), c.getNombre(), c.getDireccion(), c.getGps(), c.getEstado()
            });
        }
    }

    private void cargarFormularioDesdeTabla(int fila) {
        int id = (int) modeloTabla.getValueAt(fila, 0);
        Cancha cancha = canchaController.buscarCanchaPorId(id);
        if (cancha != null) {
            txtId.setText(String.valueOf(cancha.getId()));
            txtNombre.setText(cancha.getNombre());
            txtDireccion.setText(cancha.getDireccion());
            txtGps.setText(cancha.getGps());
            comboEstado.setSelectedItem(cancha.getEstado());
            byte[][] imgs = {cancha.getImagen1(), cancha.getImagen2(), cancha.getImagen3(), cancha.getImagen4()};
            for (int i = 0; i < imgs.length; i++) {
                if (imgs[i] != null) {
                    ImageIcon icon = new ImageIcon(new ImageIcon(imgs[i]).getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH));
                    lblImagenes[i].setIcon(icon);
                    lblImagenes[i].setText("");
                    imagenes[i] = imgs[i];
                } else {
                    lblImagenes[i].setIcon(null);
                    lblImagenes[i].setText("Imagen " + (i + 1));
                    imagenes[i] = null;
                }
            }
        }
    }

    private void eliminarCancha(int fila) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar esta cancha?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = (int) modeloTabla.getValueAt(fila, 0);
            canchaController.eliminarCancha(id);
            cargarCanchas();
            JOptionPane.showMessageDialog(this, "✅ Cancha eliminada correctamente");
        }
    }
}
