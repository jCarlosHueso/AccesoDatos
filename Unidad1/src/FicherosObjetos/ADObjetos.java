package FicherosObjetos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import FicherosBinarios.Album;

public class ADObjetos {

	private String nombreF = "canciones.obj";
	private String nombreTmp = "canciones.tmp";

	public ADObjetos() {

	}

	public ArrayList<Cancion> obtenerCanciones() {
		
		ArrayList<Cancion> resultado = new ArrayList<>();

		ObjectInputStream f = null;

		try {
			f = new ObjectInputStream(new FileInputStream(nombreF));
			while (true) {
				// Leemos objeto
				Cancion c = (Cancion) f.readObject();
				resultado.add(c);
			}
		} catch (EOFException e) {

		} catch (FileNotFoundException e) {
			
			System.out.println("No hay canciones");
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public Cancion obtenerCancion(String titulo, Album al) {
		
		Cancion resultado = null;

		ObjectInputStream f = null;

		try {
			f = new ObjectInputStream(new FileInputStream(nombreF));
			while (true) {
				// Leemos objeto
				Cancion c = (Cancion) f.readObject();
				if (c.getTitulo().equalsIgnoreCase(titulo) && c.getAlbum() == al) {
					return c;
				}
			}
		} catch (EOFException e) {
			// 
		} catch (FileNotFoundException e) {
			
			System.out.println("No hay canciones");
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public int obtenerUltimoID() {
		
		int resultado = 0;
		ObjectInputStream f = null;
		Cancion c = null;

		try {
			f = new ObjectInputStream(new FileInputStream(nombreF));
			while (true) {
				// Leemos objeto
				c = (Cancion) f.readObject();
			}
		} catch (EOFException e) {
			// 
			// obteneos el id
			if (c != null)
				resultado = c.getId();
			else
				resultado = 0;
		} catch (FileNotFoundException e) {
			
			System.out.println("No hay canciones");
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public boolean crearCancion(Cancion c) {
		
		boolean resultado = false;

		ObjectOutputStream f = null;
		try {
			File fichero = new File(nombreF);
			if (fichero.exists()) {
				// Abro con MiObject.... para que no escriba cabecera
				f = new MiObjectOutputStream(new FileOutputStream(nombreF, true));
			} else {
				// Abro con Object.... para que sí escriba cabecera
				f = new ObjectOutputStream(new FileOutputStream(nombreF, true));
			}

			f.writeObject(c);
			resultado = true;
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public Cancion obtenerCancion(int id) {
		
		Cancion resultado = null;

		ObjectInputStream f = null;

		try {
			f = new ObjectInputStream(new FileInputStream(nombreF));
			while (true) {
				Cancion c = (Cancion) f.readObject();
				if (c.getId() == id) {
					return c;
				}
			}
		}

		catch (EOFException e) {
			// 
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public boolean modificarBorrarCancion(Cancion c, boolean borrar) {
		
		boolean resultado = false;

		ObjectInputStream fO = null;
		ObjectOutputStream fTmp = null;

		try {
			fO = new ObjectInputStream(new FileInputStream(nombreF));
			fTmp = new ObjectOutputStream(new FileOutputStream(nombreTmp, false));
			while (true) {
				Cancion cF = (Cancion) fO.readObject();
				// Compruebo si es la que hay que modificar
				if (cF.getId() == c.getId()) {
					if (!borrar) {
						fTmp.writeObject(c);
					}
				} else {
					fTmp.writeObject(cF);
				}
			}
		}

		catch (EOFException e) {
			// 
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} finally {
			if (fO != null) {
				try {
					fO.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			if (fTmp != null) {
				try {
					fTmp.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		File fOriginal = new File(nombreF);
		if (fOriginal.delete()) {
			File fTemp = new File(nombreTmp);
			if (fTemp.renameTo(fOriginal)) {
				resultado = true;
			} else {
				System.out.println("Error al renombrar " + nombreTmp);
			}
		} else {
			System.out.println("Error al borrar " + nombreF);
		}
		return resultado;
	}

	public ArrayList<Cancion> obtenerCanciones(Album al) {
		
		ArrayList<Cancion> resultado = new ArrayList<Cancion>();

		ObjectInputStream f = null;

		try {
			f = new ObjectInputStream(new FileInputStream(nombreF));
			while (true) {
				Cancion c = (Cancion) f.readObject();

				if (c.getAlbum().getId() == al.getId()) {
					resultado.add(c);
				}
			}

		} catch (EOFException e) {
			// FIN
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

}