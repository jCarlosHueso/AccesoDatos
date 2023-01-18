package SpotiflyPremium;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class AccesoDatos {
	private Connection conexion = null;
	private String bd = "spotiflyp", puerto = "3306", servidor = "localhost";
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

	private void ejecutarScript(String fichero) {
		// TODO Auto-generated method stub
		//Cargar el fichero en una variable
		BufferedReader f = null;
		String script="";
		try {
			f = new BufferedReader(new FileReader(fichero));
			String linea;
			while((linea=f.readLine())!=null) {
				
					script+=linea;
				
			}
		
			Statement consulta = conexion.createStatement();
			consulta.executeUpdate(script);			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(f!=null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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

	public Artista obtenerArtista(String nombre, String us) {
		// TODO Auto-generated method stub
		Artista resultado = null;

		try {
			// Creo una consulta con parámetros
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from artista where nombre = ? and usuario = ?");
			// Relleno los parámetros
			consulta.setString(1, nombre);
			consulta.setString(2, us);
			// Ejecuto consulta
			ResultSet r = consulta.executeQuery();
			// Compruebo si se devuelve algo
			// Como máximo va a devolver 1
			if (r.next()) {
				resultado = new Artista(r.getInt(1), r.getString(2), r.getString(3),
						// new java.util.Date(r.getDate(4).getTime()),
						r.getDate(4), r.getBoolean(5));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearArtista(Artista a, String us) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"insert into artista values (null,?,?,?,true,?)");
			consulta.setString(1, a.getNombre());
			consulta.setString(2, a.getGenero());
			consulta.setDate(3, new java.sql.Date(a.getFechaL().getTime()));
			consulta.setString(4, us);
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

	public ArrayList<Artista> obtenerArtistas(String us) {
		// TODO Auto-generated method stub
		ArrayList<Artista> resultado = new ArrayList<>();

		Statement consulta;
		try {
			consulta = conexion.createStatement();
			//Ejemplo de lo que no se debe hacer
			//Mejor PreparedStatement
			ResultSet r = consulta.executeQuery("select * from artista "
					+ "where usuario = '"+us+"'");
			while (r.next()) {
				resultado.add(new Artista(r.getInt(1), r.getString(2), r.getString(3),
						// new java.util.Date(r.getDate(4).getTime()),
						r.getDate(4), r.getBoolean(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public Artista obtenerArtista(int id,String us) {
		// TODO Auto-generated method stub
		Artista resultado = null;

		try {
			// Creo una consulta con parámetros
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from artista where id = ? and usuario = ?");
			// Relleno los parámetros
			consulta.setInt(1, id);
			consulta.setString(2, us);
			// Ejecuto consulta
			ResultSet r = consulta.executeQuery();
			// Compruebo si se devuelve algo
			// Como máximo va a devolver 1
			if (r.next()) {
				resultado = new Artista(r.getInt(1), r.getString(2), r.getString(3),
						// new java.util.Date(r.getDate(4).getTime()),
						r.getDate(4), r.getBoolean(5));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Album obtenerAlbum(Artista a, String titulo) {
		// TODO Auto-generated method stub
		Album resultado = null;
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(
					"select * from album where titulo=? and artista=?");
			sentencia.setString(1, titulo);
			sentencia.setInt(2, a.getId());
			
			ResultSet r = sentencia.executeQuery();
			if(r.next()) {
				resultado = new Album(r.getInt(1), titulo, a, r.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearAlbum(Album al) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(
					"insert into album values (null,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, al.getTitulo());
			sentencia.setInt(2, al.getArtista().getId());
			sentencia.setInt(3, al.getAnio());
			
			int r = sentencia.executeUpdate();
			if(r==1) {
				resultado=true;
				ResultSet idS = sentencia.getGeneratedKeys();
				if(idS.next()) {
					//Rellenamos el id del álbum que se acaba de insertar
					al.setId(idS.getInt(1));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Album> obtenerAlbumes(String us) {
		// TODO Auto-generated method stub
		ArrayList<Album> resultado = new ArrayList<>();
	
		try {
			Statement sentencia = conexion.createStatement();
			//Otro ejemplo de lo que no se debe hacer
			ResultSet r = sentencia.executeQuery(
					"select * from album al inner join artista ar "
					+ "on al.artista = ar.id "
					+ "where ar.usuario = '"+us+"'");
			while(r.next()) {
				Album al = new Album(r.getInt(1), 
						r.getString(2), 
						new Artista(r.getInt(3), r.getString(6), r.getString(7), 
								    r.getDate(8), r.getBoolean(9)), 
						r.getInt(4));
				resultado.add(al);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Album obtenerAlbum(int album,String us) {
		// TODO Auto-generated method stub
		Album resultado = null;
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(
					"select * from album inner join artista "
					+ "on album.artista = artista.id "
					+ "where album.id=? and artista.usuario = ?");
			sentencia.setInt(1, album);
			sentencia.setString(2, us);
			ResultSet r = sentencia.executeQuery();
			if(r.next()) {
				resultado = new Album(r.getInt(1), r.getString(2), 
						new Artista(r.getInt(3), 
								    r.getString(6), 
								    r.getString(7), 
								    r.getDate(8), 
								    r.getBoolean(9)), 
						r.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearCancion(Cancion c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"insert into cancion values (?,?,?)");
			consulta.setString(1, c.getTitulo());
			consulta.setInt(2, c.getAlbum().getId());
			consulta.setFloat(3, c.getValoracion());
			int filas = consulta.executeUpdate();
			if(filas==1) {
				resultado= true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean crearCancionYAlbum(Cancion c) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		
		//Tenemos que hacer una transacción porque hay
		//hacer inserts en dos tablas
		//Es la forma de asegurar que o se hacen los dos
		//o no se hace ninguno
		//Marcar el inicio de la transacción
		try {
			conexion.setAutoCommit(false);
			//Crear álbum
			PreparedStatement consulta = conexion.prepareStatement(
					"insert into album values(null,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			consulta.setString(1, c.getAlbum().getTitulo());
			consulta.setInt(2, c.getAlbum().getArtista().getId());
			consulta.setInt(3, c.getAlbum().getAnio());
			int filas = consulta.executeUpdate();
			if(filas==1) {
				//REcuperar el id del album creado
				ResultSet ids =  consulta.getGeneratedKeys();
				if(ids.next()) {
					c.getAlbum().setId(ids.getInt(1));
					//Insert de la canción
					consulta = conexion.prepareStatement(
							"insert into cancion values (?,?,?)");
					consulta.setString(1, c.getTitulo());
					consulta.setInt(2, c.getAlbum().getId());
					consulta.setFloat(3, c.getValoracion());
					filas = consulta.executeUpdate();
					if(filas==1) {
						//Guardamos cambios de forma permanente en la BD
						conexion.commit();
						resultado=true;
					}
					else {
						conexion.rollback();
					}
				}
				else {
					conexion.rollback();
				}
				
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				//Si hay algún error deshacemos lo que se ha hecho
				conexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Object[]> infoArtista(int id) {
		// TODO Auto-generated method stub
		ArrayList<Object[]> resultado = new ArrayList<>();
		
		try {
			//Hacemos left outer join para que saque lo álbumes sin canciones
			PreparedStatement consulta = conexion.prepareStatement(
					"select a.titulo, count(c.titulo), avg(c.valoracion) "
					+ "from album a left outer join cancion c "
					+ "on a.id = c.album "
					+ "where a.artista = ? "
					+ "group by a.id");
			consulta.setInt(1, id);
			ResultSet r = consulta.executeQuery();
			while(r.next()) {
				//Creo un array de objetos con 3 objetos: Título, count, avg 
				Object[] o = {r.getString(1), r.getInt(2), r.getFloat(3)};
				resultado.add(o);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Cancion> obtenerCanciones(int id, String us) {
		// TODO Auto-generated method stub
		ArrayList<Cancion> resultado = new ArrayList<>();
		try {
			PreparedStatement sentencia = conexion.prepareStatement(
					"select * from cancion c inner join album al"
					+ " on c.album = al.id "
					+ " inner join artista a "
					+ "   on al.artista = a.id "
					+ "where al.id = ? and ar.usuario = ?");
			sentencia.setInt(1, id);
			sentencia.setString(2, us);
			ResultSet r = sentencia.executeQuery();			
			while (r.next()) {
				Cancion c = new Cancion(r.getString(1), 
						new Album(r.getInt(2), r.getString(5), 
								new Artista(r.getInt(6), r.getString(9), 
										    r.getString(10),
										    r.getDate(11), 
										    r.getBoolean(12)), 
								r.getInt(7)), 
						r.getFloat(3));
				resultado.add(c);
			}
	
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return resultado;
	}

	public boolean borrarAlbum(int id) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"delete from album where id = ?");
			consulta.setInt(1, id);
			int filas = consulta.executeUpdate();
			if(filas==1) {
				resultado=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarAlbumCanciones(int id) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			conexion.setAutoCommit(false);
			//Borrar primero canciones
			PreparedStatement consulta = conexion.prepareStatement(
					"delete from cancion "
					+ "where album = ?");
			consulta.setInt(1, id);
			int filas = consulta.executeUpdate();
			if(filas>0) {
				if(borrarAlbum(id)) {
					conexion.commit();
					resultado = true;
				}
				else {
					conexion.rollback();
				}
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean dejarSeguir(int id) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"update artista set seguir = false "
					+ "where id = ?");
			consulta.setInt(1, id);
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

	public String login(String usuario, String psw) {
		// TODO Auto-generated method stub
		String resultado="NE";
		
		try {
			CallableStatement funcion = 
					conexion.prepareCall("{? = call login(?,?)}");
			funcion.setString(2, usuario);
			funcion.setString(3, psw);
			//¡¡¡RELLENAR PARÁMETRO DE SALIDA!!!
			funcion.registerOutParameter(1, Types.VARCHAR);
			funcion.executeUpdate();
			//Retornamos lo que devuelve la función
			return funcion.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Usuario obtenerUsuario(String usuario) {
		// TODO Auto-generated method stub
		Usuario resultado = null;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from usuario where usuario = ?");
			consulta.setString(1, usuario);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new  Usuario();
				resultado.setUsuario(r.getString(1));
				resultado.setTipo(r.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearUsuario(Usuario u) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"insert into usuario values (?,sha2(?,512),?)");
			consulta.setString(1, u.getUsuario());
			consulta.setString(2, u.getUsuario());
			consulta.setString(3, u.getTipo());
			int filas = consulta.executeUpdate();
			if(filas==1) {
				resultado=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Usuario> obtenerUsuarios(String tipo) {
		// TODO Auto-generated method stub
		ArrayList<Usuario> resultado = new ArrayList<>();
		
		try {
			PreparedStatement consulta = 
					conexion.prepareStatement("select * from usuario "
							+ "where tipo = ?");
			consulta.setString(1, tipo);
			ResultSet r = consulta.executeQuery();
			while(r.next()) {
				Usuario u = new Usuario();
				u.setUsuario(r.getString(1));
				u.setTipo(r.getString(3));
				resultado.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public String borrarUsuario(Usuario usLogeado, Usuario u) {
		// TODO Auto-generated method stub
		String resultado = "Error, al borrar el usuario";
		
		try {
			CallableStatement rutina = conexion.prepareCall(
					"{call borrarUsuario(?,?)}");
			rutina.setString(1, usLogeado.getUsuario());
			rutina.setString(2, u.getUsuario());
			
			ResultSet r = rutina.executeQuery();
			if(r.next()) {
				return r.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

}