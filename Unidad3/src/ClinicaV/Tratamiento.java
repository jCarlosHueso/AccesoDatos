package ClinicaV;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tratamiento {
	private int codigo;
	private Date fecha;
	private String descripcion;
	
	public Tratamiento() {
	}
	
	public Tratamiento(int codigo,  Date fecha, String descripcion) {
		this.codigo = codigo;
	
		this.fecha = fecha;
		this.descripcion = descripcion;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
		System.out.println("Codigo:"+codigo + 
			
				"\tFecha:" + formato.format(fecha) +
				"\tDescripción:"+descripcion);
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
