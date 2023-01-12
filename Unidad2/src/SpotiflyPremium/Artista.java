package SpotiflyPremium;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Artista{
	private int id;
	private String nombre;
	private String genero;
	private Date fechaL; // FEcha Lanzamiento
	private boolean seguir=true;

	public Artista() {
		
	}

	public Artista(int id, String nombre, String genero, Date fechaL, boolean seguir) {
		this.id = id;
		this.nombre = nombre;
		this.genero = genero;
		this.fechaL = fechaL;
		this.seguir = seguir;
	}

	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Id:" + id +
				"\tNombre Artístico:"+nombre+
				"\tGénero:" + genero +
				"\tFecha Lanzamiento:" + formato.format(fechaL) +
				"\tSeguiendo:" + seguir);
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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



	public Date getFechaL() {
		return fechaL;
	}



	public void setFechaL(Date fechaL) {
		this.fechaL = fechaL;
	}



	public boolean isSeguir() {
		return seguir;
	}



	public void setSeguir(boolean seguir) {
		this.seguir = seguir;
	}

	
}
