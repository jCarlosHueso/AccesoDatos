package instituto;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesoDatos {
	private Connection cnx = null;
	
	private String url="jdbc:postgresql://database-1.c6ponazvhiex.us-east-1.rds.amazonaws.com:5432/instituto";
	private String us="postgres";
	private String ps="postgres";

	public AccesoDatos() {
		try {
			Class.forName("org.postgresql.Driver");
			cnx=DriverManager.getConnection(url, us, ps);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getCnx() {
		return cnx;
	}

	public void setCnx(Connection cnx) {
		this.cnx = cnx;
	}
	public void cerrar() {
		if(cnx!=null) {
			try {
				cnx.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Alumno> obtenerAlumnos() {
		ArrayList<Alumno> resultado=new ArrayList<>();
		
		try {
			Statement consulta = cnx.createStatement();
			ResultSet r = consulta.executeQuery(""
					+ "select id,nombre,(dir).*"
					+ ",fechaN,numExpe,curso from alumno");
			while(r.next()) {
				
				Alumno a = new Alumno(r.getInt(1),r.getString(2),new Direccion(r.getString(3),
						r.getString(4),r.getInt(5)),r.getDate(6),r.getInt(7),r.getString(8));
				resultado.add(a);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}

	public Alumno obtenerAlumno(int id) {
		// TODO Auto-generated method stub
		Alumno resultado = null;
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"select id, nombre, "
					+ "(dir).*, "
					+ "fechaN, numExpe, curso "
					+ "from alumno where id = ?");
			consulta.setInt(1, id);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Alumno(r.getInt(1), 
						r.getString(2), 
						new Direccion(r.getString(3), r.getString(4), 
						r.getInt(5)), 
						r.getDate(6), r.getInt(7), r.getString(8));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Asignatura> obtenerAsignaturas() {
		// TODO Auto-generated method stub
		ArrayList<Asignatura> resultado = new ArrayList();
		try {
			Statement consulta = cnx.createStatement();
			ResultSet r = consulta.executeQuery("select * "
					+ "from asignatura");
			while(r.next()) {
				Asignatura a = new Asignatura(r.getString(1), 
						r.getString(2));
				resultado.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Asignatura obtenerAsignatura(String asig) {
		// TODO Auto-generated method stub
		Asignatura resultado = null;
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"select * "
					+ "from asignatura where codigo = ?");
			consulta.setString(1, asig);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Asignatura(r.getString(1), r.getString(2));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Nota obtenerNota(Alumno al, Asignatura as) {
		
		Nota resultado = null;
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"select * "
					+ "from nota where alumno = ? and asignatura = ?");

			consulta.setInt(1, al.getId());
			consulta.setString(2, as.getCodigo());
			ResultSet r = consulta.executeQuery();
			
			if(r.next()) {
				resultado = new Nota();
				resultado.setAlumno(al);
				resultado.setAsig(as);
				
				Array tmp = r.getArray(3);
				String[][] tmp2 = (String[][])tmp.getArray();
				
					for (String[] strings : tmp2) {
						resultado.getNotas().add(strings);
					}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
		
	}
	
}