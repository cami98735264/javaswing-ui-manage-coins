package edu.itfip.interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import edu.itfip.logica.Iniciador;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultarMoneda extends JDialog {

	private static final long serialVersionUID = 1L;
	private Connection coneccion;
	private JTable table;
	private DefaultTableModel model;
	/**
	 * Create the dialog.
	 */
	public ConsultarMoneda() {
		Iniciador baseDeDatos = new Iniciador();
		coneccion = baseDeDatos.conectarDB();
		
		setBounds(100, 100, 712, 493);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JRadioButton botonMonedasActivas = new JRadioButton("Solo mostrar monedas activas");
			botonMonedasActivas.setFont(new Font("Arial", Font.PLAIN, 11));
			buttonPane.add(botonMonedasActivas);
			{
				JButton btnNewButton = new JButton("Confirmar");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(table.getRowCount() != 0) model.setRowCount(0);
							try {
								Statement st = coneccion.createStatement();
								ResultSet rs = st.executeQuery(botonMonedasActivas.isSelected() ? "select * from monedas where est = 'true' order by id" : "select * from monedas order by id");
								while(rs.next()) {
									model.addRow(new Object[]{rs.getInt("id"), rs.getString("descripcion"), rs.getString("pais"), rs.getString("abreviatura"), rs.getString("est")});
								}
								st.close();
								rs.close();
								
							} catch (SQLException err) {
								// TODO Auto-generated catch block
								err.printStackTrace();
							}
					}
				});
				btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
				buttonPane.add(btnNewButton);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				model = new DefaultTableModel();
				table = new JTable(model);
				model.addColumn("Ids");
				model.addColumn("Descripcion");
				model.addColumn("País");
				model.addColumn("Abreviatura");
				model.addColumn("Estado");
				scrollPane.setViewportView(table);
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
