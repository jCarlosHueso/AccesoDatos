package FicherosObjetos;

import java.util.ArrayList;
import java.util.Scanner;

import FicherosBinarios.ADBinario;
import FicherosBinarios.Album;

public class PrincipalCancion {
	static Scanner t = new Scanner(System.in);
	static ADObjetos fCanciones = new ADObjetos();
	static ADBinario fAlbumes = new ADBinario();

	public static void main(String[] args) {

		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-Mostrar Canciones");
			System.out.println("2-Crear Canción");
			System.out.println("3-Modificar Álbum");
			System.out.println("4-Borrar Canción");
			System.out.println("5-Mostrar canciones de un álbum");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				mostrarCanciones();
				break;
			case 2:
				crearCancion();
				break;
			case 3:
				modificarBorrarAlbum(false);
				break;
			case 4:
				modificarBorrarAlbum(true);
				break;
			case 5:
				mostrarCancionAlbum();
				break;
			}

		} while (opcion != 0);

	}

	private static void mostrarCancionAlbum() {

		ArrayList<Album> albumes = fAlbumes.obtenerAlbumes();
		for (Album a : albumes) {
			a.mostrar();
		}
		System.out.println("Introduce Id album");
		int id = t.nextInt();
		t.nextLine();
		Album al = fAlbumes.obtenerAlbum(id);
		if (al != null) {
			ArrayList<Cancion> canciones = fCanciones.obtenerCanciones(al);
			for (Cancion c : canciones) {
				c.mostrar();
			}
		} else {
			System.out.println("Error, no existe el álbum");
		}
	}

	private static void modificarBorrarAlbum(boolean borrar) {
		mostrarCanciones();
		System.out.println("Introduce el id de la canción");
		int id = t.nextInt();
		t.nextLine();
		Cancion c = fCanciones.obtenerCancion(id);
		if (c != null) {
			if (!borrar) {
				ArrayList<Album> albumes = fAlbumes.obtenerAlbumes();
				for (Album a : albumes) {
					a.mostrar();
				}

				System.out.println("Introduce el id del nuevo álbum");
				int idA = t.nextInt();
				t.nextLine();
				Album al = fAlbumes.obtenerAlbum(idA);
				if (al != null) {

					c.setAlbum(al);
					if (fCanciones.modificarBorrarCancion(c, false)) {
						System.out.println("Canción modificada");
					} else {
						System.out.println("Error al modificar la canción");
					}
				} else {
					System.out.println("Error, álbum no existe");
				}
			} else {
				if (fCanciones.modificarBorrarCancion(c, true)) {
					System.out.println("Canción borrada");
				} else {
					System.out.println("Error al borrar la canción");
				}
			}
		} else {
			System.out.println("Error, no existe canción");
		}
	}

	private static void crearCancion() {
		ArrayList<Album> albumes = fAlbumes.obtenerAlbumes();
		for (Album a : albumes) {
			a.mostrar();
		}
		System.out.println("Introduce Álbum:");
		int id = t.nextInt();
		t.nextLine();
		Album al = fAlbumes.obtenerAlbum(id);
		if (al != null) {
			System.out.println("Introduce título");
			String titulo = t.nextLine();
			// Comprobar que no hay otra canción con el mismo título y album
			if (fCanciones.obtenerCancion(titulo, al) == null) {
				Cancion c = new Cancion(fCanciones.obtenerUltimoID() + 1, titulo, al);
				if (fCanciones.crearCancion(c)) {
					System.out.println("Canción creada");
				} else {
					System.out.println("Error, al crear canción");
				}
			} else {
				System.out.println("Error, ya existe");
			}
		} else {
			System.out.println("Error, no existe el álbum");
		}
	}

	private static void mostrarCanciones() {

		ArrayList<Cancion> canciones = fCanciones.obtenerCanciones();
		for (Cancion c : canciones) {
			c.mostrar();
		}
	}

}
