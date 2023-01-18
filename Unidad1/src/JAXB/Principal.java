package JAXB;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import FicherosAL.ADAleatorio;
import FicherosBinarios.Album;
import FicherosTexto.ADTexto;
import FicherosTexto.Artista;


public class Principal {
	static Scanner t = new Scanner(System.in);
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	static ADTexto fArtistas = new ADTexto();
	static ADAleatorio fAlbumes =  new ADAleatorio();
	static AdJaxb fInfoArtista = new AdJaxb();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-Generar info de artista");
			System.out.println("2-Mostrar info de artista");
			System.out.println("3-Añadir album");
			System.out.println("4-Borrar album");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				generarInfoArtista();
				break;
			case 2:
				MostrarInfoArtista();
				break;
			case 3:
				AddAlbum();
				break;
			case 4:
				borrarAlbum();
				break;
			}
			
		} while (opcion != 0);

	}

	private static void borrarAlbum() {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = fArtistas.obtenerArtistas();
		for(Artista a: artistas) {
			a.mostrar();
		}
		System.out.println("Introduce Nombre:");
		Artista a = fArtistas.obtenerArtista(t.nextLine());
		if(a!=null) {
			ArrayList<AlbumXML> albumes = 
					 fInfoArtista.obtenerAlbumes(a.getNombre());
			for(AlbumXML al:albumes) {
				al.mostrar();
			}
			System.out.println("Introduce el id a borrar");
			int id = t.nextInt(); t.nextLine();
			if(fInfoArtista.borrarAlbum(a.getNombre(),id)) {
				System.out.println("Álbum borrado");
			}
			else {
				System.out.println("Error, puede que no exista "
						+ "información del artista");
			}
			
		}
		else {
			System.out.println("Error, no existe el artista");
		}
	}

	private static void AddAlbum() {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = fArtistas.obtenerArtistas();
		for(Artista a: artistas) {
			a.mostrar();
		}
		System.out.println("Introduce Nombre:");
		Artista a = fArtistas.obtenerArtista(t.nextLine());
		if(a!=null) {
			System.out.println("Título del álbum");
			String titulo = t.nextLine();
			if(fInfoArtista.addAlbum(a.getNombre(),titulo)) {
				fInfoArtista.mostrar(a);
			}
			else {
				System.out.println("Error, puede que no exista "
						+ "información del artista");
			}
			
		}
		else {
			System.out.println("Error, no existe el artista");
		}
	}

	private static void MostrarInfoArtista() {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = fArtistas.obtenerArtistas();
		for(Artista a: artistas) {
			a.mostrar();
		}
		System.out.println("Introduce Nombre:");
		Artista a = fArtistas.obtenerArtista(t.nextLine());
		if(a!=null) {
			fInfoArtista.mostrar(a);
			
		}
		else {
			System.out.println("Error, no existe el artista");
		}
	}

	private static void generarInfoArtista() {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = fArtistas.obtenerArtistas();
		for(Artista a: artistas) {
			a.mostrar();
		}
		System.out.println("Introduce Nombre:");
		Artista a = fArtistas.obtenerArtista(t.nextLine());
		if(a!=null) {
			ArrayList<Album> albumes = fAlbumes.obtenerAlbumes(a);
			if(fInfoArtista.crearXML(a, albumes)) {
				System.out.println("Información generada");
			}
			else {
				System.out.println("Error al generar la información");
			}
			
		}
		else {
			System.out.println("Error, no existe el artista");
		}
	}


}
