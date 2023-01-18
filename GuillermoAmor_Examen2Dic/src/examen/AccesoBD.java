package examen;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

public class AccesoBD {
	
	private String url = "jdbc:mysql://localhost:3306/mensajes";
	private String usuario = "root";
	private String pwd = "root";
	private Connection cnx;

	public AccesoBD() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			cnx = DriverManager.getConnection(url, usuario, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getCnx() {
		return cnx;
	}
	
	public void setCnx(Connection cnx) {
		this.cnx = cnx;
	}

	public int login(int codigo, String ps) {
		int resultado = 0;
		
		try {
			CallableStatement funcion = 
					cnx.prepareCall("{? = call login(?,?)}");
			funcion.setInt(2, codigo);
			funcion.setString(3, ps);
			funcion.registerOutParameter(1, Types.INTEGER);
			funcion.executeUpdate();
			//Retornamos lo que devuelve la función
			resultado = funcion.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Empleado obtenerEmpleado(int codigo) {
		Empleado resultado = null;

		try {
			PreparedStatement c = cnx.prepareStatement(
					"select * from empleado where codigo = ?");
			c.setInt(1, codigo);
			ResultSet r = c.executeQuery();
			if (r.next()) {
				resultado = new Empleado(r.getInt(1) , 
										 r.getString(2), 
										 r.getString(3), 
										 r.getString(4), 
										 new java.util.Date(r.getDate(5).getTime()), 
										 obtenerDepartamento(r.getInt(6)), 
										 r.getBoolean(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public Departamento obtenerDepartamento(int numero) {
		Departamento resultado = null;
		
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"select * from departamento where numero = ?");
			consulta.setInt(1, numero);			

			ResultSet r = consulta.executeQuery();			

			if(r.next()) {
				resultado = new Departamento(r.getInt(1), r.getString(2));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		return resultado;
	}

	public boolean modificarClave(Empleado emLogeado, String nuevaC) {
		boolean resultado = false;
		
		try {
			PreparedStatement c = cnx.prepareStatement(
					"update empleado "
					+ "set ps = sha2(?, 0), cambiarPs = false "
					+ "where codigo = ?");
			c.setString(1, nuevaC);
			c.setInt(2, emLogeado.getCodigo());
			
			int filas = c.executeUpdate();
			
			if(filas == 1) {
				resultado = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Departamento> obtenerDepartamentos() {
		ArrayList<Departamento> resultado = new ArrayList<>();
		
		try {

			Statement consulta = cnx.createStatement();

			ResultSet r = consulta.executeQuery("select * from departamento");

			while(r.next()) {
				resultado.add(new Departamento(r.getInt(1), r.getString(2)));
			}			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Empleado> obtenerEmpleadosDepa(Departamento dep) {
		ArrayList<Empleado> resultado = new ArrayList<>();
		
		try {
			PreparedStatement c = cnx.prepareStatement(
					"select * "
					+ "from empleado "
					+ "where departamento = ?");
			c.setInt(1, dep.getNumero());
			
			ResultSet r = c.executeQuery();
			
			while(r.next()) {
				resultado.add(new Empleado(
						r.getInt(1), 
						r.getString(2), 
						r.getString(3), 
						r.getString(4), 
						new java.util.Date(r.getDate(5).getTime()), 
						obtenerDepartamento(r.getInt(6)), 
						r.getBoolean(7)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean enviarMensaje(Mensaje m, Empleado emLogeado, Departamento dep, String asu, String tex,
			ArrayList<Empleado> empleadosDep) {
		boolean resultado = false;
		
		try {
			cnx.setAutoCommit(false);
			
			PreparedStatement c = cnx.prepareStatement(
					"insert into mensaje values"
					+ "(null, ?, ?, ?, curdate(), ?)", 
					Statement.RETURN_GENERATED_KEYS);
			c.setInt(1, emLogeado.getCodigo());
			c.setInt(2, dep.getNumero());
			c.setString(3, asu);
			c.setString(4, tex);
			
			int filas = c.executeUpdate();
			
			if(filas == 1) {
				
				ResultSet idMens =  c.getGeneratedKeys();
				
				if(idMens.next()) {
					m.setId(idMens.getInt(1));
				}
				
				// Si se inserta el mensaje, seguimos
				// Ahora hay que insertar registros en la tabla para
				String values = "";
				String query;
				boolean noHecho = true;
				for(Empleado e: empleadosDep) {
					
					c = cnx.prepareStatement(
							"insert into para values(?, ?, false)");
					c.setInt(1, m.getId());
					c.setInt(2, e.getCodigo());
							
					int fil = c.executeUpdate();
					
					if(fil != 1) {
						
						cnx.rollback();
						return false;
						
					}

				}
				
				// Si ha llegado hasta aquí es que todo
				// se ha hecho bien
				cnx.commit();
				resultado = true;
				
			} else {
				
				cnx.rollback();
				
			}
			
		} catch (SQLException e) {
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Mensaje> obtenerMensajesEmp(Empleado emLogeado) {
		ArrayList<Mensaje> resultado = new ArrayList<>();
		
		try {
			PreparedStatement c = cnx.prepareStatement(
					"select * "
					+ "from mensaje "
					+ "where paraDepartamento = ?");
			c.setInt(1, emLogeado.getDepartamento().getNumero());
			
			ResultSet r = c.executeQuery();
			
			while(r.next()) {
				resultado.add(
						new Mensaje(
								r.getInt(1), 
								emLogeado, 
								emLogeado.getDepartamento(), 
								r.getString(4), 
								new java.util.Date(r.getDate(5).getTime()), 
								r.getString(6)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean leerMensajes(Empleado em, ArrayList<Mensaje> mensajes) {
		boolean resultado = false;
		
		try {
			
			PreparedStatement c = cnx.prepareStatement(
				"update para "
				+ "set leido = true "
				+ "where paraEmpleado = ? and "
				+ "leido = false");
			c.setInt(1, em.getCodigo());
				
			int filas = c.executeUpdate();
			
			if(filas >= 1) {
				resultado = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	
	
}
