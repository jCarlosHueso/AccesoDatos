package Alumnos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			//Conectarnos a la base de datos
			//Cargar el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Crear el conexión
			String url="jdbc:mysql://localhost:3306/alumnos";
			String usuario="root";
			String clave="root";
			
			Connection conexion = 
					DriverManager.getConnection(url, usuario, clave);
			
			
			DatabaseMetaData datos = conexion.getMetaData();
			
			
			
			System.out.println("Tipo de SGBD:" + datos.getDatabaseProductName());
			System.out.println("Versión:"+datos.getDatabaseProductVersion());
			System.out.println("URL:"+datos.getURL());
			System.out.println("Usuario:"+datos.getUserName());
			
			System.out.println("Tablas:");
			ResultSet tablas = datos.getTables("alumnos", null, null, null);
			while(tablas.next()) {
				System.out.println("Nombre tabla:"+tablas.getString(3));
			}
			/*
			//Insertar un alumno - Consulta con parámetros
			PreparedStatement sentencia = conexion.prepareStatement(
					"insert into alumnos values (null,?, ?)"); 
			//REllenamos parámetros
			sentencia.setString(1, "Pepe Pérez");
			sentencia.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			//Ejecutar sentencia
			int filas = sentencia.executeUpdate();
			if(filas==1) {
				System.out.println("Alumno insertado");
			}*/
			
			//Mostrar datos
			Statement sentencia = conexion.createStatement();
			ResultSet r = sentencia.executeQuery("select * from alumnos");
			while(r.next()) {
				System.out.println("Id:" + r.getInt(1) + 
					"\tNombre:" + r.getString(2) +
					"\tFecha:" + r.getString(3)); //¡¡¡ FECHA COMO STRING !!!
			}
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
