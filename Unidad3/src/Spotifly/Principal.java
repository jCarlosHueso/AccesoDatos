package Spotifly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos sf = new AccesoDatos();
	// Definimos el formato con el que vamos
	// a pintar/pedir fechas
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (sf.getBd() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Artista");
				System.out.println("2-Mostrar Artistas");
				System.out.println("3-Mostrar Nombres Artistas por genero");
				System.out.println("4-Modificar Nombre Artista");
				System.out.println("5-Dejar de seguir un género");
				System.out.println("6-Borrar artistas no seguidos");
				System.out.println("7-Crear Álbum");
				System.out.println("8-Mostrar Albumes");
				System.out.println("9-Crear Canción");
				System.out.println("10-Borrar Canción");
				System.out.println("11-Valorar Canción");
				System.out.println("12-Ver canciones por valoración");
				System.out.println("13-Info Artista");
				System.out.println("14-Info Albumes");
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
					mostrarArtistasGenero();
					break;
				case 4:
					modificarNombreArtista();
					break;
				case 5:
					dejarSeguirGenero();
					break;
				case 6:
					borrarArtistasNoSeguidos();
					break;
				case 7:
					crearAlbum();
					break;
				case 8:
					mostrarAlbumes();
					break;
				case 9:
					crearCancion();
					break;
				case 10:
					borrarCancion();
					break;
				case 11:
					valorarCancion();
					break;
				case 12:
					mostrarCancionPorValoracion();
					break;
				case 13:
					InfoArtista();
					break;
				case 14:
					InfoAlbumes();
					break;
				}
			} while (opcion != 0);
			//Cerrar conexión
			sf.cerrar();
		} else {
			System.out.println("Error, no hay conexión con SpotiFly");
		}
	}
	private static void InfoAlbumes() {
		// TODO Auto-generated method stub
		sf.infoAlbumes();
	}
	private static void InfoArtista() {
		// TODO Auto-generated method stub
		sf.infoArtistas();
		sf.totalArtistaSeguidos();
	}
	private static void mostrarCancionPorValoracion() {
		// TODO Auto-generated method stub
		System.out.println("Valoración:");
		double val = t.nextDouble();
		ArrayList<Object[]> canciones = sf.obtenerCancionesPorValoracion(val);
		for(Object[] o:canciones) {
			System.out.println("Artista:"+o[0]+
					"\tÁlbum:"+o[1]+
					"\tCanción:"+o[2]+
					"\tValoración:"+o[3]);
		}
	}
	private static void valorarCancion() {
		// TODO Auto-generated method stub
		mostrarArtistas();
		System.out.println("Introduce artista");
		String nombre = t.nextLine();
		Artista  a = sf.obtenerArtista(nombre);
		if(a!=null) {
			mostrarAlbumes(a);
			System.out.println("Introduce álbum");
			String tituloA = t.nextLine();
			Album al = sf.obtenerAlbum(nombre, tituloA);
			if(al!=null) {
				System.out.println("Título de la canción:");
				String tituloC = t.nextLine();				
				Cancion c = sf.obtenerCancion(al, tituloC);
				if(c!=null) {
					System.out.println("Valoración");
					c.setValoracion(t.nextDouble());t.nextLine();
					if(sf.valorarCancion(al,c)) {
						System.out.println("Valoración modificada");
					}
					else {
						System.out.println("Error al modificar canción");
					}
				}
				else {
					System.out.println("Error, canción no existe");
				}
			}
			else {
				System.out.println("Error, album no existe");
			}
		}
		else {
			System.out.println("Error, no existe el artista");
		}
	}
	private static void borrarCancion() {
		// TODO Auto-generated method stub
		mostrarArtistas();
		System.out.println("Introduce artista");
		String nombre = t.nextLine();
		Artista  a = sf.obtenerArtista(nombre);
		if(a!=null) {
			mostrarAlbumes(a);
			System.out.println("Introduce álbum");
			String tituloA = t.nextLine();
			Album al = sf.obtenerAlbum(nombre, tituloA);
			if(al!=null) {
				System.out.println("Título de la canción:");
				String tituloC = t.nextLine();				
				Cancion c = sf.obtenerCancion(al, tituloC);
				if(c!=null) {
					if(sf.borrarCancion(al,c)) {
						System.out.println("Canción borrada");
					}
					else {
						System.out.println("Error al borrar la canción");
					}
				}
				else {
					System.out.println("Error, canción no existe");
				}
			}
			else {
				System.out.println("Error, album no existe");
			}
		}
		else {
			System.out.println("Error, no existe el artista");
		}
	}
	private static void crearCancion() {
		// TODO Auto-generated method stub
		mostrarArtistas();
		System.out.println("Introduce artista");
		String nombre = t.nextLine();
		Artista  a = sf.obtenerArtista(nombre);
		if(a!=null) {
			mostrarAlbumes(a);
			System.out.println("Introduce álbum");
			String tituloA = t.nextLine();
			Album al = sf.obtenerAlbum(nombre, tituloA);
			if(al!=null) {
				System.out.println("Título de la canción:");
				String tituloC = t.nextLine();				
				if(!sf.existeCancion(al, tituloC)) {
					Cancion c =new Cancion();
					c.setTitulo(tituloC);
					c.setValoracion(0);
					if(sf.addCancion(al,c)) {
						System.out.println("Canción añadida");
					}
					else {
						System.out.println("Error al añadir canción");
					}
				}
				else {
					System.out.println("Error, canción ya existe");
				}
			}
			else {
				System.out.println("Error, album no existe");
			}
		}
		else {
			System.out.println("Error, no existe el artista");
		}
	}
	private static void mostrarAlbumes(Artista a) {
		// TODO Auto-generated method stub
		ArrayList<Album> als = sf.obtenerAlbumes(a.getNombre());
		for(Album al:als) {
			al.mostrar(true);
		}
	}
	private static void mostrarAlbumes() {
		// TODO Auto-generated method stub
		ArrayList<Album> als = sf.obtenerAlbumes();
		for(Album al:als) {
			al.mostrar(false);
		}
	}
	private static void crearAlbum() {
		// TODO Auto-generated method stub
		mostrarArtistas();
		System.out.println("Introduce nombre artista");
		String nombre = t.nextLine();
		Artista a = sf.obtenerArtista(nombre);
		if(a!=null) {
			System.out.println("Título del álbum");
			String titulo = t.nextLine();
			Album al = sf.obtenerAlbum(a.getNombre(),titulo);
			if(al==null) {
				al = new Album();
				al.setTitulo(titulo);
				al.setArtista(nombre);
				System.out.println("Año de publicación");
				al.setAnio(t.nextInt());t.nextLine();
				int mas = 0;
				do {
					System.out.println("Título Canción");
					String titC =t.nextLine();
					//Compobar si ya se ha metido la canción en
					//la lista de canciones que estoy creando 
					//en este bucle
					if(!al.getCanciones().stream().filter(
							o->o.getTitulo().equals(titC))
							.findAny()
							.isPresent()) {
						Cancion c = new Cancion(titC, 0);
						al.getCanciones().add(c);
					}
					else {
						System.out.println("Error, ya existe la canción");
					}
					System.out.println("Más canciones (0-No/1-Si)");
					mas = t.nextInt(); t.nextLine();
				}while(mas==1);
				if(sf.crearAlbum(al)) {
					System.out.println("Album creado");
				}
				else {
					System.out.println("Error al crear el álbum");
				}
			}
			else {
				System.out.println("Error, ya existe el álbum");
			}
		}
		else {
			System.out.println("Error, no existe el artista");
		}
	}
	private static void borrarArtistasNoSeguidos() {
		// TODO Auto-generated method stub
		System.out.println("¿Deseas borrar los artistas no seguidos?(0(No)/1(Sí)");
		int confirma = t.nextInt();t.nextLine();
		if(confirma==1) {
			long borrados  = sf.borrarArtistasNoSeguidos();
			if(borrados>=0) {
				System.out.println("Se han borrado "+borrados+"artistas");
			}
			else {
				System.out.println("Error al borrar los artistas");
			}
		}
	}
	private static void dejarSeguirGenero() {
		// TODO Auto-generated method stub
		System.out.println("Introduce el género");
		String genero = t.nextLine();		
		ArrayList<String> artistas = sf.obtenerArtistas(genero);
		if(!artistas.isEmpty()) {
			System.out.println("Dejas de seguir a los siguientes artistas:");
			for(String nombre:artistas) {
				System.out.println(nombre);
			}
			System.out.println("¿Estás seguro (0(No)/1(Sí))?");
			int confirma = t.nextInt();t.nextLine();
			if(confirma==1) {
				if(sf.dejarSeguir(genero)) {
					System.out.println("Artitas modificados");
				}
				else {
					System.out.println("Error al modificar los artistas");
				}
			}
		}
		else {
			System.out.println("No hay artista de género:"+genero);
		}
	}
	private static void modificarNombreArtista() {
		// TODO Auto-generated method stub
		mostrarArtistas();
		System.out.println("Introduce nombre");
		String nombre = t.nextLine();
		Artista a = sf.obtenerArtista(nombre);
		if(a!=null) {
			System.out.println("Nuevo nombre");
			nombre=t.nextLine();
			//Comprobar que no existe artista con el nuevo nombre
			Artista a2 = sf.obtenerArtista(nombre);
			if(a2==null) {				
				if(sf.modicarNombreArtista(a,nombre)) {
					System.out.println("Artista Modificado");
				}
				else {
					System.out.println("Error al modifcar el nombre del artista");
				}
			}
			else {
				System.out.println("Error, ya existe un artista con el nuevo nombre");
			}
		}
		else {
			System.out.println("Error, no existe el artista");
		}
	}
	private static void mostrarArtistasGenero() {
		// TODO Auto-generated method stub
		System.out.println("Introduce género");
		String genero = t.nextLine();
		ArrayList<String> artistas =  sf.obtenerArtistas(genero);
		for(String n:artistas) {
			System.out.println(n);
		}
	}
	private static void mostrarArtistas() {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas =  sf.obtenerArtistas();
		for(Artista a:artistas) {
			a.mostrar();
		}
	}
	private static void crearArtista() {
		// TODO Auto-generated method stub
		
		System.out.println("Nombre Artístico:");		
		String nombre = t.nextLine();
		
		Artista a = sf.obtenerArtista(nombre);
		if(a==null) {			
			a= new Artista();
			a.setNombre(nombre);
			a.setFechaC(new Date());
			a.setSeguir(true);
			//Pedir géneros
			String g;
			do {
				System.out.println("Introduce Género");
				g=t.nextLine();
				if(!a.getGenero().contains(g)) {
					if(!g.equals("0")){
						a.getGenero().add(g);
					}
				}
				else {
					System.out.println("Ya existe el género");
				}
			}while(!g.equals("0"));
			if(sf.crearArtista(a)) {
				System.out.println("Artista creado");
			}
			else {
				System.out.println("Error al crear el artista");
			}
		}
		else {
			System.out.println("Error, ya existe el artista");
		}
	}

}
