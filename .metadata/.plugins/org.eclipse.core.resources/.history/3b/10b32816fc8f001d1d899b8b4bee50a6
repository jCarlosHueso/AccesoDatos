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
				}
			} while (opcion != 0);
			//Cerrar conexión
			sf.cerrar();
		} else {
			System.out.println("Error, no hay conexión con SpotiFly");
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
