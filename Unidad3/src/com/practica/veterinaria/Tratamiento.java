package com.practica.veterinaria;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tratamiento
{
	private int codigo, mascota;
	private Date fecha;
	private String descripcion;
	
	public Tratamiento() {}
	
	public Tratamiento(int codigo, int mascota, Date fecha, String descripcion)
	{
		this.codigo = codigo;
		this.mascota = mascota;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() { 
		return "Tratamiento [codigo=" + codigo +
			", mascota=" + mascota +
			", fecha=" + new SimpleDateFormat("dd/MM/yyyy").format(fecha) + 
			", descripcion=" + descripcion + "]"; 
	}
}
