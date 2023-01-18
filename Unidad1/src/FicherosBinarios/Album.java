package FicherosBinarios;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import FicherosTexto.Artista;

public class Album implements Serializable{
	//Tamaño de un registro= 213B
	//4B(id)+100B(titulo)+8B(fecha)+100B(artista)+1B(activo)
	private int id;
	private String titulo;
	private Date fechaP;
	private Artista artista;
	private boolean activo=true;
	
	public Album() {
		
	}

	public Album(int id, String titulo, Date fechaP, Artista artista, boolean activo) {
		
		this.id = id;
		this.titulo = titulo;
		this.fechaP = fechaP;
		this.artista = artista;
		this.activo = activo;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Álbum Id:"+id+
				"\tTítilo:"+titulo+
				"\tF.Publicación:"+ formato.format(fechaP)+
				"\tArtista:"+ artista.getNombre() +
				"\tActivo:"+ activo);
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

	public Date getFechaP() {
		return fechaP;
	}

	public void setFechaP(Date fechaP) {
		this.fechaP = fechaP;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
}
