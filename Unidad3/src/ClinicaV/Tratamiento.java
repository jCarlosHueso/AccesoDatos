package ClinicaV;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tratamiento {
	private int codigo, mascota;
	private Date fecha;
	private String descripcion;
	
	public Tratamiento() {
	}
	
	public Tratamiento(int codigo, int mascota, Date fecha, String descripcion) {
		this.codigo = codigo;
		this.mascota = mascota;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
		System.out.println("Codigo:"+codigo + 
				"\tMascota:"+ mascota +
				"\tFecha:" + fecha +
				"\tDescripción:"+descripcion);
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getMascota() {
		return mascota;
	}

	public void setMascota(int mascota) {
		this.mascota = mascota;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
