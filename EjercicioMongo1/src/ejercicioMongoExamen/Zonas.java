package ejercicioMongoExamen;

import java.util.ArrayList;

import clinicaV.Tratamiento;

public class Zonas {
	private int codigo;
	private String nombre;
	private double precioBase;
	ArrayList<Entradas> entradas = new ArrayList<>();
	
	public Zonas() {
		
	}
	
	public Zonas(int id, String nombre,double precioB,ArrayList<Entradas>entradas) {
		this.codigo = id;
		this.nombre = nombre;
		this.precioBase = precioB;
		this.entradas = entradas;
	}

	public int getId() {
		return codigo;
	}

	public void setId(int id) {
		this.codigo = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}
	
	public ArrayList<Entradas> getEntradas() {
		return entradas;
	}

	public void setEntradas(ArrayList<Entradas> entradas) {
		this.entradas = entradas;
	}

	public void mostrar(boolean mostrarEnt){
		System.out.println("Código: "+codigo+"\tNombre: "+nombre+
				"\tPrecio Base: "+precioBase);
		if(mostrarEnt) {
			for(Entradas e: entradas) {
				e.mostrar();
			}
		}
	}
}
