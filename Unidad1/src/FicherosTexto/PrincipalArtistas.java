package FicherosTexto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class PrincipalArtistas {
	static Scanner t = new Scanner(System.in);
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	static ADTexto fArtistas = new ADTexto();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-Crear Artista");
			System.out.println("2-Mostrar Artistas");
			System.out.println("3-Dejar de seguir Artista (Modificación)");
			System.out.println("4-Borrar Artista");
			System.out.println("5-Mostrar  Artista seguidos");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				crearArtista();
				break;
			case 2:
				mostrarArtistas();
				break;
			case 3:
				dejarSeguir();
				break;
			case 4:
				borrarArtista();
				break;
			case 5:
				mostrarSeguidos();
				break;
			}
		} while (opcion != 0);
	}

	private static void mostrarSeguidos() {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = fArtistas.obtenerArtistasSeguidos();
		for (Artista a : artistas) {
			a.mostrar();
		}

	}

	private static void borrarArtista() {
		// TODO Auto-generated method stub
		mostrarArtistas();
		System.out.println("Introduce el nombre del artista a borrar:");
		String nombre = t.nextLine();

		Artista a = fArtistas.obtenerArtista(nombre);
		if (a != null) {
			if (fArtistas.borrarArtista(a)) {
				System.out.println("Artista borrado");
			} else {
				System.out.println("Error, no se ha borrado el artista");
			}
		} else {
			System.out.println("Error no existe el artista");
		}
	}

	private static void dejarSeguir() {
		// TODO Auto-generated method stub
		mostrarArtistas();
		System.out.println("Introduce el nombre del artista:");
		String nombre = t.nextLine();
		// Comprobar que el artista introducido existe
		Artista a = fArtistas.obtenerArtista(nombre);
		if (a == null) {
			System.out.println("Error, el artista no existe");
		} else {
			// Comprobamos que seguimos al artista
			if (a.isSeguir()) {
				if (fArtistas.modificarSeguir(a, false)) {
					System.out.println("Artista modificado");
				} else {
					System.out.println("Error al modificar el artista");
				}
			} else {
				System.out.println("Error, ya no sigues a ese artista");
			}
		}

	}

	private static void mostrarArtistas() {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = fArtistas.obtenerArtistas();
		for (Artista a : artistas) {
			a.mostrar();
		}
	}

	private static void crearArtista() {
		// TODO Auto-generated method stub
		Artista a = new Artista();
		System.out.println("Nombre Artístico");
		a.setNombre(t.nextLine());
		System.out.println("Género");
		a.setGenero(t.nextLine());
		System.out.println("Año de Lanzamiento");
		a.setLanzamiento(t.nextInt());
		t.nextLine();
		// Pedimos fecha de último álbum correcta
		boolean fechaCorrecta = false;
		do {
			try {
				System.out.println("Fecha Último Álbum (dd/mm/yyyy)");
				a.setFechaUA(formato.parse(t.nextLine()).getTime());
				fechaCorrecta = true;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("Error: Fecha Incorrecta");
			}
		} while (!fechaCorrecta);

		if (fArtistas.crearArtista(a)) {
			System.out.println("Artista creado con éxito");
		} else {
			System.out.println("Error al crear el artista");
		}
	}

}
