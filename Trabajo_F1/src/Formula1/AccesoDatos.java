package Formula1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	
	
	
	
	
	
	
	}