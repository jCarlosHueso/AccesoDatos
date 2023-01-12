package Spotifly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Artista{	
	private String nombre;
	private ArrayList<String> genero=new ArrayList();
	private Date fechaC; // FEcha Creación
	private boolean seguir=true;

	public Artista() {
		
	}

	

	public Artista(String nombre, ArrayList<String> genero, Date fechaC, boolean seguir) {
		this.nombre = nombre;
		this.genero = genero;
		this.fechaC = fechaC;
		this.seguir = seguir;
	}



	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Nombre Artístico:"+nombre+
				"\tGénero:" + genero +
				"\tFecha Creación:" + formato.format(fechaC) +
				"\tSeguiendo:" + seguir);
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public ArrayList<String> getGenero() {
		return genero;
	}



	public void setGenero(ArrayList<String> genero) {
		this.genero = genero;
	}



	public Date getFechaC() {
		return fechaC;
	}



	public void setFechaC(Date fechaC) {
		this.fechaC = fechaC;
	}



	public boolean isSeguir() {
		return seguir;
	}



	public void setSeguir(boolean seguir) {
		this.seguir = seguir;
	}



	
	
}
