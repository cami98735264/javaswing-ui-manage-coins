package edu.itfip.interfaz;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public InterfazPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selecciona una opción:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 155, 440);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Actualizar Moneda / Añadir Moneda");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AñadirMoneda añadirMoneda = new AñadirMoneda();
				añadirMoneda.setVisible(true);
				añadirMoneda.setResizable(false);
			}
		});
		btnNewButton.setBackground(new Color(18, 228, 186));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(175, 186, 334, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Eliminar Moneda");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarMoneda eliminarMoneda = new EliminarMoneda();
				eliminarMoneda.setVisible(true);
				eliminarMoneda.setResizable(false);
				
			}
		});
		btnNewButton_2.setBackground(new Color(18, 228, 186));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_2.setBounds(175, 233, 162, 37);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Habilitar/Inhabilitar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HabilitarMoneda habilitarMoneda = new HabilitarMoneda();
				habilitarMoneda.setVisible(true);
				habilitarMoneda.setResizable(false);
			}
		});
		btnNewButton_3.setBackground(new Color(18, 228, 186));
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_3.setBounds(347, 233, 162, 37);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("Administra tus monedas");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 48, 603, 37);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_2_1 = new JButton("Consultar Monedas");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarMoneda consultarMoneda = new ConsultarMoneda();
				consultarMoneda.setVisible(true);
			}
		});
		btnNewButton_2_1.setBackground(new Color(18, 228, 186));
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_2_1.setBounds(175, 280, 334, 37);
		contentPane.add(btnNewButton_2_1);		
	}
	
}
