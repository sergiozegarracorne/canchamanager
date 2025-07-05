package canchamanager.grupo12.upn.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import canchamanager.grupo12.upn.dao.GestorUsuariosMySQL;
import canchamanager.grupo12.upn.dao.IGestorUsuarios;
import canchamanager.grupo12.upn.model.Usuario;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GestionUsuariosFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
    private JTextField txtNombreCompleto;
    private JPasswordField txtPassword;
    private JComboBox<String> comboRol;
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;

    private IGestorUsuarios gestorUsuarios = new GestorUsuariosMySQL();

    public GestionUsuariosFrame() {
        setTitle("Gestión de Usuarios");
        setBounds(100, 100, 700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // ✅ Panel superior (formulario)
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registrar / Editar Usuario"));

        panelFormulario.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        panelFormulario.add(txtUsername);

        panelFormulario.add(new JLabel("Nombre completo:"));
        txtNombreCompleto = new JTextField();
        panelFormulario.add(txtNombreCompleto);

        panelFormulario.add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        panelFormulario.add(txtPassword);

        panelFormulario.add(new JLabel("Rol:"));
        comboRol = new JComboBox<>(new String[]{"ADMIN", "USUARIO"});
        panelFormulario.add(comboRol);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarUsuario());
        panelFormulario.add(btnGuardar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        panelFormulario.add(btnLimpiar);

        getContentPane().add(panelFormulario, BorderLayout.NORTH);

        // ✅ Tabla de usuarios
        String[] columnas = {"ID", "Username", "Nombre", "Rol", "Estado", "Acciones"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
                return false; // hacer tabla no editable
            }
        };
        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // ✅ Cargar datos
        cargarUsuarios();

        // ✅ Botón derecho para editar/dar de baja/activar
        tablaUsuarios.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int fila = tablaUsuarios.rowAtPoint(e.getPoint());
                    tablaUsuarios.setRowSelectionInterval(fila, fila);

                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem editar = new JMenuItem("Editar");
                    JMenuItem darBaja = new JMenuItem("Dar de Baja");
                    JMenuItem activar = new JMenuItem("Activar");

                    editar.addActionListener(a -> cargarFormularioDesdeTabla(fila));
                    darBaja.addActionListener(a -> darDeBajaUsuario(fila));
                    activar.addActionListener(a -> reactivarUsuario(fila));

                    String estado = modeloTabla.getValueAt(fila, 4).toString();
                    menu.add(editar);
                    if ("Activo".equals(estado)) {
                        menu.add(darBaja);
                    } else {
                        menu.add(activar);
                    }

                    menu.show(tablaUsuarios, e.getX(), e.getY());
                }
            }
        });
    }

    private void guardarUsuario() {
        String username = txtUsername.getText();
        String nombreCompleto = txtNombreCompleto.getText();
        String password = new String(txtPassword.getPassword());
        String rol = (String) comboRol.getSelectedItem();

        if (username.isEmpty() || nombreCompleto.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuarioExistente = gestorUsuarios.autenticar(username, password);
        if (usuarioExistente == null) {
            // Crear nuevo
            Usuario nuevo = new Usuario(username, nombreCompleto, password, rol);
            gestorUsuarios.registrarUsuario(nuevo);
            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente");
        } else {
            // Actualizar datos
            usuarioExistente.setNombreCompleto(nombreCompleto);
            usuarioExistente.setRol(rol);
            gestorUsuarios.actualizarUsuario(usuarioExistente);
            gestorUsuarios.cambiarPassword(usuarioExistente.getId(), password);
            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");
        }

        limpiarFormulario();
        cargarUsuarios();
    }

    private void limpiarFormulario() {
        txtUsername.setText("");
        txtNombreCompleto.setText("");
        txtPassword.setText("");
        comboRol.setSelectedIndex(0);
    }

    private void cargarUsuarios() {
        modeloTabla.setRowCount(0); // limpiar tabla
        List<Usuario> lista = gestorUsuarios.listarTodosLosUsuarios();
        for (Usuario u : lista) {
            String estado = u.isActivo() ? "Activo" : "Inactivo";
            modeloTabla.addRow(new Object[]{
                    u.getId(), u.getUsername(), u.getNombreCompleto(), u.getRol(), estado
            });
        }
    }

    private void cargarFormularioDesdeTabla(int fila) {
        int id = (int) modeloTabla.getValueAt(fila, 0);
        Usuario u = gestorUsuarios.listarTodosLosUsuarios().stream()
                .filter(us -> us.getId() == id)
                .findFirst().orElse(null);
        if (u != null) {
            txtUsername.setText(u.getUsername());
            txtNombreCompleto.setText(u.getNombreCompleto());
            txtPassword.setText(""); // no mostrar contraseña por seguridad
            comboRol.setSelectedItem(u.getRol());
        }
    }

    private void darDeBajaUsuario(int fila) {
        int id = (int) modeloTabla.getValueAt(fila, 0);
        gestorUsuarios.darDeBajaUsuario(id);
        cargarUsuarios();
    }

    private void reactivarUsuario(int fila) {
        int id = (int) modeloTabla.getValueAt(fila, 0);
        gestorUsuarios.reactivarUsuario(id);
        cargarUsuarios();
    }
}
