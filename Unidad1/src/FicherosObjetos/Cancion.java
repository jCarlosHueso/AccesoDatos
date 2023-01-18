package FicherosObjetos;

import java.io.Serializable;

import FicherosBinarios.Album;

public class Cancion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1501307634331149304L;
	
	private int id;
	private String titulo;
	private Album album;
	
	
	public void mostrar() {
		System.out.println("Canción:" + id +
				"\tTítulo:" + titulo + 
				"\tÁlbum:" + album.getTitulo() +
				"\tArtista:" + album.getArtista().getNombre());
	}
	
	public Cancion() {
		
	}


	public Cancion(int id, String titulo, Album album) {		
		this.id = id;
		this.titulo = titulo;
		this.album = album;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Album getAlbum() {
		return album;
	}


	public void setAlbum(Album album) {
		this.album = album;
	}
	
	
	
}
