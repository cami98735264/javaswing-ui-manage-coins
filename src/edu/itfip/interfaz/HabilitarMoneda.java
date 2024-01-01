package edu.itfip.interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.itfip.logica.Iniciador;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HabilitarMoneda extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Connection coneccion;

	/**
	 * Create the dialog.
	 */
	public HabilitarMoneda() {
		JButton okButton = new JButton("Confirmar");
		Iniciador logica = new Iniciador();
		coneccion = logica.conectarDB();
		setBounds(100, 100, 527, 295);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Habilita/Inhabilita tu moneda");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
			lblNewLabel.setBounds(10, 42, 493, 31);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Selecciona la id:");
			lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(10, 100, 116, 23);
			contentPanel.add(lblNewLabel_1);
		}
		{
		}
		{
			JLabel lblNewLabel_1 = new JLabel("¿Qué quieres hacer con esta moneda?");
			lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(10, 138, 261, 23);
			contentPanel.add(lblNewLabel_1);
		}
		{
			Choice choiceIds = new Choice();
			choiceIds.setBounds(132, 100, 371, 23);
			ArrayList<Integer> ids = logica.obtenerIds(coneccion);
			logica.llenarCeldasConIds(coneccion, ids, choiceIds);
			contentPanel.add(choiceIds);
			Choice choiceEleccion = new Choice();
			choiceEleccion.addItem("Habilitar");
			choiceEleccion.addItem("Inhabilitar");
			choiceEleccion.setBounds(277, 138, 226, 18);
			contentPanel.add(choiceEleccion);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String itemSeleccionado = choiceEleccion.getSelectedItem();
					String idSeleccionada = Iniciador.extraerTextoEntreBrackets(choiceIds.getSelectedItem());
					String eleccion = itemSeleccionado.equals("Habilitar") ? "true" : "false";
					try {
						String query = "update monedas set est = ? where id = ?";
						PreparedStatement st = coneccion.prepareStatement(query);
						st.setString(1, eleccion);
						st.setInt(2, Integer.valueOf(idSeleccionada));
						int filasAfectadas = st.executeUpdate();
						if(filasAfectadas == 1) {
							JOptionPane.showMessageDialog(
				                    null, 
				                    "¡La moneda con id = " + idSeleccionada + " fue " + (itemSeleccionado.equals("Habilitar") ? "habilitada" : "inhabilitada") + " correctamente!", 
				                    "Valido", 
				                    JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(
				                    null, 
				                    "Hubo un error al intentar " + itemSeleccionado.toLowerCase() + "La moneda con id = " + idSeleccionada, 
				                    "Error", 
				                    JOptionPane.ERROR_MESSAGE);
						}
					} catch(SQLException err) {
						err.printStackTrace();
						JOptionPane.showMessageDialog(
			                    null, 
			                    "Hubo un error al intentar " + itemSeleccionado.toLowerCase() + "La moneda con id = " + idSeleccionada + " (SQLError)", 
			                    "Error", 
			                    JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton.setFont(new Font("Arial", Font.PLAIN, 13));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
