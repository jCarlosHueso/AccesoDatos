package ejercicio1;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AccesoDatos {
	private Connection conexion = null;
	private String bd = "ServiHogar", puerto = "3306", servidor = "localhost";
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

	public Cliente obtenerClienteNif(String nif) {
	Cliente resultado = null;
	
	
	try {
		// Creo una consulta con parámetros
		PreparedStatement consulta = conexion.prepareStatement(
				"select nombre,telefono from cliente where nif = ?");
		// Relleno los parámetros
		consulta.setString(1, nif);
		
		// Ejecuto consulta
		ResultSet r = consulta.executeQuery();
		// Compruebo si se devuelve algo
		// Como máximo va a devolver 1
		if (r.next()) {
	resultado = new Cliente(nif,r.getString(1),r.getString(2));
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	
	}
	return resultado;

	}

	public boolean crearCliente(Cliente c) {
		boolean resultado=false;
	try {
		
		PreparedStatement consulta = conexion.prepareStatement(
				"insert into cliente values (?,?,?)");
		consulta.setString(1, c.getNif());
		consulta.setString(2, c.getNombre());
		consulta.setString(3,c.getTelefono() );

		int filas = consulta.executeUpdate();
		if (filas == 1) {
			resultado = true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		return resultado;
	}

	public boolean addPresupuesto(Presupuesto p) {
		boolean resultado=false;
			
			
		try {
			
			PreparedStatement consulta = conexion.prepareStatement(
					"insert into presupuesto values (null,?,?)");
			consulta.setDate(1,new java.sql.Date(p.getFecha().getTime()));
			consulta.setString(2, p.getCliente().getNif());
			

			int filas = consulta.executeUpdate();
			if (filas == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return resultado;
	}

	public Presupuesto obtenerIdPresupuesto(Presupuesto pre) {
		Presupuesto resultado=null;
		try {
			// Creo una consulta con parámetros
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from presupuesto where cliente = ? and fecha = ?");
			// Relleno los parámetros
			
			consulta.setString(1, pre.getCliente().getNif());
			consulta.setDate(2, new java.sql.Date(pre.getFecha().getTime()));
			
			// Ejecuto consulta
			ResultSet r = consulta.executeQuery();
			// Compruebo si se devuelve algo
			// Como máximo va a devolver 1
			if (r.next()) {
		resultado = new Presupuesto(r.getInt(1),r.getDate(2),obtenerClienteNif(r.getString(3)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		}
	return resultado;	
	}

	public ArrayList<Servicio> obtenerServicios() {
		ArrayList<Servicio>resultado = new ArrayList<>();
		
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet r = sentencia.executeQuery(
					"select * from servicio");
			while(r.next()) {
				Servicio s = new Servicio
						(r.getString(1),r.getString(2),r.getFloat(3),r.getFloat(4));
				resultado.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public Servicio obtenerServicio(String codigo) {
		Servicio resultado=null;
		
		try {
			// Creo una consulta con parámetros
			PreparedStatement consulta = conexion.prepareStatement(
					"select descripcion,horaservicio,horaempleado from servicio where codigo = ?");
			// Relleno los parámetros
			consulta.setString(1, codigo);
			
			// Ejecuto consulta
			ResultSet r = consulta.executeQuery();
			// Compruebo si se devuelve algo
			// Como máximo va a devolver 1
			if (r.next()) {
		resultado = new Servicio(codigo,r.getString(1),r.getFloat(2),r.getFloat(3));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		}
		
		return resultado;
	}

	public boolean crearDealle(Detalle_Presupuesto d) {
		boolean resultado=false;
	try {
		
		PreparedStatement consulta = conexion.prepareStatement(
				"insert into detalle_presupuesto values (?,?,?,?,?)");
		consulta.setInt(1, d.getPresupuesto().getCodigo());
		consulta.setString(2, d.getServicio().getCodigo());
		consulta.setString(3,d.getDescripcion());
		consulta.setInt(4,d.getHoras());
		consulta.setFloat(5,d.getImporte());

		int filas = consulta.executeUpdate();
		if (filas == 1) {
			resultado = true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		return resultado;
	
	}

	public ArrayList<Detalle_Presupuesto> obtenerDetalles(Presupuesto presupuesto) {
		
		ArrayList<Detalle_Presupuesto>resultado = new ArrayList<>();
		
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from detalle_presupuesto where presupuesto=?");
			
			consulta.setInt(1, presupuesto.getCodigo());
			
			ResultSet r = consulta.executeQuery();
			
			while(r.next()) {
				Detalle_Presupuesto deta = new Detalle_Presupuesto();
				deta.setPresupuesto(presupuesto);
				deta.setServicio(obtenerServicio(r.getString(2)));
				deta.setDescripcion(r.getString(3));
				deta.setHoras(r.getInt(4));
				deta.setImporte(r.getFloat(5));
				resultado.add(deta);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<Fichero> obtenerInfoFichero() {
ArrayList<Fichero>resultado = new ArrayList<>();
		
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select servicio, sum(horas),sum(importe) from detalle_presupuesto"
					+ " group by servicio");

			ResultSet r = consulta.executeQuery();
			
			while(r.next()) {
				Fichero f = new Fichero();
				f.setServicio(r.getString(1));
				f.setHoras(r.getLong(2));
				f.setTotal(r.getFloat(3));
				f.setBaja(false);
				
				resultado.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	
}
