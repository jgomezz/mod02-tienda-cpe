package pe.edu.tecsup.tienda.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	private static final String URL = "jdbc:mysql://localhost/tienda?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	public static Connection obtenerConexion() throws SQLException {
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
