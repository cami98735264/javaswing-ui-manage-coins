package edu.itfip.interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.itfip.logica.Iniciador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarMoneda extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Connection coneccion;

	/**
	 * Create the dialog.
	 */
	public EliminarMoneda() {
		Iniciador logica = new Iniciador();
		coneccion = logica.conectarDB();
		setBounds(100, 100, 549, 237);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JButton okButton = new JButton("Confirmar");
		okButton.setFont(new Font("Arial", Font.PLAIN, 13));
		{
			JLabel lblNewLabel = new JLabel("Elimina la moneda seleccionada");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
			lblNewLabel.setBounds(10, 10, 515, 49);
			contentPanel.add(lblNewLabel);
		}
		{
			ArrayList<Integer> ids = logica.obtenerIds(coneccion);
			Choice choice = new Choice();
			choice.setBounds(10, 116, 515, 18);
			contentPanel.add(choice);
			logica.llenarCeldasConIds(coneccion, ids, choice);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String idSeleccionada = Iniciador.extraerTextoEntreBrackets(choice.getSelectedItem());
					try {
						PreparedStatement ps = coneccion.prepareStatement("delete from monedas where id = ?");
						ps.setInt(1, Integer.valueOf(idSeleccionada));
						int filasEliminadas = ps.executeUpdate();
						if(filasEliminadas == 1) {
							choice.remove(choice.getSelectedIndex());
							JOptionPane.showMessageDialog(
				                    null, 
				                    "¡La moneda con id = " + idSeleccionada + " fue eliminada correctamente!", 
				                    "Valido", 
				                    JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(
				                    null, 
				                    "Ocurrió un error al tratar de eliminar la moneda con id = " + idSeleccionada, 
				                    "Error", 
				                    JOptionPane.ERROR_MESSAGE);
						}
					} catch(SQLException err) {
						err.printStackTrace();
						JOptionPane.showMessageDialog(
			                    null, 
			                    "Hubo un error al intentar" + " eliminar " + "La moneda con id = " + idSeleccionada + " (SQLError)", 
			                    "Error", 
			                    JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Selecciona la id de la moneda:");
			lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(10, 92, 515, 18);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			      try {
			    	System.out.println("CERRÓ LA VENTANA, SE CERRÓ LA CONEXIÓN A LA BASE DE DATOS");
			        coneccion.close();  
			      } catch (SQLException e) {
			        e.printStackTrace();
			      }
			    }
			  });
		}
	}

}
