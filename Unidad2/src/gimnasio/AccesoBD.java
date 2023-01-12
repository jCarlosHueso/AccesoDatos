package gimnasio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesoBD {
	private String url = "jdbc:mysql://localhost:3306/gimnasio";
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

	public Usuario obtenerUsuario(String user, String password) {
		Usuario resultado = null;
		
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"select * from usuarios u left outer join cliente c "
					+ "on u.usuario = c.usuario "
					+ "where u.usuario=? and u.clave=sha2(?,0) "
					+ "and (baja is null or baja = false)");
			consulta.setString(1, user);
			consulta.setString(2, password);
			
			ResultSet rs = consulta.executeQuery();
			if(rs.next()) {
				resultado = new Usuario(rs.getString(1), rs.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Cliente obtenerCliente(String identificador, String tipo)
	{
		try 
		{
			PreparedStatement consulta;
			
			if (tipo.equals("N"))
				consulta = cnx.prepareStatement("select * from cliente where usuario = ?");
			else
				consulta = cnx.prepareStatement("select * from cliente where dni = ?");
			
			consulta.setString(1, identificador);
			
			ResultSet rs = consulta.executeQuery();
			
			if (rs.next())
				return new Cliente(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getInt(1));
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean crearCliente(Cliente c)
	{
		try
		{
			cnx.setAutoCommit(false);
			
			PreparedStatement consulta = cnx.prepareStatement(
					"insert into usuarios values (?, sha2(?, 0), 'C')");
			
			consulta.setString(1, c.getUsuario());
			consulta.setString(2, c.getUsuario());
			
			if (consulta.executeUpdate() == 1)
			{
				consulta = cnx.prepareStatement(
						"insert into cliente values (null, ?, ?, ?, ?, ?, false)");
				
				consulta.setString(1, c.getUsuario());
				consulta.setString(2, c.getDni());
				consulta.setString(3, c.getApellidos());
				consulta.setString(4, c.getNombre());
				consulta.setString(5, c.getTelefono());
				
				if (consulta.executeUpdate() == 1)
				{
					cnx.commit();
					return true;
				}
				else
					cnx.rollback();
			}
		} 
		catch (SQLException e)
		{
			try
			{
				cnx.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
		return false;
	}

	public ArrayList<Cliente> obtenerClientes()
	{
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try
		{
			Statement consulta = cnx.createStatement();
			
			ResultSet rs = consulta.executeQuery("select * from cliente");
			
			while (rs.next())
				clientes.add(new Cliente(rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getString(6),
										rs.getBoolean(7),
										rs.getInt(1)));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return clientes;
	}

	public Actividad obtenerActividad(String nombre) {
		String cadena = "select * from actividad where nombre=?";
		try {
			PreparedStatement s = cnx.prepareStatement(cadena);
			s.setString(1, nombre);
			ResultSet res = s.executeQuery();
			while(res.next()) {
				Actividad a = new Actividad(res.getInt(1),
											res.getString(2),
											res.getFloat(3),
											res.getString(4));
				return a;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean crearActividad(Actividad a) {
		String cadena = "insert into actividad values(null, ?,?,default)";
		try {
			PreparedStatement s = cnx.prepareStatement(cadena);
			s.setString(1, a.getNombre());
			s.setFloat(2, a.getCoste());
			int control = s.executeUpdate();
			if(control>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Actividad> obtenerActividades() {
		ArrayList<Actividad> res = new ArrayList<>();
		String cadena = "select * from actividad";
		try {
			Statement s = cnx.createStatement();
			ResultSet r = s.executeQuery(cadena);
			while(r.next()) {
				res.add(new Actividad( r.getInt(1),
										r.getString(2),
										r.getFloat(3),
										r.getString(4)));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Actividad> obtenerActividades(Usuario uLogeado) {
		// TODO Auto-generated method stub
		ArrayList<Actividad> resultado = new ArrayList<>();
		
		try {
			PreparedStatement sentencia = cnx.prepareStatement("select * from "
					+ "participa p inner join actividad a on p.actividad_id = a.id "
					+ "inner join cliente c on c.id = p.cliente_id "
					+ "where c.usuario = ?");
			
			sentencia.setString(1, uLogeado.getId());
			
			ResultSet rs = sentencia.executeQuery();
			while(rs.next()) {
				resultado.add(new Actividad(rs.getInt(3), rs.getString(4),
						rs.getFloat(5), rs.getString(6)));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Actividad obtenerActividad(int idA) {
		// TODO Auto-generated method stub
		Actividad resultado = null;
		
		
		
		return resultado;
	}

	public boolean inscribirActividad(Usuario uLogeado, Actividad a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"insert into actividad values(?, "
					+ "select id from cliente where usuario = ?)");
			consulta.setInt(1, a.getId());
			consulta.setString(2, uLogeado.getId());
			
			int filas = consulta.executeUpdate();
			if(filas == 1) {
				resultado = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
