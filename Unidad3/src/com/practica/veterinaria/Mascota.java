package com.practica.veterinaria;

import java.util.ArrayList;

public class Mascota
{
	private int codigo, cliente;
	private String nombre, tipo;
	private ArrayList<Tratamiento> tratamientos = new ArrayList<Tratamiento>();
	
	public Mascota() {}

	public Mascota(int codigo, int cliente, String nombre, String tipo, ArrayList<Tratamiento> tratamientos)
	{
		this.codigo = codigo;
		this.cliente = cliente;
		this.nombre = nombre;
		this.tipo = tipo;
		this.tratamientos = tratamientos;
	}

	@Override
	public String toString()
	{
		return "Mascota [codigo=" + codigo + 
				", cliente=" + cliente + 
				", nombre=" + nombre + 
				", tipo=" + tipo + 
				", tratamientos=" + tratamientos + "]";
	}
	
	
}
