package examen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) {

		if (ad.getEm() != null) {
			int opcion;
			do {
				System.out.println("Introduce opci�n:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Partido");
				System.out.println("2-");
				System.out.println("3-");
				System.out.println("4-");
				System.out.println("5-");

				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearPartido();
					break;
				case 2:
					break;
				}

			} while (opcion != 0);
			// Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexi�n con tenis");
		}

	}

	private static void crearPartido() {
//muestro los jugadores
		mostrarJugadores();
		// pido el codigo del jugador 1
		System.out.println("introduce el codigo del jugador 1");
		int cod1 = t.nextInt();
		t.nextLine();
		// compruebo que el jugador 1 existe
		if (ad.existeJugador(cod1)) {
			// si el jugador 1 exixte pido el 2
			System.out.println("introduce el codigo del jugador 2");
			int cod2 = t.nextInt();
			t.nextLine();
			// compruebo que j2 existe y que es distinto a j1
			if ((ad.existeJugador(cod2)) && cod1 != cod2) {
				// si j2 existe y no es j1 se crea el partido
				System.out.println("introduce la fecha del partido");
				String fecha = t.nextLine();
				System.out.println("introduce el numero de sets del partido");
				int sets = t.nextInt();
				t.nextLine();
				if (sets == 3 || sets == 5) {
					// creo un partido
					partido p = new partido();
					// le meto los datos
					try {
						p.setFecha(formato.parse(fecha));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					p.setNumSets(sets);
					// le meto los jugados_partido
					List<jugador_partido> jp = new ArrayList<>();
					jp.add(new jugador_partido(new claveJ_P(p, ad.obtenerJugadores(cod1)), "resultado"));
					jp.add(new jugador_partido(new claveJ_P(p, ad.obtenerJugadores(cod2)), "resultado"));
					p.setJugadorPartido(jp);
					p.setGanador(null);
					if (ad.crearPartido(p)) {
						System.out.println("Partido creado satisfactoriamente");
					}
				} else {
					System.out.println("el numero de sets introducidos no es valido");
				}
			} else {
				System.out.println("el jugador 2 no existe o has introducido el codigo del jugador 1");
			}
		} else {
			System.out.println("El jugador 1 no existe");
		}

	}

	private static void mostrarJugadores() {

		List<jugador> jugadores = ad.obtenerJugadores();
		for (jugador jugador : jugadores) {
			System.out.println(jugador.toString());
		}
	}

}
