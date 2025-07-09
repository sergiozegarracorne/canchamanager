package canchamanager.grupo12.upn.gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;

import canchamanager.grupo12.upn.dao.ConexionDB;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import util.TemaUtil;
import java.awt.Insets;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JToggleButton toggleTema;

	public static void main(String[] args) {
		TemaUtil.aplicarTemaGuardado(); // aplica claro u oscuro según config

		EventQueue.invokeLater(() -> {
			Login frame = new Login();
			frame.setVisible(true);
		});
	}



	public Login() {
		setTitle("Ingreso al Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitulo = new JLabel("Sistema de Administración de Reservas");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(60, 20, 360, 30);
		contentPane.add(lblTitulo);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(100, 80, 70, 25);
		contentPane.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(180, 80, 180, 25);
		contentPane.add(txtUsuario);

		JLabel lblClave = new JLabel("Clave:");
		lblClave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClave.setBounds(100, 120, 80, 25);
		contentPane.add(lblClave);

		txtClave = new JPasswordField();
		txtClave.setBounds(180, 120, 180, 25);
		contentPane.add(txtClave);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIngresar.setBounds(160, 180, 120, 30);
		contentPane.add(btnIngresar);

		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = txtUsuario.getText();
				String clave = String.valueOf(txtClave.getPassword());

				if (usuario.equals("admin") && clave.equals("1234")) {
					// JOptionPane.showMessageDialog(null, "Acceso permitido");

					PanelPrincipal panel = new PanelPrincipal(txtUsuario.getText());
					panel.setVisible(true);

					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Usuario y/o clave incorrectos");
				}
			}
		});

		JButton btnEstadoConexion = new JButton("●"); // Por ahora un círculo
		btnEstadoConexion.setMargin(new Insets(0, 0, 0, 0));
		btnEstadoConexion.setForeground(Color.RED);
		btnEstadoConexion.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnEstadoConexion.setBounds(10, 209, 47, 41);
		btnEstadoConexion.setEnabled(false); // No editable
		btnEstadoConexion.setFocusable(false);
		contentPane.add(btnEstadoConexion);

		btnEstadoConexion.setEnabled(true); // Permitir click
		btnEstadoConexion.addActionListener(e -> {
			if (!hayConexion()) {
				JOptionPane.showMessageDialog(this,
						"❌ No se pudo conectar con la base de datos.\n"
								+ "Verifica tu red o contacta al administrador.",
						"Error de Conexión", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "✅ Conexión establecida correctamente.", "Conexión OK",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		toggleTema = new JToggleButton("Cambiar Tema");
		toggleTema.setFont(new Font("Tahoma", Font.PLAIN, 10));
		toggleTema.setBounds(357, 220, 97, 30);
		contentPane.add(toggleTema);

		toggleTema.addActionListener(e -> {
			TemaUtil.cambiarTema(Login.this);
		});


	
		
		
	

	}

}
