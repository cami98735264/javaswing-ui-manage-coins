package edu.itfip.interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.itfip.logica.Iniciador;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.awt.Choice;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AñadirMoneda extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField descripcionMonedaInput;
	private JTextField paisMonedaInput;
	private JLabel abreviaturaMoneda;
	private JTextField abreviaturaMonedaInput;
	private JLabel lblPasDeLa_2;
	private JLabel lblNewLabel_1;
	private Connection coneccion;
	private ArrayList<Integer> ids;
	/**
	 * Create the dialog.
	 */
	public AñadirMoneda() {
		Iniciador logica = new Iniciador();
		coneccion = logica.conectarDB();
		setBounds(100, 100, 517, 444);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDescripcinDeLa = new JLabel("Descripción de la moneda:");
		lblDescripcinDeLa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcinDeLa.setBounds(59, 87, 177, 24);
		contentPanel.add(lblDescripcinDeLa);
		
		descripcionMonedaInput = new JTextField();
		lblDescripcinDeLa.setLabelFor(descripcionMonedaInput);
		descripcionMonedaInput.setColumns(10);
		descripcionMonedaInput.setBounds(243, 86, 185, 25);
		contentPanel.add(descripcionMonedaInput);
		
		JLabel lblPasDeLa = new JLabel("País de la moneda:");
		lblPasDeLa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPasDeLa.setBounds(59, 121, 177, 24);
		contentPanel.add(lblPasDeLa);
		
		paisMonedaInput = new JTextField();
		paisMonedaInput.setColumns(10);
		paisMonedaInput.setBounds(243, 121, 185, 25);
		contentPanel.add(paisMonedaInput);
		
		abreviaturaMoneda = new JLabel("Abreviatura de la moneda");
		abreviaturaMoneda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		abreviaturaMoneda.setBounds(59, 153, 174, 25);
		contentPanel.add(abreviaturaMoneda);
		
		abreviaturaMonedaInput = new JTextField();
		abreviaturaMonedaInput.setColumns(10);
		abreviaturaMonedaInput.setBounds(243, 153, 185, 25);
		contentPanel.add(abreviaturaMonedaInput);
		
		lblPasDeLa_2 = new JLabel("Estado de la moneda:");
		lblPasDeLa_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPasDeLa_2.setBounds(59, 188, 174, 25);
		contentPanel.add(lblPasDeLa_2);
		
		Choice estadoMonedaInput = new Choice();
		estadoMonedaInput.setBackground(new Color(255, 255, 255));
		estadoMonedaInput.setBounds(243, 195, 185, 40);
		estadoMonedaInput.addItem("true");
		estadoMonedaInput.addItem("false");
		contentPanel.add(estadoMonedaInput);
		
		lblNewLabel_1 = new JLabel("Añade ó modifica tu moneda");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 28, 483, 32);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblPasDeLa_2_1 = new JLabel("Id (solo si se modifica)");
		lblPasDeLa_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPasDeLa_2_1.setBounds(59, 223, 174, 25);
		contentPanel.add(lblPasDeLa_2_1);
		
		Choice accion = new Choice();
		accion.addItem("Añadir Moneda");
		accion.addItem("Actualizar Moneda");
		accion.setBounds(59, 296, 369, 18);
		contentPanel.add(accion);
		Choice idsDB = new Choice();
		idsDB.setBounds(243, 224, 185, 24);
		ids = logica.obtenerIds(coneccion);
		for(Integer id : ids) {
			idsDB.addItem(String.valueOf(id));
		}
		idsDB.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
            	accion.select("Actualizar Moneda");
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    try {
                    	Statement st = coneccion.createStatement();
                    	ResultSet rs = st.executeQuery("select * from monedas where id = " + evt.getItem() + " order by id");
                    	while(rs.next()) {
                    		descripcionMonedaInput.setText(rs.getString("descripcion"));
                    		paisMonedaInput.setText(rs.getString("pais"));
                    		abreviaturaMonedaInput.setText(rs.getString("abreviatura"));
                    		estadoMonedaInput.select(rs.getString("est"));
                    	}
                    	st.close();
                    	rs.close();
                    } catch(SQLException e) {
                    	e.printStackTrace();
                    }
                }
            }
        });
		contentPanel.add(idsDB);
		
		JLabel lblNewLabel = new JLabel("¿Qué quieres hacer?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 258, 483, 32);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirmar");
				okButton.setFont(new Font("Arial", Font.PLAIN, 13));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String eleccion = accion.getSelectedItem();
						String nombreDescripcion = descripcionMonedaInput.getText();
						String paisMoneda = paisMonedaInput.getText();
						String abreviaturaMoneda = abreviaturaMonedaInput.getText();
						String estadoMoneda = estadoMonedaInput.getSelectedItem();
						if(nombreDescripcion.length() == 0 || paisMoneda.length() == 0 || abreviaturaMoneda.length() == 0) {
							JOptionPane.showMessageDialog(
				                    null, 
				                    "Debes poner toda la info", 
				                    "Alert", 
				                    JOptionPane.WARNING_MESSAGE);
						} else {
							try {
								String query = (eleccion.equals("Añadir Moneda")) ? "insert into monedas(descripcion, pais, abreviatura, est) values(?,?,?,?)" : "update monedas set descripcion = ?, pais = ?, abreviatura = ?, est = ? where id = " + idsDB.getSelectedItem();
								PreparedStatement ps = coneccion.prepareStatement(query);
								ps.setString(1, nombreDescripcion);
								ps.setString(2, paisMoneda);
								ps.setString(3, abreviaturaMoneda);
								ps.setString(4, estadoMoneda);
								int filasAñadidas = ps.executeUpdate();
								if(filasAñadidas == 1) {
									JOptionPane.showMessageDialog(
						                    null, 
						                    "La información fue " + (eleccion == "Actualizar Moneda" ? "actualizada (id=" + idsDB.getSelectedItem() + ")" : "añadida") + " a la base de datos!", 
						                    "Valido", 
						                    JOptionPane.INFORMATION_MESSAGE);
								}
							} catch(SQLException err) {
								err.printStackTrace();
							}
						}
					}
				});
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
			        coneccion = new Iniciador().conectarDB();
			      } catch (SQLException e) {
			        e.printStackTrace();
			      }
			    }
			  });
		}
	}
}
