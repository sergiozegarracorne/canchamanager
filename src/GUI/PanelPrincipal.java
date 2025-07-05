package GUI;

import javax.swing.*;

import util.TemaUtil;

import java.awt.*;
import java.awt.event.*;

public class PanelPrincipal extends JFrame {

    private JPanel contentPane;

    public PanelPrincipal() {
    	TemaUtil.actualizarUI(PanelPrincipal.this); // Aplica el tema actual
    	
        setTitle("Panel Principal - Sistema de Canchas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("Bienvenido al Sistema de Administración");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(120, 20, 400, 30);
        contentPane.add(lblTitulo);

        JButton btnReservas = new JButton("Gestionar Reservas");
        btnReservas.setBounds(200, 80, 200, 40);
        contentPane.add(btnReservas);

        JButton btnCanchas = new JButton("Gestionar Canchas");
        btnCanchas.setBounds(200, 140, 200, 40);
        contentPane.add(btnCanchas);

        JButton btnClientes = new JButton("Clientes");
        btnClientes.setBounds(200, 200, 200, 40);
        contentPane.add(btnClientes);

        JButton btnReportes = new JButton("Reportes");
        btnReportes.setBounds(200, 260, 200, 40);
        contentPane.add(btnReportes);

        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.setBounds(400, 310, 150, 30);
        contentPane.add(btnSalir);

        btnReservas.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir ventana de reservas"));
        btnCanchas.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir ventana de canchas"));
        
        btnClientes.addActionListener(e -> {
            VentanaClientes ventana = new VentanaClientes();
            ventana.setVisible(true);
        });
        
        btnReportes.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir ventana de reportes"));
        btnSalir.addActionListener(e -> {
            dispose();
            new Login().setVisible(true); 
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	
                PanelPrincipal frame = new PanelPrincipal();
                
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
