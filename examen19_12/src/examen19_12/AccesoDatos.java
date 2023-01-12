package examen19_12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccesoDatos {

	private Connection conexion = null;
	private String bd = "FibraNaval", puerto = "3306", servidor = "localhost";
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

	public ArrayList<Cliente> obtenerClientes() {

		ArrayList<Cliente> resultado = new ArrayList<>();

		try {
			PreparedStatement statement = conexion.prepareStatement("select * from cliente");

			ResultSet r = statement.executeQuery();

			while (r.next()) {

				Cliente c = new Cliente();
				c.setIdCliente(r.getInt(1));
				c.setDni(r.getString(2));
				c.setNombre(r.getString(3));
				c.setFechaNacimiento(r.getDate(4));
				c.setDireccion(r.getString(5));
				c.setCodigoPostal(r.getInt(6));

				resultado.add(c);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<Servicio> obtenerServiciosCliente(int idCliente){
		ArrayList<Servicio> resultado = new ArrayList<>();
try {
		PreparedStatement statement = conexion.prepareStatement("select * from servicio left outer join"
				+ " serviciocontratado on servicio.ids = serviciocontratado.servicio"
				+ " where (serviciocontratado.servicio is null) or"
				+ " (serviciocontratado.fechabaja is not null and cliente = ?)");
		
		statement.setInt(1, idCliente);
		
		ResultSet r = statement.executeQuery();
		
		while(r.next()) {
		Servicio s = new Servicio();
		
		s.setIdServicio(r.getInt(1));
		s.setNombre(r.getString(2));
		s.setPrecio(r.getFloat(3));
		
		resultado.add(s);
		}
		
}catch(SQLException e) {
	e.printStackTrace();
}
		
		return resultado;
	}

	public boolean contratarServicio(int idServicio, int idCliente) {
boolean resultado=false;
		
PreparedStatement consulta;
try {
	consulta = conexion.prepareStatement(
			"insert into serviciocontratado values(?,?,curdate(),null)");
	consulta.setInt(1, idCliente);
	consulta.setInt(2, idServicio);
	int filas = consulta.executeUpdate();
	if(filas==1) {
		resultado = true;
	}
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


		
		return resultado;
	}

	public boolean borrarCliente(int idCliente) {
		boolean resultado=false;
		
		
		try {
			
			PreparedStatement consulta = conexion.prepareStatement(
				"delete from cliente where idc = ? ");
			consulta.setInt(1, idCliente);
			int filas = consulta.executeUpdate();
			if(filas>0) {
				resultado=true;
			}else {
				System.out.println("error al borrar el cliente");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}

	public boolean servicioContratado(int idCliente) {

		boolean resultado=false;
		
		try {
			PreparedStatement statement = conexion.prepareStatement
					("select * from serviciocontratado where cliente = ?");
			
			statement.setInt(1, idCliente);

			
			ResultSet r = statement.executeQuery();
			
			if(!r.next()) {
				resultado=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

}
