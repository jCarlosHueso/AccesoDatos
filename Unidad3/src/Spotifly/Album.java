package Spotifly;

import java.util.ArrayList;

public class Album {
	private String artista;
	private String titulo;
	private int anio;
	private ArrayList<Cancion> canciones  = new ArrayList<>();
	
	
	public void mostrar(boolean mostrarCanciones) {
		System.out.println("Álbum Artista:"+artista+
				"\tTítilo:"+titulo+				
				"\tAño:"+ anio);
		if(mostrarCanciones) {
			for(Cancion c:canciones) {
				c.mostrar();
			}
		}
	}
	
	public Album() {
	}


	public Album(String artista, String titulo, int anio, ArrayList<Cancion> canciones) {
		this.artista = artista;
		this.titulo = titulo;
		this.anio = anio;
		this.canciones = canciones;
	}


	public String getArtista() {
		return artista;
	}


	public void setArtista(String artista) {
		this.artista = artista;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getAnio() {
		return anio;
	}


	public void setAnio(int anio) {
		this.anio = anio;
	}


	public ArrayList<Cancion> getCanciones() {
		return canciones;
	}


	public void setCanciones(ArrayList<Cancion> canciones) {
		this.canciones = canciones;
	}
	
	
	
}
