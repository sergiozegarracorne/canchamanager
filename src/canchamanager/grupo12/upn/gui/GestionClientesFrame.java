package canchamanager.grupo12.upn.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import canchamanager.grupo12.upn.controller.ClienteController;
import canchamanager.grupo12.upn.dao.GestorClientesMySQL;
import canchamanager.grupo12.upn.dao.IGestorClientes;
import canchamanager.grupo12.upn.model.Cliente;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GestionClientesFrame extends JFrame {

	private static final long serialVersionUID = -4351057925887383L;
	private JTextField txtNombre, txtTelefono, txtEmail, txtDni, txtId;
	private JCheckBox chkFrecuente;
	private JTable tablaClientes;
	private DefaultTableModel modeloTabla;

	private ClienteController clienteController = new ClienteController();

	public GestionClientesFrame() {
		setTitle("Gestión de Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		// Panel superior (formulario)
		JPanel panelFormulario = new JPanel();
		panelFormulario.setBorder(BorderFactory.createTitledBorder("Registrar / Editar Cliente"));
		panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));

		// Campos del formulario
		panelFormulario.add(crearFila("ID:", txtId = new JTextField(10)));
		panelFormulario.add(crearFila("DNI:*", txtDni = new JTextField(10)));
		panelFormulario.add(crearFila("Nombre:*", txtNombre = new JTextField(20)));
		panelFormulario.add(crearFila("Teléfono*:", txtTelefono = new JTextField(15)));
		panelFormulario.add(crearFila("Email:", txtEmail = new JTextField(40)));
		panelFormulario.add(crearFila("Cliente Frecuente:", chkFrecuente = new JCheckBox("")));

		// Botones guardar y limpiar
		JPanel panelBotones = new JPanel();
		JButton btnGuardar = new JButton("💾 Guardar");
		JButton btnLimpiar = new JButton("🧹 Limpiar");

		btnGuardar.addActionListener(e -> guardarCliente());
		btnLimpiar.addActionListener(e -> limpiarFormulario());

		panelBotones.add(btnGuardar);
		panelBotones.add(btnLimpiar);
		panelFormulario.add(panelBotones);

		getContentPane().add(panelFormulario, BorderLayout.NORTH);

		// Tabla de clientes
		modeloTabla = new DefaultTableModel(new String[] { "ID", "DNI", "Nombre", "Teléfono", "Email", "Frecuente" },
				0) {
			private static final long serialVersionUID = 7734755726198314684L;

			public Class<?> getColumnClass(int column) {
				return (column == 5) ? Boolean.class : String.class;
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tablaClientes = new JTable(modeloTabla);
		tablaClientes.setRowHeight(25);
		// Ajustar ancho de columnas
		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(40); // ID
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(60); // DNI
		tablaClientes.getColumnModel().getColumn(2).setPreferredWidth(150); // Nombre
		tablaClientes.getColumnModel().getColumn(3).setPreferredWidth(100); // Teléfono
		tablaClientes.getColumnModel().getColumn(4).setPreferredWidth(200); // Email
		tablaClientes.getColumnModel().getColumn(5).setPreferredWidth(80); // Frecuente

		// Crear un renderer para centrar
		DefaultTableCellRenderer centroRenderer = new DefaultTableCellRenderer();
		centroRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		tablaClientes.getColumnModel().getColumn(1).setCellRenderer(centroRenderer); // ID
		tablaClientes.getColumnModel().getColumn(3).setCellRenderer(centroRenderer); // DNI
		tablaClientes.getColumnModel().getColumn(4).setCellRenderer(centroRenderer); // DNI

		JScrollPane scrollPane = new JScrollPane(tablaClientes);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		cargarClientes();

		// Menú contextual para acciones
		tablaClientes.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int fila = tablaClientes.rowAtPoint(e.getPoint());
					tablaClientes.setRowSelectionInterval(fila, fila);

					JPopupMenu menu = new JPopupMenu();
					Object nom = tablaClientes.getValueAt(fila, 2);
					JMenuItem editar = new JMenuItem("Editar Cliente:  " + nom);

					editar.addActionListener(a -> cargarFormularioDesdeTabla(fila));

					menu.add(editar);

					menu.show(tablaClientes, e.getX(), e.getY());
				}
			}
		});

		SwingUtilities.invokeLater(() -> txtDni.requestFocusInWindow());
	}

	private JPanel crearFila(String etiqueta, JComponent campo) {
		JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel(etiqueta);
		label.setPreferredSize(new Dimension(110, 14));
		fila.add(label);
		if (etiqueta == "ID:" && campo instanceof JTextField) {
			((JTextField) campo).setEditable(false);
		}
		fila.add(campo);
		return fila;
	}

	/*
	 * 
	 * private void guardarCliente() { String id = txtId.getText().trim(); String
	 * nombre = txtNombre.getText(); String telefono = txtTelefono.getText(); String
	 * email = txtEmail.getText(); String dni = txtDni.getText(); boolean frecuente
	 * = chkFrecuente.isSelected();
	 * 
	 * if (nombre.isEmpty() || telefono.isEmpty() || dni.isEmpty()) {
	 * JOptionPane.showMessageDialog(this, "Completa los campos obligatorios",
	 * "Error", JOptionPane.ERROR_MESSAGE); return; }
	 * 
	 * if (id == null || id.isEmpty()) {
	 * 
	 * Cliente existente = gestorClientes.buscarClientePorDni(dni);
	 * 
	 * if (existente != null) { JOptionPane.showMessageDialog(this,
	 * "Ya existe un cliente con este DNI: " + existente.getNombre(), "Error",
	 * JOptionPane.ERROR_MESSAGE); } else { Cliente cliente = new Cliente(nombre,
	 * telefono, email, dni, frecuente); gestorClientes.registrarCliente(cliente);
	 * JOptionPane.showMessageDialog(this, "✅ Cliente registrado correctamente");
	 * limpiarFormulario(); }
	 * 
	 * } else {
	 * 
	 * Cliente existente = gestorClientes.buscarClientePorDni(dni); if (existente !=
	 * null && existente.getId() != Integer.parseInt(id)) {
	 * JOptionPane.showMessageDialog(this,
	 * "El DNI ingresado pertenece a otro cliente: " + existente.getNombre(),
	 * "Error", JOptionPane.ERROR_MESSAGE); } else { Cliente cliente = new
	 * Cliente(Integer.parseInt(id), nombre, telefono, email, dni, frecuente);
	 * gestorClientes.actualizarCliente(cliente);
	 * JOptionPane.showMessageDialog(this, "✅ Cliente actualizado correctamente");
	 * limpiarFormulario(); }; }
	 * 
	 * cargarClientes(); }
	 */

	private void guardarCliente() {
		String id = txtId.getText().trim();
		String nombre = txtNombre.getText();
		String telefono = txtTelefono.getText();
		String email = txtEmail.getText();
		String dni = txtDni.getText();
		boolean frecuente = chkFrecuente.isSelected();

		if (nombre.isEmpty() || telefono.isEmpty() || dni.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Completa los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (id == null || id.isEmpty()) {
			// 🔥 Usar el controller para registrar
			Cliente cliente = new Cliente(nombre, telefono, email, dni, frecuente);
			if (clienteController.registrarCliente(cliente)) {
				JOptionPane.showMessageDialog(this, "✅ Cliente registrado correctamente");
				limpiarFormulario();
			} else {
				JOptionPane.showMessageDialog(this, "❌ Ya existe un cliente con este DNI", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			// 🔥 Usar el controller para actualizar
			Cliente cliente = new Cliente(Integer.parseInt(id), nombre, telefono, email, dni, frecuente);
			if (clienteController.actualizarCliente(cliente)) {
				JOptionPane.showMessageDialog(this, "✅ Cliente actualizado correctamente");
				limpiarFormulario();
			} else {
				JOptionPane.showMessageDialog(this, "❌ El DNI pertenece a otro cliente", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		cargarClientes();
	}

	private void limpiarFormulario() {
		txtId.setText("");
		txtNombre.setText("");
		txtTelefono.setText("");
		txtEmail.setText("");
		txtDni.setText("");
		chkFrecuente.setSelected(false);
	}

	private void cargarClientes() {
		modeloTabla.setRowCount(0);
		List<Cliente> lista = clienteController.listarClientes();
		for (Cliente c : lista) {
			modeloTabla.addRow(new Object[] { c.getId(), c.getDni(), c.getNombre(), c.getTelefono(), c.getEmail(),
					c.isFrecuente() });
		}
	}

	private void cargarFormularioDesdeTabla(int fila) {
		int id = (int) modeloTabla.getValueAt(fila, 0);
		Cliente cliente = clienteController.buscarClientePorId(id);
		if (cliente != null) {
			txtId.setText(String.valueOf(cliente.getId()));
			txtDni.setText(cliente.getDni());
			txtNombre.setText(cliente.getNombre());
			txtTelefono.setText(cliente.getTelefono());
			txtEmail.setText(cliente.getEmail());
			chkFrecuente.setSelected(cliente.isFrecuente());
		}
	}

}