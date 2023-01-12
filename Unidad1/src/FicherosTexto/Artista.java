package FicherosTexto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Artista implements Serializable{
	private String nombre;
	private String genero;
	private long fechaUA; // FEcha Último Álbum
	private int lanzamiento; //Año Inicio
	private boolean seguir=true;

	public Artista() {
		
	}
	public Artista(String nombre, String genero, long fechaUA, int lanzamiento, boolean seguir) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.fechaUA = fechaUA;
		this.lanzamiento = lanzamiento;
		this.seguir = seguir;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Nombre Artístico:"+nombre+
				"\tGénero:" + genero +
				"\tFecha U.A:" + formato.format(new Date(fechaUA)) +
				"\tAño Lanzamiento:" + lanzamiento +
				"\tSeguiendo:" + seguir);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public long getFechaUA() {
		return fechaUA;
	}
	public void setFechaUA(long fechaUA) {
		this.fechaUA = fechaUA;
	}
	public int getLanzamiento() {
		return lanzamiento;
	}
	public void setLanzamiento(int lanzamiento) {
		this.lanzamiento = lanzamiento;
	}
	public boolean isSeguir() {
		return seguir;
	}
	public void setSeguir(boolean seguir) {
		this.seguir = seguir;
	}
	
	
}
