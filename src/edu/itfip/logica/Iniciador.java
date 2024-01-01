package edu.itfip.logica;

import java.awt.Choice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.itfip.interfaz.InterfazPrincipal;
import oracle.jdbc.OracleDriver;

public class Iniciador {

	public static void main(String[] args) {
		InterfazPrincipal interfazPrincipal = new InterfazPrincipal();
		interfazPrincipal.setVisible(true);
		interfazPrincipal.setResizable(false);
	}
	public Connection conectarDB() {
		Connection conn = null;
		 try {
			  DriverManager.registerDriver(new OracleDriver());    //This is for loading the odbc driver
			  System.out.println("Driver loaded Successfully"); //el cargador del controlador 
			  conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE?useUnicode=true&characterEncoding=utf8","system", "cristian123"); //connecting to the database
			  System.out.println("Conectado a la base de datos");
			 } catch (SQLException e) {
			  System.out.println("Hubo un problema");
			  
			  
			  // TODO Auto-generated catch block
			  e.printStackTrace();
			 }
		return conn;
	}
	public ArrayList<Integer> obtenerIds(Connection coneccion) {
		ArrayList<Integer> idsObtenidas = new ArrayList<>();
		try {
			String query = "SELECT id FROM monedas";
			Statement st = coneccion.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				idsObtenidas.add(rs.getInt("id"));
			}
			st.close();
			rs.close();
		} catch (SQLException err) {
			
		}
		return idsObtenidas;
	}
	public void llenarCeldasConIds(Connection coneccion, ArrayList<Integer> ids, Choice choiceItem) {
		for(Integer id : ids) {
			String idString = String.valueOf(id);
			try {
				String query = "SELECT descripcion FROM monedas where id = " + idString;
				Statement st = coneccion.createStatement();
				ResultSet rs = st.executeQuery(query);
				while(rs.next()) {
					String valorItem = "[" + idString + "] " + " (" + rs.getString("descripcion") + ")"; 
					choiceItem.addItem(valorItem);
				}
				st.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static String extraerTextoEntreBrackets(String cadenaDeTexto) {
        // Patrón para encontrar el contenido entre
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");

        // Aplicar el patrón al input
        Matcher matcher = pattern.matcher(cadenaDeTexto);

        // Verificar si se encuentra una coincidencia
        if (matcher.find()) {
            // Obtener el contenido entre corchetes (grupo 1)
            return matcher.group(1);
        } else {
            return null;
        }
    }

}
