package pe.edu.tecsup.tienda.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexionBD {
		
	public static Connection obtenerConexion() throws SQLException {
		
		Connection con = null;
	
		try {
			
			Context initContext = new InitialContext();
			
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			
			DataSource datasource = (DataSource) envContext.lookup("jdbc/tienda");
			
			con = datasource.getConnection();
			
		} catch (NamingException e) {
			e.printStackTrace();	
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		return con;
	}

	/*
	public static Connection obtenerConexion() throws SQLException {
		
		Connection con = null;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}*/
	
}
