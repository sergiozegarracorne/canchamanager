package canchamanager.grupo12.upn.gui;

import javax.swing.*;
import util.TemaUtil;
import java.awt.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PanelPrincipal extends JFrame {

   
	private static final long serialVersionUID = -7322351155600675808L;
	private JLabel lblUsuarioStatus;
    private JLabel lblHoraStatus;
    private Timer timer;
    
    public static void main(String[] args) {
		TemaUtil.aplicarTemaGuardado(); // aplica claro u oscuro según config

		EventQueue.invokeLater(() -> {
			PanelPrincipal frame = new PanelPrincipal(" aaa");
			frame.setVisible(true);
		});

	}

    public PanelPrincipal(String usuarioActual) {
        TemaUtil.actualizarUI(this); // Aplica el tema actual

        setTitle("Panel Principal - Sistema de Canchas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null); // Centrar ventana

        // ✅ Layout principal
        getContentPane().setLayout(new BorderLayout());

        // 🔵 Barra superior con título y botón cerrar sesión
        JPanel barraSuperior = new JPanel(new BorderLayout());
        barraSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("Bienvenido al Sistema de Administración");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.addActionListener(e -> {
            dispose();
            new Login().setVisible(true);
        });

        barraSuperior.add(lblTitulo, BorderLayout.WEST);
        barraSuperior.add(btnSalir, BorderLayout.EAST);
        getContentPane().add(barraSuperior, BorderLayout.NORTH);

        // 🟢 Panel central con botones
        GridBagLayout gbl_panelBotones = new GridBagLayout();
        gbl_panelBotones.rowHeights = new int[]{0, 89, 98, 0, 0};
        JPanel panelBotones = new JPanel(gbl_panelBotones);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        JButton btnReservas = new JButton("Gestionar Reservas");    
        btnReservas.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JButton btnCanchas = new JButton("Gestionar Canchas");
        btnCanchas.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JButton btnAsignarDeportes = new JButton("Asignar Deportes");
        btnAsignarDeportes.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JButton btnClientes = new JButton("Gestion de Clientes");
        btnClientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JButton btnReportes = new JButton("Reportes Generales");
        btnReportes.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        JButton btnUsuarios = new JButton("Gestión de Usuarios");
        btnUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnUsuarios.putClientProperty("JButton.buttonType", "roundRect");

        btnReservas.setPreferredSize(new Dimension(220, 40));
        btnCanchas.setPreferredSize(new Dimension(140, 40));
        btnAsignarDeportes.setPreferredSize(new Dimension(140, 40));
        btnClientes.setPreferredSize(new Dimension(220, 40));
        btnReportes.setPreferredSize(new Dimension(220, 40));
        btnUsuarios.setPreferredSize(new Dimension(220, 40));

        // 🟠 Agregar botones al grid
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(15, 15, 15, 15);
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        panelBotones.add(btnReservas, gbc1);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(15, 0, 15, 115);
        gbc2.gridx = 2;
        gbc2.gridy = 0;
        panelBotones.add(btnCanchas, gbc2);
        
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.insets = new Insets(15, 185, 15, 5);
        gbc3.gridx = 2;
        gbc3.gridy = 0;
        panelBotones.add(btnAsignarDeportes, gbc3);
        

        GridBagConstraints gbcClientes = new GridBagConstraints();
        gbcClientes.insets = new Insets(15, 15, 15, 15);
        gbcClientes.gridx = 0;
        gbcClientes.gridy = 1;
        panelBotones.add(btnClientes, gbcClientes);

        GridBagConstraints gbcReportes = new GridBagConstraints();
        gbcReportes.insets = new Insets(15, 15, 15, 15);
        gbcReportes.gridx = 2;
        gbcReportes.gridy = 1;
        panelBotones.add(btnReportes, gbcReportes);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
        panelBotones.add(btnUsuarios, gbc);

        getContentPane().add(panelBotones, BorderLayout.CENTER);

        // 🔴 Barra inferior (status bar)
        BorderLayout bl_statusBar = new BorderLayout();
        JPanel statusBar = new JPanel(bl_statusBar);
        statusBar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        lblUsuarioStatus = new JLabel("   Usuario: "+usuarioActual);
        lblUsuarioStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblHoraStatus = new JLabel();
        lblHoraStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
        actualizarHora(); // Mostrar hora inicial

        statusBar.add(lblUsuarioStatus, BorderLayout.WEST);
        statusBar.add(lblHoraStatus, BorderLayout.EAST);

        getContentPane().add(statusBar, BorderLayout.SOUTH);

        // ⏰ Timer para actualizar la hora cada segundo
        timer = new Timer(1000, e -> actualizarHora());
        timer.start();

        // 👉 Listeners de botones
        btnReservas.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir ventana de reservas"));
      
        
        
        btnCanchas.addActionListener(e -> {
        	GestionCanchasFrame ventanaCanchas = new GestionCanchasFrame();
        	ventanaCanchas.setVisible(true);
        });
        
        
        
        
        btnClientes.addActionListener(e -> {
        	GestionClientesFrame ventanaClientes = new GestionClientesFrame();
        	ventanaClientes.setVisible(true);
        });
        btnReportes.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir ventana de reportes"));
        btnUsuarios.addActionListener(e -> {
            GestionUsuariosFrame ventana = new GestionUsuariosFrame();
            ventana.setVisible(true);
        });
        
        
        btnAsignarDeportes.addActionListener(e -> {
            GestionCanchasDeportesFrame ventana = new GestionCanchasDeportesFrame();
            ventana.setVisible(true);
        });
    }

    private void actualizarHora() {
        String horaActual = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        lblHoraStatus.setText(" Hora: "+horaActual+"  ");
    }
}
