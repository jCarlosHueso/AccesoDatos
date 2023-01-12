package FicherosAL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import FicherosBinarios.Album;
import FicherosTexto.ADTexto;
import FicherosTexto.Artista;

public class PrincipalAlbumes {
	static Scanner t = new Scanner(System.in);
	static ADAleatorio fAlbumes = new ADAleatorio();
	static ADTexto fArtistas = new ADTexto();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-Mostrar Álbumes");
			System.out.println("2-Crear Álbum");
			System.out.println("3-Modificar Artista");
			System.out.println("4-Borrar Álbum");
			System.out.println("5-Mostrar Álbum de artista");
			System.out.println("6-Desactivar Álbum");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				mostrarAlbumes();
				break;
			case 2:
				crearAlbum();
				break;
			
			case 3:
				modificarArtista();
				break;
			case 4:
				borrarAlbum();
				break;
			case 5:
				mostrarAlbumArtista();
				break;
			case 6:
				desactivarAlbum();
				break;
			}	

		} while (opcion != 0);

	}

	private static void desactivarAlbum() {
		// TODO Auto-generated method stub
		mostrarAlbumes();
		System.out.println("Id de álbum a desactivar");
		int id = t.nextInt();t.nextLine();
		Album al = fAlbumes.obtenerAlbum(id);
		if(al!=null) {
			if(al.isActivo()) {
				if(fAlbumes.desactivarAlbum(al)) {
					System.out.println("Álbum desactivado");
				}
				else {
					System.out.println("Error al desactivar el álbum");
				}
			}
			else {
				System.out.println("El álbum no está activo");
			}
		}
		else {
			System.out.println("Error, no existe el álbum");
		}
	}

	private static void mostrarAlbumArtista() {
		// TODO Auto-generated method stub
		// Mostrar Artistas
		ArrayList<Artista> artistas = fArtistas.obtenerArtistas();
		for (Artista a : artistas) {
			a.mostrar();
		}
		System.out.println("Introduce el nombre del artista");
		String nombre = t.nextLine();
		Artista a = fArtistas.obtenerArtista(nombre);
		if(a!=null) {
			ArrayList<Album> albumes = fAlbumes.obtenerAlbumes(a);
			for(Album al:albumes) {
				al.mostrar();
			}
		}
		else {
			System.out.println("Error, no existe el artista");
		}
	}

	private static void borrarAlbum() {
		// TODO Auto-generated method stub
		mostrarAlbumes();
		System.out.println("Id de álbum a borrar");
		int id = t.nextInt();t.nextLine();
		Album al = fAlbumes.obtenerAlbum(id);
		if(al!=null) {
			 if(fAlbumes.borrarAlbum(al)) {
				 System.out.println("Álbum borrado");
			 }
			 else {
				 System.out.println("Error al borrar  el álbum");
			 }
		}
		else {
			System.out.println("Error, no existe el álbum");
		}
	}

	private static void modificarArtista() {
		// TODO Auto-generated method stub
		mostrarAlbumes();
		System.out.println("Id de álbum a moficar");
		int id = t.nextInt();t.nextLine();
		Album al = fAlbumes.obtenerAlbum(id);
		if(al!=null) {
			// Mostrar Artistas
			ArrayList<Artista> artistas = fArtistas.obtenerArtistas();
			for (Artista a : artistas) {
				a.mostrar();
			}
			System.out.println("Introduce el nombre del nuevo artista");
			String nombre = t.nextLine();
			Artista ar = fArtistas.obtenerArtista(nombre);
			if(ar!=null) {
				if(fAlbumes.modificarArtista(al,ar)) {
					System.out.println("Álbum modificado");
				}
				else {
					System.out.println("Error, al modificar el álbum");
				}
			}
			else {
				System.out.println("Error, no existe el artista");
			}
			
		}
		else {
			System.out.println("Error, no existe el álbum");
		}
	}

	private static void crearAlbum() {
		// TODO Auto-generated method stub
		try {
			// Mostrar Artistas
			ArrayList<Artista> artistas = fArtistas.obtenerArtistas();
			for (Artista a : artistas) {
				a.mostrar();
			}

			// Pedir datos que idenfican al álbum
			System.out.println("Introduce el artista:");
			String nombreA = t.nextLine();
			// Comprobar que existe el artista
			Artista a = fArtistas.obtenerArtista(nombreA);
			if (a != null) {
				System.out.println("Título del álbum:");
				String titulo = t.nextLine();
				// Comprobar que no existe otro álbum con el mismo nombre y el
				// mismo artista
				Album al = fAlbumes.obtenerAlbum(titulo, nombreA);
				if (al == null) {
					// Creamos el objeto álbum
					al = new Album();
					al.setTitulo(titulo);
					al.setArtista(a);
					al.setActivo(true);
					al.setId(fAlbumes.obtenerUltimoId() + 1);
					System.out.println("Fecha último álbum (dd/MM/yyyy)");
					al.setFechaP(formato.parse(t.nextLine()));
					// Crear el álbum en el fichero
					if (fAlbumes.crearAlbum(al)) {
						System.out.println("Álbum " + al.getId() + " creado");
					} else {
						System.out.println("Error al crear el álbum");
					}
				} else {
					System.out.println("Error, ya hay un álbum " + "con ese título para ese artista");
				}

			} else {
				System.out.println("Error, no existe el artista");
			}
		} catch (ParseException e) {
			// TODO: handle exception
			System.out.println("Error, fecha incorrecta");
		}
	}

	private static void mostrarAlbumes() {
		// TODO Auto-generated method stub
		ArrayList<Album> albumes = fAlbumes.obtenerAlbumes();
		for (Album a : albumes) {
			a.mostrar();
		}
	}

}
