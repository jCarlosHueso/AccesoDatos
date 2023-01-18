package FicherosTexto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ADTexto {

	String nombreF = "artistas.txt";

	public ADTexto() {

	}

	public boolean crearArtista(Artista a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		// Declaramos el fichero para abrirlo en modo escritura
		BufferedWriter f = null;

		try {
			// Abrimos fichero
			// Pasamos el nombre del fichero y el parámetro append con valor true
			// El parámetro append indica si añadimos la línea al fichero
			// manteniendo los datos anteriores o si creamos de nuevo el fichero
			f = new BufferedWriter(new FileWriter(nombreF, true));
			// Escribimos el artista
			f.write(a.getNombre() + ";" + a.getGenero() + ";" + a.getFechaUA() + ";" + a.getLanzamiento() + ";"
					+ a.isSeguir() + "\n");
			resultado = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public ArrayList<Artista> obtenerArtistas() {
		// TODO Auto-generated method stub
		ArrayList<Artista> resultado = new ArrayList<>();

		BufferedReader f = null;

		try {
			f = new BufferedReader(new FileReader(nombreF));

			String linea;
			while ((linea = f.readLine()) != null) {
				String[] campos = linea.split(";");
				// Creamos objeto artista a partir de los datos del fichero
				Artista a = new Artista(campos[0], campos[1], Long.parseLong(campos[2]), Integer.parseInt(campos[3]),
						Boolean.parseBoolean(campos[4]));
				// Añadir artista al resultado
				resultado.add(a);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay artistas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public Artista obtenerArtista(String nombreBuscado) {
		// TODO Auto-generated method stub
		Artista resultado = null;
		BufferedReader f = null;
		try {
			f = new BufferedReader(new FileReader(nombreF));
			String linea = "";
			while ((linea = f.readLine()) != null) {
				String[] campos = linea.split(";");
				// Comprobamos si el valor leído es el buscado
				if (campos[0].equalsIgnoreCase(nombreBuscado)) {
					// Creo el objeto artista y finalizo
					resultado = new Artista(campos[0], campos[1], Long.parseLong(campos[2]),
							Integer.parseInt(campos[3]), Boolean.parseBoolean(campos[4]));
					return resultado;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	public boolean modificarSeguir(Artista a, boolean b) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		// Declarar los ficheros
		BufferedReader fO = null;
		BufferedWriter fTmp = null;

		try {
			fO = new BufferedReader(new FileReader(nombreF));
			// Si el fichero existe, hay que sobreescribirlo
			fTmp = new BufferedWriter(new FileWriter("artistas.tmp", false));
			String linea;
			while ((linea = fO.readLine()) != null) {
				String[] campos = linea.split(";");
				// Comprobar si el artista leído es el que hay que modificar
				if (campos[0].equalsIgnoreCase(a.getNombre())) {
					campos[4] = "false";
					fTmp.write(String.join(";", campos) + "\n");

				} else {
					// Escribimos la línea tal y como se ha leído
					fTmp.write(linea + "\n");
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No existen artistas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Cerrar los dos ficheros
			if (fO != null) {
				try {
					fO.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fTmp != null) {
				try {
					fTmp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// Borramos el fichero original
		File fOriginal = new File(nombreF);
		File fTemporal = new File("artistas.tmp");

		if (fOriginal.delete()) {
			// REnombramos el fichero temporal
			if (fTemporal.renameTo(fOriginal)) {
				resultado = true;
			} else {
				System.out.println("Error al renombrar el fichero temporal");
			}
		} else {
			System.out.println("Error al borrar el fichero original");
		}

		return resultado;
	}

	public boolean borrarArtista(Artista a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		BufferedReader fO = null;
		BufferedWriter fTmp = null;
		try {
			fO = new BufferedReader(new FileReader(nombreF));
			fTmp = new BufferedWriter(new FileWriter("artistas.tmp", false));

			String linea;
			while ((linea = fO.readLine()) != null) {
				String[] campos = linea.split(";");

				if (!campos[0].equalsIgnoreCase(a.getNombre())) {
					// Si el artista no es el que hay que borrar
					// lo pasamos al temporal tal y como se ha leído
					fTmp.write(linea + "\n");
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Aún no hay artistas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fO != null) {
				try {
					fO.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fTmp != null) {
				try {
					fTmp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// Borrar fichero original y renombra el temporal
		File fOriginal = new File(nombreF);
		if (fOriginal.delete()) {
			File fTemporal = new File("artistas.tmp");
			if (fTemporal.renameTo(fOriginal)) {
				resultado = true;
			} else {
				System.out.println("Error, al renombrar el fichero temporal");
			}
		} else {
			System.out.println("Error, al borrar el fichero original");
		}

		return resultado;
	}

	public ArrayList<Artista> obtenerArtistasSeguidos() {
		// TODO Auto-generated method stub
		ArrayList<Artista> resultado = new ArrayList<>();

		BufferedReader f = null;

		try {
			f = new BufferedReader(new FileReader(nombreF));
			String linea;
			while ((linea = f.readLine()) != null) {
				String[] campos = linea.split(";");
				// Compruebo que sigo al artista
				if (Boolean.parseBoolean(campos[4])) {
					// Creamos objeto artista
					Artista a = new Artista(campos[0], campos[1], Long.parseLong(campos[2]),
							Integer.parseInt(campos[3]), Boolean.parseBoolean(campos[4]));
					// Añado objeto a a resultado
					resultado.add(a);

				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay artistas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return resultado;
	}

}
