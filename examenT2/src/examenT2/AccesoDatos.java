package examenT2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccesoDatos {
	private Connection conexion = null;
	private String bd = "mensajes",
			puerto = "3306",
			servidor = "localhost";
	private String url = "jdbc:mysql://" + servidor + ":" + puerto + "/" + bd;
	private String us = "root";
	private String ps = "root";

	public AccesoDatos() {
		try {
			// Cargar el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Crear la conexión
			conexion = DriverManager.getConnection(url, us, ps);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cerrar() {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Connection getConexion() {
		return conexion;
	}


	public int login(int us2, String ps2) {
int resultado = 0;
		try {
			
			PreparedStatement statement = conexion.prepareStatement("select login(?,?)");
			
			statement.setInt(1,us2);
			statement.setString(2,ps2);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				resultado= rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean cambiarPassword(String nuevaContra, int us2) {
		boolean resultado = false;
		
		try {
			PreparedStatement statement = conexion.prepareStatement
					("update empleado set ps = sha2(?,0), cambiarPs=? where codigo = ?");
			
			statement.setString(1, nuevaContra);
			statement.setInt(2, 0);
			statement.setInt(3, us2);
			
			int k=statement.executeUpdate();
			if(k==1) {
				resultado=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<departamento> mostarDepartamentos() {
	
		ArrayList<departamento> resultado= new ArrayList<>();
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement("select * from departamento");
			
ResultSet rs = sentencia.executeQuery();

while(rs.next()) {
departamento d = new departamento();	
d.setNumero(rs.getInt(1));
d.setNombre(rs.getString(2));
resultado.add(d);
}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean enviarMensaje(int idDept, String asunto, String cuerpo) {
		boolean resultado=false;
		
		
		return resultado;
	}

	public ArrayList<mensaje> leerMensajes(int usLogueado) {
		
		ArrayList<mensaje> resultado = new ArrayList<>(); 

		try {
			
			PreparedStatement statement = conexion.prepareStatement 
					("select * from mensaje where deEmpleado = ?");
			 
			statement.setInt(1, usLogueado);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				mensaje msg = new mensaje(
						rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),
						
						rs.getString(6), 
						new java.util.Date(rs.getDate(5).getTime()));
				resultado.add(msg);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return resultado;
	}

	public boolean marcarMensajesLeidos(int usLogueado) {
boolean resultado=false;
int departamento;
try {
	PreparedStatement statement = conexion.prepareStatement("select departamento from mensaje where deEmpleado = ? ");
	
	statement.setInt(1, usLogueado);
	ResultSet rs = statement.executeQuery();
	while(rs.next()) {
		
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

		return resultado;
	}

	

}