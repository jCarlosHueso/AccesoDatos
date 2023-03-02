package instituto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Persona {
	private int id;
	private String nombre;
	private Direccion dir;
	private Date fechaN;
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Id:"+id + 
				"\tNombre:"+nombre+
				"\tDirección:"+dir.getTipoVia()+"-"+dir.getNombre()+"-"+dir.getCp()+
				"\tFecha Nacicimiento:"+formato.format(fechaN));
	}
	public Persona() {
	}
	public Persona(int id, String nombre, Direccion dir, Date fechaN) {
		this.id = id;
		this.nombre = nombre;
		this.dir = dir;
		this.fechaN = fechaN;
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
	public Direccion getDir() {
		return dir;
	}
	public void setDir(Direccion dir) {
		this.dir = dir;
	}
	public Date getFechaN() {
		return fechaN;
	}
	public void setFechaN(Date fechaN) {
		this.fechaN = fechaN;
	}
	
	
}