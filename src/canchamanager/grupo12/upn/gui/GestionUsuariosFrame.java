package canchamanager.grupo12.upn.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import canchamanager.grupo12.upn.controller.UsuarioController;
import canchamanager.grupo12.upn.model.Usuario;


public class GestionUsuariosFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtId;
	private JTextField txtUsername;
	private JTextField txtNombreCompleto;
	private JPasswordField txtPassword;
	private JComboBox<String> comboRol;
	private JTable tablaUsuarios;
	private DefaultTableModel modeloTabla;

	private UsuarioController usuarioController = new UsuarioController();

	public GestionUsuariosFrame() {
		setTitle("Gestión de Usuarios");
		setBounds(100, 100, 539, 658);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

		JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
		panelFormulario.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Registrar / Editar Usuario"),
						BorderFactory.createEmptyBorder(10, 5, 10, 5) // 👈 Padding interno
				));

		panelFormulario.add(new JLabel("ID:"));
		txtId = new JTextField();
		txtId.setEditable(false);
		panelFormulario.add(txtId);

		panelFormulario.add(new JLabel("Nombre Usuario:"));
		txtUsername = new JTextField();
		panelFormulario.add(txtUsername);

		panelFormulario.add(new JLabel("Nombre completo:"));
		txtNombreCompleto = new JTextField();
		panelFormulario.add(txtNombreCompleto);

		panelFormulario.add(new JLabel("Contraseña:"));
		txtPassword = new JPasswordField();
		panelFormulario.add(txtPassword);

		panelFormulario.add(new JLabel("Rol:"));
		comboRol = new JComboBox<>(new String[] { "ADMIN", "USUARIO" });
		panelFormulario.add(comboRol);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(e -> guardarUsuario());
		panelFormulario.add(btnGuardar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(e -> limpiarFormulario());
		panelFormulario.add(btnLimpiar);

		panelPrincipal.add(panelFormulario, BorderLayout.NORTH);

		String[] columnas = { "ID", "Username", "Nombre", "Rol", "Estado" };
		modeloTabla = new DefaultTableModel(columnas, 0) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false; // hacer tabla no editable
			}
		};
		tablaUsuarios = new JTable(modeloTabla);
		tablaUsuarios.setRowHeight(25);

		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(30); // ID
		tablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(60); // username
		tablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(150); // Nombre
		tablaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(50); // rol
		tablaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(50); // estado

		JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
		panelPrincipal.add(scrollPane, BorderLayout.CENTER);

		cargarUsuarios();

		tablaUsuarios.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				// Obtener el valor de la columna Estado (asumo que está en la columna 4)
				String estado = table.getValueAt(row, 4).toString();

				if ("Inactivo".equalsIgnoreCase(estado)) {
					c.setForeground(Color.RED); // 🔴 Texto rojo para inactivos
				} else {
					c.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
				}

				return c;
			}
		});

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
					JMenuItem resetClave = new JMenuItem("Reset Clave");

					editar.addActionListener(a -> cargarFormularioDesdeTabla(fila));
					darBaja.addActionListener(a -> darDeBajaUsuario(fila));
					activar.addActionListener(a -> reactivarUsuario(fila));
					resetClave.addActionListener(a -> resetClave(fila));

					String estado = modeloTabla.getValueAt(fila, 4).toString();
					menu.add(editar);
					if ("Activo".equals(estado)) {
						menu.add(darBaja);
					} else {
						menu.add(activar);
					}

					menu.add(resetClave);
					menu.show(tablaUsuarios, e.getX(), e.getY());
				}
			}
		});

		// Añadir panel principal al contenido
		getContentPane().add(panelPrincipal);
	}

	private void guardarUsuario() {
		String id = txtId.getText().trim();
		String username = txtUsername.getText();
		String nombreCompleto = txtNombreCompleto.getText();
		String password = new String(txtPassword.getPassword());
		String rol = (String) comboRol.getSelectedItem();

		if (username.isEmpty() || nombreCompleto.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (id == null || id.isEmpty()) {
			
			Usuario nuevo = new Usuario(username, nombreCompleto, password, rol);			
			if(usuarioController.registrarUsuario(nuevo)) {
				JOptionPane.showMessageDialog(this, "Usuario registrado correctamente");
				limpiarFormulario();
			}else {
				JOptionPane.showMessageDialog(this,
						"Ya existe un usuario con este Nombre de Usuario: ", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;				
			}
			
		} else {
			Usuario actualizar = new Usuario(Integer.valueOf(id),username,nombreCompleto,password,rol,null,null);
			if(usuarioController.actualizarUsuario(actualizar)) {
				JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");
				limpiarFormulario();				
			}else {
				JOptionPane.showMessageDialog(this,"El Nombre de Usuario ya esta en Uso", "Error",JOptionPane.ERROR_MESSAGE);
			}				
		}		
		cargarUsuarios();
	}

	private void limpiarFormulario() {
		txtId.setText("");
		txtUsername.setText("");
		txtNombreCompleto.setText("");
		txtPassword.setEnabled(true);
		txtPassword.setText("");
		comboRol.setModel(new DefaultComboBoxModel<String>(new String[] { "ADMIN", "USUARIO" }));
		comboRol.setSelectedIndex(0);
	}

	private void cargarUsuarios() {
		modeloTabla.setRowCount(0); // limpiar tabla
		List<Usuario> lista = usuarioController.listarUsuarios();
		for (Usuario u : lista) {
			String estado = u.isActivo() ? "Activo" : "Inactivo";
			modeloTabla.addRow(new Object[] { u.getId(), u.getUsername(), u.getNombreCompleto(), u.getRol(), estado });
		}
	}

	private void cargarFormularioDesdeTabla(int fila) {
		int id = (int) modeloTabla.getValueAt(fila, 0);
		Usuario u = usuarioController.listarUsuarios().stream().filter(us -> us.getId() == id).findFirst()
				.orElse(null);
		if (u != null) {
			txtId.setText(String.valueOf(u.getId()));
			txtUsername.setText(u.getUsername());
			txtNombreCompleto.setText(u.getNombreCompleto());
			txtPassword.setText(u.getPassword());//.setText("*************"); // no mostrar contraseña por seguridad
			txtPassword.setEnabled(false);
			comboRol.setSelectedItem(u.getRol());
		}
	}

	private void darDeBajaUsuario(int fila) {
		int id = (int) modeloTabla.getValueAt(fila, 0);
		usuarioController.darDeBaja(id);
		cargarUsuarios();
	}

	private void reactivarUsuario(int fila) {
		int id = (int) modeloTabla.getValueAt(fila, 0);
		usuarioController.reactivar(id);
		cargarUsuarios();
	}
	
	private void resetClave(int fila) {
		int id = (int) modeloTabla.getValueAt(fila, 0);
		usuarioController.cambiarPassword(id);
		cargarUsuarios();		
	}
}
