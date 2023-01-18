package FicherosAL;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

import FicherosBinarios.Album;
import FicherosTexto.ADTexto;
import FicherosTexto.Artista;

public class ADAleatorio {
	String nombreF = "albumes.bin";
	String nombreTmp = "albumes.tmp";

	ADTexto fArtistas = new ADTexto();

	public ADAleatorio() {
		super();
	}

	public ArrayList<Album> obtenerAlbumes() {
		// TODO Auto-generated method stub
		ArrayList<Album> resultado = new ArrayList<>();

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreF, "r");
			// Bucle infinito => Parará cuando se produzca la
			// excepción de fin de fichero EOF_EXCEPTION
			while (true) {
				Album a = new Album();
				// Leemos dato a dato
				// Id
				a.setId(f.readInt());

				// Título - 50 caracteres
				String titulo = "";
				for (int i = 0; i < 50; i++) {
					titulo += f.readChar();
				}
				// Limpiamos el título para quitar espacions en blanco
				a.setTitulo(titulo.trim());

				// FEcha Publicación
				a.setFechaP(new Date(f.readLong()));

				// Artista
				String nombre = "";
				for (int i = 0; i < 50; i++) {
					nombre += f.readChar();
				}
				Artista artista = fArtistas.obtenerArtista(nombre.trim());
				a.setArtista(artista);

				// Activo
				a.setActivo(f.readBoolean());

				// Añadir el álbum al resultado
				resultado.add(a);

			}

		}

		catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay álbumes");
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

	public Album obtenerAlbum(String titulo, String nombreA) {
		// TODO Auto-generated method stub
		Album resultado = null;

		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile(nombreF, "r");
			while (true) {
				// Saltamos el id, no es necesario leerlo
				// Para ello posicionamos el apuntador
				// sumando 4B a la posición actual
				f.seek(f.getFilePointer() + 4);

				// Leemos el título
				String tit = "";
				for (int i = 0; i < 50; i++) {
					tit += f.readChar();
				}

				// Saltamos la fecha, no es necesario leerla
				// Para ello posicionamos el apuntador
				// sumando 8B a la posición actual
				f.seek(f.getFilePointer() + 8);

				// Leemos el nombre del artista
				String nombre = "";
				for (int i = 0; i < 50; i++) {
					nombre += f.readChar();
				}
				// Compruebo si coincide con el buscado
				if (tit.trim().equalsIgnoreCase(titulo) && nombre.trim().equalsIgnoreCase(nombreA)) {
					resultado = new Album();
					resultado.setTitulo(titulo);
					resultado.setArtista(fArtistas.obtenerArtista(nombreA));
					// Leemos activo ¡¡ NO HAY COLOCAR EL APUNTADOR, ESTÁ AHÍ!!!
					resultado.setActivo(f.readBoolean());
					// Leemos el id
					f.seek(f.getFilePointer() - 213);
					resultado.setId(f.readInt());
					// Leemos la fecha
					f.seek(f.getFilePointer() + 100);
					resultado.setFechaP(new Date(f.readLong()));
					return resultado;
				} else {
					// Saltamos el campo activo y lo dejamos
					// posicionado al principio del siguiente registro
					f.seek(f.getFilePointer() + 1);
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
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

	public int obtenerUltimoId() {
		// TODO Auto-generated method stub
		int resultado = 0;

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreF, "r");
			// Nos posicionamos al final del fichero
			f.seek(f.length() - 213);
			resultado = f.readInt();
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay canciones");
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

	public boolean crearAlbum(Album al) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile(nombreF, "rw");

			// ¡¡COLOCAR EL APUNTADOR AL FINAL DEL FICHERO!!
			
			f.seek(f.length());
			// Escribo el álbum
			f.writeInt(al.getId());

			// Hacemos que el título tenga 50 caracteres
			StringBuffer texto50 = new StringBuffer(al.getTitulo());
			texto50.setLength(50);
			f.writeChars(texto50.toString());

			f.writeLong(al.getFechaP().getTime());

			texto50 = new StringBuffer(al.getArtista().getNombre());
			texto50.setLength(50);
			f.writeChars(texto50.toString());

			f.writeBoolean(al.isActivo());
			resultado = true;

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

	public Album obtenerAlbum(int id) {
		// TODO Auto-generated method stub
		Album resultado = null;

		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile(nombreF, "r");
			while (true) {
				int idFichero = f.readInt();
				if (idFichero == id) {
					resultado = new Album();
					resultado.setId(idFichero);
					resultado.setTitulo("");
					for (int i = 0; i < 50; i++) {
						resultado.setTitulo(resultado.getTitulo() + f.readChar());
					}
					resultado.setFechaP(new Date(f.readLong()));
					String nombreA = "";
					for (int i = 0; i < 50; i++) {
						nombreA += f.readChar();
					}
					resultado.setArtista(fArtistas.obtenerArtista(nombreA.trim()));
					resultado.setActivo(f.readBoolean());
					return resultado;

				} else {
					// SAltamos al siguiente id
					f.seek(f.getFilePointer() + 209);
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
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

	public boolean modificarArtista(Album al, Artista ar) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile(nombreF, "rw");

			while (true) {
				int idFichero = f.readInt();
				if (idFichero == al.getId()) {
					// Nos posicionamos después de la fecha
					f.seek(f.getFilePointer() + 108);
					StringBuffer texto = new StringBuffer(ar.getNombre());
					texto.setLength(50);
					f.writeChars(texto.toString());
					return true;
				} else {
					// SAltamos al siguiente id
					f.seek(f.getFilePointer() + 209);
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
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

	public boolean borrarAlbum(Album al) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		RandomAccessFile fO = null;
		RandomAccessFile fTmp = null;

		try {
			fO = new RandomAccessFile(nombreF, "r");
			fTmp = new RandomAccessFile(nombreTmp, "rw");

			while (true) {
				// Leemos id
				int id = fO.readInt();

				if (id == al.getId()) {
					// Saltamos al siguiente registro
					fO.seek(fO.getFilePointer() + 209);
				} else {
					byte[] buffer = new byte[209];
					fO.read(buffer, 0, 209);
					// Escribir datos
					fTmp.writeInt(id);
					fTmp.write(buffer);
				}

			}
		}

		catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		File FOriginal = new File(nombreF);
		if (FOriginal.delete()) {
			File fTempo = new File(nombreTmp);
			if (fTempo.renameTo(FOriginal)) {
				resultado = true;
			} else {
				System.out.println("Error al renombrar el fichero temporal");
			}
		} else {
			System.out.println("Error al borrar el fichero original");
		}

		return resultado;
	}

	public ArrayList<Album> obtenerAlbumes(Artista a) {
		// TODO Auto-generated method stub

		ArrayList<Album> resultado = new ArrayList<>();

		RandomAccessFile f = null;

		try {
			f = new RandomAccessFile(nombreF, "r");
			while (true) {
				// Vamos al artista
				f.seek(f.getFilePointer() + 112);
				String texto = "";
				for (int i = 0; i < 50; i++) {
					texto += f.readChar();
				}
				if (a.getNombre().equalsIgnoreCase(texto.trim())) {
					f.seek(f.getFilePointer() - 212);
					Album al = new Album();
					al.setId(f.readInt());
					String texto2 = "";
					for (int i = 0; i < 50; i++) {
						texto2 += f.readChar();
					}
					al.setTitulo(texto2.trim());
					al.setFechaP(new Date(f.readLong()));
					al.setArtista(fArtistas.obtenerArtista(texto.trim()));
					f.seek(f.getFilePointer() + 100);
					al.setActivo(f.readBoolean());
					resultado.add(al);

				} else {
					f.seek(f.getFilePointer() + 1);
				}
			}

		} catch (EOFException e) {
			// TODO: handle exception
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

	public boolean desactivarAlbum(Album al) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile(nombreF, "rw");

			while (true) {
				int idFichero = f.readInt();
				if (idFichero == al.getId()) {
					// Nos posicionamos después del artista
					f.seek(f.getFilePointer() + 208);
					f.writeBoolean(false);
					return true;
				} else {
					// SAltamos al siguiente id
					f.seek(f.getFilePointer() + 209);
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
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

}
